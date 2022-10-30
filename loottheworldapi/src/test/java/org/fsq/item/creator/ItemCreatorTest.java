package org.fsq.item.creator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ItemCreatorTest {

    @Before
    public void setup() {

    }

    @Test
    public void test() {
        ObjectMapper om = new ObjectMapper();
        File f = new File("./src/test/java/org/fsq/item/creator/test.json");
        Map<String, Object> container = new HashMap<>();
        List<Object> items = new ArrayList<>();
        Map<String, Object> itemParent = new HashMap<>();

        itemParent.put("tier",1);
        items.add(itemParent);

        List<Object> prefixList = new ArrayList<>();

        prefixList.add("Masterful");
        prefixList.add("Shining");

        itemParent.put("list",prefixList);

        Map<String, Object> itemParentTwo = new HashMap<>();
        List<Object> prefixListTwo = new ArrayList<>();
        itemParentTwo.put("tier",2);
        prefixListTwo.add("Insane");
        prefixListTwo.add("Poggers");
        itemParentTwo.put("items",prefixListTwo);

        items.add(itemParentTwo);
        container.put("items",items);

        try {
            om.writer().writeValue(f,container);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ItemNameData result = om.readValue(f, ItemNameData.class);
            List<Object> res = (List<Object>) result.getItems().get(1).get("items");
            assertEquals("Poggers",res.get(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
