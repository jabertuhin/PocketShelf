package com.example.tuhin.pocketshelf;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class borrowed_books extends Activity {

    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrowed_books);

        dataBase = new DataBase(this);


        ListView list = (ListView) findViewById(R.id.borrow_list);

        ArrayList<String> items = dataBase.getBorrow();

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,items);
        list.setAdapter(itemsAdapter);
    }

    public void goToAddBorrow(View view){
        Intent goThere = new Intent(this,addBorrowedBook.class);
        startActivity(goThere);
    }
}
