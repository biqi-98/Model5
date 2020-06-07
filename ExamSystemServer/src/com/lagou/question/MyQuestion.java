package com.lagou.question;

import java.io.Serializable;
import java.util.Scanner;

/**
 * 问题和自己的答案
 */
public class MyQuestion implements Serializable {
    private static final long serialVersionUID = -7022423693263670396L;
    Question question;

    String myAnswer;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getMyAnswer() {
        return myAnswer;
    }

    public void setMyAnswer(String myAnswer) {
        this.myAnswer = myAnswer;
    }

    public MyQuestion(Question question, String myAnswer) {
        this.question = question;
        this.myAnswer = myAnswer;
    }

    public MyQuestion(Question question) {
        this.question = question;
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入答案");
        this.setMyAnswer(sc.nextLine());
    }

    @Override
    public String toString() {
        return "MyQuestion{" +
                "question=" + question.show() +
                ", myAnswer='" + myAnswer + '\'' +
                '}';
    }
}
