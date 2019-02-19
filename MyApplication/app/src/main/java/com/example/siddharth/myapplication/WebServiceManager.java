package com.example.siddharth.myapplication;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;

public class WebServiceManager {

    public static WebServiceManager objWebServiceManagerInstance = null;
    StringRequest objStringRequest;
    private RequestQueue objRequestQueue;
    Context objContext;
    StudentDetails objStudentDetails;
    Exam_Details objExam_Details;
    ExamQuestions objExamQuestions;
    List<ExamQuestions> listOfExamQuestions = new ArrayList();
    String strResult;
    JSONObject jsonobject = null;

    private WebServiceManager(Context context) {
        objContext = context;
        objRequestQueue = getRequestQueue();
    }

    public static synchronized WebServiceManager getInstance(Context context) {
        if (objWebServiceManagerInstance == null) {
            objWebServiceManagerInstance = new WebServiceManager(context);
        }
        return objWebServiceManagerInstance;
    }

    public RequestQueue getRequestQueue() {
        if (objRequestQueue == null) {
            objRequestQueue = Volley.newRequestQueue(objContext.getApplicationContext());
        }
        return objRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request, String strTag) {
        request.setTag(TextUtils.isEmpty(strTag) ? TAG : strTag);
        getRequestQueue().add(request);
    }

    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(TAG);
        getRequestQueue().add(request);
    }

    public void cancelPendingRequests(Object tag) {
        if (objRequestQueue != null) {
            objRequestQueue.cancelAll(tag);
        }
    }



    public void getLoginStudentDetails(String strUrl, final String strUserName, final String strPassword) {

        objRequestQueue = WebServiceManager.getInstance(objContext).getRequestQueue();
        objStringRequest = new StringRequest(Request.Method.POST, strUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonResponse = new JSONArray(response);
                            CheckStudentLoginDetails(jsonResponse);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            //Post method parameters
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("uName", strUserName);
                params.put("passwd", strPassword);
                return params;
            }
        };
        objRequestQueue.add(objStringRequest);
    }

    private void CheckStudentLoginDetails(JSONArray jsonResponse) {

        jsonobject = null;
        strResult = "";
        try {
            jsonobject = jsonResponse.getJSONObject(0);
            strResult = jsonobject.getString("result");
            if (0 == strResult.compareToIgnoreCase("1")) {
                objStudentDetails.setId(jsonobject.getString("id"));
                objStudentDetails.setUserName(jsonobject.getString("name"));
                objStudentDetails.setDateOfBirth(jsonobject.getString("dob"));
                objStudentDetails.setAdmissionDate(jsonobject.getString("admissionDate"));
                objStudentDetails.setDateAdded(jsonobject.getString("dateAdded"));
                objStudentDetails.setFranId(jsonobject.getString("franId"));
                objStudentDetails.setEmail(jsonobject.getString("email"));
                objStudentDetails.setCourseLevel(jsonobject.getString("courseLevel"));
                objStudentDetails.setCourseId(jsonobject.getString("courseId"));
                objStudentDetails.setStd(jsonobject.getString("std"));
                objStudentDetails.setSchool(jsonobject.getString("school"));
                objStudentDetails.setMobileNo(jsonobject.getString("mobileNo"));
                objStudentDetails.setMotherOccupation(jsonobject.getString("motherOccupation"));
                objStudentDetails.setFatherOccupation(jsonobject.getString("fatherOccupation"));
                objStudentDetails.setMotherName(jsonobject.getString("motherName"));
                objStudentDetails.setAddress(jsonobject.getString("address"));
                objStudentDetails.setFatherName(jsonobject.getString("fatherName"));
                objStudentDetails.setSex(jsonobject.getString("sex"));
            }

        } catch (Exception objException) {
            objException.printStackTrace();
        }
    }

    public void getExamDetails(String strUrl, final String strExam_Code) {

        objRequestQueue = WebServiceManager.getInstance(objContext).getRequestQueue();
        objStringRequest = new StringRequest(Request.Method.POST, strUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonResponse = new JSONArray(response);
                            CheckExam_CodeDetails(jsonResponse);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            //Post method parameters
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("exam_code", strExam_Code);
                return params;
            }
        };
        objRequestQueue.add(objStringRequest);
    }

    private void CheckExam_CodeDetails(JSONArray jsonResponse) {

        jsonobject = null;
        strResult = "";
        try {
            jsonobject = jsonResponse.getJSONObject(0);
            strResult = jsonobject.getString("result");
            if (0 == strResult.compareToIgnoreCase("1")) {
                objExam_Details.setId(jsonobject.getString("id"));
                objExam_Details.setName(jsonobject.getString("name"));
                objExam_Details.setCourse(jsonobject.getString("course"));
                objExam_Details.setLevel(jsonobject.getString("level"));
                objExam_Details.setType(jsonobject.getString("type"));
                objExam_Details.setStartDate(jsonobject.getString("startDate"));
                objExam_Details.setEndDate(jsonobject.getString("endDate"));
                objExam_Details.setIsCompleted(jsonobject.getString("isCompleted"));
            }
        } catch (Exception objException)

        {
            objException.printStackTrace();
        }

    }

    public void getExamQuestion(String strUrl, final String strExamId) {

        objRequestQueue = WebServiceManager.getInstance(objContext).getRequestQueue();
        objStringRequest = new StringRequest(Request.Method.POST, strUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonResponse = new JSONArray(response);
                            CheckExamQuestion(jsonResponse);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            //Post method parameters
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("examId", strExamId);
                return params;
            }
        };
        objRequestQueue.add(objStringRequest);
    }

    private void CheckExamQuestion(JSONArray jsonResponse) {

        jsonobject = null;
        strResult = "";

        try {
            for (int nIndex = 0; nIndex < jsonResponse.length(); nIndex++) {

                jsonobject = jsonResponse.getJSONObject(nIndex);
                objExamQuestions.setId(jsonobject.getString("id"));
                objExamQuestions.setDescription(jsonobject.getString("description"));
                objExamQuestions.setOptionA(jsonobject.getString("optionA"));
                objExamQuestions.setOptionB(jsonobject.getString("optionB"));
                objExamQuestions.setOptionC(jsonobject.getString("optionC"));
                objExamQuestions.setOptionD(jsonobject.getString("optionD"));
                objExamQuestions.setCourse(jsonobject.getString("course"));
                objExamQuestions.setAnswer(jsonobject.getString("answer"));

                listOfExamQuestions.add(objExamQuestions);
            }

        } catch (Exception objException) {
            objException.printStackTrace();
        }
    }
}

