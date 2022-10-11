package fsq.core.entity.item.mapper;

import fsq.core.entity.item.mapper.types.impl.BigDecimalMapper;
import fsq.core.entity.item.mapper.types.TypeMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FieldMappings {

    public static boolean requiresCustomMapping(String fieldName) {
        List<String> requireMapping = new ArrayList<>();
        //To Move out to properties or config later
        requireMapping.add("weapondmgmin");
        requireMapping.add("weapondmgmax");

        return requireMapping.contains(fieldName.toLowerCase());
    }

    public static TypeMapper getTypeMapper(String field) {
        if (field.equalsIgnoreCase("weapondmgmin") || field.equalsIgnoreCase("weapondmgmax")) {
            return new BigDecimalMapper();
        }
        return null;
    }

    public static Object mapValue(String field, Object value) {
        if (field.equalsIgnoreCase("weaponDmgMin") || field.equalsIgnoreCase("weaponDmgMax")) {
            String dmgStr = (String) value;
            return mapWeaponDmgFields(dmgStr);
        }
        return null;
    }


    private static BigDecimal mapWeaponDmgFields(String fieldValue) {
        return new BigDecimal(fieldValue);
    }
}
