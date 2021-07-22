package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class img extends AppCompatActivity implements View.OnClickListener{
    Bitmap bmp;
    Image img;
    static int width = 600;
    static int height= 300;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);
        Intent intent =getIntent();
        String path  = getIntent().getStringExtra("image");
        Bitmap bitmap=null;
        try
        {
            bitmap = MediaStore.Images.Media.getBitmap(img.this.getContentResolver()
                    , Uri.fromFile(new File(path)));
        }
        catch(Exception e)
        {
            System.out.println(e.getStackTrace());
        }


        ImageView imageView=(ImageView)findViewById(R.id.hello);
        ViewTreeObserver vto = imageView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                com.example.gridview.img.width  = imageView.getMeasuredWidth();
                com.example.gridview.img.height = imageView.getMeasuredHeight();


            }
        });
        System.out.println(com.example.gridview.img.width);
        bitmap= ThumbnailUtils.extractThumbnail(bitmap,com.example.gridview.img.width,com.example.gridview.img.height);

        imageView.setImageBitmap(bitmap);
        Button mButton = findViewById(R.id.back);
        mButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.back:
                Intent intent = new Intent(this, gridact.class);
                startActivity(intent);
                break;
        }
    }
}