package com.example.tuhin.pocketshelf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewBook extends Activity {
    DataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_book);
        database = new DataBase(this);
    }

    public void addAndGoBack(View view){
        EditText etBookName = (EditText) findViewById(R.id.editText_book);
        EditText etAuthor = (EditText) findViewById(R.id.editText_author);
        EditText etPublisher = (EditText) findViewById(R.id.editText_publisher);
        EditText etDate = (EditText) findViewById(R.id.editText_date);
        EditText etShopName = (EditText) findViewById(R.id.editText_shop_name);
        EditText etPrice = (EditText) findViewById(R.id.editText_price);
        EditText etStatus = (EditText) findViewById(R.id.editText_status);

        String book = etBookName.getText().toString();
        String author = etAuthor.getText().toString();
        String publisher = etPublisher.getText().toString();
        String date = etDate.getText().toString();
        String shopName = etShopName.getText().toString();
        int price = Integer.parseInt(etPrice.getText().toString());
        String status = etStatus.getText().toString();

        int stat = 0;

        if(status == "reading") stat = 2;
        else stat = 1;

        boolean chk = database.addToMyShelf(book,author,publisher,date,shopName,price,stat);
        if(chk) Toast.makeText(getApplicationContext(),"Added successfully.",Toast.LENGTH_SHORT).show();
        else Toast.makeText(getApplicationContext(),"Try again later.",Toast.LENGTH_SHORT).show();

        Intent goThere = new Intent(this,my_book_shelf.class);
        startActivity(goThere);
    }


}
