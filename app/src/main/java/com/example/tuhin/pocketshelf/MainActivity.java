package com.example.tuhin.pocketshelf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    public static DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBase = new DataBase(this);
    }

    public void toMyShelf(View view) {
        Intent goThere = new Intent(this,my_book_shelf.class);
        startActivity(goThere);
    }

    public void toBorrowed(View view) {
        Intent goThere = new Intent(this,borrowed_books.class);
        startActivity(goThere);
    }

    public void toBoughtBook(View view) {
        Intent goThere = new Intent(this,bought_books.class);
        startActivity(goThere);
    }

    public void toWantToBuy(View view) {
        Intent goThere = new Intent(this,want_to_buy.class);
        startActivity(goThere);
    }

    public void toReading(View view) {
        Intent goThere = new Intent(this,reading.class);
        startActivity(goThere);
    }

    public void toRead(View view) {
        Intent goThere = new Intent(this,read.class);
        startActivity(goThere);
    }

}
