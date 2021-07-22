package com.example.gridview;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class gridAdapter extends BaseAdapter {
    // private final String name[];
    ArrayList<String> images;
    public static int width = 600;
    public static int height = 300;
    Context context;

    public gridAdapter(Context context, ArrayList<String> e) {
        this.context = context;
        this.images = e;
    }

    @Override
    public int getCount() {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.singleframe, null);
        ImageView img = (ImageView) view.findViewById(R.id.iconimage);
        String path = images.get(position);
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver()
                    , Uri.fromFile(new File(path)));
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }


        ViewTreeObserver vto = view.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                gridAdapter.width = view.getMeasuredWidth();
                gridAdapter.height = view.getMeasuredHeight();


            }
        });

        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height);
        img.setImageBitmap(bitmap);


        return view;
    }

}
