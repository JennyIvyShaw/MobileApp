package com.example.finalproject;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.RecyclerView;

public class VisitorsLog extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        ArrayList<VisitorItem> mExampleList;
//        private RecyclerView mRecyclerView;
//        private VisitorAdapter mAdapter;
//        private RecyclerView.LayoutManager mLayoutManager;


        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.bbc_news_layout, contentFrameLayout);
    }
}