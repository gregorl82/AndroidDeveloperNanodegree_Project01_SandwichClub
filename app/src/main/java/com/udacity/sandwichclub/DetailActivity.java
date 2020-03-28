package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView alsoKnownAsTv;
    private TextView placeOfOriginTv;
    private TextView ingredientsTv;
    private TextView descriptionTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        alsoKnownAsTv = findViewById(R.id.also_known_tv);
        placeOfOriginTv = findViewById(R.id.origin_tv);
        ingredientsTv = findViewById(R.id.ingredients_tv);
        descriptionTv = findViewById(R.id.description_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        // COMPLETED (1) Move assignment of TextViews to OnCreate
        // COMPLETED (3) Display 'not_applicable_message' string if any TextView is empty

        if (sandwich.getAlsoKnownAs().isEmpty()) {
            alsoKnownAsTv.setText(R.string.not_applicable_message);
        } else {
            int akaLength = sandwich.getAlsoKnownAs().size();
            for (int i = 0; i < akaLength; i++) {
                alsoKnownAsTv.append(sandwich.getAlsoKnownAs().get(i) + "\n");
            }
        }

        if (sandwich.getPlaceOfOrigin().isEmpty()) {
            placeOfOriginTv.setText(R.string.not_applicable_message);
        } else {
            placeOfOriginTv.append(sandwich.getPlaceOfOrigin() + "\n");
        }

        if (sandwich.getIngredients().isEmpty()) {
            ingredientsTv.setText(R.string.not_applicable_message);
        } else {
            int ingredientLength = sandwich.getIngredients().size();
            for (int i = 0; i < ingredientLength; i++) {
                ingredientsTv.append(sandwich.getIngredients().get(i) + "\n");
            }
        }

        if (sandwich.getDescription().isEmpty()) {
            descriptionTv.setText(R.string.not_applicable_message);
        } else {
            descriptionTv.append(sandwich.getDescription() + "\n");
        }

    }
}
