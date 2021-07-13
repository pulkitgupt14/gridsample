package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class gridact extends AppCompatActivity {
    GridView grid;
    int icons[]=new int[1000];
    String lo[]= new String[1000];
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridact);
        for(int i=0;i<1000;i++)
            icons[i]= R.drawable.a;
       // System.out.println(R.drawable.a);
        grid = (GridView)findViewById(R.id.datagrid);
        gridAdapter obj = new gridAdapter(this,icons);
        grid.setAdapter(obj);
        //context =this;
        Intent intent = new Intent(this, img.class);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                System.out.println(position);
                intent.putExtra("image", Integer.toString(icons[position]));

                startActivity(intent);

            }
        });
    }

}