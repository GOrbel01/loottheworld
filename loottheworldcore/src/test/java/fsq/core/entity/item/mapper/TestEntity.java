package fsq.core.entity.item.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestEntity {
    private String testFieldOne;

    @JsonProperty("tstFA")
    private String testFieldTwo;
    private Long testFieldThree;

    public String getTestFieldOne() {
        return testFieldOne;
    }

    public void setTestFieldOne(String testFieldOne) {
        this.testFieldOne = testFieldOne;
    }

    public String getTestFieldTwo() {
        return testFieldTwo;
    }

    public void setTestFieldTwo(String testFieldTwo) {
        this.testFieldTwo = testFieldTwo;
    }

    public Long getTestFieldThree() {
        return testFieldThree;
    }

    public void setTestFieldThree(Long testFieldThree) {
        this.testFieldThree = testFieldThree;
    }
}
