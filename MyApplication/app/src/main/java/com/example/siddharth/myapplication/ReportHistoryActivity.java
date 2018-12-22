package com.example.siddharth.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ReportHistoryActivity extends AppCompatActivity
{
    List<String> listOfCourse = new ArrayList<String>();
    List<String> listOfLevel = new ArrayList<String>();
    Spinner spinnerOfCourse;
    Spinner spinnerOfLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_history);
        this.setTitle(R.string.report_history);
        InitSpinner();
    }
    private void InitSpinner()
    {
        spinnerOfCourse = (Spinner) findViewById(R.id.spinnerCourse);
        listOfCourse.add("Select Course");
        listOfCourse.add("CSD Abacus");
        listOfCourse.add("CSD Vedic Maths");
        listOfCourse.add("CSD Smart Writing");
        ArrayAdapter adapterCourse = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfCourse);
        adapterCourse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfCourse.setAdapter(adapterCourse);

        spinnerOfLevel = (Spinner) findViewById(R.id.spinnerLavel_Category);
        listOfLevel.add("Cource Level");
        listOfLevel.add("1");
        listOfLevel.add("2");
        listOfLevel.add("3");
        ArrayAdapter adapterLevel = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfLevel);
        adapterLevel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfLevel.setAdapter(adapterLevel);


    }
    public void onClickDisplayReport(View view)
    {
        Intent objIntent = new Intent(this,ReportActivity.class);
        startActivity(objIntent);
    }
}
