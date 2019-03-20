package com.example.siddharth.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class StudentLoginActivity extends AppCompatActivity {

    Intent objIntent;
    Spinner spinnerOfStudentNameView;
    EditText edittextStudentPassword;
    EditText editTextStudentName;
    List<String> listOfStudentName = new ArrayList<String>();
    Database objDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        InitVariables();
        InitSpinner();
    }

    public void OnClickSend(View view) {
    }

    public void OnClickAddNewUser(View view) {
        objIntent = new Intent(StudentLoginActivity.this, RegistrationFormActivity.class);
        startActivity(objIntent);
    }

    private void InitSpinner() {
        objDatabase = new Database(this);
        String strQuery = "SELECT * FROM *****TABLENAME*****";
        //listOfStudentName = objDatabase.getStudentName(strQuery);
        /*TESTING*/
        listOfStudentName.add("aaaaaa");
        listOfStudentName.add("bbbbbb");
        listOfStudentName.add("cccccc");
        /**/
        spinnerOfStudentNameView = (Spinner) findViewById(R.id.spinnerOfStudentName);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfStudentName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfStudentNameView.setAdapter(adapter);
    }

    private void InitVariables() {
        edittextStudentPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextStudentName = (EditText) findViewById(R.id.editTextUserName);
    }

}

