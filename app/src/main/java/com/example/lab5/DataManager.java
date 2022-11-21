package com.example.lab5;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Permission;
import java.util.Scanner;

public class DataManager {

    public static Meal[] getMealsFromIng(String mainIngredient) throws IOException {
        Meal[] meals = null;
        String composedUrl = Constants.GETMEALBYINGR + mainIngredient;
        String inlineJson = downloadUrl(composedUrl);

        try {
            meals = DataParser.getMealsFromStream(inlineJson);
        } catch (Exception e)
        { e.printStackTrace();
        }
        return meals;
    }

    public static Recipe getRecipeFromMeal (String mealId) throws IOException {
        String composedUrl = Constants.GETRECIPEBYMEAL + mealId;
        String inlineJson = downloadUrl(composedUrl);
        Recipe recipe = null;

        try {
            recipe = DataParser.getRecipeFromStream(inlineJson);
        } catch (Exception e)
        { e.printStackTrace();
        }

        return recipe;
    }

    private static String downloadUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        String connectionRes = "";
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");


        try {
            Permission connPerm = conn.getPermission();
        }catch (SecurityException e)
        {e.printStackTrace();}
        conn.setDoInput(true);
        try {
            conn.connect();

            Scanner scanner = new Scanner((url.openStream()));
            while (scanner.hasNext())
            {
                connectionRes += scanner.nextLine();
            }
            scanner.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            conn.disconnect();
        }
        return connectionRes;
    }
}