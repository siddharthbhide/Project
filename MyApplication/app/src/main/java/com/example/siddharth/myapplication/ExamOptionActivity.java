package com.example.siddharth.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ExamOptionActivity extends AppCompatActivity
{
    List<String> listOfCourse = new ArrayList<String>();
    List<String> listOfLevel = new ArrayList<String>();
    Spinner spinnerOfCourse;
    Spinner spinnerOfLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_option);
        InitSpinner();
    }

    /*public void OnClickRegularExam(View view)
    {
        Intent objExample = new Intent(ExamOptionActivity.this,ExamActivity.class);
        startActivity(objExample);
    }

    public void OnClickCompetitionExam(View view)
    {
    }

    public void OnClickHomeWork(View view)
    {
        Intent objRegistrationFormActivity = new Intent(ExamOptionActivity.this,RegistrationFormActivity.class);
        startActivity(objRegistrationFormActivity);
    }*/

    public void onClickStart(View view)
    {
        /*Intent objRegistrationFormActivity = new Intent(ExamOptionActivity.this,RegistrationFormActivity.class);
        startActivity(objRegistrationFormActivity);*/
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
}
