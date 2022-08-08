package com.example.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class Favorites extends BaseActivity {
    //Variables associated to objects on the layout (references)
    ListView favoriteListView;

    ArrayList<NewsList> newsList = new ArrayList<>();
    MyOwnAdapter myAdapter;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.favorites_layout, contentFrameLayout);

        favoriteListView = findViewById(R.id.favorite_listView);
        loadDataFromDatabase();

        //create an adapter object and send it to the listVIew
        myAdapter = new MyOwnAdapter();
        favoriteListView.setAdapter(myAdapter);

        favoriteListView.setOnItemClickListener(( parent,  view,  position,  id) -> {
            showItem( position );
        });

    }

    private void showItem(int position)
    {
        NewsList selectedItem = newsList.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.selection))
                .setMessage(getString(R.string.selected) + position)
                .setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteItem(selectedItem);
                        newsList.remove(position);
                        myAdapter.notifyDataSetChanged();
                    }
                })
                .setNeutralButton(getString(R.string.open_link), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String link = newsList.get(position).getUrl();
                        Uri uri = Uri.parse(link);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);



                    }
                })
                        .setNegativeButton(getString(R.string.news_alert_cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
    }



    private void deleteItem(NewsList selectedItem) {
        db.delete(DBHelper.TABLE_NAME, DBHelper.COL_ID + "= ?", new String[] {Long.toString(selectedItem.getId())});
    }




    private void loadDataFromDatabase()
    {
        //get a database connection.
        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        // We want to get all of the columns.
        String [] columns = {DBHelper.COL_ID, DBHelper.COL1_TITLE, DBHelper.COL2_URL};

        //query all the results from the database:
        Cursor results = db.query(false, DBHelper.TABLE_NAME, columns, null, null, null, null, null, null);

        //Now the results object has rows of results that match the query.
        int urlColumnIndex = results.getColumnIndex(DBHelper.COL2_URL);
        int titleColIndex = results.getColumnIndex(DBHelper.COL1_TITLE);
        int idColIndex = results.getColumnIndex(DBHelper.COL_ID);

        //iterate over the results, return true if there is a next item.
        while(results.moveToNext())
        {
            String title = results.getString(titleColIndex);
            String url = results.getString(urlColumnIndex);
            long id = results.getLong(idColIndex);

            //add the new Item to the array list:
            newsList.add(new NewsList(title, url, id));
        }
    }






    //Custom Adapter
    protected class MyOwnAdapter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return newsList.size();
        }

        public NewsList getItem(int position){
            return newsList.get(position);
        }

        public long getItemId(int position)  { return getItem(position).getId(); }

        public View getView(int position, View old, ViewGroup parent)
        {
            View newView = getLayoutInflater().inflate(R.layout.favorite_item, parent, false );
            NewsList thisRow = getItem(position);

            //Get the text view for the Rows (aka title and url)
            TextView titleName = newView.findViewById(R.id.favorite_title_textView);
            titleName.setText(  thisRow.getTitle());

            TextView urlName = newView.findViewById(R.id.favorite_url_textView);
            urlName.setText( thisRow.getUrl());

            return newView;
        }
    }
}