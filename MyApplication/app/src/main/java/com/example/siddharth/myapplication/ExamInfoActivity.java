package com.example.siddharth.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ExamInfoActivity extends AppCompatActivity
{
    NavigationListener navigationLitener = null;
    ExamDetails examDetails = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_info);
        setTitle("Exam Information");
        ExamDetails examDetails = (ExamDetails) getIntent().getParcelableExtra("exam_details");

        if(examDetails != null)
        {
            this.examDetails = examDetails;
            TextView examName = (TextView) findViewById(R.id.txtExamName);
            examName.setText(examDetails.getName());

            TextView course = (TextView) findViewById(R.id.txtCourse);
            course.setText(examDetails.getCourse());

            TextView level = (TextView) findViewById(R.id.txtCourseLevel);
            level.setText(examDetails.getLevel());

            TextView validity = (TextView) findViewById(R.id.txtValidDate);
            validity.setText("Valid From :"+examDetails.getStartDate()+"  till :"+examDetails.getEndDate());
        }

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        this.navigationLitener = new NavigationListener(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this.navigationLitener);*/
    }

    public void onStartExam(View view)
    {
        if (this.examDetails != null)
        {
            WebServiceManager.getInstance(getApplicationContext()).getExamQuestion(getString(R.string.url_get_questions), this.examDetails.getId(), this);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please enter valid exam code", Toast.LENGTH_LONG).show();
        }
    }

    public void onReceiveQuestions(ArrayList<ExamQuestionDetails> questions)
    {
        if (questions != null)
        {
            Intent intent = new Intent(ExamInfoActivity.this, ExamActivity.class);
            intent.putExtra("questions", questions);
            intent.putExtra("exam_details", examDetails);
            startActivity(intent);
        }
    }
}
