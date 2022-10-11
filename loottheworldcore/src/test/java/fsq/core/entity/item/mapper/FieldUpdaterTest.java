package fsq.core.entity.item.mapper;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class FieldUpdaterTest {

    private FieldUpdater fieldUpdater;

    private Map<String, UpdateItem> items;

    @Before
    public void setup() {
        this.items = new HashMap<>();
        String testFOneNew = "Something New";
        String testFOneOld = "Something Old";

        Long testLNew = 6L;
        Long testLOld = 4L;

        UpdateItem u1 = new UpdateItem();
        u1.setBefore(testFOneOld);
        u1.setAfter(testFOneNew);
        UpdateItem u2 = new UpdateItem();
        u2.setBefore(testLOld);
        u2.setAfter(testLNew);

        items.put("testFieldOne", u1);
        items.put("testFieldThree", u2);

        this.fieldUpdater = new FieldUpdater();
    }

    @Test
    public void testFieldUpdater() {

        TestEntity te = new TestEntity();

        fieldUpdater.updateFields(te, items);

        assertEquals("Something New", te.getTestFieldOne());
        assertEquals(6L, te.getTestFieldThree().longValue());
    }

    @Test
    public void testFieldUpdaterJsonCheck() {
        UpdateItem u2 = new UpdateItem();
        u2.setBefore("Before");
        u2.setAfter("After");

        TestEntity te = new TestEntity();

        items.put("tstFA",u2);

        fieldUpdater.updateFields(te, items);

        assertEquals("After", te.getTestFieldTwo());
    }
}
