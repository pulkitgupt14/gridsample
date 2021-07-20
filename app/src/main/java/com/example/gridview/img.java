package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class img extends AppCompatActivity implements View.OnClickListener{
    Bitmap bmp;
    Image img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);
        Intent intent =getIntent();
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ImageView imageView=(ImageView)findViewById(R.id.hello);

        imageView.setImageBitmap(bmp);
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