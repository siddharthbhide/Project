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

   /* public static final String TABLE_Question_Answere_Table = "Question_Answere_Table";
    public static final String TABLE_Result_Details_Table = "Result_Details";
    public static final String TABLE_Exam_Details_Table = "Exam_Details";*/

    //Result Details
    public static final String Col_Result_Exam_Id = "ExamId";
    public static final String Col_Result_Course_Id = "Course";
    public static final String Col_Result_Course_Level = "CourseLevel";
    public static final String Col_Result_Date = "ExamDate";
    public static final String Col_Result_Score = "ExamScore";
    public static final String Col_Result_StartDate = "StartDate";
    public static final String Col_Result_EndDate = "EndDate";
    public static final String Col_Result_ExamType = "ExamType";
    public static final String Col_Result_Total_Time = "TotalTime";
    public static final String Col_Result_Total_Question = "TotalQuestion";
    public static final String Col_Result_Attempted_Question = "Attempted";
    public static final String Col_Result_Non_Attempted_Question = "NonAttempted";
    public static final String Col_Result_Correct_Answere = "Correct";
    public static final String Col_Result_Wrong_Answere = "Wrong";
    public static final String Col_Result_Uploaded = "Uploaded";
    public static final String Col_Result_ExamName = "ExamName";

    //History Column names
    public static final String Col_History_ExamName = "ExamName";
    public static final String Col_History_Exam_Id = "ExamId";
    public static final String Col_History_Exam_Type = "ExamType";
    public static final String Col_History_Course = "Course";
    public static final String Col_History_Level = "Level";
    public static final String Col_History_StartDate = "StartDate";
    public static final String Col_History_EndDate = "EndDate";
    public static final String Col_History_ExamDate = "ExamDate";
    public static final String Col_History_Total = "Total";
    public static final String Col_History_Time = "Time";
    public static final String Col_History_Attempted_Question = "Attempted";
    public static final String Col_History_Non_Attempted_Question = "NonAttempted";
    public static final String Col_History_Correct = "Correct";
    public static final String Col_History_Wrong = "Wrong";
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
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
            objSQLiteDatabase.beginTransaction();
            longRet = objSQLiteDatabase.insert(strTableName, null, objContentValues);
            objSQLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            objSQLiteDatabase.endTransaction();
        }

        return longRet;
    }

    public long updateRecord(String strTableName, ContentValues objContentValues,String id) {
        long longRet = -1;

        /*try {
            objSQLiteDatabase = getWritableDatabase();
            objSQLiteDatabase.beginTransaction();
            longRet = objSQLiteDatabase.update(strTableName,objContentValues,Col_Question_Id+"=?",new String[] {id});
            objSQLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            objSQLiteDatabase.endTransaction();
        }*/

        return longRet;
    }

    public long addResult(ExamResultDetails objResultDetails) {
        long longRet = 0;
        try {
            ContentValues objContentValues = new ContentValues();
            int examId = Integer.parseInt(objResultDetails.getExamId());
            objContentValues.put(Col_Result_Exam_Id, examId);
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
            objContentValues.put(Col_Result_Uploaded, objResultDetails.getUploaded());
            objContentValues.put(Col_Result_ExamName, objResultDetails.getExamName());
            objContentValues.put(Col_Result_StartDate, objResultDetails.getStartDate());
            objContentValues.put(Col_Result_EndDate, objResultDetails.getEndDate());
            objContentValues.put(Col_Result_ExamType, objResultDetails.getExamType());
            longRet = addRecord(myContext.getString(R.string.table_result_details), objContentValues);

        } catch (Exception objException) {
            objException.printStackTrace();
        }
        return longRet;
    }

    public ArrayList<ExamResultDetails> getResult(String strSelectQuery) {

        ArrayList<ExamResultDetails> list = new ArrayList<ExamResultDetails>();
        String selectQuery = strSelectQuery;
        SQLiteDatabase db = this.getReadableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {

                if (cursor.moveToFirst())
                {
                    do {
                        ExamResultDetails objResultDetails = new ExamResultDetails();
                        objResultDetails.setExamId(""+cursor.getInt(0));
                        objResultDetails.setCourseId(cursor.getString(1));
                        objResultDetails.setCourseLevel(cursor.getString(2));
                        objResultDetails.setExamDate(cursor.getString(3));
                        objResultDetails.setExamScore(cursor.getString(4));
                        objResultDetails.setTotalTime(cursor.getString(5));
                        objResultDetails.setTotalQuestion(cursor.getString(6));
                        objResultDetails.setAttemptedQuestion(cursor.getString(7));
                        objResultDetails.setNoAttemptedQuestion(cursor.getString(8));
                        objResultDetails.setCorrectAnswere(cursor.getString(9));
                        objResultDetails.setWrongAnswere(cursor.getString(10));
                        objResultDetails.setUploaded(cursor.getInt(11));

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

    public ArrayList<HistoryDetails> getHistoryRecords(String strSelectQuery) {

        ArrayList<HistoryDetails> list = new ArrayList<HistoryDetails>();
        String selectQuery = strSelectQuery;
        SQLiteDatabase db = this.getReadableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {

                if (cursor.moveToFirst())
                {
                    do {
                        HistoryDetails historyRecord = new HistoryDetails();

                        ExamDetails examDetails = new ExamDetails();
                        examDetails.setId(""+cursor.getInt(0));
                        examDetails.setName(cursor.getString(1));
                        examDetails.setCourse(cursor.getString(2));
                        examDetails.setLevel(cursor.getString(3));
                        examDetails.setStartDate(cursor.getString(4));
                        examDetails.setEndDate(cursor.getString(5));
                        examDetails.setType(cursor.getString(13));

                        ExamResultDetails result = new ExamResultDetails();
                        result.setExamDate(cursor.getString(6));
                        result.setTotalQuestion(""+cursor.getInt(7));
                        result.setAttemptedQuestion(""+cursor.getInt(8));
                        result.setNoAttemptedQuestion(""+cursor.getInt(9));
                        result.setCorrectAnswere(""+cursor.getInt(10));
                        result.setWrongAnswere(""+cursor.getInt(11));
                        result.setTotalTime(cursor.getString(12));

                        historyRecord.setExamDetails(examDetails);
                        historyRecord.setResultDetails(result);

                        list.add(historyRecord);
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

    public long addHistory(HistoryDetails history) {
        long longRet = 0;
        try {
            ContentValues objContentValues = new ContentValues();
            int examId = Integer.parseInt(history.getExamDetails().getId());
            objContentValues.put(Col_History_Exam_Id, examId);
            objContentValues.put(Col_History_ExamName, history.getExamDetails().getName());
            objContentValues.put(Col_History_Exam_Type, history.getExamDetails().getType());
            objContentValues.put(Col_History_Course, history.getExamDetails().getCourse());
            objContentValues.put(Col_History_Level, history.getExamDetails().getLevel());
            objContentValues.put(Col_History_StartDate, history.getExamDetails().getStartDate());
            objContentValues.put(Col_History_EndDate, history.getExamDetails().getEndDate());
            objContentValues.put(Col_History_ExamDate, history.getResultDetails().getExamDate());
            objContentValues.put(Col_History_Total, Integer.parseInt(history.getResultDetails().getTotalQuestion()));
            objContentValues.put(Col_History_Time, history.getResultDetails().getTotalTime());
            objContentValues.put(Col_History_Attempted_Question, Integer.parseInt(history.getResultDetails().getAttemptedQuestion()));
            objContentValues.put(Col_History_Non_Attempted_Question, Integer.parseInt(history.getResultDetails().getNoAttemptedQuestion()));
            objContentValues.put(Col_History_Correct, Integer.parseInt(history.getResultDetails().getCorrectAnswere()));
            objContentValues.put(Col_History_Wrong, Integer.parseInt(history.getResultDetails().getWrongAnswere()));

            longRet = addRecord("History", objContentValues);

        } catch (Exception objException) {
            objException.printStackTrace();
        }
        return longRet;
    }

    public ArrayList<String> getStudentName(String strSelectQuery) {

        ArrayList<String> list = new ArrayList<String>();
        String selectQuery = strSelectQuery;
        SQLiteDatabase db = this.getReadableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {

                if (cursor.moveToFirst())
                {
                    do {
                        String strStudentName;
                        strStudentName = cursor.getString(0);
                        list.add(strStudentName);
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
        }

        if(!dbExist)
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
            String myPath = DB_PATH;
            File dbfile = new File(myPath);
            checkDB = dbfile.exists();
        }
        catch(SQLiteException e)
        {
            Log.e("Database", "SQLiteException : "+e.getLocalizedMessage());
        }
        return checkDB;
    }

    //Copies your database from your local assets-folder to the just created empty database in the system folder
    private void copyDataBase() throws IOException
    {

        InputStream mInput = myContext.getAssets().open(DATABASE_NAME);
        String outFileName = DB_PATH;
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
