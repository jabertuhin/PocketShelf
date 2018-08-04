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
    public static final int database_version = 3;
    //(_id integer primary key AUTOINCREMENT, book_name text,author text,publisher text)
    //Table 1
    public static final String TABLE_NAME = "MyBook";
    public static final String[] Column = {"_id","book_name","author","publisher","buying_date","shop_name","price","reading_status"};

    //Table 2
    public static final String TABLE_NAME_2 = "want_to_buy";
    public static final String[] Column2 = {"book_name","author","publisher","price"};

    //Table 3
    public static final String TABLE_NAME_3 = "borrowedBook";
    public static final String[] Column3 = {"book_name","borrowed_from","borrowed_date"};




    public DataBase(Context context) {
        super(context, DATABASE_NAME , null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("CREATE TABLE MyBook (_id INTEGER primary key AUTOINCREMENT,book_name TEXT,author TEXT,publisher TEXT,buying_date TEXT,shop_name TEXT,price int,reading_status int)");
        db.execSQL("CREATE TABLE want_to_buy (book_name TEXT,author TEXT,publisher TEXT,price int)");
        db.execSQL("CREATE TABLE borrowedBook (book_name TEXT,borrowed_from TEXT,borrowed_date TEXT)");
        addToMyShelf(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_2);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_3);
        onCreate(db);
    }
    //public static final String[] Column = {"_id","book_name","author","publisher","buying_date","shop_name","price","reading_status"};
    public void addToMyShelf(SQLiteDatabase db){
        db = this.getWritableDatabase();
        ContentValues res = new ContentValues();
        res.put(Column[1],"1984");
        res.put(Column[2],"Orwell");
        res.put(Column[3],"Rupa");
        res.put(Column[4],"21st Feb");
        res.put(Column[5],"Pathok Somabesh");
        res.put(Column[6],"150");
        res.put(Column[7],"0");
        db.insert(TABLE_NAME,null,res);
        res = new ContentValues();
        res.put(Column[1],"Shobnom");
        res.put(Column[2],"Mujtoba ali");
        res.put(Column[3],"jani na");
        res.put(Column[4],"21st Feb");
        res.put(Column[5],"Rokomari");
        res.put(Column[6],"250");
        res.put(Column[7],"1");
        db.insert(TABLE_NAME,null,res);
        res = new ContentValues();
        res.put(Column[1],"Oporajito");
        res.put(Column[2],"Bivutivushon");
        res.put(Column[3],"Desh");
        res.put(Column[4],"mone nai");
        res.put(Column[5],"Islamia Library");
        res.put(Column[6],"120");
        res.put(Column[7],"2");
        db.insert(TABLE_NAME,null,res);
    }


    public ArrayList<String> getMyBookList(){
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
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
        SQLiteDatabase db = this.getReadableDatabase();
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


    public boolean addBorrow(String book,String author,String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues res = new ContentValues();
        res.put(Column3[0],book);
        res.put(Column3[1],author);
        res.put(Column3[2],date);
        long chk = db.insert(TABLE_NAME_3,null,res);
        return (chk<0)? false:true;
    }

    public ArrayList<String> getBorrow(){
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
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


}
