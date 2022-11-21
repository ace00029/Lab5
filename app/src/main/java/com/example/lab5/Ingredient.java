package com.example.lab5;

public class Ingredient {
    private String ingName;
    private String ingMeasure;

    public Ingredient(String ingName, String ingMeasure) {
        this.ingName = ingName;
        this.ingMeasure = ingMeasure;
    }

    public String getIngMeasure() {
        return ingMeasure;
    }

    public void setIngMeasure(String ingMeasure) {
        this.ingMeasure = ingMeasure;
    }

    public void setIngName(String ingName) {
        this.ingName = ingName;
    }

    public String getIngName() {
        return ingName;
    }

    @Override
    public String toString() {
        return " - " + ingName + ": " + ingMeasure;
    }
}
