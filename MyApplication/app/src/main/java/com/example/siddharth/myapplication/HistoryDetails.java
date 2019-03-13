package com.example.siddharth.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class HistoryDetails implements Parcelable
{
    ExamDetails examDetails;
    ExamResultDetails resultDetails;


    public ExamDetails getExamDetails() {
        return examDetails;
    }

    public void setExamDetails(ExamDetails examDetails) {
        this.examDetails = examDetails;
    }

    public ExamResultDetails getResultDetails() {
        return resultDetails;
    }

    public void setResultDetails(ExamResultDetails resultDetails) {
        this.resultDetails = resultDetails;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeParcelable(this.examDetails, flags);
        dest.writeParcelable(this.resultDetails, flags);
    }

    public HistoryDetails()
    {
    }

    protected HistoryDetails(Parcel in)
    {
        this.examDetails = in.readParcelable(ExamDetails.class.getClassLoader());
        this.resultDetails = in.readParcelable(ExamResultDetails.class.getClassLoader());
    }

    public static final Parcelable.Creator<HistoryDetails> CREATOR = new Parcelable.Creator<HistoryDetails>()
    {
        @Override
        public HistoryDetails createFromParcel(Parcel source)
        {
            return new HistoryDetails(source);
        }

        @Override
        public HistoryDetails[] newArray(int size)
        {
            return new HistoryDetails[size];
        }
    };
}
