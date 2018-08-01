package com.example.tuhin.pocketshelf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    //Variables for database

    public static final String DATABASE_NAME = "pocketShelf";
    //(_id integer primary key AUTOINCREMENT, book_name text,author text,publisher text)
    public static final String TABLE_NAME_1 = "MyBook";
    public static final String[] bookColumn = {"_id","book_name","author","publisher"};
    public static final String TABLE_NAME_2 = "borrowed_book";
    public static final String[] borrowColumn = {"_id","book_name","from"};
    public static final String TABLE_NAME_3 = "bought_book";
    public static final String TABLE_NAME_4 = "want_to_buy";
    public static final String TABLE_NAME_5 = "reading";
    public static final String TABLE_NAME_6 = "read";



    public DataBase(Context context) {
        super(context, DATABASE_NAME , null, 1);
        //Log.d("pocketShelf",TABLE_NAME_1+" - created.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("CREATE TABLE MyBook (_id INTEGER primary key AUTOINCREMENT,book_name TEXT,author TEXT, publisher TEXT)");
        //db.execSQL("create table "+TABLE_NAME_2 + " (_id integer  key, name text,phone text,email text, street text,place text)");
        //db.execSQL("create table "+TABLE_NAME_3 + " (id integer primary key, name text,phone text,email text, street text,place text)");
        //db.execSQL("create table "+TABLE_NAME_4 + " (id integer primary key, name text,phone text,email text, street text,place text)");
        //db.execSQL("create table "+TABLE_NAME_5 + " (id integer primary key, name text,phone text,email text, street text,place text)");
        //db.execSQL("create table "+TABLE_NAME_6 + " (id integer primary key, name text,phone text,email text, street text,place text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_1);
        onCreate(db);
    }


    public ArrayList<String> getMyBookList(){
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME_1);

        array_list.add("Number Of Books - "+numRows);

        Cursor res =  db.rawQuery( "SELECT * FROM "+TABLE_NAME_1, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            String oneRow = res.getString(res.getColumnIndex(bookColumn[1]));
            oneRow += " by ";
            oneRow += (res.getString(res.getColumnIndex(bookColumn[2])));
            oneRow += " \n published by ";
            oneRow += (res.getString(res.getColumnIndex(bookColumn[3])));
            array_list.add(oneRow);
            res.moveToNext();
        }
        return array_list;
    }

    public void addDummyBook(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("book_name", "1982");
        contentValues.put("author", "GEorge Orwell");
        contentValues.put("publisher", "nai");
        long ret = db.insert("MyBook", null, contentValues);
    }

}
