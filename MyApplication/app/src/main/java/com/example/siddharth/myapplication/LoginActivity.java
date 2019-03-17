package com.example.siddharth.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class LoginActivity extends AppCompatActivity {

    List<String> listOfFranchiseName = new ArrayList<String>();
    String strResponse;
    Intent objIntent;
    EditText edittextFranchisPassword;
    Spinner spinnerOfFranchiseNameView;
    CValidation objCValidation = CValidation.getInstance();
    WebServiceManager objWebServiceManager;
    SharedPreferences prefs;
    EditText editTextUserName;
    NavigationListener navigationLitener = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        objWebServiceManager = WebServiceManager.getInstance(getApplicationContext());
        objWebServiceManager.showProgressBar(this);
        objWebServiceManager.getNetworkList(getString(R.string.url_network_list),this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        this.navigationLitener = new NavigationListener(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener( this.navigationLitener);
        this.setTitle(R.string.login_for_franchise);
        InitVariables();

    }

    public void initFranchiseNameSpinner(String objCSDNetworkList) {
        JSONArray jsonarray = null;
        objCValidation.objHashMapOfFranchiseName.clear();
        listOfFranchiseName.add("Select Item from List");
        try {
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

        InitSpinner();
        objWebServiceManager.stopProgressBar();
    }

    private void InitVariables() {
        edittextFranchisPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
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

    /*@Override
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
    }*/


    public void OnClickSend(View v) {
        CValidation objValidation = CValidation.getInstance();
        String strFranchiseName = spinnerOfFranchiseNameView.getSelectedItem().toString();

        if (!objValidation.isSpinnerValueSelected(strFranchiseName))
        {
            Toast.makeText(getApplicationContext(), "Select Franchise", Toast.LENGTH_SHORT).show();
            return;
        }

        if (objValidation.isStringEmpty(edittextFranchisPassword.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Correct Password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (objValidation.isStringEmpty(editTextUserName.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Correct User Name", Toast.LENGTH_SHORT).show();
            return;
        }

        String strFranchiseId = objValidation.GetFranchiseId(strFranchiseName);
        objWebServiceManager = WebServiceManager.getInstance(getApplicationContext());
        objWebServiceManager.getFranchiseLogin(getString(R.string.url_franchisee_login),editTextUserName.getText().toString(),edittextFranchisPassword.getText().toString(),strFranchiseId,this);
    }

    public  void getResponseAndCallActivity(String strResponse)
    {
        JSONObject jsonobject = null;
        String strResult = "";

        try {
            jsonobject = new JSONObject(strResponse);
            strResult = jsonobject.getString("result");
            if (strResult.equalsIgnoreCase("1")) {
                String strFranId = jsonobject.getString("id");
                /*Save data on globally */
                prefs = getApplicationContext().getSharedPreferences(getString(R.string.preferences), 0);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(getString( R.string.shared_preferences_franchisee_id), strFranId);
                editor.commit(); //important, otherwise it wouldn't save.

                Intent objIntent = new Intent(this, RegistrationFormActivity.class);
                startActivity(objIntent);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception objException) {
            objException.printStackTrace();
        }
    }
}
