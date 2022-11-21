package com.example.lab5;

import android.os.AsyncTask;

import java.io.PrintWriter;
import java.io.StringWriter;

public class MealLoader extends AsyncTask<String, Void, Recipe> {

    protected Recipe doInBackground(String... params) {
        try {
            return DataManager.getRecipeFromMeal(params[0]);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
        }
        return null;
    }
}
