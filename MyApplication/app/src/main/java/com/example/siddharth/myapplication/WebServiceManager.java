package com.example.siddharth.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.Toast;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;

public class WebServiceManager {

    public static WebServiceManager objWebServiceManagerInstance = null;
    StringRequest objStringRequest;
    private RequestQueue objRequestQueue;
    Context objContext;
    StudentDetails objStudentDetails;
    ExamDetails objExam_Details;
    List<ExamQuestionDetails> listOfExamQuestions = new ArrayList();
    String strResult;
    JSONObject jsonobject = null;
    private ProgressDialog objProgress = null;
    String strError = "Error In Network Connection.";

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


    public void getLoginStudentDetails(String strUrl, final String strUserName, final String strPassword, final RegistrationFormActivity activity) {
        showProgressBar(activity);
        objRequestQueue = WebServiceManager.getInstance(objContext).getRequestQueue();
        objStringRequest = new StringRequest(Request.Method.POST, strUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            CheckStudentLoginDetails(response, activity);
                            stopProgressBar();

                        } catch (Exception objException) {
                            objException.printStackTrace();
                            stopProgressBar();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(objContext,strError, Toast.LENGTH_SHORT).show();
                stopProgressBar();

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

    private void CheckStudentLoginDetails(String strResponse, RegistrationFormActivity regForm) {

        jsonobject = null;
        strResult = "";
        try {
            jsonobject = new JSONObject(strResponse);
            strResult = jsonobject.getString("result");
            if (strResult.equalsIgnoreCase("1"))
            {
                StudentDetails studentDetails = new StudentDetails();
                studentDetails.setId(jsonobject.getString("id"));
                studentDetails.setStudentName(jsonobject.getString("name"));
                studentDetails.setRollNo("001");
                //studentDetails.setRollNo(jsonobject.getString("rollNo"));
                studentDetails.setDateOfBirth(jsonobject.getString("dob"));
                studentDetails.setAdmissionDate(jsonobject.getString("admissionDate"));
                studentDetails.setDateAdded(jsonobject.getString("dateAdded"));
                studentDetails.setFranId(jsonobject.getString("franId"));
                studentDetails.setEmail(jsonobject.getString("email"));
                studentDetails.setCourseLevel(jsonobject.getString("courseLevel"));
                studentDetails.setCourseId(jsonobject.getString("courseId"));
                studentDetails.setStd(jsonobject.getString("std"));
                studentDetails.setSchool(jsonobject.getString("school"));
                studentDetails.setMobileNo(jsonobject.getString("mobileNo"));
                studentDetails.setMotherOccupation(jsonobject.getString("motherOccupation"));
                studentDetails.setFatherOccupation(jsonobject.getString("fatherOccupation"));
                studentDetails.setMotherName(jsonobject.getString("motherName"));
                studentDetails.setAddress(jsonobject.getString("address"));
                studentDetails.setFatherName(jsonobject.getString("fatherName"));
                studentDetails.setSex(jsonobject.getString("sex"));

                regForm.displayStudentInfo(studentDetails);

            }

        } catch (Exception objException) {
            objException.printStackTrace();
        }
    }

    public void getExamDetails(String strUrl, final String strExam_Code, final ExamOptionActivity activity) {

        showProgressBar(activity);
        objRequestQueue = WebServiceManager.getInstance(objContext).getRequestQueue();
        objStringRequest = new StringRequest(Request.Method.POST, strUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            ExamDetails examDetails = CheckExam_CodeDetails(response);
                            if (activity != null)
                            {
                                activity.showExamInfo(examDetails);
                                stopProgressBar();
                            }

                        } catch (Exception objException) {
                            objException.printStackTrace();
                            stopProgressBar();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(objContext, strError, Toast.LENGTH_SHORT).show();
                stopProgressBar();
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

    private ExamDetails CheckExam_CodeDetails(String strResponse) {

        jsonobject = null;
        strResult = "";
        try {
            jsonobject = new JSONObject(strResponse);
            strResult = jsonobject.getString("result");
            if (0 == strResult.compareToIgnoreCase("1")) {
                objExam_Details = new ExamDetails();
                objExam_Details.setId(jsonobject.getString("id"));
                objExam_Details.setName(jsonobject.getString("name"));
                objExam_Details.setCourse(jsonobject.getString("course"));
                objExam_Details.setLevel(jsonobject.getString("level"));
                objExam_Details.setType(jsonobject.getString("type"));
                objExam_Details.setStartDate(jsonobject.getString("startDate"));
                objExam_Details.setEndDate(jsonobject.getString("endDate"));
                objExam_Details.setIsCompleted(jsonobject.getString("isCompleted"));

                return  objExam_Details;
            }
            else
            {
                Toast.makeText(this.objContext, "Please enter valida exam code", Toast.LENGTH_LONG).show();
            }
        } catch (Exception objException) {
            objException.printStackTrace();
        }
        return null;
    }

    public void getExamQuestion(String strUrl, final String strExamId, final ExamInfoActivity activity) {

        showProgressBar(activity);
        objRequestQueue = WebServiceManager.getInstance(objContext).getRequestQueue();
        objStringRequest = new StringRequest(Request.Method.POST, strUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            ArrayList<ExamQuestionDetails> questions = CheckExamQuestion(response);
                            if (activity != null)
                            {
                                activity.onReceiveQuestions(questions);
                                stopProgressBar();
                            }
                        } catch (Exception objException) {
                            objException.printStackTrace();
                            stopProgressBar();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                stopProgressBar();
                Toast.makeText(objContext, strError, Toast.LENGTH_SHORT).show();
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

    private ArrayList<ExamQuestionDetails> CheckExamQuestion(String strResponse) {

        ArrayList<ExamQuestionDetails> result = new ArrayList<ExamQuestionDetails>();
        JSONArray jsonarray = null;
        strResult = "";

        try {
            jsonarray = new JSONArray(strResponse);
            if(jsonarray.length() > 0)
            {
                for (int i = 0; i < jsonarray.length(); i++)
                {

                    jsonobject = (JSONObject) jsonarray.get(i);

                    ExamQuestionDetails objExamQuestions = new ExamQuestionDetails();
                    objExamQuestions.setId(jsonobject.getString("id"));
                    objExamQuestions.setDescription(jsonobject.getString("description"));
                    objExamQuestions.setOptionA(jsonobject.getString("optionA"));
                    objExamQuestions.setOptionB(jsonobject.getString("optionB"));
                    objExamQuestions.setOptionC(jsonobject.getString("optionC"));
                    objExamQuestions.setOptionD(jsonobject.getString("optionD"));
                    objExamQuestions.setAnswer(jsonobject.getString("answer"));

                    result.add(objExamQuestions);
                }
            }
            else
            {
                Toast.makeText(objContext, "Not a single question available for this exam", Toast.LENGTH_LONG).show();
            }

        } catch (Exception objException)
        {
            objException.printStackTrace();
            Toast.makeText(objContext, "Error occurred for this exam. Please contact CSD.", Toast.LENGTH_LONG).show();
        }
        return  result;
    }

    public void getNetworkList(String strUrl, final LoginActivity objLoginActivity) {

        showProgressBar(objLoginActivity);
        objRequestQueue = WebServiceManager.getInstance(objContext).getRequestQueue();
        objStringRequest = new StringRequest(Request.Method.GET, strUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            objLoginActivity.initFranchiseNameSpinner(response);
                            stopProgressBar();

                        } catch (Exception objException) {
                            objException.printStackTrace();
                            stopProgressBar();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                stopProgressBar();
                Toast.makeText(objContext, strError, Toast.LENGTH_SHORT).show();
            }
        });
        objRequestQueue.add(objStringRequest);
    }

    public void sendStudentDetailsToServer(String strUrl, final RegistrationFormActivity objRegistrationFormActivity) {

        objRequestQueue = WebServiceManager.getInstance(objContext).getRequestQueue();
        objStringRequest = new StringRequest(Request.Method.POST, strUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            jsonobject = null;
                            jsonobject = new JSONObject(response);
                            //strResult = jsonobject.getString("result");
                            objRegistrationFormActivity.isStudedntInfromationUpdated(jsonobject);

                        } catch (Exception objException) {
                            objException.printStackTrace();
                            stopProgressBar();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                stopProgressBar();
                Toast.makeText(objContext, strError, Toast.LENGTH_SHORT).show();
            }
        }) {
            //Post method parameters
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return objRegistrationFormActivity.getStudentInformation();
            }
        };
        objRequestQueue.add(objStringRequest);
    }

    public void getFranchiseLogin(String strUrl, final String franchiseUserName, final String franchisePassword,final String franchiseId,final LoginActivity objLoginActivity ) {

        showProgressBar(objLoginActivity);
        objRequestQueue = WebServiceManager.getInstance(objContext).getRequestQueue();
        objStringRequest = new StringRequest(Request.Method.POST, strUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            objLoginActivity.getResponseAndCallActivity(response);
                            stopProgressBar();

                        } catch (Exception objException) {
                            objException.printStackTrace();
                            stopProgressBar();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                stopProgressBar();
                Toast.makeText(objContext, strError, Toast.LENGTH_SHORT).show();
            }
        }) {
            //Post method parameters
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("uName", franchiseUserName);
                params.put("passwd", franchisePassword);
                params.put("frId", franchiseId);
                return params;
            }
        };
        objRequestQueue.add(objStringRequest);
    }

    public void showProgressBar(Context context){
        try {
            objProgress = ProgressDialog.show(context, "Loading","Please wait while loading...");
            objProgress.show();
        }catch (Exception objException) {
            objException.printStackTrace();
        }
    }

    public void stopProgressBar(){
        if(objProgress!=null) {
            objProgress.dismiss();
        }
    }


    public void uploadResultToServer(String strUrl, final ExamResultDetails resultDetails) {

        objRequestQueue = WebServiceManager.getInstance(objContext).getRequestQueue();
        objStringRequest = new StringRequest(Request.Method.POST, strUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            jsonobject = null;
                            jsonobject = new JSONObject(response);
                            //move result to history
                        } catch (Exception objException) {
                            objException.printStackTrace();
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
                Map<String, String> params = null;
                if (resultDetails != null)
                {
                    params = resultDetails.getParams();
                }
                return params;
            }
        };
        objRequestQueue.add(objStringRequest);
    }


}

