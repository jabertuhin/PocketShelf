package com.example.tuhin.pocketshelf;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    //Variables for database

    public static final String DATABASE_NAME = "pocketShelf.db";
    //(_id integer primary key AUTOINCREMENT, book_name text,author text,publisher text)
    public static final String TABLE_NAME_1 = "book";
    public static final String[] column = {"_id","book_name","author","publisher"};





    public static final String TABLE_NAME_2 = "borrowed_book";
    public static final String TABLE_NAME_3 = "bought_book";
    public static final String TABLE_NAME_4 = "want_to_buy";
    public static final String TABLE_NAME_5 = "reading";
    public static final String TABLE_NAME_6 = "read";



    public DataBase(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table "+TABLE_NAME_1 + " (_id integer primary key AUTOINCREMENT, book_name text,author text,publisher text)");
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

        //Dummy book name
        array_list.add("1982");
        array_list.add("Kafka on the shore");

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from book", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(column[1])));
            res.moveToNext();
        }
        return array_list;
    }

}
