package fsq.core.entity.stat;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ItemStat implements Serializable {

    @JsonProperty("statIndex")
    private Integer statIndex;

    @JsonProperty("statId")
    private Integer statId;

    @JsonProperty("statValue")
    private Integer statvalue;

    public Integer getStatIndex() {
        return statIndex;
    }

    public void setStatIndex(Integer statIndex) {
        this.statIndex = statIndex;
    }

    public Integer getStatvalue() {
        return statvalue;
    }

    public void setStatvalue(Integer statvalue) {
        this.statvalue = statvalue;
    }

    public Integer getStatId() {
        return statId;
    }

    public void setStatId(Integer statId) {
        this.statId = statId;
    }
}
