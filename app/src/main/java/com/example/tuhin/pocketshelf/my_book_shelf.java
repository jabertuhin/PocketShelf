package com.example.tuhin.pocketshelf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class my_book_shelf extends Activity{

    DataBase dataBase;

    int a;

    ListView list;
    ArrayAdapter<String> itemsAdapter;
    ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_book_shelf);

        dataBase = new DataBase(this);

        list = (ListView) findViewById(R.id.list_item);

        items = dataBase.getMyBookList();

        itemsAdapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,items);
        list.setAdapter(itemsAdapter);
    }

    public void goToAddBook(View view){
        Intent goThere = new Intent(this,addBorrowedBook.class);
        startActivity(goThere);
    }
}
