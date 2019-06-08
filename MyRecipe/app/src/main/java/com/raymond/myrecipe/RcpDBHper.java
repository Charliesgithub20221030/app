package com.raymond.myrecipe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class RcpDBHper extends SQLiteOpenHelper {
//private static final String DBname = "MyRecipe.db";
//private static final int DBversion = 1;
    private static final String TBname = "Recipe";
    private static final String crTBsql = "CREATE TABLE " + TBname + "(" +
                                        "_id  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        "_name VARCHAR(50), " +
                                        "_category VARCHAR(50), " +
                                        "_level INTEGER, " +
                                        "_intro VARCHAR(255), " +
                                        "_ingredient VARCHAR(255), " +
                                        "_fact VARCHAR(100), " +
                                        "_method VARCHAR(255));";

    public RcpDBHper(Context context, String DBname, CursorFactory factory, int DBversion){
        super(context, "MyRecipe.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(crTBsql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBname);
        onCreate(db);
    }

    public long insertRcp(String name, String category, int level, String intro, String ingredient, String fact, String method) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues rcp = new ContentValues();
        rcp.put("_name", name);
        rcp.put("_category", category);
        rcp.put("_level", level);
        rcp.put("_intro", intro);
        rcp.put("_ingredient", ingredient);
        rcp.put("_fact", fact);
        rcp.put("_method", method);
        long rowID = db.insert(TBname, null, rcp);
        db.close();
        return rowID;
    }

    public void buildData(){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues[] rcp = new ContentValues[2];
        for(int i=0; i<rcp.length; i++){
            rcp[i] = new ContentValues();
        }

        rcp[0].put("_name", "dish1");
        rcp[0].put("_category", "Chinese Dish");
        rcp[0].put("_level", 5);
        rcp[0].put("_intro", "Yummy");
        rcp[0].put("_ingredient", "1.Vegetable\n2.Meat");
        rcp[0].put("_fact", "1.fact\n2.fact");
        rcp[0].put("_method", "1.First step\n2.Second step");
        rcp[1].put("_name", "dish2");
        rcp[1].put("_category", "America Dish");
        rcp[1].put("_level", 5);
        rcp[1].put("_intro", "Yummy");
        rcp[1].put("_ingredient", "1.Vegetable\n2.Meat");
        rcp[1].put("_fact", "1.fact\n2.fact");
        rcp[1].put("_method", "1.First step\n2.Second step");

        for(ContentValues c: rcp){
            db.insert(TBname, null, c);
        }
        db.close();
    }

    public ArrayList<Recipe> getRcpSet(){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM " + TBname;
        Cursor rcpSet = db.rawQuery(sql, null);
        ArrayList<Recipe> rcpList = new ArrayList();
//        int colCount = rcpSet.getColumnCount();
        while(rcpSet.moveToNext()){
            int id = rcpSet.getInt(0);
            String name = rcpSet.getString(1);
            String category = rcpSet.getString(2);
            int level = rcpSet.getInt(3);
            String intro = rcpSet.getString(4);
            String ingredient = rcpSet.getString(5);
            String fact = rcpSet.getString(6);
            String method = rcpSet.getString(7);
            Recipe r = new Recipe(id, name, category, level, intro, ingredient, fact, method);
            rcpList.add(r);
        }


        rcpSet.close();
        db.close();
        return rcpList;
    }
}
