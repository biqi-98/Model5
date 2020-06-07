package com.lagou.question;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * id,考试时间，试题列表，分数,考生ID，分数
 */

public class Paper implements Serializable {
    private static final long serialVersionUID = -1431772584457124778L;
    //考试时间
    Date dateTime;

    List<MyQuestion> myQuestions;
    //分数
    Integer num;

    Integer studentId;

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public List<MyQuestion> getMyQuestions() {
        return myQuestions;
    }

    public void setMyQuestions(List<MyQuestion> myQuestions) {
        this.myQuestions = myQuestions;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Paper(Date dateTime, List<MyQuestion> myQuestions, Integer studentId) {
        this.dateTime = dateTime;
        this.myQuestions = myQuestions;
        this.num = ExamTest.getScore(myQuestions);
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "dateTime=" + dateTime +
                ", myQuestions=" + myQuestions +
                ", num=" + num +
                ", studentId=" + studentId +
                '}';
    }
}
