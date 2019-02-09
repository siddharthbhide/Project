package com.example.siddharth.myapplication;


import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CValidation {

    private static CValidation objCValidation = null;

    private CValidation ()
    {

    }

    public boolean isStringEmpty(String objStr)
    {
        return objStr.isEmpty();
    }

    public static CValidation getInstance()
    {
        if(objCValidation == null )
        {
            objCValidation = new CValidation();
        }
        return objCValidation;
    }
    public boolean isNumeric(String objStr) {
        try {
            double d = Double.parseDouble(objStr);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public int getLength(String objStr) {

        return objStr.length();
    }

    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isSpinnerValueSelected(String spinnerText)
    {
        boolean bReturn = true;

        if (spinnerText.isEmpty())
        {
            bReturn = false;
        }

        if (0 != spinnerText.compareToIgnoreCase("Select Item from List"))
        {
            bReturn = false;
        }

        return bReturn;
    }

    public void PostDataToWebService(String strUrl,String strDataString)
    {
        String strResult = "";
        try {
            URL objUrl = new URL(strUrl);
            URLConnection conn = objUrl.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter objOutputStreamWriter = new OutputStreamWriter(conn.getOutputStream());
            objOutputStreamWriter .write( strDataString );
            objOutputStreamWriter .flush();

            BufferedReader reader=null;
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder objStringBuilder = new StringBuilder();
            String line = null;

            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                objStringBuilder.append(line + "\n");
            }
            strResult = objStringBuilder.toString();

        }catch (Exception objException){
            Log.e("ERROR", objException.getMessage(), objException);
        }
    }

}
