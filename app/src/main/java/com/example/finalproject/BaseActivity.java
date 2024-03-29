package com.example.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


import com.google.android.material.navigation.NavigationView;

/**This class acts a super layout, all the activities are loaded into a frame in activity_main.xml
 * Navigation and toolbar navigate the user to other activity*/
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
            /**
             * This method allows the user to click a navigation menu option and load the activity accordingly
             *
             * @param item Item in the navigation drawer
             * @return
             */
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

    /**
     * This method allows the user to click a toolbar menu option and load the activity accordingly
     *
     * @param item Option in the toolbar
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        String message = null;
        //Look at your menu XML file. Put a case for every id in that file:
        switch(item.getItemId())
        {
            //what to do when the menu item is selected:
            case R.id.tool_home:
                Intent homeIntent = new Intent(getApplicationContext(), Home.class);
                startActivity(homeIntent);
                drawerLayout.closeDrawers();
                break;

            case R.id.tool_favorites:
                Intent favIntent = new Intent(getApplicationContext(), Favorites.class);
                startActivity(favIntent);
                drawerLayout.closeDrawers();
                break;

            case R.id.tool_Help:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.info))
                        .setNegativeButton(getString(R.string.news_alert_cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();


                break;

        }

        return true;
    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        actionBarDrawerToggle.syncState();
    }
}
