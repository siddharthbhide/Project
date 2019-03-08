package com.example.siddharth.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class ExamDetails implements Parcelable
{

    private String id;
    private String name;
    private String course;
    private String level;
    private String type;
    private String startDate;
    private String endDate;
    private String isCompleted;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }

    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getIsCompleted() {
        return isCompleted;
    }
    public void setIsCompleted(String isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.course);
        dest.writeString(this.level);
        dest.writeString(this.type);
        dest.writeString(this.startDate);
        dest.writeString(this.endDate);
        dest.writeString(this.isCompleted);
    }

    public ExamDetails()
    {
    }

    protected ExamDetails(Parcel in)
    {
        this.id = in.readString();
        this.name = in.readString();
        this.course = in.readString();
        this.level = in.readString();
        this.type = in.readString();
        this.startDate = in.readString();
        this.endDate = in.readString();
        this.isCompleted = in.readString();
    }

    public static final Parcelable.Creator<ExamDetails> CREATOR = new Parcelable.Creator<ExamDetails>()
    {
        @Override
        public ExamDetails createFromParcel(Parcel source)
        {
            return new ExamDetails(source);
        }

        @Override
        public ExamDetails[] newArray(int size)
        {
            return new ExamDetails[size];
        }
    };
}

