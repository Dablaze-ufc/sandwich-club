package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject name = jsonObject.getJSONObject("name");
            String mainName = name.getString("mainName");
            JSONArray akaObject = name.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAsList = new ArrayList<>();
            for (int i = 0; i < akaObject.length(); i++) {
                alsoKnownAsList.add(akaObject.getString(i));
            }
            String placeOfOrigin = jsonObject.getString("placeOfOrigin");
            String description = jsonObject.getString("description");
            String image = jsonObject.getString("image");
            JSONArray ingredientsListObject = jsonObject.getJSONArray("ingredients");
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
