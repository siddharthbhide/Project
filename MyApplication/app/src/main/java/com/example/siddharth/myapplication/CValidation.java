package com.example.siddharth.myapplication;


import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class CValidation {

    private static CValidation objCValidation = null;

    public HashMap<String, String> objHashMapOfFranchiseName = new HashMap<String, String>();

    private CValidation() {

    }

    public static CValidation getInstance() {
        if (objCValidation == null) {
            objCValidation = new CValidation();
        }
        return objCValidation;
    }

    public boolean isStringEmpty(String objStr) {
        return objStr.isEmpty();
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

    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isSpinnerValueSelected(String spinnerText) {
        boolean bReturn = true;

        if (spinnerText.isEmpty()) {
            bReturn = false;
        }

        if (0 == spinnerText.compareToIgnoreCase("Select Item from List")) {
            bReturn = false;
        }

        return bReturn;
    }

    public void PostDataToWebService(String strUrl, String strDataString) {
        String strResult = "";
        try {

            URL objUrl = new URL(strUrl);

            HttpURLConnection objHttpURLConnection = (HttpURLConnection) objUrl.openConnection();
            //objHttpURLConnection.setReadTimeout(15000);
            //objHttpURLConnection.setConnectTimeout(15000);
            objHttpURLConnection.setRequestMethod("POST");
            objHttpURLConnection.setDoInput(true);
            objHttpURLConnection.setDoOutput(true);

            OutputStreamWriter objOutputStreamWriter = new OutputStreamWriter(objHttpURLConnection.getOutputStream());
            objOutputStreamWriter.write(strDataString);
            objOutputStreamWriter.flush();
            objOutputStreamWriter.close();
            int nResponseCode = objHttpURLConnection.getResponseCode();
            if (nResponseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(objHttpURLConnection.getInputStream()));
                while ((line = br.readLine()) != null) {
                    strResult += line;
                }
            } else {
                strResult = "";

            }
            objHttpURLConnection.disconnect();

        } catch (Exception objException) {
            Log.e("ERROR", objException.getMessage(), objException);
        }

        /*String strResult = "";
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
        }*/
    }

    public String GetFranchiseId(String FranchiseName) {
        String strFranchiseId = "";
        Iterator<String> keySetIterator = objHashMapOfFranchiseName.keySet().iterator();

        while (keySetIterator.hasNext()) {
            String strKey = keySetIterator.next();
            String strVale = objHashMapOfFranchiseName.get(strKey);
            if (0 == strVale.compareToIgnoreCase(FranchiseName)) {
                strFranchiseId = strKey;
                break;
            }
        }
        return strFranchiseId;
    }

}
