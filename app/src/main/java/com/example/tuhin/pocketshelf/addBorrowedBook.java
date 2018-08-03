package com.example.tuhin.pocketshelf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class addBorrowedBook extends Activity {

    DataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_borrowed_book);
        database = new DataBase(this);
    }

    public void addAndGo(View view){
        EditText etBook = (EditText) findViewById(R.id.bookName);
        EditText etFrom = (EditText) findViewById(R.id.BorrowedFrom);
        EditText etDate = (EditText) findViewById(R.id.BorrowedDate);

        String book = etBook.getText().toString();
        String from = etFrom.getText().toString();
        String date = etDate.getText().toString();

        boolean chk = database.addBorrow(book,from,date);
        if(chk) Toast.makeText(getApplicationContext(),"Added successfully.",Toast.LENGTH_SHORT).show();
        else Toast.makeText(getApplicationContext(),"Try again later.",Toast.LENGTH_SHORT).show();

        Intent goThere = new Intent(this,borrowed_books.class);
        startActivity(goThere);

    }
}
