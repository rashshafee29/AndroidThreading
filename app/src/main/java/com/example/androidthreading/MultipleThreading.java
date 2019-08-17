package com.example.androidthreading;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MultipleThreading extends AppCompatActivity {

    Button mStartButton, mChangeButton, mReset;
    TextView mThread1, mThread2;
    Handler handler1;
    Handler handler2;
    int i,j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_threading);

        mStartButton = findViewById(R.id.startBtn);
        mChangeButton = findViewById(R.id.changeBackGroundBtn);
        mReset = findViewById(R.id.resetButton);

        mThread1 = findViewById(R.id.thread1Counter);
        mThread2 = findViewById(R.id.thread2Counter);
        handler1 = new Handler();
        handler2 = new Handler();

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread1 = new Thread() {
                    @Override
                    public void run() {
                        for (i = 1; i < 100; i++) {
                            try {
                                sleep(100);
                            } catch (Exception e){}
                            handler1.post(new Runnable() {
                                @Override
                                public void run() {
                                    mThread1.setText("" + i);
                                }
                            });
                        }
                    }
                };
                thread1.start();

                Thread thread2 = new Thread() {
                    @Override
                    public void run() {
                        for (j = 1; j < 100; j++) {
                            try {
                                sleep(200);
                            } catch (Exception e){}
                            handler2.post(new Runnable() {
                                @Override
                                public void run() {
                                    mThread2.setText("" + j);
                                }
                            });
                        }
                    }
                };
                thread2.start();
            }
        });

        mChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mThread1.setBackgroundColor(Color.YELLOW);
                mThread2.setBackgroundColor(Color.GREEN);
            }
        });

        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mThread1.setBackgroundColor(Color.TRANSPARENT);
                mThread2.setBackgroundColor(Color.TRANSPARENT);
            }
        });

    }
}
