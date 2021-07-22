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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.security.Permission;
import java.util.ArrayList;

public class gridact extends AppCompatActivity {
    GridView grid;

    String lo[] = new String[1000];
    public static final int READ_STORAGE_PERMISSION = 101;
    public static ArrayList<String> list = new ArrayList<String>();

    Context context;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridact);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_STORAGE_PERMISSION);


        message("all the files are read");
        try {
            if (list.size() == 0)
                list = func(this);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        grid = (GridView) findViewById(R.id.datagrid);
        gridAdapter obj = new gridAdapter(this, list);
        grid.setAdapter(obj);

        Intent intent = new Intent(this, img.class);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                System.out.println(position);

                String bp = list.get(position);

                intent.putExtra("image", bp);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int grantResults[]) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_STORAGE_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                message("permission granted");

            } else {
                message("permission declined");
            }
        }
    }

    public void message(String s) {
        gridact.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                Toast.makeText(gridact.this, s, Toast.LENGTH_SHORT);
            }

        });
    }

    public ArrayList<String> func(Context context) throws IOException {
        String projections[] = new String[]{MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.WIDTH,
                MediaStore.Images.Media.HEIGHT

        };
        //commented code for calculating time of activities

        ArrayList<String> b = new ArrayList<String>();

        //  long start = System.nanoTime();
        Uri images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        /*long end = System.nanoTime();
        double cal = end-start;
        cal= cal/1000000000;*/
        Cursor cur = context.getContentResolver().query(images, projections, null, null, null);
        //    System.out.println("count     :"+"   "+cur.getCount()+" time : "+cal);

        // start = System.nanoTime();
        if (cur.moveToFirst()) {
            double w, h;
            String absolutepath = null;
            int wCol = cur.getColumnIndex(MediaStore.Images.Media.WIDTH);
            int hCol = cur.getColumnIndex(MediaStore.Images.Media.HEIGHT);
            int data = cur.getColumnIndex(MediaStore.MediaColumns.DATA);
            do {
                w = cur.getInt(wCol);
                h = cur.getInt(hCol);
                absolutepath = cur.getString(data);
                if ((w / h) >= 2.0) {
                    b.add(absolutepath);
                }

            } while (cur.moveToNext());

        }
        // for calculating load time
        /*
        end= System.nanoTime();
        cal = end-start;
        cal= cal/(1000000000);
        System.out.println("bitmap conversion : "+cal);*/
        return b;
    }

}