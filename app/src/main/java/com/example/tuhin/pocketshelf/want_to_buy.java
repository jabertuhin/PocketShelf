package com.example.tuhin.pocketshelf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class want_to_buy extends Activity {

    DataBase dataBase;
    ListView list;
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.want_to_buy);
        dataBase = new DataBase(this);

        list = (ListView) findViewById(R.id.buy_list);

        items = dataBase.getBuyBook();

        itemsAdapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,items);
        list.setAdapter(itemsAdapter);

    }

    public void goToAddBuyBook(View view){
        Intent goThere = new Intent(this,add_buy_book.class);
        startActivity(goThere);
    }
}
