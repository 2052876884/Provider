package com.example.netlibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    private  int resourceId;
    public BookAdapter(Context context, int textViewResourceId, List<Book> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        Book book=getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.name=(TextView) view.findViewById(R.id.name_text);
            viewHolder.categoryname=(TextView) view.findViewById(R.id.categoryname_text);
            viewHolder.price=(TextView) view.findViewById(R.id.price_text);
            view.setTag(viewHolder);
        }
        else{
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }
        viewHolder.name.setText(book.getName());
        viewHolder.categoryname.setText(book.getCategoryname());
        viewHolder.price.setText(Float.toString(book.getPrice()));
        return view;
    }
    class ViewHolder{
        TextView name;
        TextView categoryname;
        TextView price;
    }
}
