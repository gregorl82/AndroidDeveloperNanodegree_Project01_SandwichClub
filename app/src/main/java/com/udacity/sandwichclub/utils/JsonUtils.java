package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {

        // Declare variables for return object

        String mainName;
        List<String> alsoKnownAs = new ArrayList<>();
        String placeOfOrigin;
        String description;
        String image;
        List<String> ingredients = new ArrayList<>();

        Sandwich sandwich;


        // Create new JSONObject from the passed-in JSON string
        JSONObject sandwichJsonObject = new JSONObject(json);


        // Get strings from first level of sandwichJsonObject
        placeOfOrigin = sandwichJsonObject.getString("placeOfOrigin");
        description = sandwichJsonObject.getString("description");
        image = sandwichJsonObject.getString("image");


        // Create JSONArray for ingredients
        JSONArray ingredientJsonArray = sandwichJsonObject.getJSONArray("ingredients");

        // Loop over ingredientJsonArray, get string and add to ingredients list
        // TODO (4) Modify for loop so that empty values are not added
        for (int i = 0; i < ingredientJsonArray.length(); i++) {
            ingredients.add(ingredientJsonArray.getString(i));
        }

        // Create JSONObject for name
        JSONObject nameJsonObject = sandwichJsonObject.getJSONObject("name");

        // Get string from first level
        mainName = nameJsonObject.getString("mainName");


        // Create JSONArray for alsoKnownAs
        JSONArray alsoKnownAsJsonArray = nameJsonObject.getJSONArray("alsoKnownAs");

        // Loop over alsoKnownAsJsonArray, get string and add to alsoKnownAs list
        // TODO (5) Modify for loop so that empty values are not added
        for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
            alsoKnownAs.add(alsoKnownAsJsonArray.getString(i));
        }

        // Create new sandwich object and return

        sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        return sandwich;
    }
}
