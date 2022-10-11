package fsq.core.entity.item.mapper;

import java.io.Serializable;

public class UpdateItem implements Serializable {
    private Object before;
    private Object after;

    public Object getBefore() {
        return before;
    }
    public void setBefore(Object before) {
        this.before = before;
    }

    public Object getAfter() {
        return after;
    }

    public void setAfter(Object after) {
        this.after = after;
    }
}
