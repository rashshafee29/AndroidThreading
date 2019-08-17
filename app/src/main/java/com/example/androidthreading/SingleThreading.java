package com.example.androidthreading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SingleThreading extends AppCompatActivity {

    Button mChangeText, mChangeBackGround;
    Button mReset;
    TextView mTextView;
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Toast toast = Toast.makeText(getApplicationContext(), "10 SEC", Toast.LENGTH_LONG);
            toast.show();
            mTextView.setText("OK changed After 10 SEC! YAHOO!");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_threading);

        mTextView = findViewById(R.id.textView);
        mChangeText = findViewById(R.id.changeTextBtn);
        mReset = findViewById(R.id.resetButton);
        mChangeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis() + 10000;
                        while (System.currentTimeMillis() < time) {
                            synchronized (this) {
                                try {
                                    wait(time - System.currentTimeMillis());
                                } catch (Exception e) {}
                            }
                        }
                        mHandler.sendEmptyMessage(0);
                    }
                };
                Toast toast = Toast.makeText(getApplicationContext(), "0 SEC", Toast.LENGTH_LONG);
                toast.show();
                Thread thread = new Thread(runnable);
                thread.start();

            }
        });

        mChangeBackGround = findViewById(R.id.changeBackGroundBtn);
        mChangeBackGround.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextView.setBackgroundColor(Color.RED);
            }
        });

        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextView.setBackgroundColor(Color.TRANSPARENT);
                mTextView.setText(R.string.single_threadingText);
            }
        });

    }
}
