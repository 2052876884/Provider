package com.example.providertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ContactsAdapter extends ArrayAdapter<Contacts> {
    private  int resourceId;
    public ContactsAdapter(Context context, int textViewResourceId, List<Contacts> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        Contacts book=getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.name=(TextView) view.findViewById(R.id.name_text);
            viewHolder.number=(TextView) view.findViewById(R.id.number_text);
            viewHolder.sex=(TextView) view.findViewById(R.id.sex_text);
            view.setTag(viewHolder);
        }
        else{
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }
        viewHolder.name.setText(book.getName());
        viewHolder.number.setText(book.getNumber());
        viewHolder.sex.setText(book.getSex());
        return view;
    }
    class ViewHolder{
        TextView name;
        TextView number;
        TextView sex;
    }
}
