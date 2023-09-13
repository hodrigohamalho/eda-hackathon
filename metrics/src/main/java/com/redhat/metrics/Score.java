package com.redhat.metrics;

public class Score {

    private Integer value;

    public Score() {
        this(0);
    }

    public Score(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setMaxValue(Integer value) {
        this.value = Math.max(this.value, value);
    }

}
