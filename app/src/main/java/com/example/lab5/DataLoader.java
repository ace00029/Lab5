package com.example.lab5;

import android.os.AsyncTask;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

public class DataLoader extends AsyncTask<String, Void, Meal[]> {

    protected Meal[] doInBackground(String... params) {
        try {
            return DataManager.getMealsFromIng(params[0]);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
        }
        return null;
    }
}