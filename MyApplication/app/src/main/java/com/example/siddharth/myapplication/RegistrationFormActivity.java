package com.example.siddharth.myapplication;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RegistrationFormActivity extends AppCompatActivity {
    CValidation objValidation;
    EditText editStudentname;
    EditText editRollno;
    EditText editFatherName;
    EditText editResidentialAddress;
    EditText editFatherOccupation;
    EditText editMotherOccupation;
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
    EditText datePickerAdmissionDate;
    EditText datePickerDOB;
    Spinner spinnerOfFranchiseNameView;
    List <String> listOfSex = new ArrayList<>();
    List<String> listOfCourse = new ArrayList<>();
    List<String> listOfCourseLevel = new ArrayList<>();
    WebServiceManager objWebServiceManager;

    NavigationListener navigationLitener = null;
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        this.navigationLitener = new NavigationListener(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener( this.navigationLitener);
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

    public void OnClickSend(View view)
    {

        if(false == ValidateString ())
            return;

        objWebServiceManager = WebServiceManager.getInstance(getApplicationContext());
        objWebServiceManager.sendStudentDetailsToServer(getString(R.string.url_save_update_student_details),this);

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
        if (objValidation.isStringEmpty(editFatherOccupation.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Enter Father Occupation", Toast.LENGTH_SHORT).show();
            return bReturn;
        }
        if (objValidation.isStringEmpty(editMotherOccupation.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Enter Mother Occupation", Toast.LENGTH_SHORT).show();
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
        if (!objValidation.isNumeric(editMobileNumber.getText().toString()))
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
        editFatherOccupation = (EditText) findViewById(R.id.editFatherOccupation);
        editMotherOccupation = (EditText) findViewById(R.id.editMotherOccupation);
        editMotherName = (EditText) findViewById(R.id.editMotherName);
        editMobileNumber = (EditText) findViewById(R.id.editMobileNumber);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editSchoolName = (EditText) findViewById(R.id.editSchoolName);
        editStandard = (EditText) findViewById(R.id.editStandard);
        spinnerCourseName = (Spinner) findViewById(R.id.spinnerCourseName);
        spinnerCourseLevel = (Spinner) findViewById(R.id.spinnerCourseLevel);
        spinnerOfFranchiseNameView = (Spinner) findViewById(R.id.spinnerOfFranchiseName);
        spinnerSex = (Spinner) findViewById(R.id.spinnerSex);
        datePickerAdmissionDate = (EditText) findViewById(R.id.dateAdmissionDate);
        datePickerDOB= (EditText) findViewById(R.id.dateDateOfBirth);

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

    }


    public Map<String, String> getStudentInformation()
    {
        Map<String, String> params = new HashMap<String, String>();

        try {

            long intSpinnerSex = spinnerSex.getSelectedItemId()-1;
            params.put("sex", Long.toString(intSpinnerSex));

            params.put("fatherName", editFatherName.getText().toString());
            params.put("address", editResidentialAddress.getText().toString());
            params.put("motherName", editMotherName.getText().toString());
            params.put("fatherOccupation", editFatherOccupation.getText().toString());
            params.put("motherOccupation", editMotherOccupation.getText().toString());
            params.put("mobileNo", editMobileNumber.getText().toString());
            params.put("school", editSchoolName.getText().toString());
            params.put("std", editSchoolName.getText().toString());
            params.put("courseId", spinnerCourseName.getSelectedItem().toString());
            params.put("courseLevel", spinnerCourseLevel.getSelectedItem().toString());
            params.put("email", editEmail.getText().toString());

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String strFranId = prefs.getString(getString( R.string.shared_preferences_franchisee_id), "0");
            strFranId = "63";
            params.put("franId", strFranId);

            String strCurrentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            params.put("dateAdded", strCurrentDate);

            params.put("admissionDate", datePickerAdmissionDate.getText().toString());

            params.put("name", editStudentname.getText().toString());

            params.put("dob", datePickerDOB.getText().toString());

        }
        catch (Exception objException)
        {
            objException.printStackTrace();
        }
        return params;
    }


    public void isStudedntInfromationUpdated(JSONObject jsonobject)
    {
        try
        {

            String strResult = jsonobject.getString("result");
            if (0 != strResult.compareToIgnoreCase("0")) {
                /*check is student register on first time
                * if he is register 1st time then save his ID and display username and pssword
                * else do nothing*/
                prefs = getApplicationContext().getSharedPreferences(getString(R.string.preferences), 0);
                String strStudentId = prefs.getString(getString( R.string.shared_preferences_student_id), "000");
                if(0 == strStudentId.compareToIgnoreCase("000")) {
                    String studentId = jsonobject.getString("id");
                    String userName = jsonobject.getString("userName");
                    String passwd = jsonobject.getString("passwd");
                    /*Save data on globally */

                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(getString(R.string.shared_preferences_student_id), studentId);
                    editor.putString(getString(R.string.shared_preferences_student_uname), userName);
                    editor.putString(getString(R.string.shared_preferences_student_passwd), passwd);
                    editor.commit();
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception objException) {
            objException.printStackTrace();
        }

    }

    public void onAdmissionDateClick(View view)
    {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);



        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        DecimalFormat mFormat= new DecimalFormat("00");
                        String day = mFormat.format(Double.valueOf(dayOfMonth));
                        datePickerAdmissionDate.setText(day + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void onBirthDateClick(View view)
    {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        DecimalFormat mFormat= new DecimalFormat("00");
                        String day = mFormat.format(Double.valueOf(dayOfMonth));
                        datePickerDOB.setText(day + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

}
