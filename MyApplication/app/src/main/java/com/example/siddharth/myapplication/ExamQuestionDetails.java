package com.example.siddharth.myapplication;


public class ExamQuestionDetails
{
    private String id;
    private String description;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String course;
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

    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }

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
}
