package fsq.core.entity.item.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import fsq.core.entity.item.mapper.UpdateItem;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.xml.transform.Result;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

@Component("fieldUpdater")
public class FieldUpdater {
    /**
     * Use Reflection to dynamically update objects when they are retrieved by PUT request
     * NOTE: Requires strict matching of field names and setter methods. Will also trip up with similar field name
     * naming. May require custom annotations in future to work properly. Or very strict naming of Field and Setter.
     * I.e. if field is String name setter is called setName(String name) if field is statName setter is setStatName()
     * so that we can directly establish setter from field name. Second will probably be the choice with an annotation for cases when
     * this is not possible
     * @param mObj Object to update
     * @param items Diffs
     */
    public void updateFields(Object mObj, Map<String, UpdateItem> items) {
        Class myClass = mObj.getClass();
        List<Method> methods = new ArrayList<>();
        boolean isTopLevelClass = false;
        while (!isTopLevelClass) {
            if (myClass.getSuperclass().getName().equals("java.lang.Object")) {
                isTopLevelClass = true;
            }
            methods.addAll(Arrays.stream(myClass.getDeclaredMethods()).toList());
            myClass = myClass.getSuperclass();
        }
        try {
            for (Method m : methods) {
                String mLower = m.getName().toLowerCase();
                Set<String> fields = items.keySet();
                Optional<String> jsonProperty = findJsonPropertyAnnotationValueUsingMethod(m);
                for (String field : fields) {
                    boolean useJsonPropertyValue = useAndCheckJsonPropertyAnnotation(jsonProperty, field.toLowerCase());
                    if (mLower.contains("set") && ((mLower.contains(field.toLowerCase())) || useJsonPropertyValue)) {
                        Object newValue = items.get(field).getAfter();
                        String fieldVal = useJsonPropertyValue ? jsonProperty.get() : field;
                        if (FieldMappings.requiresCustomMapping(fieldVal)) {
                            newValue = FieldMappings.getTypeMapper(fieldVal).mapValue(newValue);
                        }
                        m.invoke(mObj, newValue);
                    }
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private boolean useAndCheckJsonPropertyAnnotation(Optional<String> result, String fieldName) {
        if (result.isPresent() && result.get().equalsIgnoreCase(fieldName)) {
            return true;
        } else {
            return false;
        }
    }

    private Optional<String> findJsonPropertyAnnotationValueUsingMethod(Method m) {
        //Only check with get Method
        if (m.getName().startsWith("set") || m.getName().startsWith("Set")) {
            String fieldName = m.getName().substring(3);
            Field[] fields = m.getDeclaringClass().getDeclaredFields();
            for (Field f : fields) {
                if (f.getName().equalsIgnoreCase(fieldName.toLowerCase())) {
                    JsonProperty[] annotations = f.getDeclaredAnnotationsByType(JsonProperty.class);
                    if (annotations != null && annotations.length > 0) {
                        return Optional.of(annotations[0].value());
                    }
                }
            }
        }
        return Optional.empty();
    }


}
