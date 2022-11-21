package com.example.lab5;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.*;


public class DataParser {
    public static Meal[] getMealsFromStream(String inlineJson) {
        Meal[] mealsArray = null;

        try {
            Gson gson = new Gson();

            JsonElement mealsJsonElement = JsonParser.parseString(inlineJson);
            JsonElement meals = mealsJsonElement.getAsJsonObject().get("meals");
            JsonArray mealsJsonArray = meals.getAsJsonArray();

            mealsArray = gson.fromJson(mealsJsonArray, Meal[].class);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return mealsArray;
    }

    public static Recipe getRecipeFromStream(String inlineJson)
    {
        Recipe recipe = null;
        try {

            JsonElement mealsJsonElement = JsonParser.parseString(inlineJson);
            JsonElement meals = mealsJsonElement.getAsJsonObject().get("meals");
            JsonArray mealsJsonArray = meals.getAsJsonArray();

            GsonBuilder gsonBuilder = new GsonBuilder();

            JsonDeserializer<Recipe> deserializer = new JsonDeserializer<Recipe>() {
                @Override
                public Recipe deserialize(JsonElement json, Type typeOfT,
                                          JsonDeserializationContext context)
                                                        throws JsonParseException {

                    JsonObject jsonObject = json.getAsJsonObject();

                    ArrayList<Ingredient> ingredientList = new ArrayList<>();
                    int index =1;
                    String ingRequest = "strIngredient" + index;
                    String measureRequest = "strMeasure" + index;

                    String ingReponse = jsonObject.get(ingRequest).getAsString();
                    String measureReponse = jsonObject.get(measureRequest).getAsString();
                    while (!ingReponse.isEmpty() && ingReponse != null && index<=20)
                    {
                        ingRequest = "strIngredient" + index;
                        measureRequest = "strMeasure" + index;
                        try {
                            ingReponse = jsonObject.get(ingRequest).getAsString();
                            measureReponse = jsonObject.get(measureRequest).getAsString();
                        }catch (Exception e)
                        {
                            ingReponse = "";
                            measureReponse = "";
                            e.printStackTrace();
                        }


                        if (!ingReponse.isEmpty() && ingReponse != null)
                            ingredientList.add(new Ingredient(ingReponse,measureReponse));

                        index++;

                    }

                    return new Recipe(
                        jsonObject.get("strMeal").getAsString(),
                        jsonObject.get("strMealThumb").getAsString(),
                        jsonObject.get("idMeal").getAsString(),
                        jsonObject.get("strArea").getAsString(),
                        jsonObject.get("strInstructions").getAsString(),
                        ingredientList
                    );
                }

            };

            gsonBuilder.registerTypeAdapter(Recipe.class, deserializer);

            Gson customGson = gsonBuilder.create();

            recipe = customGson.fromJson(mealsJsonArray.get(0), Recipe.class);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return recipe;

    }
}