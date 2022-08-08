package com.example.finalproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class VisitorsLog extends BaseActivity {
    ArrayList<VisitorItem> mVisitorList;
    private RecyclerView mRecyclerView;
    private VisitorAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.visitors_log_layout, contentFrameLayout);

        loadData();
        buildRecyclerView();
        setInsertButton();

        Button buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }


    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mVisitorList);
        editor.putString("visitor list", json);
        editor.apply();
        Toast.makeText(this, "Your name has been saved!", Toast.LENGTH_LONG).show();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("visitor list", null);
        Type type = new TypeToken<ArrayList<VisitorItem>>() {}.getType();
        mVisitorList = gson.fromJson(json, type);

        if (mVisitorList == null) {
            mVisitorList = new ArrayList<>();
        }
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new VisitorAdapter(mVisitorList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setInsertButton() {
        Button buttonInsert = findViewById(R.id.button_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText line1 = findViewById(R.id.edittext_line_1);
                EditText line2 = findViewById(R.id.edittext_line_2);
                insertItem(line1.getText().toString(), line2.getText().toString());

                view = findViewById(R.id.menu_visitorsLog);
                Snackbar snackbar = Snackbar.make(view, getString(R.string.saving_name), Snackbar.LENGTH_LONG);

                snackbar.show();
            }
        });
    }

    private void insertItem(String line1, String line2) {
        mVisitorList.add(new VisitorItem(line1, line2));
        mAdapter.notifyItemInserted(mVisitorList.size());
    }
}