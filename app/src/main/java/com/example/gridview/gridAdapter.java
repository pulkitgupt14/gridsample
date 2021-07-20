package com.example.gridview;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class gridAdapter extends BaseAdapter
{
   // private final String name[];
   ArrayList<Bitmap> images;
    Context context;

    public gridAdapter(Context context, ArrayList<Bitmap> e) {
        this.context = context;
        this.images = e;
    }

    @Override
    public int getCount()
    {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.singleframe,null);
        ImageView img=(ImageView)view.findViewById(R.id.iconimage);
        img.setImageBitmap(images.get(position));
        return view;
    }

}
