package com.raymond.myrecipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RcpAdapter.ListItemClickListener {

    private RcpAdapter mAdapter;
    private RecyclerView mRcpList;
    private ArrayList<Recipe> rcpList;
    private static final String DBname = "MyRecipe.db";
    private static final int DBversion = 1;
    private RcpDBHper dbHper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDB();

//        Recipe food1 = new Recipe(1, "ctg1", "name1", "igd1", "mt1");
//        Recipe food2 = new Recipe(2, "ctg2", "name2", "igd2", "mt2");
//        rcpList = new ArrayList();
//        rcpList.add(food1);
//        rcpList.add(food2);

        mRcpList = (RecyclerView) findViewById(R.id.my_recycler_view);
        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
        mRcpList.setLayoutManager(layoutManager);

        mRcpList.setHasFixedSize(true);
        mAdapter = new RcpAdapter(rcpList, this);
        mRcpList.setAdapter(mAdapter);
    }

    private void initDB(){
        if(dbHper == null){
            dbHper = new RcpDBHper(this, DBname, null, DBversion);
        }
        dbHper.buildData();
        rcpList = dbHper.getRcpSet();
    }

    public void onResume(){
        super.onResume();
        if(dbHper == null){
            dbHper = new RcpDBHper(this, DBname, null, DBversion);
        }
        rcpList = dbHper.getRcpSet();
    }

    public void onPause(){
        super.onPause();
        if (dbHper != null) {
            dbHper.close();
            dbHper = null;
        }
        rcpList.clear();
    }

    public void onListItemClick(int clickedItemIndex) {
        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        Toast mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
        mToast.show();

//        Intent intent = new Intent();
//        intent.setClass(MainActivity.this, DetailActivity);
//
//        Bundle bundle = new Bundle();
//        bundle.putString("Name", );
    }
}
