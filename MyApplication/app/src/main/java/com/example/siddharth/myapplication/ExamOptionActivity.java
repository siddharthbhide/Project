package com.example.siddharth.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ExamOptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_option);
    }

    public void OnClickRegularExam(View view)
    {
        Intent objExample = new Intent(ExamOptionActivity.this,ExamActivity.class);
        startActivity(objExample);
    }

    public void OnClickCompetitionExam(View view)
    {
    }

    public void OnClickHomeWork(View view)
    {
    }
}
