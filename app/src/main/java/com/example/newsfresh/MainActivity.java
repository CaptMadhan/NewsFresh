package com.example.newsfresh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecycleAdapter adapter;
    String[] text = {"January", "February","March","April","May","June","July","August","October","November","December"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecycleAdapter(text,this);
        recyclerView.setAdapter(adapter);

    }
}