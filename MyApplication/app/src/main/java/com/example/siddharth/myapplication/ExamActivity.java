package com.example.siddharth.myapplication;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;

public class ExamActivity extends AppCompatActivity {

    String question;
    String opt1;
    String opt2;
    String opt3;
    String opt4;
    String ans;
    private TextView objTimeView;

    Database objDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        objTimeView = (TextView) findViewById(R.id.textTime);
        countDownTimer.start();
        /*objDatabase = new Database(this);
        objDatabase.addRecord("What is One","1","2","1","3","4","1");
        objDatabase.getRecord(0,question,opt1,opt2,opt3,opt4);*/
    }

    CountDownTimer countDownTimer = new CountDownTimer(60 * 1000, 1000)
    {
        public void onTick(long millisUntilFinished)
        {
            objTimeView.setText("Seconds remaining: " + millisUntilFinished / 1000);
        }
        public void onFinish()
        {
            objTimeView.setText("Done !");
        }
    };

    public void OnClickSave(View view)
    {
        countDownTimer.onFinish();
    }
}
