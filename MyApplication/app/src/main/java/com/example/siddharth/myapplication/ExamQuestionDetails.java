package com.example.siddharth.myapplication;


import android.os.Parcel;
import android.os.Parcelable;

public class ExamQuestionDetails implements Parcelable
{
    private String id;
    private String description;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    //private String course;
    private String answer;
    private String StudentAns;
    private String StartTime;
    private String EndTime;
    private String ExamId;
    private String LastQuestion;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getOptionA() {
        return optionA;
    }
    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }
    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }
    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }
    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    /*public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }*/

    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getStudentAns() {
        return StudentAns;
    }
    public void setStudentAns(String studentAns) {
        StudentAns = studentAns;
    }

    public String getStartTime() {
        return StartTime;
    }
    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }
    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getExamId() {
        return ExamId;
    }
    public void setExamId(String examId) {
        ExamId = examId;
    }

    public String getLastQuestion() {
        return LastQuestion;
    }
    public void setLastQuestion(String lastQuestion) {
        LastQuestion = lastQuestion;
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
        dest.writeString(this.description);
        dest.writeString(this.optionA);
        dest.writeString(this.optionB);
        dest.writeString(this.optionC);
        dest.writeString(this.optionD);
        dest.writeString(this.answer);
        dest.writeString(this.StudentAns);
        dest.writeString(this.StartTime);
        dest.writeString(this.EndTime);
        dest.writeString(this.ExamId);
        dest.writeString(this.LastQuestion);
    }

    public ExamQuestionDetails()
    {
    }

    protected ExamQuestionDetails(Parcel in)
    {
        this.id = in.readString();
        this.description = in.readString();
        this.optionA = in.readString();
        this.optionB = in.readString();
        this.optionC = in.readString();
        this.optionD = in.readString();
        this.answer = in.readString();
        this.StudentAns = in.readString();
        this.StartTime = in.readString();
        this.EndTime = in.readString();
        this.ExamId = in.readString();
        this.LastQuestion = in.readString();
    }

    public static final Parcelable.Creator<ExamQuestionDetails> CREATOR = new Parcelable.Creator<ExamQuestionDetails>()
    {
        @Override
        public ExamQuestionDetails createFromParcel(Parcel source)
        {
            return new ExamQuestionDetails(source);
        }

        @Override
        public ExamQuestionDetails[] newArray(int size)
        {
            return new ExamQuestionDetails[size];
        }
    };
}
