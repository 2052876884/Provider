package com.example.providertest;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class showContacts extends AppCompatActivity {

    private List<Contacts> contactsList=new ArrayList<>();

    private String newId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contacts);
        Uri uri=Uri.parse("content://com.example.netlibrary.provider/contacts");
        Cursor cursor=getContentResolver().query(uri,null,null,
                null,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                String name=cursor.getString(cursor.getColumnIndex("contacts_name"));
                String number=cursor.getString(cursor.getColumnIndex("contacts_number"));
                String sex=cursor.getString(cursor.getColumnIndex("contacts_sex"));
                Contacts contacts=new Contacts(name,number,sex);
                contactsList.add(contacts);
            }
            cursor.close();
        }
        ContactsAdapter adapter=new ContactsAdapter(showContacts.this,
                R.layout.contacts_item,contactsList);
        ListView listView=(ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}
