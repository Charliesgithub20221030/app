package com.raymond.myrecipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private RadioGroup clothRadioGroup;
    private Button ansButton;
    private static final String DBname = "MyRecipe.db";
    private static final int DBversion = 1;
    private RcpDBHper dbHper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        clothRadioGroup = (RadioGroup)findViewById(R.id.cloth_rg);
        ansButton = (Button)findViewById(R.id.ans_btn);

        ansButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clothRadioGroup.getCheckedRadioButtonId() == R.id.option_c){
                    Toast.makeText(QuizActivity.this, "Correct Answer\nYou'll get Prof. Cha's private home cuisine.", Toast.LENGTH_SHORT).show();
                    addCuisine();
                    finish();
                }else{
                    Toast.makeText(QuizActivity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void addCuisine(){
        if(dbHper == null){
            dbHper = new RcpDBHper(this, DBname, null, DBversion);
        }
        dbHper.insertRcp("Locust Honeydew", "Chinese Dish", 5, "A nutritious snack.", "1. Locusts\n2. Soy Sauce\n3. Mirin\n4. Sake\n5. Sugar", "1. Put all of the infredients into a pot.\n2. Stew it until drying up.\n3. When the color turns golden, it's done.", "Protein, Vitamin A, VitaminE\n Unestimated Nutrition!");
    }
}
