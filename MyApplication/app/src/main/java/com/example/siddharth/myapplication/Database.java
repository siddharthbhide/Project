package com.example.siddharth.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {


    private SQLiteDatabase objSQLiteDatabase;
    public static final String DATABASE_NAME = "CSD_Database.db";
    private static final int DATABASE_VERSION = 1;
    private static String DB_PATH = "";
    private final Context myContext;

    public static final String TABLE_Question_Answere_Table = "Question_Answere_Table";
    public static final String TABLE_Result_Details_Table = "Result_Details";
    public static final String TABLE_Exam_Details_Table = "Exam_Details";


    //Question_Answere_Table
    public static final String Col_Question_Id = "Question_Id";
    public static final String Col_QA_Question = "Question";
    public static final String Col_QA_Opt1 = "Option_1";
    public static final String Col_QA_Opt2 = "Option_2";
    public static final String Col_QA_Opt3 = "Option_3";
    public static final String Col_QA_Opt4 = "Option_4";
    public static final String Col_QA_Ans = "Correct_Ans";
    public static final String Col_QA_Correct_Ans = "Correct_Ans";
    public static final String Col_QA_Course = "Course";
    public static final String Col_QA_Student_Ans = "Student_Ans";
    public static final String Col_QA_Time_Start = "Time_Start";
    public static final String Col_QA_Time_End = "Time_End";
    public static final String Col_QA_Last_Question = "Last_Question";
    private static final String Col_QA_Exam_Id = "Exam_Id";

    //Result Details
    public static final String Col_Result_Exam_Id = "Exam_Id";
    public static final String Col_Result_Course_Id = "Course_Id";
    public static final String Col_Result_Course_Level = "Course_Level";
    public static final String Col_Result_Date = "Exam_Date";
    public static final String Col_Result_Score = "Exam_Score";
    public static final String Col_Result_Total_Time = "Total_Time";
    public static final String Col_Result_Total_Question = "Total_Question";
    public static final String Col_Result_Attempted_Question = "Attempted_Question";
    public static final String Col_Result_Non_Attempted_Question = "Non_Attempted_Question";
    public static final String Col_Result_Correct_Answere = "Correct_Answere";
    public static final String Col_Result_Wrong_Answere = "Wrong_Answere";
    public static final String Col_Result_Time_Per_Question = "Time_Per_Question";

    //Exam Details
    public static final String Col_Exam_Id = "Exam_Id";
    public static final String Col_Exam_Course_Id = "Course_Id";
    public static final String Col_Exam_Course_level = "Course_Level";
    public static final String Col_Exam_Start_Date = "Exam_Satrt_Date";
    public static final String Col_Exam_End_Date = "Exam_End_Date";
    private static final String Col_Exam_Name = "Exam_Name";
    private static final String Col_Exam_Course_Type = "Course_Type";
    private static final String Col_Exam_Is_Completed = "Is_Completed";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //objSQLiteDatabase = getWritableDatabase();
        myContext = context;
        try {
            DB_PATH = context.getDatabasePath(DATABASE_NAME).getPath();
            createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

       /* String strQurey;
        try
        {
            strQurey = "create table "+TABLE_NAME+" (id integer,"+
                    Col_Question+" text,"+
                    Col_Opt1+" text,"+
                    Col_Opt2+" text,"+
                    Col_Opt3+" text,"+
                    Col_Opt4+" text,"+
                    Col_Ans+" text,"+
                    Col_CorrectAns+" text)";
            db.execSQL(strQurey);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //onCreate(db);
    }

    public int numberOfRows(String strTableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, strTableName);
        return numRows;
    }


    private long addRecord(String strTableName, ContentValues objContentValues) {
        long longRet = -1;
        try {

            objSQLiteDatabase = getWritableDatabase();
            /*boolean bOpen = objSQLiteDatabase.isOpen();
            if (objSQLiteDatabase != null && !objSQLiteDatabase.isOpen())
                objSQLiteDatabase.close();*/

            objSQLiteDatabase.beginTransaction();
            longRet = objSQLiteDatabase.insert(strTableName, null, objContentValues);
            objSQLiteDatabase.setTransactionSuccessful();
            //objSQLiteDatabase.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            objSQLiteDatabase.endTransaction();
        }

        return longRet;
    }

    public boolean updateRecord(Integer id, String correctAns) {
        boolean bRet = false;

       /* try
        {
            objSQLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Col_CorrectAns, correctAns);
            objSQLiteDatabase.update(TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
            objSQLiteDatabase.close();
            bRet = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*/
        return bRet;
    }

    public void getRecord(Integer id, String question, String opt1,
                          String opt2, String opt3, String opt4) {
        /*objSQLiteDatabase = this.getReadableDatabase();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                new String[]{Col_Id, Col_Opt1, Col_Opt2,Col_Opt3,Col_Opt4,Col_Question},
                Col_Id + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        id = cursor.getColumnIndex(Col_Id);
        question = cursor.getString(cursor.getColumnIndex(Col_Question));
        opt1 = cursor.getString(cursor.getColumnIndex(Col_Opt1));
        opt2 = cursor.getString(cursor.getColumnIndex(Col_Opt2));
        opt3 = cursor.getString(cursor.getColumnIndex(Col_Opt3));
        opt4 = cursor.getString(cursor.getColumnIndex(Col_Opt4));
        // close the db connection
        cursor.close();
        objSQLiteDatabase.close();*/
    }


    public long addExam_Details(ExamDetails objExam_Details) {
        long longRet = 0;
        try {

            ContentValues objContentValues = new ContentValues();
            objContentValues.put(Col_Exam_Id, objExam_Details.getId());
            objContentValues.put(Col_Exam_Name, objExam_Details.getName());
            objContentValues.put(Col_Exam_Course_Id, objExam_Details.getCourse());
            objContentValues.put(Col_Exam_Course_level, objExam_Details.getLevel());
            objContentValues.put(Col_Exam_Course_Type, objExam_Details.getType());
            objContentValues.put(Col_Exam_Start_Date, objExam_Details.getStartDate());
            objContentValues.put(Col_Exam_End_Date, objExam_Details.getEndDate());
            objContentValues.put(Col_Exam_Is_Completed, objExam_Details.getIsCompleted());

            longRet = addRecord(TABLE_Exam_Details_Table, objContentValues);


        } catch (Exception objException) {
            objException.printStackTrace();
        }
        return longRet;
    }

    public long addQuestion_Answere(ExamQuestionDetails objExamQuestions) {
        long longRet = 0;
        try {
            ContentValues objContentValues = new ContentValues();
            objContentValues.put(Col_QA_Exam_Id, objExamQuestions.getId());
            objContentValues.put(Col_QA_Question, objExamQuestions.getDescription());
            objContentValues.put(Col_QA_Opt1, objExamQuestions.getOptionA());
            objContentValues.put(Col_QA_Opt2, objExamQuestions.getOptionB());
            objContentValues.put(Col_QA_Opt3, objExamQuestions.getOptionC());
            objContentValues.put(Col_QA_Opt4, objExamQuestions.getOptionD());
            objContentValues.put(Col_QA_Course, objExamQuestions.getCourse());
            objContentValues.put(Col_QA_Correct_Ans, objExamQuestions.getAnswer());
            objContentValues.put(Col_QA_Student_Ans, objExamQuestions.getStudentAns());
            objContentValues.put(Col_QA_Time_Start, objExamQuestions.getStartTime());
            objContentValues.put(Col_QA_Time_End, objExamQuestions.getEndTime());
            objContentValues.put(Col_QA_Exam_Id, objExamQuestions.getExamId());
            objContentValues.put(Col_QA_Last_Question, objExamQuestions.getLastQuestion());


            longRet = addRecord(TABLE_Question_Answere_Table, objContentValues);

        } catch (Exception objException) {
            objException.printStackTrace();
        }
        return longRet;
    }

    public long addResult(ExamResultDetails objResultDetails) {
        long longRet = 0;
        try {
            ContentValues objContentValues = new ContentValues();
            objContentValues.put(Col_Result_Exam_Id, objResultDetails.getId());
            objContentValues.put(Col_Result_Course_Id, objResultDetails.getCourseId());
            objContentValues.put(Col_Result_Course_Level, objResultDetails.getCourseLevel());
            objContentValues.put(Col_Result_Date, objResultDetails.getExamDate());
            objContentValues.put(Col_Result_Score, objResultDetails.getExamScore());
            objContentValues.put(Col_Result_Total_Time, objResultDetails.getTotalTime());
            objContentValues.put(Col_Result_Total_Question, objResultDetails.getTotalQuestion());
            objContentValues.put(Col_Result_Attempted_Question, objResultDetails.getAttemptedQuestion());
            objContentValues.put(Col_Result_Non_Attempted_Question, objResultDetails.getNoAttemptedQuestion());
            objContentValues.put(Col_Result_Correct_Answere, objResultDetails.getCorrectAnswere());
            objContentValues.put(Col_Result_Wrong_Answere, objResultDetails.getWrongAnswere());
            objContentValues.put(Col_Result_Time_Per_Question, objResultDetails.getTimePerQuestion());

            longRet = addRecord(TABLE_Result_Details_Table, objContentValues);

        } catch (Exception objException) {
            objException.printStackTrace();
        }
        return longRet;
    }

    public ArrayList<ExamDetails> getExam_Details(String strSelectQuery) {

        ArrayList<ExamDetails> list = new ArrayList<ExamDetails>();
        String selectQuery = strSelectQuery;
        SQLiteDatabase db = this.getReadableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {

                if (cursor.moveToFirst()) {
                    do {
                        int nCoulumIndex = 0;
                        ExamDetails objExam_Details = new ExamDetails();

                        objExam_Details.setId(cursor.getString(nCoulumIndex++));
                        objExam_Details.setName(cursor.getString(nCoulumIndex++));
                        objExam_Details.setCourse(cursor.getString(nCoulumIndex++));
                        objExam_Details.setLevel(cursor.getString(nCoulumIndex++));
                        objExam_Details.setType(cursor.getString(nCoulumIndex++));
                        objExam_Details.setStartDate(cursor.getString(nCoulumIndex++));
                        objExam_Details.setEndDate(cursor.getString(nCoulumIndex++));
                        objExam_Details.setIsCompleted(cursor.getString(nCoulumIndex++));

                        list.add(objExam_Details);
                    } while (cursor.moveToNext());
                }

            } finally {
                try {
                    cursor.close();
                } catch (Exception ignore) {
                }
            }

        } finally {
            try {
                db.close();
            } catch (Exception ignore) {
            }
        }

        return list;
    }

    public ArrayList<ExamQuestionDetails> getQuestion_Answere(String strSelectQuery) {

        ArrayList<ExamQuestionDetails> list = new ArrayList<ExamQuestionDetails>();
        String selectQuery = strSelectQuery;
        SQLiteDatabase db = this.getReadableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {

                if (cursor.moveToFirst()) {
                    do {
                        int nCoulumIndex = 0;
                        ExamQuestionDetails objExamQuestions = new ExamQuestionDetails();

                        objExamQuestions.setId(cursor.getString(nCoulumIndex++));
                        objExamQuestions.setDescription(cursor.getString(nCoulumIndex++));
                        objExamQuestions.setOptionA(cursor.getString(nCoulumIndex++));
                        objExamQuestions.setOptionB(cursor.getString(nCoulumIndex++));
                        objExamQuestions.setOptionC(cursor.getString(nCoulumIndex++));
                        objExamQuestions.setOptionD(cursor.getString(nCoulumIndex++));
                        objExamQuestions.setCourse(cursor.getString(nCoulumIndex++));
                        objExamQuestions.setAnswer(cursor.getString(nCoulumIndex++));
                        objExamQuestions.setStudentAns(cursor.getString(nCoulumIndex++));
                        objExamQuestions.setStartTime(cursor.getString(nCoulumIndex++));
                        objExamQuestions.setEndTime(cursor.getString(nCoulumIndex++));
                        objExamQuestions.setExamId(cursor.getString(nCoulumIndex++));
                        objExamQuestions.setLastQuestion(cursor.getString(nCoulumIndex++));

                        list.add(objExamQuestions);
                    } while (cursor.moveToNext());
                }

            } finally {
                try {
                    cursor.close();
                } catch (Exception ignore) {
                }
            }

        } finally {
            try {
                db.close();
            } catch (Exception ignore) {
            }
        }

        return list;
    }

    public ArrayList<ExamResultDetails> getResult(String strSelectQuery) {

        ArrayList<ExamResultDetails> list = new ArrayList<ExamResultDetails>();
        String selectQuery = strSelectQuery;
        SQLiteDatabase db = this.getReadableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {

                if (cursor.moveToFirst()) {
                    do {
                        int nCoulumIndex = 0;
                        ExamResultDetails objResultDetails = new ExamResultDetails();

                        objResultDetails.setId(cursor.getString(nCoulumIndex++));
                        objResultDetails.setCourseId(cursor.getString(nCoulumIndex++));
                        objResultDetails.setCourseLevel(cursor.getString(nCoulumIndex++));
                        objResultDetails.setExamDate(cursor.getString(nCoulumIndex++));
                        objResultDetails.setExamScore(cursor.getString(nCoulumIndex++));
                        objResultDetails.setTotalTime(cursor.getString(nCoulumIndex++));
                        objResultDetails.setTotalQuestion(cursor.getString(nCoulumIndex++));
                        objResultDetails.setAttemptedQuestion(cursor.getString(nCoulumIndex++));
                        objResultDetails.setNoAttemptedQuestion(cursor.getString(nCoulumIndex++));
                        objResultDetails.setCorrectAnswere(cursor.getString(nCoulumIndex++));
                        objResultDetails.setWrongAnswere(cursor.getString(nCoulumIndex++));
                        objResultDetails.setTimePerQuestion(cursor.getString(nCoulumIndex++));

                        list.add(objResultDetails);
                    } while (cursor.moveToNext());
                }

            } finally {
                try {
                    cursor.close();
                } catch (Exception ignore) {
                }
            }

        } finally {
            try {
                db.close();
            } catch (Exception ignore) {
            }
        }

        return list;
    }

    //Create a empty database on the system
    public void createDatabase() throws IOException
    {

        boolean dbExist = checkDataBase();

        if(dbExist)
        {
            Log.v("DB Exists", "db exists");
            // By calling this method here onUpgrade will be called on a
            // writeable database, but only if the version number has been
            // bumped
            //onUpgrade(myDataBase, DATABASE_VERSION_old, DATABASE_VERSION);
        }

        boolean dbExist1 = checkDataBase();
        if(!dbExist1)
        {
            this.getReadableDatabase();
            try
            {
                this.close();
                copyDataBase();
            }
            catch (IOException e)
            {
                throw new Error("Error copying database");
            }
        }

    }
    //Check database already exist or not
    private boolean checkDataBase()
    {
        boolean checkDB = false;
        try
        {
            String myPath = DB_PATH + DATABASE_NAME;
            File dbfile = new File(myPath);
            checkDB = dbfile.exists();
        }
        catch(SQLiteException e)
        {
        }
        return checkDB;
    }
    //Copies your database from your local assets-folder to the just created empty database in the system folder
    private void copyDataBase() throws IOException
    {

        InputStream mInput = myContext.getAssets().open(DATABASE_NAME);
        String outFileName = DB_PATH + DATABASE_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[2024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

}
