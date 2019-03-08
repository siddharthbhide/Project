package com.example.siddharth.myapplication;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
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
    static int nCounter;
    boolean bIsPause = false;
    ArrayList<ExamQuestionDetails> listOfQuestions = new ArrayList<ExamQuestionDetails>();
    int nQuestionIndex = 0;
    String strQuestionNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        listOfQuestions =  getIntent().getParcelableArrayListExtra("questions");

        InitVariables();
        countDownTimer.start();

        /*objDatabase = new Database(this);
        objDatabase.addRecord("What is One","1","2","1","3","4","1");
        objDatabase.getRecord(0,question,opt1,opt2,opt3,opt4);*/
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
            //objTimeView.setText("Done !");
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
        //setQuestionInDatabse();
        //getQuestionFromDatabase();
        displayQuestion(nQuestionIndex);
    }

    public void OnClickSave(View view)
    {
        ExamQuestionDetails objExamQuestions;
        objExamQuestions = listOfQuestions.get(nQuestionIndex);
        int selectedId = radioGroupQuestion.getCheckedRadioButtonId();/*get ans from ui*/
        radioButtonStuAns = (RadioButton) findViewById(selectedId);/*get ans from ui*/
        String strAns = radioButtonStuAns.getText().toString();/*get ans from ui*/
        objExamQuestions.setStudentAns(strAns);/*set ans in object*/
        objDatabase.updateQuestion_Answere(objExamQuestions);/*update data base*/
        getQuestionFromDatabase();/*Update list with new ans*/
    }

    public void OnClickNext(View view) {
        nQuestionIndex++;
        displayQuestion(nQuestionIndex);
    }

    public void OnClickPrev(View view) {
        nQuestionIndex--;
        displayQuestion(nQuestionIndex);
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

    void setQuestionInDatabse(){

        for (int nIndex = 0; nIndex<5; nIndex++) {
            ExamQuestionDetails objExamQuestions = new ExamQuestionDetails();
            //objExamQuestions.setId(Integer.toString(nIndex));
            objExamQuestions.setDescription(Integer.toString(nIndex));
            objExamQuestions.setOptionA(Integer.toString(nIndex));
            objExamQuestions.setOptionB(Integer.toString(nIndex));
            objExamQuestions.setOptionC(Integer.toString(nIndex));
            objExamQuestions.setOptionD(Integer.toString(nIndex));
            objExamQuestions.setExamId(Integer.toString(1));
            //objExamQuestions.setCourse(Integer.toString(1));
            objExamQuestions.setAnswer(Integer.toString(1));
            objExamQuestions.setStudentAns(Integer.toString(1));
            objExamQuestions.setStartTime(Integer.toString(1));
            objExamQuestions.setEndTime(Integer.toString(1));
            objExamQuestions.setLastQuestion(Integer.toString(1));

            objDatabase.addQuestion_Answere(objExamQuestions);
        }
    }

    void getQuestionFromDatabase(){

        /*fire a query and get question array in list
        * before get list clear that list*/
        String strQuery = "Select * from "+getString(R.string.table_question_answere)+
                " where Exam_Id = 1";
        listOfQuestions.clear(); /*Empty list*/
        listOfQuestions = objDatabase.getQuestion_Answere(strQuery);
    }

    void displayQuestion(int nIndex){
        ExamQuestionDetails objExamQuestions;
        try {
            if (nIndex < listOfQuestions.size() && nIndex >= 0) {
                strQuestionNumber = Integer.toString(nIndex+1) + " / " + Integer.toString(listOfQuestions.size());
                textQuestionNumber.setText(strQuestionNumber);
                objExamQuestions = listOfQuestions.get(nIndex);
                textViewQuestion.setText(objExamQuestions.getDescription());
                radioButtonOpt1.setText(objExamQuestions.getOptionA());
                radioButtonOpt2.setText(objExamQuestions.getOptionB());
                radioButtonOpt3.setText(objExamQuestions.getOptionC());
                radioButtonOpt4.setText(objExamQuestions.getOptionD());
            }

        }catch (Exception objException) {
            objException.printStackTrace();
        }

    }
}
