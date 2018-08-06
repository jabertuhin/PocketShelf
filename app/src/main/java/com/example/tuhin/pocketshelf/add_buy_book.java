package com.example.tuhin.pocketshelf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class add_buy_book extends Activity {

    DataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_buy_book);
        database = new DataBase(this);
    }

    public void addAndGo(View view){
        EditText etBook = (EditText) findViewById(R.id.editText_book);
        EditText etFrom = (EditText) findViewById(R.id.editText_author);

        String book = etBook.getText().toString();
        String from = etFrom.getText().toString();

        boolean chk = database.addBuyBook(book,from);
        if(chk) Toast.makeText(getApplicationContext(),"Added successfully.",Toast.LENGTH_SHORT).show();
        else Toast.makeText(getApplicationContext(),"Try again later.",Toast.LENGTH_SHORT).show();

        Intent goThere = new Intent(this,want_to_buy.class);
        startActivity(goThere);
    }
}
