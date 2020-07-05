package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String JSON_NAME = "name";
    private static final String JSON_MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";


    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject name = jsonObject.getJSONObject(JSON_NAME);
            String mainName = name.getString(JSON_MAIN_NAME);
            JSONArray akaObject = name.getJSONArray(ALSO_KNOWN_AS);
            List<String> alsoKnownAsList = new ArrayList<>();
            for (int i = 0; i < akaObject.length(); i++) {
                alsoKnownAsList.add(akaObject.getString(i));
            }
            String placeOfOrigin = jsonObject.optString(PLACE_OF_ORIGIN, "Unknown");
            String description = jsonObject.getString(DESCRIPTION);
            String image = jsonObject.getString(IMAGE);
            JSONArray ingredientsListObject = jsonObject.getJSONArray(INGREDIENTS);
            List<String> ingredients = new ArrayList<>();
            for (int i = 0; i < ingredientsListObject.length(); i++) {
                ingredients.add(ingredientsListObject.getString(i));
            }
            sandwich = new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            Log.e("JsonUtils", "Error: " + e.getLocalizedMessage());
        }
        return sandwich;
    }
}
