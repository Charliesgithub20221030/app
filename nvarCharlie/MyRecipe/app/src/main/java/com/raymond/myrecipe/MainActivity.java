package com.raymond.myrecipe;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements RcpAdapter.ListItemClickListener {

    private RcpAdapter mAdapter;
    private RecyclerView mRcpList;
    private ArrayList<Recipe> rcpList;
    private FloatingActionButton floatBtn;
    private static final String DBname = "MyRecipe.db";
    private static final int DBversion = 1;
    private RcpDBHper dbHper;

    //preference
    Intent intentPref;
    MediaPlayer mp;
    static boolean playmusic = false;

    //timer
    Calendar cal;
    AlarmManager am;
    PendingIntent pi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        this.deleteDatabase(DBname);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDB();

        floatBtn = (FloatingActionButton) findViewById(R.id.quiz_btn);
        floatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        mRcpList = (RecyclerView) findViewById(R.id.my_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRcpList.setLayoutManager(layoutManager);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(mAdapter));
        itemTouchHelper.attachToRecyclerView(mRcpList);

        mRcpList.setHasFixedSize(true);
        mAdapter = new RcpAdapter(rcpList, this);
        mRcpList.setAdapter(mAdapter);


        //charlie's builder
        viewsBuilder();

    }

    private void initDB() {
        if (dbHper == null) {
            dbHper = new RcpDBHper(this, DBname, null, DBversion);
        }
//        dbHper.buildData();
        rcpList = dbHper.getRcpSet();
    }

    public void onResume() {
        super.onResume();
        if (dbHper == null) {
            dbHper = new RcpDBHper(this, DBname, null, DBversion);
        }
        rcpList = dbHper.getRcpSet();
        mAdapter = new RcpAdapter(rcpList, this);
        mRcpList.setAdapter(mAdapter);


        //share preference
        SharedPreferences pref = getSharedPreferences("sharePref", MODE_PRIVATE);
        playmusic = pref.getBoolean("activated", false);
        String s = new String();
        if (playmusic) {
            mp.start();
        } else {
            mp.stop();
            try {
                mp.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //set timer
        setTimer();


    }

    public void onPause() {
        super.onPause();
        if (dbHper != null) {
            dbHper.close();
            dbHper = null;
        }
        rcpList.clear();
    }

    public void onListItemClick(int clickedItemIndex) {
//        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
//        Toast mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
//        mToast.show();

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
//                Toast.makeText(MainActivity.this, "refresh", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_add:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, AddActivity.class);
                startActivity(intent);
//                Toast.makeText(MainActivity.this, "add", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_set:
                mAdapter = new RcpAdapter(rcpList, this);
                mRcpList.setAdapter(mAdapter);
//                Toast.makeText(MainActivity.this, "set", Toast.LENGTH_SHORT).show();
                startActivity(intentPref);
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


    void viewsBuilder() {
        intentPref = new Intent();
        intentPref.setClass(MainActivity.this, preferenceSettings.class);
        SharedPreferences pref = getSharedPreferences("sharePref", MODE_PRIVATE);
        pref.edit().putBoolean("activated", false).apply();
        mp = MediaPlayer.create(MainActivity.this, R.raw.alan);

        SharedPreferences pref_note = getSharedPreferences("Notification", MODE_PRIVATE);
        pref_note.edit().putBoolean("note",false).apply();
    }

    void setTimer() {
        SharedPreferences pref = getSharedPreferences("Notification", MODE_PRIVATE);
        Intent intent = new Intent(MainActivity.this, notifier.class);
        pi = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        if (pref.getBoolean("note", false)) {

            int hour = pref.getInt("hour", 0);
            Toast.makeText(MainActivity.this,
                    "每日提醒時間:" + String.valueOf(hour) + "時, 0 分, 0秒",
                    Toast.LENGTH_SHORT).show();

            cal = Calendar.getInstance();
            cal.set(2018, 1, 1, hour, 0, 0);

            am = (AlarmManager) getSystemService(ALARM_SERVICE);

//            am.cancel(pi);
            am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 1000 * 60 * 60 * 60 * 24, pi);
//            am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 1000 * 60 * 3, pi);
        } else {
            // If the alarm has been set, cancel it.
            if (am != null) {
                am.cancel(pi);
            }
        }
    }

    public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
        private RcpAdapter tAdapter;
        public SwipeToDeleteCallback(RcpAdapter adapter) {
            super(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
            tAdapter = adapter;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            Recipe deletedRecipe = rcpList.get(position);
            String tempToast = deletedRecipe.name + " has been deleted.";
            dbHper.deleteRcp(deletedRecipe.id);
            rcpList = dbHper.getRcpSet();
            mAdapter = new RcpAdapter(rcpList, MainActivity.this);
            mRcpList.setAdapter(mAdapter);

            Toast.makeText(MainActivity.this, tempToast, Toast.LENGTH_SHORT).show();
        }

        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            // used for up and down movements
            return false;
        }
    }
}
