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

    ListView list;
    ArrayAdapter<String> itemsAdapter;
    ArrayList<String> items;
    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_book_shelf);

        list = (ListView) findViewById(R.id.list_item);
        items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,items);
        list.setAdapter(itemsAdapter);

    }
}
