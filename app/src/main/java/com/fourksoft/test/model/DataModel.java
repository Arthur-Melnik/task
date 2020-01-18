package com.fourksoft.test.model;

import java.util.List;

public class DataModel {
    private List<Integer> values;
    private int color;

    public DataModel(List<Integer> values, int color) {
        this.values = values;
        this.color = color;
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
