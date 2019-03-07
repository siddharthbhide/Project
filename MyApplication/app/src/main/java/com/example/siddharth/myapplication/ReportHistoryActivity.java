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
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ReportHistoryActivity extends AppCompatActivity
{
    ListView listView = null;
    ExamHistoryAdaptor adaptor = null;
    NavigationListener navigationLitener = null;
    Database objDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_history);
        this.setTitle(R.string.report_history);

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
        objDatabase = new Database(getApplicationContext());
        getResultFromDatabase();
        /*ArrayList<HistoryDetails> items = new ArrayList<>();
        for (int i=0; i<10; i++)
        {
            ExamDetails examDetails = new ExamDetails();
            examDetails.setName("Exam Name "+i);
            examDetails.setCourse("Abacus");
            examDetails.setLevel("FT-L1");
            examDetails.setStartDate("01-03-2019");
            examDetails.setEndDate("10-03-2019");

            HistoryDetails details = new HistoryDetails();
            details.setExamDetails(examDetails);
            items.add(details);
        }
        adaptor = new ExamHistoryAdaptor(getApplicationContext(), items);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adaptor);*/
    }
    public void onClickDisplayReport(View view)
    {
        Intent objIntent = new Intent(this,ReportActivity.class);
        startActivity(objIntent);
    }

    void initExamHistoryAdaptor(ArrayList<ExamDetails> objExamDetails)
    {
        ArrayList<HistoryDetails> items = new ArrayList<>();
        for (int nIndex=0; nIndex < objExamDetails.size(); nIndex++)
        {
            HistoryDetails details = new HistoryDetails();
            details.setExamDetails(objExamDetails.get(nIndex));
            items.add(details);
        }
        adaptor = new ExamHistoryAdaptor(getApplicationContext(), items);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adaptor);
    }

    void getResultFromDatabase(){
        String strQuery = "Select * from " + getString(R.string.table_exam_details) +
                " where Is_Completed = 1";
        ArrayList<ExamDetails> objExamDetails = new ArrayList<>();
        objExamDetails = objDatabase.getExam_Details(strQuery);
        initExamHistoryAdaptor(objExamDetails);
    }
}
