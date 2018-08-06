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
    public static final int database_version = 4;
    //(_id integer primary key AUTOINCREMENT, book_name text,author text,publisher text)
    //Table 1
    public static final String TABLE_NAME = "MyBook";
    public static final String[] Column = {"_id","book_name","author","publisher","buying_date","shop_name","price","reading_status"};

    //Table 2
    public static final String TABLE_NAME_2 = "want_to_buy";
    public static final String[] Column2 = {"book_name","author"};

    //Table 3
    public static final String TABLE_NAME_3 = "borrowedBook";
    public static final String[] Column3 = {"book_name","borrowed_from","borrowed_date"};

    public static SQLiteDatabase db;




    public DataBase(Context context) {
        super(context, DATABASE_NAME , null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("CREATE TABLE MyBook (_id INTEGER primary key AUTOINCREMENT,book_name TEXT,author TEXT,publisher TEXT,buying_date TEXT,shop_name TEXT,price int,reading_status int)");
        db.execSQL("CREATE TABLE want_to_buy (book_name TEXT,author TEXT,publisher TEXT,price int)");
        db.execSQL("CREATE TABLE borrowedBook (book_name TEXT,borrowed_from TEXT,borrowed_date TEXT)");
        //addToMyShelf(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_2);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_3);
        onCreate(db);
    }

    /*
     My Book List
     */

    public boolean addToMyShelf(String book_name,String author_name,String publisher,String date,String shop_name,int price,int status){
        db = this.getWritableDatabase();
        ContentValues res = new ContentValues();
        res.put(Column[1],book_name);
        res.put(Column[2],author_name);
        res.put(Column[3],publisher);
        res.put(Column[4],date);
        res.put(Column[5],shop_name);
        res.put(Column[6],price);
        res.put(Column[7],status);
        long ret = db.insert(TABLE_NAME,null,res);
        return (ret<0)? false:true;
    }


    public ArrayList<String> getMyBookList(){
        ArrayList<String> array_list = new ArrayList<String>();

        db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);

        array_list.add("Number Of Books - "+numRows);

        Cursor res =  db.rawQuery( "SELECT * FROM "+TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            String oneRow = res.getString(res.getColumnIndex(Column[1]));
            oneRow += " by ";
            oneRow += (res.getString(res.getColumnIndex(Column[2])));
            oneRow += " \n published by ";
            oneRow += (res.getString(res.getColumnIndex(Column[3])));
            array_list.add(oneRow);
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getReadBook(){
        ArrayList<String> array_list = new ArrayList<String>();
        db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT book_name,author FROM "+TABLE_NAME+" where reading_status = 1", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            String oneRow = res.getString(0);
            oneRow += " by ";
            oneRow += res.getString(1);
            array_list.add(oneRow);
            res.moveToNext();
        }
        return array_list;
    }
    /*
    *BOrrow Book POrtion
     */

    public boolean addBorrow(String book,String author,String date){
        db = this.getWritableDatabase();
        ContentValues res = new ContentValues();
        res.put(Column3[0],book);
        res.put(Column3[1],author);
        res.put(Column3[2],date);
        long chk = db.insert(TABLE_NAME_3,null,res);
        return (chk<0)? false:true;
    }

    public ArrayList<String> getBorrow(){
        ArrayList<String> array_list = new ArrayList<String>();
        db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT *FROM "+TABLE_NAME_3, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            String oneRow = res.getString(res.getColumnIndex(Column3[0]));
            oneRow += "\n borrowed from ";
            oneRow += (res.getString(res.getColumnIndex(Column3[1])));
            oneRow += "\n borrowed date ";
            oneRow += (res.getString(res.getColumnIndex(Column3[2])));
            array_list.add(oneRow);
            res.moveToNext();
        }
        return array_list;
    }

    /*
    *Buy Book
    */
    public boolean addBuyBook(String book,String author){
        db = this.getWritableDatabase();
        ContentValues res = new ContentValues();
        res.put(Column2[0],book);
        res.put(Column2[1],author);
        long chk = db.insert(TABLE_NAME_2,null,res);
        return (chk<0)? false:true;
    }

    public ArrayList<String> getBuyBook(){
        ArrayList<String> array_list = new ArrayList<String>();
        db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT *FROM "+TABLE_NAME_2, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            String oneRow = res.getString(res.getColumnIndex(Column2[0]));
            oneRow += "\n by ";
            oneRow += (res.getString(res.getColumnIndex(Column2[1])));
            res.moveToNext();
        }
        return array_list;
    }

}
