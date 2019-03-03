package com.example.siddharth.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

public class NavigationListener implements NavigationView.OnNavigationItemSelectedListener
{
    private Context cntx = null;
    private Activity activity = null;

    public NavigationListener(Activity activity)
    {
        this.cntx = activity.getApplicationContext();
        this.activity = activity;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();
        Intent objIntent = null;

        if (id == R.id.nav_gotowebsite) {

            objIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://csdfoundation.co.in/index.php"));
            cntx.startActivity(objIntent);
        }
        else if (id == R.id.nav_gallery) {
            objIntent = new Intent(this.activity, AboutUsActivity.class);
            cntx.startActivity(objIntent);
        }
        else if (id == R.id.nav_level_exam) {
            objIntent = new Intent(this.activity, ExamOptionActivity.class);
            objIntent.putExtra("exam_type", "Level");
            cntx.startActivity(objIntent);
        }
        else if (id == R.id.nav_practice_paper) {
            objIntent = new Intent(this.activity, ExamOptionActivity.class);
            objIntent.putExtra("exam_type", "Practice");
            cntx.startActivity(objIntent);
        }
        else if (id == R.id.nav_online_exam) {
            objIntent = new Intent(this.activity, ExamOptionActivity.class);
            objIntent.putExtra("exam_type", "Annual");
            cntx.startActivity(objIntent);
        }
        else if (id == R.id.nav_report_history) {
            objIntent = new Intent(this.activity, ReportHistoryActivity.class);
            cntx.startActivity(objIntent);
        }
        else if (id == R.id.nav_rule) {
            objIntent = new Intent(this.activity, RulesActivity.class);
            cntx.startActivity(objIntent);
        }
        else if (id == R.id.nav_faq) {

        }
        else if (menuItem.getTitle().equals("Contact Us")) {
            objIntent = new Intent(this.activity, ContactUsActivity.class);
            cntx.startActivity(objIntent);
        }


        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
