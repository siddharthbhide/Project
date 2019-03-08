package com.example.siddharth.myapplication;

public class ExamResultDetails
{
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
    private String TimePerQuestion;

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

    public String getTimePerQuestion() {
        return TimePerQuestion;
    }
    public void setTimePerQuestion(String tiimePerQuestion) {
        TimePerQuestion = tiimePerQuestion;
    }

}