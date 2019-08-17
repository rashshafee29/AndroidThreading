package com.example.androidthreading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mSingleThreading, mMultipleThreading, mAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSingleThreading = findViewById(R.id.singleTreadingBtn);
        mMultipleThreading = findViewById(R.id.multipleThreadingBtn);
        mAsyncTask = findViewById(R.id.asyncTaskBtn);

        mSingleThreading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SingleThreading.class));
            }
        });

        mMultipleThreading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MultipleThreading.class));
            }
        });

        mAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AsyncTask.class));
            }
        });
    }
}
