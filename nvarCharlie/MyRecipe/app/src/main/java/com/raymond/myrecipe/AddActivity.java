package com.raymond.myrecipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private EditText titleEditText;
    private Spinner categorySpinner;
    private Spinner levelSpinner;
    private EditText introEditText;
    private EditText ingredientEditText;
    private EditText methodEditText;
    private EditText factEditText;
    private Button addButton;
    private static final String DBname = "MyRecipe.db";
    private static final int DBversion = 1;
    private RcpDBHper dbHper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        buildViews();
    }

    private void buildViews(){
        titleEditText = findViewById(R.id.title_et);
        categorySpinner = findViewById(R.id.category_sp);
        levelSpinner = findViewById(R.id.level_sp);
        introEditText = findViewById(R.id.intro_et);
        ingredientEditText = findViewById(R.id.ingredient_et);
        methodEditText = findViewById(R.id.directions_et);
        factEditText = findViewById(R.id.fact_et);
        addButton = findViewById(R.id.add_btn);

        ArrayAdapter<CharSequence> ctgList = ArrayAdapter.createFromResource(this, R.array.ctg_arr, android.R.layout.simple_spinner_item);
        ctgList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(ctgList);
        ArrayAdapter<CharSequence> levelList = ArrayAdapter.createFromResource(this, R.array.level_arr, android.R.layout.simple_spinner_item);
        levelList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(levelList);

        addButton.setOnClickListener(btnOnClickListener);
    }

    private void initDB(){
        if(dbHper == null){
            dbHper = new RcpDBHper(this, DBname, null, DBversion);
        }
    }

    private OnClickListener btnOnClickListener = new OnClickListener(){
        public void  onClick(View v){
            initDB();
            String addTitle = titleEditText.getText().toString();
            String addCtg = (String)categorySpinner.getSelectedItem();
            int addLevel = Integer.parseInt((String)levelSpinner.getSelectedItem());
            String addIntro = introEditText.getText().toString();
            String addIgd = ingredientEditText.getText().toString();
            String addMethod = methodEditText.getText().toString();
            String addFact = factEditText.getText().toString();

            dbHper.insertRcp(addTitle, addCtg, addLevel, addIntro, addIgd, addMethod, addFact);
            dbHper.close();
            Toast.makeText(AddActivity.this, "Success", Toast.LENGTH_SHORT).show();
            finish();
        }
    };
}
