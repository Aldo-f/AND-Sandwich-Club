package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try {
            // 1
            JSONObject rootObject = new JSONObject(json);

            // 1.1
            JSONObject subObject = rootObject.getJSONObject("name");

            // 1.1.1
            String mainName = subObject.getString("mainName");
            JSONArray alsoKnownAsArray = subObject.getJSONArray("alsoKnownAs");

            // 1.1
            String placeOfOrigin = rootObject.getString("placeOfOrigin");
            if (placeOfOrigin.isEmpty()) {
                placeOfOrigin = "Unknown";
            }
            String description = rootObject.getString("description");
            String imagePath = rootObject.getString("image");
            JSONArray ingredientsArray = rootObject.getJSONArray("ingredients");


            //Iterate through the array of alsoKnowAsArray and add it to list
            List<String> alsoKnownAsList = new ArrayList<>();
            if (alsoKnownAsArray.length() == 0) {
                alsoKnownAsList.add("No other name known");
            } else {
                for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                    alsoKnownAsList.add(alsoKnownAsArray.getString(i));
                }
            }

            //Iterate through the array of ingredientsArray and add it to list
            List<String> ingredientsList = new ArrayList<>();
            for (int i = 0; i < ingredientsArray.length(); i++) {
                ingredientsList.add(ingredientsArray.getString(i));
            }

            return new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, imagePath, ingredientsList);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
