package com.example.siddharth.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ExamActivity extends AppCompatActivity {

    String question;
    String opt1;
    String opt2;
    String opt3;
    String opt4;
    String ans;

    Database objDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        objDatabase = new Database(this);
        objDatabase.addRecord("What is One","1","2","1","3","4","1");
        objDatabase.getRecord(0,question,opt1,opt2,opt3,opt4);
    }


}
