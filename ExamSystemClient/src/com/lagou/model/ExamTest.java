package com.lagou.model;

import com.lagou.client.ClientScanner;
import com.lagou.question.MyQuestion;
import com.lagou.question.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExamTest {

    public static List<MyQuestion> question(List<Question> questions){
        List<MyQuestion> myQuestions=new ArrayList<>();
        ClientScanner.getScanner().nextLine();
        for (Question question:questions){
            System.out.println(question.show());
            System.out.println("请输入你的选项");
            String myAnswer= ClientScanner.getScanner().nextLine();
            MyQuestion myQuestion=new MyQuestion(question,myAnswer);
            myQuestions.add(myQuestion);
        }

        return myQuestions;
    }




}
