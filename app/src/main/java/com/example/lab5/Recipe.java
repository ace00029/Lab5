package com.example.lab5;

import java.util.ArrayList;

public class Recipe {

    private String strMeal;
    private String strMealThumb;
    private String idMeal;
    private String strArea;
    private String strInstructions;
    private ArrayList<Ingredient> strIngredients;

    public Recipe(String strMeal, String strMealThumb, String idMeal, String strArea,
                  String strInstructions, ArrayList<Ingredient> strIngredients) {
        this.strMeal = strMeal;
        this.strMealThumb = strMealThumb;
        this.idMeal = idMeal;
        this.strArea = strArea;
        this.strInstructions = strInstructions;
        this.strIngredients = strIngredients;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public ArrayList<Ingredient> getStrIngredients() {
        return strIngredients;
    }

    public void setStrIngredients(ArrayList<Ingredient> strIngredients) {
        this.strIngredients = strIngredients;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }
}
