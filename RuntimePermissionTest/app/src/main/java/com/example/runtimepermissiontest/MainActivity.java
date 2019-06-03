package com.example.runtimepermissiontest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    List<String> contactList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView contactsView=(ListView) findViewById(R.id.contacts_view);
        adapter=new ArrayAdapter<String>(this, android.R.layout.
                simple_list_item_1,contactList);
        contactsView.setAdapter(adapter);
        //判断用户是否有授权
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.
                READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            //如果没有，则向用户申请权限
            ActivityCompat.requestPermissions(this,new String[]{Manifest.
                    permission.READ_CONTACTS},1);
        }
        else{
            readContacts();//如果有则直接读取联系人信息
        }
    }
    private void readContacts(){
        Cursor cursor=null;
        try{
            //查询联系人的数据
            cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.
                    CONTENT_URI,null,null,null,null);
            if(cursor!=null){
                while (cursor.moveToNext()){
                    //获取联系人姓名
                    String displayName=cursor.getString(cursor.getColumnIndex
                            (ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    //获取联系人手机号
                    String number=cursor.getString(cursor.getColumnIndex
                            (ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactList.add(displayName+"\n"+number);
                }
                adapter.notifyDataSetChanged();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
        }
    }
    @Override
    //不论任何结果，最终都会回调到onRequestPermissionsResult()方法里，授权的结果封装在grantResults中
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults){
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    readContacts();
                }
                else {
                    Toast.makeText(this,"You denied the permission",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}
