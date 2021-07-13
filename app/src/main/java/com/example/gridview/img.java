package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class img extends AppCompatActivity implements View.OnClickListener{
    String eyo;
    Image img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);
        Intent intent =getIntent();
        eyo = intent.getStringExtra("image");
        ImageView imageView=(ImageView)findViewById(R.id.hello);
        int i=Integer.parseInt(eyo);
        imageView.setImageResource(i);
        Button mButton = findViewById(R.id.back);
        mButton.setOnClickListener(this);
        System.out.println(eyo);
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