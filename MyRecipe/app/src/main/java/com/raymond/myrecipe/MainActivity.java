package com.raymond.myrecipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements RcpAdapter.ListItemClickListener {

    private RcpAdapter mAdapter;
    private RecyclerView mRcpList;
    private ArrayList<Recipe> rcpList;
    private static final String DBname = "MyRecipe.db";
    private static final int DBversion = 1;
    private RcpDBHper dbHper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        this.deleteDatabase(DBname);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDB();

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
//        dbHper.buildData();
        rcpList = dbHper.getRcpSet();
    }

    public void onResume(){
        super.onResume();
        if(dbHper == null){
            dbHper = new RcpDBHper(this, DBname, null, DBversion);
        }
        rcpList = dbHper.getRcpSet();
        mAdapter = new RcpAdapter(rcpList, this);
        mRcpList.setAdapter(mAdapter);
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

        Intent intent = new Intent();
        intent.setClass(MainActivity.this, DetailActivity.class);

        Bundle bundle = new Bundle();
        Recipe temp = rcpList.get(clickedItemIndex);
        bundle.putString("Name", temp.name);
        bundle.putString("Category", temp.category);
        bundle.putInt("Level", temp.level);
        bundle.putString("Intro", temp.intro);
        bundle.putString("Ingredient", temp.ingredient);
        bundle.putString("Fact", temp.fact);
        bundle.putString("Method", temp.method);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        MyComparator c = new MyComparator();

        switch (itemId) {

            case R.id.action_refresh:
                mAdapter = new RcpAdapter(rcpList, this);
                mRcpList.setAdapter(mAdapter);
                Toast.makeText(MainActivity.this, "refresh", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_add:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, AddActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "add", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_set:
                mAdapter = new RcpAdapter(rcpList, this);
                mRcpList.setAdapter(mAdapter);
                Toast.makeText(MainActivity.this, "set", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.sort_name:
                Collections.sort(rcpList, c.tCompare);
                mAdapter = new RcpAdapter(rcpList, this);
                mRcpList.setAdapter(mAdapter);
                return true;

            case R.id.sort_category:
                Collections.sort(rcpList, c.cCompare);
                mAdapter = new RcpAdapter(rcpList, this);
                mRcpList.setAdapter(mAdapter);
                return true;

            case R.id.sort_level:
                Collections.sort(rcpList, c.lCompare);
                mAdapter = new RcpAdapter(rcpList, this);
                mRcpList.setAdapter(mAdapter);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
