package com.example.siddharth.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class Database extends SQLiteOpenHelper
{
    private SQLiteDatabase objSQLiteDatabase;
    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String TABLE_NAME = "OnLineExam";
    public static final String Col_Id = "id";
    public static final String Col_Question = "question";
    public static final String Col_Opt1 = "Opt1";
    public static final String Col_Opt2 = "Opt2";
    public static final String Col_Opt3 = "Opt3";
    public static final String Col_Opt4 = "Opt4";
    public static final String Col_Ans = "ans";
    public static final String Col_CorrectAns = "CorrectAns";


    public Database(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        String strQurey;
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
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onCreate(db);
    }

    public int numberOfRows()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }


    public boolean addRecord(String question, String ans, String opt1,
                          String opt2,String opt3,String opt4,String correctAns)
    {
        boolean bRet = false;
        try
        {
            objSQLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Col_Id,numberOfRows() + 1);
            contentValues.put(Col_Opt1, opt1);
            contentValues.put(Col_Opt2, opt2);
            contentValues.put(Col_Opt3, opt3);
            contentValues.put(Col_Opt4, opt4);
            contentValues.put(Col_Ans, ans);
            contentValues.put(Col_CorrectAns, correctAns);
            contentValues.put(Col_Question, question);
            objSQLiteDatabase.insert(TABLE_NAME,null, contentValues);
            objSQLiteDatabase.close();
            bRet = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return bRet;
    }

    public boolean updateRecord(Integer id, String correctAns)
    {
        boolean bRet = false;

        try
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
        }
        return bRet;
    }

    public void getRecord(Integer id,String question,String opt1,
                          String opt2,String opt3,String opt4)
    {
        objSQLiteDatabase = this.getReadableDatabase();
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
        objSQLiteDatabase.close();
    }
}
