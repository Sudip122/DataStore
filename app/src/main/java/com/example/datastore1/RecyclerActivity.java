package com.example.datastore1;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datastore1.database.DbAccessObj;

public class RecyclerActivity extends AppCompatActivity {
    DbAccessObj dao;
    String[] countries = new String[]{"india","saudi","iran","afghanistan","india","saudi","iran","afghanistan","india","saudi","iran","afghanistan",
            "india","saudi","iran","afghanistan","india","saudi","iran","afghanistan","india","saudi","iran","afghanistan",
            "india","saudi","iran","afghanistan","india","saudi","iran","afghanistan","india","saudi","iran","afghanistan"};
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        dao = new DbAccessObj(this);
        dao.openDb();
    }

    @Override
    protected void onStart() {
        super.onStart();
        RecyclerView cRecyclerView = findViewById(R.id.recyclerView);

        // CountriesAdapter adapter = new CountriesAdapter(countries);
        Cursor dataCursor = dao.readRows();
        CountriesAdapter cursorAdapter1 = new CountriesAdapter(dataCursor);
        cRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        cRecyclerView.setAdapter(cursorAdapter1);
    }
}
