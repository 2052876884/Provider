package com.example.netlibrary;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertActivity extends AppCompatActivity {

    private EditText name_editText;
    private EditText author_editText;
    private EditText price_editText;
    private EditText pages_editText;
    private EditText category_id_editText;
    private Button sumbit_button;
    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        dbHelper=new MyDatabaseHelper(this,"library",null,3);

        name_editText=(EditText) findViewById(R.id.name_editText);
        author_editText=(EditText) findViewById(R.id.author_editText);
        price_editText=(EditText) findViewById(R.id.price_editText);
        pages_editText=(EditText) findViewById(R.id.pages_editText);
        category_id_editText=(EditText) findViewById(R.id.category_id_editText);
        sumbit_button=(Button) findViewById(R.id.sumbit_button);
        sumbit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=name_editText.getText().toString();
                String author=author_editText.getText().toString();
                float price=Float.parseFloat(price_editText.getText().toString());
                int pages=Integer.parseInt(pages_editText.getText().toString());
                String category_id=category_id_editText.getText().toString();

                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("name",name);
                values.put("author",author);
                values.put("price",price);
                values.put("pages",pages);
                values.put("category_id",category_id);
                db.insert("Book",null,values);
                values.clear();

                Intent intent=new Intent(InsertActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
