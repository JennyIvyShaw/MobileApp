package com.example.finalproject;

import android.os.Bundle;
import android.widget.FrameLayout;
/**This class loads the home fragement which displays a welcome message */
public class Home extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.home_layout, contentFrameLayout);
    }
}
