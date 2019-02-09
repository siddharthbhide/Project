package com.example.siddharth.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RegistrationFormActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    CValidation objValidation;
    EditText editStudentname;
    EditText editRollno;
    EditText editFatherName;
    EditText editResidentialAddress;
    EditText editOccupation;
    EditText editMotherName;
    EditText editMobileNumber;
    EditText editEmail;
    EditText editSchoolName;
    EditText editStandard;
    EditText editUserName;
    EditText editUserPassword;
    Spinner spinnerCourseName;
    Spinner spinnerCourseLevel;
    Spinner spinnerSex;
    DatePicker datePickerAdmissionDate;
    DatePicker datePickerDOB;
    List <String> listOfSex = new ArrayList<>();
    List<String> listOfCourse = new ArrayList<>();
    List<String> listOfCourseLevel = new ArrayList<>();
    String strPostdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        objValidation = CValidation.getInstance();
        initFields();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.registration_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void OnClickSend(View view)
    {

        /*if(false == ValidateString ())
            return;
        sendInformationToServer();*/
        testsendInformationToServer();
    }

    private boolean ValidateString() {
        boolean bReturn = false;
        if (objValidation.isStringEmpty(editStudentname.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Enter Name", Toast.LENGTH_SHORT).show();
            return bReturn;
        }
        if (objValidation.isStringEmpty(editRollno.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Enter Roll no", Toast.LENGTH_SHORT).show();
            return bReturn;
        }
        if (objValidation.isStringEmpty(editFatherName.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Enter Father Name", Toast.LENGTH_SHORT).show();
            return bReturn;
        }
        if (objValidation.isStringEmpty(editStudentname.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Enter Name", Toast.LENGTH_SHORT).show();
            return bReturn;
        }
        if (objValidation.isStringEmpty(editResidentialAddress.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Enter Residential Address", Toast.LENGTH_SHORT).show();
            return bReturn;
        }
        if (objValidation.isStringEmpty(editOccupation.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Enter Occupation", Toast.LENGTH_SHORT).show();
            return bReturn;
        }
        if (objValidation.isStringEmpty(editMotherName.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Enter Mother Name", Toast.LENGTH_SHORT).show();
            return bReturn;
        }
        if (objValidation.isStringEmpty(editMobileNumber.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Enter Mobile Number", Toast.LENGTH_SHORT).show();
            return bReturn;
        }
        if (objValidation.isStringEmpty(editEmail.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_SHORT).show();
            return bReturn;
        }
        if (objValidation.isStringEmpty(editSchoolName.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Enter School Name", Toast.LENGTH_SHORT).show();
            return bReturn;
        }
        if (objValidation.isStringEmpty(editStandard.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Enter Standard", Toast.LENGTH_SHORT).show();
            return bReturn;
        }
        if (objValidation.isNumeric(editMobileNumber.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Enter Valid Phone Number", Toast.LENGTH_SHORT).show();
            return bReturn;
        }
        if (10 != objValidation.getLength(editMobileNumber.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Enter Valid Phone Number", Toast.LENGTH_SHORT).show();
            return bReturn;
        }
        if (!objValidation.emailValidator(editEmail.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Enter valid Email Id", Toast.LENGTH_SHORT).show();
            return bReturn;
        }
        if (!objValidation.emailValidator(editEmail.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Enter valid Email Id", Toast.LENGTH_SHORT).show();
            return bReturn;
        }

        if (!objValidation.emailValidator(editUserName.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Enter valid User Name", Toast.LENGTH_SHORT).show();
            return bReturn;
        }

        if (!objValidation.emailValidator(editUserPassword.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Enter valid Password", Toast.LENGTH_SHORT).show();
            return bReturn;
        }

        if (!objValidation.isSpinnerValueSelected(spinnerSex.getSelectedItem().toString()))
        {
            Toast.makeText(getApplicationContext(), "Select Sex", Toast.LENGTH_SHORT).show();
            return bReturn;
        }

        if (!objValidation.isSpinnerValueSelected(spinnerCourseName.getSelectedItem().toString()))
        {
            Toast.makeText(getApplicationContext(), "Select Course Name", Toast.LENGTH_SHORT).show();
            return bReturn;
        }

        if (!objValidation.isSpinnerValueSelected(spinnerCourseLevel.getSelectedItem().toString()))
        {
            Toast.makeText(getApplicationContext(), "Select Course Level", Toast.LENGTH_SHORT).show();
            return bReturn;
        }

        bReturn = true;
        return bReturn;
    }

    private void initFields()
    {
        editStudentname = (EditText) findViewById(R.id.editStudentname);
        editRollno = (EditText) findViewById(R.id.editRollno);
        editFatherName = (EditText) findViewById(R.id.editFatherName);
        editResidentialAddress = (EditText) findViewById(R.id.editResidentialAddress);
        editOccupation = (EditText) findViewById(R.id.editOccupation);
        editMotherName = (EditText) findViewById(R.id.editMotherName);
        editMobileNumber = (EditText) findViewById(R.id.editMobileNumber);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editSchoolName = (EditText) findViewById(R.id.editSchoolName);
        editStandard = (EditText) findViewById(R.id.editUserName);
        editUserName = (EditText) findViewById(R.id.editPassword);
        editUserPassword = (EditText) findViewById(R.id.editStandard);
        spinnerCourseName = (Spinner) findViewById(R.id.spinnerCourseName);
        spinnerCourseLevel = (Spinner) findViewById(R.id.spinnerCourseLevel);
        spinnerSex = (Spinner) findViewById(R.id.spinnerSex);
        datePickerAdmissionDate = (DatePicker) findViewById(R.id.dateAdmissionDate);
        datePickerDOB= (DatePicker) findViewById(R.id.dateDateOfBirth);

        listOfSex.add("Select Item from List");
        listOfSex.add("Male");
        listOfSex.add("Female");
        ArrayAdapter adapterOfSex = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfSex);
        adapterOfSex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSex.setAdapter(adapterOfSex);

        listOfCourse.add("Select Item from List");
        listOfCourse.add("Abacus");
        listOfCourse.add("Vedic Maths");
        listOfCourse.add("Smart Writing");
        listOfCourse.add("Smart English");
        listOfCourse.add("Mind Activation");
        ArrayAdapter adapterOfCourse = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfCourse);
        adapterOfCourse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourseName.setAdapter(adapterOfCourse);


        listOfCourseLevel.add("Select Item from List");
        listOfCourseLevel.add("1");
        listOfCourseLevel.add("2");
        listOfCourseLevel.add("3");
        listOfCourseLevel.add("4");
        listOfCourseLevel.add("5");
        ArrayAdapter adapterOfCourseLevel = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfCourseLevel);
        adapterOfCourse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourseLevel.setAdapter(adapterOfCourseLevel);

       /* listOfFranchiseName.add("Select Item from List");
        listOfFranchiseName.add("CSDFOUNDATION");
        listOfFranchiseName.add("Shreyas Abacus Academy");
        listOfFranchiseName.add("Desai Abacus Academy");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfFranchiseName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfFranchiseNameView.setAdapter(adapter);
*/

    }

    private void testsendInformationToServer()
    {

        try {
            long intSpinnerSex = spinnerSex.getSelectedItemId()-1;
            strPostdata = URLEncoder.encode("sex", "UTF-8")
                    + "=" + URLEncoder.encode("0", "UTF-8");

            strPostdata += "&" + URLEncoder.encode("fatherName", "UTF-8") + "="
                    + URLEncoder.encode("Test fatherName", "UTF-8");

            strPostdata += "&" + URLEncoder.encode("address", "UTF-8") + "="
                    + URLEncoder.encode("Test address", "UTF-8");

            strPostdata += "&" + URLEncoder.encode("motherName", "UTF-8") + "="
                    + URLEncoder.encode("Test motherName", "UTF-8");

            strPostdata += "&" + URLEncoder.encode("fatherOccupation", "UTF-8") + "="
                    + URLEncoder.encode("Test fatherOccupation", "UTF-8");

            strPostdata += "&" + URLEncoder.encode("motherOccupation", "UTF-8") + "="
                    + URLEncoder.encode("Test motherOccupation", "UTF-8");

            strPostdata += "&" + URLEncoder.encode("mobileNo", "UTF-8") + "="
                    + URLEncoder.encode("8087614678", "UTF-8");

            strPostdata += "&" + URLEncoder.encode("motherOccupation", "UTF-8") + "="
                    + URLEncoder.encode("Test motherOccupation", "UTF-8");

            strPostdata += "&" + URLEncoder.encode("school", "UTF-8") + "="
                    + URLEncoder.encode("AB High School", "UTF-8");

            strPostdata += "&" + URLEncoder.encode("std", "UTF-8") + "="
                    + URLEncoder.encode("5", "UTF-8");

            strPostdata += "&" + URLEncoder.encode("courseId", "UTF-8") + "="
                    + URLEncoder.encode("0", "UTF-8");

            strPostdata += "&" + URLEncoder.encode("courseLevel", "UTF-8") + "="
                    + URLEncoder.encode("FT-L1", "UTF-8");

            strPostdata += "&" + URLEncoder.encode("email", "UTF-8") + "="
                    + URLEncoder.encode("smart.ios.developer@gmail.com", "UTF-8");

            strPostdata += "&" + URLEncoder.encode("franId", "UTF-8") + "="
                    + URLEncoder.encode("63", "UTF-8");

            String strCurrentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            strPostdata += "&" + URLEncoder.encode("dateAdded", "UTF-8") + "="
                    + URLEncoder.encode(strCurrentDate, "UTF-8");


            StringBuilder stringBuilderAdmissionDate = new StringBuilder();            //month is 0 based
            stringBuilderAdmissionDate.append("06-01-2019");
            strPostdata += "&" + URLEncoder.encode("admissionDate", "UTF-8") + "="
                    + URLEncoder.encode(stringBuilderAdmissionDate.toString(), "UTF-8");

            strPostdata += "&" + URLEncoder.encode("name", "UTF-8") + "="
                    + URLEncoder.encode("Test address", "UTF-8");

            StringBuilder stringBuilderDOB = new StringBuilder();
            stringBuilderDOB.append("07-02-2019");

            strPostdata += "&" + URLEncoder.encode("dob", "UTF-8") + "="
                    + URLEncoder.encode(stringBuilderDOB.toString(), "UTF-8");

            strPostdata += "&" + URLEncoder.encode("uName", "UTF-8") + "="
                    + URLEncoder.encode("Siddharth", "UTF-8");

            strPostdata += "&" + URLEncoder.encode("passwd", "UTF-8") + "="
                    + URLEncoder.encode("siddharth", "UTF-8");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        objValidation.PostDataToWebService(getString(R.string.url_save_update_student_details),strPostdata.toString());
                    } catch (Exception objException) {

                    }
                }
            }).start();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }
        catch (Exception objException)
        {
                Log.e("ERROR", objException.getMessage(), objException);
        }
    }

    private void sendInformationToServer()
    {
        try {
            long intSpinnerSex = spinnerSex.getSelectedItemId()-1;

            strPostdata = URLEncoder.encode("sex", "UTF-8")
                    + "=" + URLEncoder.encode(Long.toString(intSpinnerSex), "UTF-8");

            strPostdata += "&" + URLEncoder.encode("fatherName", "UTF-8") + "="
                    + URLEncoder.encode(editFatherName.getText().toString(), "UTF-8");

            strPostdata += "&" + URLEncoder.encode("address", "UTF-8") + "="
                    + URLEncoder.encode(editFatherName.getText().toString(), "UTF-8");

            strPostdata += "&" + URLEncoder.encode("motherName", "UTF-8") + "="
                    + URLEncoder.encode(editMotherName.getText().toString(), "UTF-8");

            strPostdata += "&" + URLEncoder.encode("fatherOccupation", "UTF-8") + "="
                    + URLEncoder.encode(editOccupation.getText().toString(), "UTF-8");

            strPostdata += "&" + URLEncoder.encode("motherOccupation", "UTF-8") + "="
                    + URLEncoder.encode(editOccupation.getText().toString(), "UTF-8");

            strPostdata += "&" + URLEncoder.encode("mobileNo", "UTF-8") + "="
                    + URLEncoder.encode(editMobileNumber.getText().toString(), "UTF-8");

            strPostdata += "&" + URLEncoder.encode("motherOccupation", "UTF-8") + "="
                    + URLEncoder.encode(editOccupation.getText().toString(), "UTF-8");

            strPostdata += "&" + URLEncoder.encode("school", "UTF-8") + "="
                    + URLEncoder.encode(editSchoolName.getText().toString(), "UTF-8");

            strPostdata += "&" + URLEncoder.encode("std", "UTF-8") + "="
                    + URLEncoder.encode(editStandard.getText().toString(), "UTF-8");

            strPostdata += "&" + URLEncoder.encode("courseId", "UTF-8") + "="
                    + URLEncoder.encode(spinnerCourseName.getSelectedItem().toString(), "UTF-8");

            strPostdata += "&" + URLEncoder.encode("courseLevel", "UTF-8") + "="
                    + URLEncoder.encode(spinnerCourseLevel.getSelectedItem().toString(), "UTF-8");

            strPostdata += "&" + URLEncoder.encode("email", "UTF-8") + "="
                    + URLEncoder.encode(editEmail.getText().toString(), "UTF-8");

          /*  strPostdata += "&" + URLEncoder.encode("franId", "UTF-8") + "="
                    + URLEncoder.encode(editOccupation.getText().toString(), "UTF-8");*/

            String strCurrentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            strPostdata += "&" + URLEncoder.encode("dateAdded", "UTF-8") + "="
                    + URLEncoder.encode(strCurrentDate, "UTF-8");


            StringBuilder stringBuilderAdmissionDate = new StringBuilder();            //month is 0 based
            stringBuilderAdmissionDate.append(String.format("%02d",datePickerAdmissionDate.getDayOfMonth())+"-");
            stringBuilderAdmissionDate.append(String.format("%02d",datePickerAdmissionDate.getMonth() + 1)+"-");
            stringBuilderAdmissionDate.append(datePickerAdmissionDate.getYear());
            strPostdata += "&" + URLEncoder.encode("admissionDate", "UTF-8") + "="
                    + URLEncoder.encode(stringBuilderAdmissionDate.toString(), "UTF-8");

            strPostdata += "&" + URLEncoder.encode("name", "UTF-8") + "="
                    + URLEncoder.encode(editStudentname.getText().toString(), "UTF-8");

            StringBuilder stringBuilderDOB = new StringBuilder();
            stringBuilderDOB.append(String.format("%02d",datePickerDOB.getDayOfMonth())+"/");
            stringBuilderDOB.append(String.format("%02d",datePickerDOB.getMonth() + 1)+"/");
            stringBuilderDOB.append(datePickerDOB.getYear());
            strPostdata += "&" + URLEncoder.encode("dob", "UTF-8") + "="
                    + URLEncoder.encode(stringBuilderDOB.toString(), "UTF-8");

            strPostdata += "&" + URLEncoder.encode("uName", "UTF-8") + "="
                    + URLEncoder.encode(editUserName.getText().toString(), "UTF-8");

            strPostdata += "&" + URLEncoder.encode("passwd", "UTF-8") + "="
                    + URLEncoder.encode(editUserPassword.getText().toString(), "UTF-8");


            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        objValidation.PostDataToWebService(getString(R.string.url_save_update_student_details),strPostdata.toString());
                    } catch (Exception objException) {

                    }
                }
            }).start();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        catch (Exception objException)
        {
            Log.e("ERROR", objException.getMessage(), objException);
        }

    }
}
