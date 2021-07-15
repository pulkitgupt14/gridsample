package com.example.gridview;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;
import java.security.Permission;
import java.util.ArrayList;

public class gridact extends AppCompatActivity {
    GridView grid;

    String lo[]= new String[1000];
    public static final int READ_STORAGE_PERMISSION = 101;
    ArrayList<File> list = new ArrayList<File>();
    Context context;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridact);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_STORAGE_PERMISSION);


        //System.out.println(list.size());
        list = imageReader(Environment.getExternalStorageDirectory());
        grid = (GridView)findViewById(R.id.datagrid);
        gridAdapter obj = new gridAdapter(this,list);
        grid.setAdapter(obj);

        Intent intent = new Intent(this, img.class);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                System.out.println(position);
                intent.putExtra("image", list.get(position).toString());
                startActivity(intent);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode , String permissions[], int grantResults[]) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==READ_STORAGE_PERMISSION)
        {
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(gridact.this, "permission granted", Toast.LENGTH_SHORT);

            }
            else{
                Toast.makeText(gridact.this,"Permission   Denied", Toast.LENGTH_SHORT);

            }
        }
    }


    public ArrayList<File> imageReader(File e)
    {
        ArrayList<File> b = new ArrayList<>();

        File files[]=e.listFiles();
        if(files==null)
            return b;

        for(int i =0;i<files.length;i++)
        {
             if(files[i].isDirectory())
             {
                 b.addAll(imageReader(files[i]));
             }
             else {
                 if(files[i].getName().endsWith("jpg"))
                 {
                     BitmapFactory.Options options = new BitmapFactory.Options();
                     options.inJustDecodeBounds = true;
                     BitmapFactory.decodeFile(files[i].getAbsolutePath(), options);
                     double imageHeight = options.outHeight;
                     double imageWidth = options.outWidth;
                     double ratio = imageWidth/imageHeight;
                     if(ratio>=2.0)
                     {
                         b.add(files[i]);
                     }
                 }
             }
        }
        return b ;
    }
}