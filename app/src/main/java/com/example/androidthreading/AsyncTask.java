package com.example.androidthreading;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AsyncTask extends AppCompatActivity {

    Button mStartBtn, mStopBtn, mChangeBtn, mResetBtn;
    TextView mCounterText;
    int count, i;
    private MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        mStartBtn = findViewById(R.id.startBtn);
        mStopBtn = findViewById(R.id.stopBtn);
        mChangeBtn = findViewById(R.id.changeBackGroundBtn);
        mResetBtn = findViewById(R.id.resetButton);

        mCounterText = findViewById(R.id.counterText);

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute(count);
                Log.d("Shafee", String.valueOf(myAsyncTask.getStatus()));
            }
        });

        mStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAsyncTask.cancel(true);
                Log.d("Shafee", String.valueOf(myAsyncTask.getStatus()));
            }
        });

        mChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCounterText.setBackgroundColor(Color.YELLOW);
            }
        });

        mResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCounterText.setBackgroundColor(Color.TRANSPARENT);
            }
        });

    }

    private class MyAsyncTask extends android.os.AsyncTask<Integer, Integer, Integer> {

        private int counter;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            counter = 0;
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            for (i = 1; i<= 100; i++) {
                try {
                    Thread.sleep(100);
                    publishProgress(i);
                } catch (Exception e){}
            }
            counter = i;
            return counter;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mCounterText.setText("" + values[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            mCounterText.setText("" + integer);
            counter = integer;
        }
    }
}
