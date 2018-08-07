package com.example.tuhin.pocketshelf;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class bought_books extends Activity {


    DataBase dataBase;
    ListView list;
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bought_books);


        dataBase = new DataBase(this);

        list = (ListView) findViewById(R.id.boughtBook_list);

        items = dataBase.getBoughtBook();

        itemsAdapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,items);
        list.setAdapter(itemsAdapter);
    }
}
