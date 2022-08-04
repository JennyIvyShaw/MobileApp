package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


import com.google.android.material.navigation.NavigationView;

public class BaseActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);






        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.menu_home:
                        Intent homeIntent = new Intent(getApplicationContext(), Home.class);
                        startActivity(homeIntent);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.menu_bbcNews:
                        Intent bbcIntent = new Intent(getApplicationContext(), BBCNews.class);
                        startActivity(bbcIntent);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.menu_favorites:
                        Intent favIntent = new Intent(getApplicationContext(), Favorites.class);
                        startActivity(favIntent);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.menu_visitorsLog:
                        Intent visitorIntent = new Intent(getApplicationContext(), VisitorsLog.class);
                        startActivity(visitorIntent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.menu_exit:
                        finishAffinity();
                        break;
                }
                return false;
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        String message = null;
        //Look at your menu XML file. Put a case for every id in that file:
        switch(item.getItemId())
        {
            //what to do when the menu item is selected:
            case R.id.tool_home:
                message = "You clicked item 1";
                break;
            case R.id.tool_bbcNews:
                message = "You clicked item 2";
                break;

        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        return true;
    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        actionBarDrawerToggle.syncState();
    }
}
