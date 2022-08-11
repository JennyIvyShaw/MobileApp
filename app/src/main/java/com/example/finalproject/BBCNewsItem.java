package com.example.finalproject;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

/**
 * This class collects content parsed by the previous activity and displays it in TextViews
 */
public class BBCNewsItem extends BaseActivity {

    TextView titleView, descriptionView, pubDateView, urlView;
    AlertDialog.Builder builder;
    ArrayList<NewsList> newsList;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.news_items, contentFrameLayout);

        titleView = (TextView)findViewById(R.id.news_title_textView);
        descriptionView = (TextView)findViewById(R.id.news_description_textView);
        pubDateView = (TextView)findViewById(R.id.news_date_textView);
        urlView = (TextView)findViewById(R.id.news_url_textView);

        newsList = new ArrayList<NewsList>();



        Intent intent = getIntent();
        String title = intent.getStringExtra("TITLE");
        String description = intent.getStringExtra("DESCRIPTION");
        String link = intent.getStringExtra("LINK");
        String pubDate = intent.getStringExtra("PUBDATE");



        titleView.setText(getResources().getString(R.string.news_title) + title);
        descriptionView.setText(getResources().getString(R.string.news_description) + description);
        pubDateView.setText(getResources().getString(R.string.news_date)+ pubDate);
        urlView.setText(getResources().getString(R.string.news_url) +link);

        builder =new AlertDialog.Builder(BBCNewsItem.this);

        titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle(getString(R.string.save_article))
                        .setCancelable(true)
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(BBCNewsItem.this, getString(R.string.article_saved), Toast.LENGTH_SHORT).show();

                                ContentValues newRowValues = new ContentValues();
                                newRowValues.put(DBHelper.COL1_TITLE, title);
                                newRowValues.put(DBHelper.COL2_URL, link);

                                DBHelper dbHelper = new DBHelper(BBCNewsItem.this);
                                db = dbHelper.getWritableDatabase();

                                long newId = db.insert(DBHelper.TABLE_NAME, null, newRowValues);

                                //creating the News Item object
                                NewsList newNewsListItem = new NewsList(title, link, newId);

                                //adding item list
                                newsList.add(newNewsListItem);



                            }
                        })
                        .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
            }
        });

        urlView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(link);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }



}