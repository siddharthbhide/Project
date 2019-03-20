package com.example.siddharth.myapplication;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ExamOptionActivity extends AppCompatActivity
{
    List<String> listOfCourse = new ArrayList<String>();
    List<String> listOfLevel = new ArrayList<String>();
    Spinner spinnerOfCourse;
    Spinner spinnerOfLevel;
    NavigationListener navigationLitener = null;
    String examType = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_option);
        InitSpinner();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        this.navigationLitener = new NavigationListener(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this.navigationLitener);
        Utility.getInstance().setHeaderMenu(navigationView, getApplicationContext());

        String examType = this.getIntent().getStringExtra("exam_type");
        this.examType = examType;
        if (examType.equalsIgnoreCase("Level"))
        {
            getSupportActionBar().setTitle(getResources().getString(R.string.competitionexam));
        }
        else if (examType.equalsIgnoreCase("Practice"))
        {
            getSupportActionBar().setTitle(getResources().getString(R.string.practice_paper));
        }
        else if (examType.equalsIgnoreCase("Annual"))
        {
            getSupportActionBar().setTitle(getResources().getString(R.string.regularexam));
        }
    }

    private void InitSpinner()
    {
        /*spinnerOfCourse = (Spinner) findViewById(R.id.spinnerCourse);
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
    */

    }

    public void validateExamCode(View view)
    {
        EditText txtCode = (EditText)findViewById(R.id.editExamCode);
        String examCode = txtCode.getText().toString();
        if (!examCode.isEmpty())
        {
            WebServiceManager.getInstance(getApplicationContext()).getExamDetails(getString(R.string.url_validate_exam), examCode, this);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please enter valid exam code", Toast.LENGTH_LONG).show();
        }
    }

    public void showExamInfo(ExamDetails examDetails)
    {
        if (examDetails != null)
        {
            Intent intent = new Intent(ExamOptionActivity.this, ExamInfoActivity.class);
            intent.putExtra("exam_details", examDetails);
            intent.putExtra("exam_type", this.examType);
            startActivity(intent);
        }
    }
}
