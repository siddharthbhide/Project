package com.example.siddharth.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class ExamResultDetails implements Parcelable {
    private String id;
    private String CourseId;
    private String CourseLevel;
    private String ExamDate;
    private String ExamScore;
    private String TotalTime;
    private String TotalQuestion;
    private String AttemptedQuestion;
    private String NoAttemptedQuestion;
    private String CorrectAnswere;
    private String WrongAnswere;
    private String StartDate;
    private String EndDate;

    public String getStartDate()
    {
        return StartDate;
    }

    public void setStartDate(String startDate)
    {
        StartDate = startDate;
    }

    public String getEndDate()
    {
        return EndDate;
    }

    public void setEndDate(String endDate)
    {
        EndDate = endDate;
    }

    public String getExamType()
    {
        return ExamType;
    }

    public void setExamType(String examType)
    {
        ExamType = examType;
    }

    private String ExamType;

    public String getExamName()
    {
        return ExamName;
    }

    public void setExamName(String examName)
    {
        ExamName = examName;
    }

    public static Creator<ExamResultDetails> getCREATOR()
    {
        return CREATOR;
    }

    private String ExamName;

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    private String examId;

    public int getUploaded() {
        return Uploaded;
    }

    public void setUploaded(int uploaded) {
        Uploaded = uploaded;
    }

    private int Uploaded;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return CourseId;
    }
    public void setCourseId(String courseId) {
        CourseId = courseId;
    }

    public String getCourseLevel() {
        return CourseLevel;
    }
    public void setCourseLevel(String courseLevel) {
        CourseLevel = courseLevel;
    }

    public String getExamDate() {
        return ExamDate;
    }
    public void setExamDate(String examDate) {
        ExamDate = examDate;
    }


    public String getExamScore() {
        return ExamScore;
    }
    public void setExamScore(String examScore) {
        ExamScore = examScore;
    }

    public String getTotalTime() {
        return TotalTime;
    }
    public void setTotalTime(String totalTime) {
        TotalTime = totalTime;
    }


    public String getTotalQuestion() {
        return TotalQuestion;
    }
    public void setTotalQuestion(String totalQuestion) {
        TotalQuestion = totalQuestion;
    }

    public String getAttemptedQuestion() {
        return AttemptedQuestion;
    }
    public void setAttemptedQuestion(String attemptedQuestion) {
        AttemptedQuestion = attemptedQuestion;
    }

    public String getNoAttemptedQuestion() {
        return NoAttemptedQuestion;
    }
    public void setNoAttemptedQuestion(String noAttemptedQuestion) {
        NoAttemptedQuestion = noAttemptedQuestion;
    }

    public String getCorrectAnswere() {
        return CorrectAnswere;
    }
    public void setCorrectAnswere(String correctAnswere) {
        CorrectAnswere = correctAnswere;
    }

    public String getWrongAnswere() {
        return WrongAnswere;
    }
    public void setWrongAnswere(String wrongAnswere) {
        WrongAnswere = wrongAnswere;
    }

    public ExamResultDetails() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.CourseId);
        dest.writeString(this.CourseLevel);
        dest.writeString(this.ExamDate);
        dest.writeString(this.ExamScore);
        dest.writeString(this.TotalTime);
        dest.writeString(this.TotalQuestion);
        dest.writeString(this.AttemptedQuestion);
        dest.writeString(this.NoAttemptedQuestion);
        dest.writeString(this.CorrectAnswere);
        dest.writeString(this.WrongAnswere);
        dest.writeInt(this.Uploaded);
        dest.writeString(this.examId);
        dest.writeString(this.StartDate);
        dest.writeString(this.EndDate);
        dest.writeString(this.ExamType);
    }

    protected ExamResultDetails(Parcel in) {
        this.id = in.readString();
        this.CourseId = in.readString();
        this.CourseLevel = in.readString();
        this.ExamDate = in.readString();
        this.ExamScore = in.readString();
        this.TotalTime = in.readString();
        this.TotalQuestion = in.readString();
        this.AttemptedQuestion = in.readString();
        this.NoAttemptedQuestion = in.readString();
        this.CorrectAnswere = in.readString();
        this.WrongAnswere = in.readString();
        this.Uploaded = in.readInt();
        this.examId = in.readString();
        this.ExamName = in.readString();
        this.StartDate = in.readString();
        this.EndDate = in.readString();
        this.ExamType = in.readString();
    }

    public static final Creator<ExamResultDetails> CREATOR = new Creator<ExamResultDetails>() {
        @Override
        public ExamResultDetails createFromParcel(Parcel source) {
            return new ExamResultDetails(source);
        }

        @Override
        public ExamResultDetails[] newArray(int size) {
            return new ExamResultDetails[size];
        }
    };
}