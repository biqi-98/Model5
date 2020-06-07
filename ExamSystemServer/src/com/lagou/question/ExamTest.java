package com.lagou.question;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

public class ExamTest {

    public static List<Question> questionRandom(List<Question> questions,Integer i){
        Collections.shuffle(questions);
        //随机抽取i个值
        return questions.size() < i ? questions : questions.subList(0, i);
    }

    public static Integer getScore(List<MyQuestion> myQuestions){
        Integer score=0;
        for (MyQuestion myQuestion:myQuestions) {
            if(myQuestion.getQuestion().getAnswer().equals(myQuestion.getMyAnswer())){
                score+=100/myQuestions.size();
            }
        }
//        DecimalFormat df   = new DecimalFormat("######0.00");
//        return df.format(score);
        return score;
    }


}
