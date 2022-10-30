package fsq.core.entity.item.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class UpdateItemMulti {
    private Long id;

    private Map<String, UpdateItem> updateItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, UpdateItem> getUpdateItems() {
        return updateItems;
    }

    public void setUpdateItems(Map<String, UpdateItem> updateItems) {
        this.updateItems = updateItems;
    }
}
