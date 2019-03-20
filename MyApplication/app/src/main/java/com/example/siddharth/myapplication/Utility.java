package com.example.siddharth.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.TextView;

public class Utility
{
    private static volatile Utility mInstance;

    private Utility()
    {
    }

    public static Utility getInstance()
    {
        if (mInstance == null)
        {
            synchronized (Utility.class)
            {
                if (mInstance == null)
                {
                    mInstance = new Utility();
                }
            }
        }
        return mInstance;
    }

    public void setHeaderMenu(NavigationView navView, Context cntx)
    {
        if (navView != null)
        {
            SharedPreferences shaPref = cntx.getSharedPreferences(cntx.getString(R.string.preferences), 0);
            String frname = "";
            String frUser = "";
            if (isFranchieseLoggedin(cntx))
            {
                frname  = shaPref.getString(cntx.getString( R.string.shared_preferences_franchisee_name),"Children Skill Development");
                frUser = shaPref.getString(cntx.getString( R.string.shared_preferences_franchisee_user),"CSD");
            }

            View header = navView.getHeaderView(0);
            TextView txtTitle = header.findViewById(R.id.navHeaderTitle);
            txtTitle.setText(frname);

            TextView txtSubTitle = header.findViewById(R.id.navHeaderSubTitle);
            txtSubTitle.setText(frUser);
        }
    }

    public boolean isFranchieseLoggedin(Context cntx)
    {
        boolean result = false;

        SharedPreferences prefs = cntx.getSharedPreferences(cntx.getString(R.string.preferences), 0);
        if (prefs != null)
        {
            String frId = prefs.getString(cntx.getString(R.string.shared_preferences_franchisee_id), null);
            if (frId != null)
            {
                result = true;
            }
        }

        return result;
    }
}
