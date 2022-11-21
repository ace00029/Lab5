package com.example.lab5;

public class Meal {
    private String strMeal;
    private String strMealThumb;
    private String idMeal;



    @Override
    public String toString() {
        return String.format("(strMeal=%s, strMealThumb=%s, idMeal=%s)", getStrMeal(), getStrMealThumb(), getIdMeal());
    }

    public String getIdMeal() {
        return idMeal;
    }

    public String getStrMeal() {
        return String.format("%s",strMeal);
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

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

}
