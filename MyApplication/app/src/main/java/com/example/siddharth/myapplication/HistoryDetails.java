package com.example.siddharth.myapplication;

public class HistoryDetails
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
}
