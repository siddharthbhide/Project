package com.example.siddharth.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class ExamActivity extends AppCompatActivity {

    private TextView objTimeView;
    Database objDatabase;

    TextView textViewQuestion;
    RadioButton radioButtonOpt1;
    RadioButton radioButtonOpt2;
    RadioButton radioButtonOpt3;
    RadioButton radioButtonOpt4;
    TextView textQuestionNumber;
    private RadioGroup radioGroupQuestion;
    private RadioButton radioButtonStuAns;

    long longTest;
    int nCounter = 0;
    boolean bIsPause = false;
    ArrayList<ExamQuestionDetails> listOfQuestions = new ArrayList<ExamQuestionDetails>();
    int nQuestionIndex = 0;
    String strQuestionNumber;
    ExamDetails examDetails = null;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        listOfQuestions =  getIntent().getParcelableArrayListExtra("questions");
        this.examDetails = getIntent().getParcelableExtra("exam_details");

        InitVariables();
        countDownTimer.start();
    }

    CountDownTimer countDownTimer = new CountDownTimer(1360 * 1000, 1000)
    {
        public void onTick(long millisUntilFinished)
        {
            if(!bIsPause) {
                long remainedSecs = millisUntilFinished / 1000;
                longTest = remainedSecs;
                long s = nCounter % 60;
                long m = (nCounter / 60) % 60;
                long h = (nCounter / (60 * 60)) % 24;
                nCounter++;
                String strTime = String.format("%d:%02d:%02d", h, m, s);
                objTimeView.setText(strTime);
            }
        }
        public void onFinish()
        {
            objTimeView.setText("00:00:00");
            cancel();
        }
    };

    private void InitVariables() {
        textQuestionNumber = (TextView) findViewById(R.id.textQuestionNumber);
        textViewQuestion = (TextView) findViewById(R.id.Question);
        radioButtonOpt1 = (RadioButton) findViewById(R.id.radioAnsFirst);
        radioButtonOpt2 = (RadioButton) findViewById(R.id.radioAnsSecond);
        radioButtonOpt3 = (RadioButton) findViewById(R.id.radioAnsThird);
        radioButtonOpt4 = (RadioButton) findViewById(R.id.radioAnsFourth);
        objTimeView = (TextView) findViewById(R.id.textTime);
        radioGroupQuestion = (RadioGroup) findViewById(R.id.RGroup);
        objTimeView.setText("00:00:00");
        objDatabase = new Database(getApplicationContext());
        displayQuestion(nQuestionIndex);
    }

    public void OnClickSave(View view)
    {
        if (nQuestionIndex < listOfQuestions.size() && nQuestionIndex >= 0)
        {
            ExamQuestionDetails objExamQuestions = listOfQuestions.get(nQuestionIndex);
            int selectedId = radioGroupQuestion.getCheckedRadioButtonId();
            RadioButton radioButtonStuAns = (RadioButton) findViewById(selectedId);
            if (radioButtonStuAns != null)
            {
                String strAns = radioButtonStuAns.getText().toString();
                objExamQuestions.setStudentAns(strAns);
                Toast.makeText(this, "Alright Saved !", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void OnClickNext(View view) {
        Button nextBtn = (Button)view;

        if (nQuestionIndex < listOfQuestions.size()-1 && nQuestionIndex >= 0)
        {
            nQuestionIndex++;

            displayQuestion(nQuestionIndex);

            if (nQuestionIndex == listOfQuestions.size()-1)
            {
                nextBtn.setText("Finish");
            }
        }
        else if (nextBtn.getText().toString().equalsIgnoreCase("Finish"))
        {
                //store result in DB
                ExamResultDetails result = new ExamResultDetails();
                result.setTotalTime(objTimeView.getText().toString());
                result.setCourseId(examDetails.getCourse());
                result.setExamId(examDetails.getId());
                result.setCourseLevel(examDetails.getLevel());
                result.setStartDate(examDetails.getStartDate());
                result.setEndDate(examDetails.getEndDate());
                result.setUploaded(0);
                result.setExamScore("");
                result.setTotalQuestion(""+getTotalQuestions());
                result.setCorrectAnswere(""+getCorrectQuestions());
                result.setWrongAnswere(""+getWrongQuestions());
                result.setAttemptedQuestion(""+getAttemptedQuestions());
                result.setNoAttemptedQuestion(""+getNonAttemptedQuestions());
                result.setExamName(examDetails.getName());
                result.setExamType(examDetails.getType());
                Date today = Calendar.getInstance().getTime();

                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                String formattedDate = df.format(today);
                result.setExamDate(formattedDate);

                if(objDatabase != null)
                {
                    long dbOperationResult = objDatabase.addResult(result);
                    if (dbOperationResult > 0)
                    {
                        //upload exam result to server on successful
                        showExamEmoji();
                    }
                }
        }
    }

    public void OnClickPrev(View view) {
        if (nQuestionIndex <= listOfQuestions.size() && nQuestionIndex > 0)
        {
            Button nextBtn = findViewById(R.id.btnNext);
            nextBtn.setText("Next");
            nQuestionIndex--;
            displayQuestion(nQuestionIndex);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        bIsPause = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        bIsPause = false;
    }

    void displayQuestion(int nIndex){
        try {

            if (nIndex < listOfQuestions.size() && nIndex >= 0)
            {
                ExamQuestionDetails objExamQuestions = listOfQuestions.get(nIndex);

                strQuestionNumber = Integer.toString(nIndex+1) + " / " + Integer.toString(listOfQuestions.size());
                textQuestionNumber.setText(strQuestionNumber);

                textViewQuestion.setText(objExamQuestions.getDescription());
                radioButtonOpt1.setText(objExamQuestions.getOptionA());
                radioButtonOpt2.setText(objExamQuestions.getOptionB());
                radioButtonOpt3.setText(objExamQuestions.getOptionC());
                radioButtonOpt4.setText(objExamQuestions.getOptionD());

                String studentAns = objExamQuestions.getStudentAns();
                if (studentAns != null && !studentAns.isEmpty())
                {
                    if (studentAns.equalsIgnoreCase(objExamQuestions.getOptionA()))
                    {
                        radioButtonOpt1.setChecked(true);
                    }
                    else if (studentAns.equalsIgnoreCase(objExamQuestions.getOptionB()))
                    {
                        radioButtonOpt2.setChecked(true);
                    }
                    else if (studentAns.equalsIgnoreCase(objExamQuestions.getOptionC()))
                    {
                        radioButtonOpt3.setChecked(true);
                    }
                    else if (studentAns.equalsIgnoreCase(objExamQuestions.getOptionD()))
                    {
                        radioButtonOpt4.setChecked(true);
                    }
                }
                else
                {
                    radioGroupQuestion.clearCheck();
                }
            }

        }catch (Exception objException) {
            objException.printStackTrace();
        }

    }

    int getTotalQuestions()
    {
        int result = 0;
        if (listOfQuestions != null)
        {
            result = listOfQuestions.size();
        }
        return result;
    }

    int getAttemptedQuestions()
    {
        int result = 0;
        if (listOfQuestions != null)
        {
            for (ExamQuestionDetails item:listOfQuestions)
            {
                if (item.getStudentAns() != null && !item.getStudentAns().isEmpty())
                {
                    result++;
                }
            }
        }
        return result;
    }

    int getNonAttemptedQuestions()
    {
        int result = 0;
        if (listOfQuestions != null)
        {
            for (ExamQuestionDetails item:listOfQuestions)
            {
                if (item.getStudentAns() == null || item.getStudentAns().isEmpty())
                {
                    result++;
                }
            }
        }
        return result;
    }

    int getWrongQuestions()
    {
        int result = 0;
        if (listOfQuestions != null)
        {
            for (ExamQuestionDetails item:listOfQuestions)
            {
                if (item.getStudentAns() != null && !item.getStudentAns().isEmpty() && item.getAnswer() != null)
                {
                    if (!item.getStudentAns().equalsIgnoreCase(item.getAnswer()))
                        result++;
                }
            }
        }
        return result;
    }

    int getCorrectQuestions()
    {
        int result = 0;
        if (listOfQuestions != null)
        {
            for (ExamQuestionDetails item:listOfQuestions)
            {
                if (item.getStudentAns() != null && !item.getStudentAns().isEmpty() && item.getAnswer() != null)
                {
                    if (item.getStudentAns().equalsIgnoreCase(item.getAnswer()))
                        result++;
                }
            }
        }
        return result;
    }

    void showExamEmoji()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.result_emoji, null);
        dialogBuilder.setView(dialogView);

        /*EditText editText = (EditText) dialogView.findViewById(R.id.label_field);
        editText.setText("test label");*/
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "SUBMIT", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                finish();
                Intent objIntent = new Intent(ExamActivity.this, ExamOptionActivity.class);
                objIntent.putExtra("exam_type", examDetails.getType());
                startActivity(objIntent);
            }
        });
        alertDialog.show();
    }
}
