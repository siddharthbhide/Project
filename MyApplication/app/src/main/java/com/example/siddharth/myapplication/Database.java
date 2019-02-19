package com.example.siddharth.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {


    private SQLiteDatabase objSQLiteDatabase;
    public static final String DATABASE_NAME = "CSDDatabase.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_Question_Answere_Table = "Question_Answere_Table";
    public static final String TABLE_Result_Details_Table = "Result_Details";
    public static final String TABLE_Exam_Details_Table = "Exam_Details";

    //Common
    public static final String Col_Exam_Id = "Exam_Id";
    public static final String Col_Course_id = "Course_Id";
    public static final String Col_Course_level = "Course_Level";

    //Question_Answere_Table
    public static final String Col_Id = "id";
    public static final String Col_Question = "Question";
    public static final String Col_Opt1 = "OptionA";
    public static final String Col_Opt2 = "OptionB";
    public static final String Col_Opt3 = "OptionC";
    public static final String Col_Opt4 = "OptionD";
    public static final String Col_Ans = "Ans";
    public static final String Col_CorrectAns = "CorrectAns";
    public static final String Col_Course = "Course";
    public static final String Col_StudentAns = "StudentAns";
    public static final String Col_StartTime = "StartTime";
    public static final String Col_EndTime = "EndTime";
    public static final String Col_Last_Question = "Last_Question";
    //private static final String Col_Exam_Id = "Exam_Id";

    //Result Details
    public static final String Col_Exam_Date = "Exam_Date";
    public static final String Col_Exam_Score = "Exam_Score";
    public static final String Col_Total_Time = "Total_Time";
    public static final String Col_Total_Question = "Total_Question";
    public static final String Col_Attempted_Question = "Attempted_Question";
    public static final String Col_Non_Attempted_Question = "Non_Attempted_Question";
    public static final String Col_Correct_Answere = "Correct_Answere";
    public static final String Col_Wrong_Answere = "Wrong_Answere";
    public static final String Col_Time_Per_Question = "Time_Per_Question";

    //Exam Details
    public static final String Col_Exam_Start_Date = "Exam_Start_Date";
    public static final String Col_Exam_End_Date = "Exam_End_Date";
    private static final String Col_Exam_Name = "Exam_Name";
    private static final String Col_Course_Type = "Course_Type";
    private static final String Col_Is_Completed = "Is_Completed";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //SQLiteDatabase db = this.getReadableDatabase();
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
            objSQLiteDatabase = this.getWritableDatabase();
            longRet = objSQLiteDatabase.insert(strTableName, null, objContentValues);
            objSQLiteDatabase.setTransactionSuccessful();
            objSQLiteDatabase.close();
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


    public long addExam_Details(Exam_Details objExam_Details) {
        long longRet = 0;
        try {
            ContentValues objContentValues = new ContentValues();
            objContentValues.put(Col_Id, objExam_Details.getId());
            objContentValues.put(Col_Exam_Name, objExam_Details.getName());
            objContentValues.put(Col_Course, objExam_Details.getCourse());
            objContentValues.put(Col_Course_level, objExam_Details.getLevel());
            objContentValues.put(Col_Course_Type, objExam_Details.getType());
            objContentValues.put(Col_StartTime, objExam_Details.getStartDate());
            objContentValues.put(Col_Exam_Date, objExam_Details.getEndDate());
            objContentValues.put(Col_Is_Completed, objExam_Details.getIsCompleted());

            longRet = addRecord(TABLE_Exam_Details_Table, objContentValues);

        } catch (Exception objException) {
            objException.printStackTrace();
        }
        return longRet;
    }

    public long addQuestion_Answere(ExamQuestions objExamQuestions) {
        long longRet = 0;
        try {
            ContentValues objContentValues = new ContentValues();
            objContentValues.put(Col_Id, objExamQuestions.getId());
            objContentValues.put(Col_Question, objExamQuestions.getDescription());
            objContentValues.put(Col_Opt1, objExamQuestions.getOptionA());
            objContentValues.put(Col_Opt2, objExamQuestions.getOptionB());
            objContentValues.put(Col_Opt3, objExamQuestions.getOptionC());
            objContentValues.put(Col_Opt4, objExamQuestions.getOptionD());
            objContentValues.put(Col_Course, objExamQuestions.getCourse());
            objContentValues.put(Col_CorrectAns, objExamQuestions.getAnswer());
            objContentValues.put(Col_StudentAns, objExamQuestions.getStudentAns());
            objContentValues.put(Col_StartTime, objExamQuestions.getStartTime());
            objContentValues.put(Col_EndTime, objExamQuestions.getEndTime());
            objContentValues.put(Col_Exam_Id, objExamQuestions.getExamId());
            objContentValues.put(Col_Last_Question, objExamQuestions.getLastQuestion());


            longRet = addRecord(TABLE_Question_Answere_Table, objContentValues);

        } catch (Exception objException) {
            objException.printStackTrace();
        }
        return longRet;
    }

    public long addResult(ResultDetails objResultDetails) {
        long longRet = 0;
        try {
            ContentValues objContentValues = new ContentValues();
            objContentValues.put(Col_Id, objResultDetails.getId());
            objContentValues.put(Col_Course_id, objResultDetails.getCourseId());
            objContentValues.put(Col_Course_level, objResultDetails.getCourseLevel());
            objContentValues.put(Col_Exam_Date, objResultDetails.getExamDate());
            objContentValues.put(Col_Exam_Score, objResultDetails.getExamScore());
            objContentValues.put(Col_Total_Time, objResultDetails.getTotalTime());
            objContentValues.put(Col_Total_Question, objResultDetails.getTotalQuestion());
            objContentValues.put(Col_Attempted_Question, objResultDetails.getAttemptedQuestion());
            objContentValues.put(Col_Non_Attempted_Question, objResultDetails.getNoAttemptedQuestion());
            objContentValues.put(Col_Correct_Answere, objResultDetails.getCorrectAnswere());
            objContentValues.put(Col_Wrong_Answere, objResultDetails.getWrongAnswere());
            objContentValues.put(Col_Time_Per_Question, objResultDetails.getTimePerQuestion());

            longRet = addRecord(TABLE_Result_Details_Table, objContentValues);

        } catch (Exception objException) {
            objException.printStackTrace();
        }
        return longRet;
    }


    public ArrayList<Exam_Details> getExam_Details(String strSelectQuery) {

        ArrayList<Exam_Details> list = new ArrayList<Exam_Details>();
        String selectQuery = strSelectQuery;
        SQLiteDatabase db = this.getReadableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {

                if (cursor.moveToFirst()) {
                    do {
                        int nCoulumIndex = 0;
                        Exam_Details objExam_Details = new Exam_Details();

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

    public ArrayList<ExamQuestions> getQuestion_Answere(String strSelectQuery) {

        ArrayList<ExamQuestions> list = new ArrayList<ExamQuestions>();
        String selectQuery = strSelectQuery;
        SQLiteDatabase db = this.getReadableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {

                if (cursor.moveToFirst()) {
                    do {
                        int nCoulumIndex = 0;
                        ExamQuestions objExamQuestions = new ExamQuestions();

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

    public ArrayList<ResultDetails> getResult(String strSelectQuery) {

        ArrayList<ResultDetails> list = new ArrayList<ResultDetails>();
        String selectQuery = strSelectQuery;
        SQLiteDatabase db = this.getReadableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {

                if (cursor.moveToFirst()) {
                    do {
                        int nCoulumIndex = 0;
                        ResultDetails objResultDetails = new ResultDetails();

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
}
