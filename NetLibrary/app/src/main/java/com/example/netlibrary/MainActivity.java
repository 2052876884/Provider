package com.example.netlibrary;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Book> booksList=new ArrayList<>();
    private MyDatabaseHelper dbHelper;
    private Button addBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper=new MyDatabaseHelper(this,"library",null,3);
        initBook();
        initCategory();
        showBook();
        initContacts();

        addBook=(Button) findViewById(R.id.addBook);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,InsertActivity.class);
                startActivity(intent);
            }
        });

        BookAdapter adapter=new BookAdapter(MainActivity.this,
                R.layout.book_item,booksList);
        ListView listView=(ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
    private void initBook(){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        Cursor cursor1=db.query("Book",new String[]{"name"},"name='目送'",
                null,null,null,null);
        if(cursor1==null){
        //第一条数据
        values.put("name","目送");
        values.put("author","龙应台");
        values.put("price",29);
        values.put("pages",50);
        values.put("category_id","文学类");
        db.insert("Book",null,values);
        values.clear();}
        cursor1.close();

        Cursor cursor2=db.query("Book",new String[]{"name"},"name='亲爱的安德烈'",
                null,null,null,null);
        if(cursor2==null){
        //第二条数据
        values.put("name","亲爱的安德烈");
        values.put("author","龙应台");
        values.put("price",29);
        values.put("pages",50);
        values.put("category_id","文学类");
        db.insert("Book",null,values);
        values.clear();}
        cursor2.close();

        Cursor cursor3=db.query("Book",new String[]{"name"},"name=?",
                new String[]{"孩子你慢慢来"},null,null,null);
        if(cursor3==null){
        //第三条数据
        values.put("name","孩子你慢慢来");
        values.put("author","龙应台");
        values.put("price",29);
        values.put("pages",50);
        values.put("category_id","文学类");
        db.insert("Book",null,values);
        values.clear();}
        cursor3.close();
    }
    private void initCategory(){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        Cursor cursor4=db.query("Category",new String[]{"category_name"},"category_name=?",
                new String[]{"文学类"},null,null,null);
        if(cursor4==null){
        //第一条数据
        values.put("category_name","文学类");
        values.put("category_code",111);
        db.insert("Category",null,values);
        values.clear();}
        cursor4.close();

        Cursor cursor5=db.query("Category",new String[]{"category_name"},"category_name=?",
                new String[]{"新闻类"},null,null,null);
        if(cursor5==null){
        //第二条数据
        values.put("category_name","新闻类");
        values.put("category_code",222);
        db.insert("Category",null,values);
        values.clear();}
        cursor5.close();
    }
    private void showBook(){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query("Book",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String category_id=cursor.getString(cursor.getColumnIndex("category_id"));
                float price=cursor.getFloat(cursor.getColumnIndex("price"));
                Book book=new Book(name,category_id,price);
                booksList.add(book);
            }while(cursor.moveToNext());
        }
        cursor.close();
    }
    private void initContacts(){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
//        Cursor cursor7=db.query("Contacts",new String[]{"contacts_name"},"contacts_name=?",
//                new String[]{"张三"},null,null,null);
//        if(cursor7==null){
            //第一条数据
            values.put("contacts_name","张三");
            values.put("contacts_number","12345678909");
            values.put("contacts_sex","男");
            db.insert("Contacts",null,values);
            values.clear();//}
//        cursor7.close();

//        Cursor cursor8=db.query("Contacts",new String[]{"contacts_name"},"contacts_name=?",
//                new String[]{"李四"},null,null,null);
//        if(cursor8==null){
            //第一条数据
            values.put("contacts_name","李四");
            values.put("contacts_number","12345678911");
            values.put("contacts_sex","女");
            db.insert("Contacts",null,values);
            values.clear();//}
//        cursor8.close();
    }
}
