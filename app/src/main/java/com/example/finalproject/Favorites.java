package com.example.finalproject;

import android.os.Bundle;
import android.widget.FrameLayout;

public class Favorites extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.visitors_log_layout, contentFrameLayout);
    }
}