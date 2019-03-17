package com.example.siddharth.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

public class NavigationListener implements NavigationView.OnNavigationItemSelectedListener
{
    private Context cntx = null;
    private Activity activity = null;
    NavigationView navigationView;

    public NavigationListener(Activity activity)
    {
        this.cntx = activity.getApplicationContext();
        this.activity = activity;
        show_hideItem();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();
        Intent objIntent = null;

        if (id == R.id.nav_home)
        {
            if (isFranchieseLoggedin())
            {
                objIntent = new Intent(this.activity, RegistrationFormActivity.class);
            }
            else
            {
                objIntent = new Intent(this.activity, LoginActivity.class);
            }

            objIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);/*Ref:- https://stackoverflow.com/questions/3918517/calling-startactivity-from-outside-of-an-activity-context*/
            cntx.startActivity(objIntent);
        }
        else if (id == R.id.nav_gotowebsite) {

            objIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://csdfoundation.co.in/index.php"));
            objIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);/*Ref:- https://stackoverflow.com/questions/3918517/calling-startactivity-from-outside-of-an-activity-context*/
            cntx.startActivity(objIntent);
        }
        else if (id == R.id.nav_gallery) {
            objIntent = new Intent(this.activity, AboutUsActivity.class);
            objIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);/*Ref:- https://stackoverflow.com/questions/3918517/calling-startactivity-from-outside-of-an-activity-context*/
            cntx.startActivity(objIntent);
        }
        else if (id == R.id.nav_level_exam) {
            objIntent = new Intent(this.activity, ExamOptionActivity.class);
            objIntent.putExtra("exam_type", "Level");
            objIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);/*Ref:- https://stackoverflow.com/questions/3918517/calling-startactivity-from-outside-of-an-activity-context*/
            cntx.startActivity(objIntent);
        }
        else if (id == R.id.nav_practice_paper) {
            objIntent = new Intent(this.activity, ExamOptionActivity.class);
            objIntent.putExtra("exam_type", "Practice");
            objIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);/*Ref:- https://stackoverflow.com/questions/3918517/calling-startactivity-from-outside-of-an-activity-context*/
            cntx.startActivity(objIntent);
        }
        else if (id == R.id.nav_online_exam) {
            objIntent = new Intent(this.activity, ExamOptionActivity.class);
            objIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);/*Ref:- https://stackoverflow.com/questions/3918517/calling-startactivity-from-outside-of-an-activity-context*/
            objIntent.putExtra("exam_type", "Annual");
            cntx.startActivity(objIntent);
        }
        else if (id == R.id.nav_report_history) {
            objIntent = new Intent(this.activity, ReportHistoryActivity.class);
            objIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);/*Ref:- https://stackoverflow.com/questions/3918517/calling-startactivity-from-outside-of-an-activity-context*/
            cntx.startActivity(objIntent);
        }
        else if (id == R.id.nav_rule) {
            objIntent = new Intent(this.activity, RulesActivity.class);
            objIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);/*Ref:- https://stackoverflow.com/questions/3918517/calling-startactivity-from-outside-of-an-activity-context*/
            cntx.startActivity(objIntent);
        }
        else if (id == R.id.nav_faq) {

        }
        else if (menuItem.getTitle().equals("Contact Us")) {
            objIntent = new Intent(this.activity, ContactUsActivity.class);
            objIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);/*Ref:- https://stackoverflow.com/questions/3918517/calling-startactivity-from-outside-of-an-activity-context*/
            cntx.startActivity(objIntent);
        }


        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean isFranchieseLoggedin()
    {
        boolean result = false;

        SharedPreferences prefs = this.cntx.getSharedPreferences(this.cntx.getString(R.string.preferences), 0);
        if (prefs != null)
        {
            String frId = prefs.getString(this.cntx.getString(R.string.shared_preferences_franchisee_id), null);
            if (frId != null)
            {
                result = true;
            }
        }

        return result;
    }

    public void show_hideItem()
    {
        boolean result = isFranchieseLoggedin();
        navigationView = (NavigationView) activity.findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_level_exam).setVisible(result);
        nav_Menu.findItem(R.id.nav_practice_paper).setVisible(result);
        nav_Menu.findItem(R.id.nav_online_exam).setVisible(result);
        nav_Menu.findItem(R.id.nav_report_history).setVisible(result);
        nav_Menu.findItem(R.id.nav_rule).setVisible(result);

    }
}
