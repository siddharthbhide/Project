package com.example.siddharth.myapplication;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class ReportActivity extends AppCompatActivity
{

    NavigationListener navigationLitener = null;
    HistoryDetails history = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        this.setTitle(R.string.result_details);

        history = (HistoryDetails) getIntent().getParcelableExtra("historyRecord");

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

        setupView();
    }

    void setupView()
    {
        TextView examName = (TextView)findViewById(R.id.txtExamName);
        TextView course = (TextView)findViewById(R.id.txtCourse);
        TextView courseLevel = (TextView)findViewById(R.id.txtCourseLevel);
        TextView examDate = (TextView)findViewById(R.id.txtExamDate);
        TextView time = (TextView)findViewById(R.id.txtTime);
        TextView questions = (TextView)findViewById(R.id.txtQuestions);
        TextView attempted = (TextView)findViewById(R.id.txtAttempted);
        TextView correct = (TextView)findViewById(R.id.txtCorrect);
        TextView nonAttempted = (TextView)findViewById(R.id.txtNonAttempted);
        TextView incorrect = (TextView)findViewById(R.id.txtIncorrect);

        if (history != null)
        {
            examName.setText(this.history.getExamDetails().getName());
            course.setText(this.history.getExamDetails().getCourse());
            courseLevel.setText(this.history.getExamDetails().getLevel());
            examDate.setText(this.history.getResultDetails().getExamDate());
            time.setText(this.history.getResultDetails().getTotalTime());
            questions.setText(this.history.getResultDetails().getTotalQuestion());

            attempted.setText(this.history.getResultDetails().getAttemptedQuestion());
            correct.setText(this.history.getResultDetails().getCorrectAnswere());
            nonAttempted.setText(this.history.getResultDetails().getNoAttemptedQuestion());
            incorrect.setText(this.history.getResultDetails().getWrongAnswere());

        }
    }
}
