package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String SUB_OBJECT = "name";
    public static final String MAIN_NAME = "mainName";
    public static final String ALSO_KNOWN_AS_ARRAY = "alsoKnownAs";
    public static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE_PATH = "image";
    public static final String INGREDIENTS_ARRAY = "ingredients";


    public static Sandwich parseSandwichJson(String json) {


        try {
            // 1
            JSONObject rootObject = new JSONObject(json);

            // 1.1
            JSONObject subObject = rootObject.getJSONObject(SUB_OBJECT);

            // 1.1.1
            // https://stackoverflow.com/questions/13790726/the-difference-between-getstring-and-optstring-in-json/13790789#13790789
            String mainName = subObject.optString(MAIN_NAME);
            JSONArray alsoKnownAsArray = subObject.getJSONArray(ALSO_KNOWN_AS_ARRAY);

            // 1.1
            String placeOfOrigin = rootObject.getString(PLACE_OF_ORIGIN);
            if (placeOfOrigin.isEmpty()) {
                placeOfOrigin = "Unknown";
            }
            String description = rootObject.optString(DESCRIPTION);
            String imagePath = rootObject.optString(IMAGE_PATH);
            JSONArray ingredientsArray = rootObject.getJSONArray(INGREDIENTS_ARRAY);


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
