package com.redhat.metrics;

public class MelhorProposta {

    private Double value;

    public MelhorProposta() {
        this(0.0);
    }

    public MelhorProposta(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setMaxValue(Double value) {
        this.value = Math.max(this.value, value);
    }

}
