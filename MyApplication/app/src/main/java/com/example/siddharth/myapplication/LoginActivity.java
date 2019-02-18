package com.example.siddharth.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Editable;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.makeText;

public class LoginActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<String> listOfFranchiseName = new ArrayList<String>();
    //private Spinner objSpinnerFranchiseName;
    Intent objIntent;
    EditText edittextFranchisPassword;
    Spinner spinnerOfFranchiseNameView;
    CValidation objCValidation = CValidation.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
        this.setTitle(R.string.login_for_franchise);
        InitVariables();
/*        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String objCSDNetworkList;
                    objCSDNetworkList = getCSDNetworkList();
                    initFranchiseNameSpinner(objCSDNetworkList);
                } catch (Exception objException) {
                    initFranchiseNameSpinner("");
                }
            }
        }).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //InitSpinner();
    }

    private void initFranchiseNameSpinner(String objCSDNetworkList) {
        JSONArray jsonarray = null;
        objCValidation.objHashMapOfFranchiseName.clear();
        try {
            listOfFranchiseName.add("Select Item from List");
            jsonarray = new JSONArray(objCSDNetworkList);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String strTitle = jsonobject.getString("title");
                String strId = jsonobject.getString("id");
                if(strTitle != null) {
                    listOfFranchiseName.add(strTitle);
                    objCValidation.objHashMapOfFranchiseName.put(strId,strTitle);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void InitVariables() {
        edittextFranchisPassword = (EditText) findViewById(R.id.editTextPassword);
        //Spinner spinnerOfFranchiseNameView = (Spinner) findViewById(R.id.spinnerOfFranchiseName);
    }

    protected String getCSDNetworkList() {
        try {
            URL url = new URL(getString(R.string.url_network_list));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    private void InitSpinner()
    {
        spinnerOfFranchiseNameView = (Spinner) findViewById(R.id.spinnerOfFranchiseName);
        listOfFranchiseName.add("Select Item from List");
        listOfFranchiseName.add("CSDFOUNDATION");
        listOfFranchiseName.add("Shreyas Abacus Academy");
        listOfFranchiseName.add("Desai Abacus Academy");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfFranchiseName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfFranchiseNameView.setAdapter(adapter);



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
        getMenuInflater().inflate(R.menu.login, menu);
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

        if (id == R.id.nav_gotowebsite) {

            objIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://csdfoundation.co.in/index.php"));
            startActivity(objIntent);
        }
        if (id == R.id.nav_camera) {
          /* objIntent = new Intent(this,RegistrationFormActivity.class);
            startActivity(objIntent);*/
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            objIntent = new Intent(this, AboutUsActivity.class);
            startActivity(objIntent);
        } else if (id == R.id.nav_slideshow) {
            /*objIntent = new Intent(this,ContactUsActivity.class);
            startActivity(objIntent);*/
        } else if (id == R.id.nav_manage) {
            /*objIntent = new Intent(this,ContactUsActivity.class);
            startActivity(objIntent);*/
        } else if (id == R.id.nav_share) {
            /*objIntent = new Intent(this,ContactUsActivity.class);
            startActivity(objIntent);*/
        } else if (id == R.id.nav_send) {
           /* objIntent = new Intent(this,ContactUsActivity.class);
            startActivity(objIntent);*/
        } else if (id == R.id.nav_level_exam) {
            objIntent = new Intent(this, ExamOptionActivity.class);
            startActivity(objIntent);
        } else if (id == R.id.nav_practice_paper) {
            objIntent = new Intent(this, ExamOptionActivity.class);
            startActivity(objIntent);
        } else if (id == R.id.nav_report_history) {
            objIntent = new Intent(this, ReportHistoryActivity.class);
            startActivity(objIntent);
        }
        if (id == R.id.nav_result) {
            objIntent = new Intent(this, RegistrationFormActivity.class);
            startActivity(objIntent);
            // Handle the camera action
        } else if (item.getTitle().equals("Contact Us")) {
            objIntent = new Intent(this, ContactUsActivity.class);
            startActivity(objIntent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void OnClickSend(View v) {
        CValidation objValidation = CValidation.getInstance();
        String strFranchiseName = spinnerOfFranchiseNameView.getSelectedItem().toString();

        if (objValidation.isSpinnerValueSelected(strFranchiseName))
        {
            Toast.makeText(getApplicationContext(), "Select Franchise", Toast.LENGTH_SHORT).show();
            return;
        }

        if (objValidation.isStringEmpty(edittextFranchisPassword.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Correct Password", Toast.LENGTH_SHORT).show();
            return;
        }

        /*if (edittextFranchisPassword.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Enter Correct Password", Toast.LENGTH_SHORT).show();
            return;
        }*/

        Intent objIntent = new Intent(this, ExamOptionActivity.class);
        startActivity(objIntent);
    }
}