package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {

    EditText edIngredient;
    ListView mealsLV;
    ArrayAdapter<String> searchResultsAdapter;
    ArrayList<String> listMealsItem = new ArrayList<>();
    Meal[] currentMeals;

    ArrayAdapter<Ingredient> ingredientsAdapter;
    ArrayList<Ingredient> listIngredients = new ArrayList<>();

    TextView mealNameTV, mealAreaTV, mealInstructionsTV;
    ImageView mealImageIV;
    ListView mealIngredientsLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mealsLV = findViewById(R.id.mealsLV);
        this.searchResultsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                this.listMealsItem);
        this.mealsLV.setAdapter(searchResultsAdapter);

        this.edIngredient = findViewById(R.id.edIngredient);


        ingredientsAdapter = new ArrayAdapter<Ingredient>(this,
                android.R.layout.simple_list_item_1, listIngredients);


        mealsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int itemClickedPosition, long mylng) {
                String mealSelected =(String) (mealsLV.getItemAtPosition(itemClickedPosition));
                String mealId = getMealIdByName(currentMeals, mealSelected);
                Meal mealObject = getMealById(currentMeals, mealId);




                // inflate the layout of the popup window
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_meal_layout, null);


                mealNameTV = popupView.findViewById(R.id.mealNameTV);
                mealNameTV.setText(mealObject.getStrMeal());
                mealAreaTV = popupView.findViewById(R.id.mealAreaTV);
                mealInstructionsTV = popupView.findViewById(R.id.mealInstructionsTV);
                mealInstructionsTV.setMovementMethod(new ScrollingMovementMethod());
                mealIngredientsLV = popupView.findViewById(R.id.mealIngredientsLV);

                mealIngredientsLV.setAdapter(ingredientsAdapter);

                try {
                    new MealLoader() {
                        @Override
                        protected void onPostExecute(Recipe mealRecipe) {
                            if (mealRecipe != null) {
                                try {
                                    mealAreaTV.setText(mealRecipe.getStrArea());
                                    mealInstructionsTV.setText(mealRecipe.getStrInstructions());
                                    listIngredients.clear();
                                    listIngredients.addAll(mealRecipe.getStrIngredients());
                                    ingredientsAdapter.notifyDataSetChanged();
                                } catch (Exception e) {
                                    throw e;
                                }
                            }else
                            {
                                mealAreaTV.setText(R.string.not_found);
                                mealInstructionsTV.setText(R.string.not_found);
                                listIngredients.clear();
                                ingredientsAdapter.notifyDataSetChanged();
                            }

                        }
                    }.execute(mealId);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }



                //AsyncTask for loading the image
                mealImageIV = popupView.findViewById(R.id.mealImageIV);
                new ImageLoaderTask(){
                    @Override
                    protected void onPostExecute(Bitmap result) {
                        super.onPostExecute(result);
                        mealImageIV.setImageBitmap(result);
                    }
                }.execute(mealObject.getStrMealThumb());


                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(myView, Gravity.CENTER, 0, 0);


                // dismiss the popup window when touched
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }

                });

            }
        });

    }

    public void onBtnDownloadClick(View view) {
        String ingSelected = (String) this.edIngredient.getText().toString();
        if (!ingSelected.isEmpty()) {
            // Check if no view has focus:
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            new DataLoader() {
                @Override
                public void onPostExecute(Meal[] results) {
                    currentMeals = results;
                    listMealsItem.clear();
                    listMealsItem.addAll(getMealsNames(results));
                    searchResultsAdapter.notifyDataSetChanged();
                }
            }.execute(ingSelected);
        }else
            Toast.makeText(this, "Introduce an ingredient!", Toast.LENGTH_SHORT).show();
    }


    public Collection<String> getMealsNames (Meal[] meals)
    {
        ArrayList<String> toRet = new ArrayList<>();
        if (meals != null)
            for (Meal aMeal: meals)
            {
                toRet.add(aMeal.getStrMeal());
            }

        return toRet;
    }

    public String getMealIdByName (Meal[] meals, String mealName)
    {
        if (meals != null)
        {
            for (Meal aMeal: meals)
            {
                if (aMeal.getStrMeal().equals(mealName))
                    return aMeal.getIdMeal();
            }
        }
        return "";
    }

    public Meal getMealById (Meal[] meals, String mealId)
    {
        if (meals != null)
        {
            for (Meal aMeal: meals)
            {
                if (aMeal.getIdMeal().equals(mealId))
                    return aMeal;
            }
        }
        return null;
    }

}