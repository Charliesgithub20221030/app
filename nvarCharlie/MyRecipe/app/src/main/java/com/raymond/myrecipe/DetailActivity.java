package com.raymond.myrecipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    public Bundle bundle;
    private ImageView foodImageView;
//    private TextView titleTextView;
    private TextView introTagTextView;
    private TextView introTextView;
    private TextView ingredientTagTextView;
    private TextView ingredientTextView;
    private TextView methodTagTextView;
    private TextView methodTextView;
    private TextView factTagTextView;
    private TextView factTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        buildViews();
    }

    private void buildViews(){
        foodImageView = (ImageView)findViewById(R.id.food_iv);
//        titleTextView = (TextView)findViewById(R.id.tv_name);
        introTagTextView = (TextView)findViewById(R.id.tv_intro_tag);
        introTextView = (TextView)findViewById(R.id.tv_intro);
        ingredientTagTextView = (TextView)findViewById(R.id.tv_ingredient_tag);
        ingredientTextView = (TextView)findViewById(R.id.tv_ingredient);
        methodTagTextView = (TextView)findViewById(R.id.tv_method_tag);
        methodTextView = (TextView)findViewById(R.id.tv_method);
        factTagTextView = (TextView)findViewById(R.id.tv_fact_tag);
        factTextView = (TextView)findViewById(R.id.tv_fact);

        Bundle bundle = this.getIntent().getExtras();

//        titleTextView.setText(bundle.getString("Name"));
        introTextView.setText(bundle.getString("Intro"));
        ingredientTextView.setText(bundle.getString("Ingredient"));
        methodTextView.setText(bundle.getString("Method"));
        factTextView.setText(bundle.getString("Fact"));

        this.setTitle(bundle.getString("Name"));

        switch (bundle.getString("Name")){
            case "Chicken Katsu":
                foodImageView.setImageResource(R.drawable.chickenkatsu);
                break;
            case "Miso Soup":
                foodImageView.setImageResource(R.drawable.misosoup);
                break;
            case "Japanese Tamago Egg":
                foodImageView.setImageResource(R.drawable.japanesetamagoegg);
                break;
            case "Steamed Egg":
                foodImageView.setImageResource(R.drawable.steamedegg);
                break;
            case "Madeleines":
                foodImageView.setImageResource(R.drawable.madeleines);
                break;
            case "French Crepes":
                foodImageView.setImageResource(R.drawable.frenchcrepes);
                break;
            case "Lyonnaise Potatoes":
                foodImageView.setImageResource(R.drawable.lyonnaisepotatoes);
                break;
            case "Korean Tofu Stew":
                foodImageView.setImageResource(R.drawable.koreantofustew);
                break;
            case "Indonesian Spiced Rice":
                foodImageView.setImageResource(R.drawable.indonesianspicedrice);
                break;
            case "Easy Chinese Corn Soup ":
                foodImageView.setImageResource(R.drawable.easychinesecornsoup);
                break;
            case "Chinese Tea Leaf Eggs":
                foodImageView.setImageResource(R.drawable.chinesetealeafeggs);
                break;
            case "Locust Honeydew":
                foodImageView.setImageResource(R.drawable.cuisine);
                break;
            default:
                foodImageView.setImageResource(R.drawable.every);
                break;
        }
    }
}
