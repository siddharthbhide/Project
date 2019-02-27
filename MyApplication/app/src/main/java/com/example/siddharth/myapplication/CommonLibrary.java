package com.example.siddharth.myapplication;

public class CommonLibrary {
}

class StudentDetails {
    private String sex;
    private String fatherName;
    private String address;
    private  String motherName;
    private String fatherOccupation;
    private String motherOccupation;
    private String mobileNo;
    private String school;
    private String std;
    private String courseId;
    private String courseLevel;
    private String email;
    private String franId;
    private String dateAdded;
    private String admissionDate;
    private String studentName;
    private String dateOfBirth;
    private String userName;
    private String password;
    private String id;

    public String getSex() { return this.sex; }
    public void setSex(String strsex) { this.sex = strsex; }

    public String getFatherName() { return this.fatherName; }
    public void setFatherName(String strfatherName) { this.fatherName = strfatherName; }

    public String getAddress() { return this.address; }
    public void setAddress(String straddress) { this.address = straddress; }

    public String getMotherName() { return this.motherName; }
    public void setMotherName(String strmotherName) { this.motherName = strmotherName; }

    public String getFatherOccupation() { return this.fatherOccupation; }
    public void setFatherOccupation(String strfatherOccupation) { this.fatherOccupation = strfatherOccupation; }

    public String getMotherOccupation() { return this.motherOccupation; }
    public void setMotherOccupation(String strMotherOccupation) { this.motherOccupation = strMotherOccupation; }

    public String getMobileNo() { return this.mobileNo; }
    public void setMobileNo(String strmobileNo) { this.mobileNo = strmobileNo; }

    public String getSchool() { return this.school; }
    public void setSchool(String strSchool) { this.school = strSchool; }

    public String getStd() { return this.std; }
    public void setStd(String strStd) { this.std = strStd; }

    public String getCourseId() { return this.courseId; }
    public void setCourseId(String strCourseId) { this.courseId = strCourseId; }

    public String getCourseLevel() { return this.courseLevel; }
    public void setCourseLevel(String strCourseLevel) { this.courseLevel = strCourseLevel; }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFranId() {
        return franId;
    }
    public void setFranId(String franId) {
        this.franId = franId;
    }

    public String getDateAdded() {
        return dateAdded;
    }
    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }
    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}

class Exam_Details
{

    private String id;
    private String name;
    private String course;
    private  String level;
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
}

class ExamQuestions
{

    private String id;
    private String description;
    private String optionA;
    private String optionB;
    private  String optionC;
    private String optionD;
    private String course;
    private String answer;
    private  String StudentAns;
    private String StartTime;
    private  String EndTime;
    private  String ExamId;
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

class ResultDetails
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