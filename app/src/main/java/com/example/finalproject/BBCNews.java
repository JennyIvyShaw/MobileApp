package com.example.finalproject;


import static java.net.Proxy.Type.HTTP;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class BBCNews extends BaseActivity {
    ListView newsListview;
    ArrayList<String> titles;
    ArrayList<String> descriptions;
    ArrayList<String> links;
    ArrayList<String> pubDates;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.bbc_news_layout, contentFrameLayout);

        newsListview = findViewById(R.id.news_listView);
        titles = new ArrayList<String>();
        descriptions = new ArrayList<String>();
        links = new ArrayList<String>();
        pubDates = new ArrayList<String>();

        newsListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String title = titles.get(position);
                String description = descriptions.get(position);
                String link = links.get(position);
                String pubDate = pubDates.get(position);

                Intent intent = new Intent(BBCNews.this, BBCNewsItem.class );
                intent.putExtra("TITLE", title);
                intent.putExtra("DESCRIPTION", description);
                intent.putExtra("LINK", link);
                intent.putExtra("PUBDATE", pubDate);
                startActivity(intent);


            }
        });
        new ProcessInBackground().execute();
    }

    public InputStream getInputStream(URL url){
        try{
            return url.openConnection().getInputStream();
        }catch(IOException e){
            return null;
        }
    }

    public class ProcessInBackground extends AsyncTask<Integer, Void, String>{
        ProgressDialog progressDialog = new ProgressDialog(BBCNews.this);
        Exception exception;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Loading content from BBC News");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Integer... params) {

            try{
                URL url = new URL("https://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml");
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(getInputStream(url), "UTF_8");
                Log.e("connection", "connection made");

                boolean insideItem = false;

                //return the types of the current event start/end tags, etc...
                int eventType = xpp.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT)
                {
                    if (eventType == XmlPullParser.START_TAG)
                    {
                        if (xpp.getName().equalsIgnoreCase("item"))
                        {
                            Log.e("inside the item tag", "inside item");
                            insideItem = true;
                        }
                        else if (xpp.getName().equalsIgnoreCase("title"))
                        {
                            if (insideItem)
                            {
                                titles.add(xpp.nextText());
                            }
                        }
                        else if (xpp.getName().equalsIgnoreCase("description"))
                        {
                            if(insideItem)
                            {
                               descriptions.add(xpp.nextText());
                            }
                        }
                        else if (xpp.getName().equalsIgnoreCase("link"))
                        {
                            if(insideItem)
                            {
                                links.add(xpp.nextText());
                            }
                        }
                        else if (xpp.getName().equalsIgnoreCase("pubDate"))
                        {
                            if(insideItem)
                            {
                                pubDates.add(xpp.nextText());
                            }
                        }
                    }
                    else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item"))
                    {
                        insideItem = false;
                    }
                    eventType = xpp.next();
                }

            }
            catch (MalformedURLException e)
            {
                exception = e;
            }
            catch (XmlPullParserException e)
            {
                exception = e;
            }
            catch (IOException e)
            {
                exception = e;
            }


            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(BBCNews.this, android.R.layout.simple_list_item_1, titles);
            newsListview.setAdapter(adapter);

            progressDialog.dismiss();
        }
    }

}

