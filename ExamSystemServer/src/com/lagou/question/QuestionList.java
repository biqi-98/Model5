package com.lagou.question;

import com.lagou.server.ServerInitClose;

import java.io.IOException;
import java.util.List;

public class QuestionList {
    //试题添加
    public static List<Question> add(List<Question> questions, Question question, ServerInitClose sic) throws IOException {

        for(int i=0;i<questions.size();i++){
            if(questions.get(i).getId().equals(question.getId())){
                try {
                    sic.getOos().writeObject("试题编号已存在！");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return questions;
            }
        }
        questions.add(question);
        sic.getOos().writeObject("试题添加成功！");
        return questions;

    }
    //删除试题
    public static List<Question> delete(List<Question> questions,Integer id, ServerInitClose sic){

        for(int i=0;i<questions.size();i++){
            if(questions.get(i).getId().equals(id)){
                questions.remove(i);
                try {
                    sic.getOos().writeObject("试题删除成功！");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return questions;
            }
        }
        try {
            sic.getOos().writeObject("试题库里面没有该编号的试题！");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }
    //修改试题
    public static List<Question> update(List<Question> questions,Question question,ServerInitClose sic) throws  IOException {

        Integer id=question.getId();
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getId().equals(id)) {
                questions.set(i, question);
                sic.getOos().writeObject("修改成功！");
                return questions;
            }
        }
        sic.getOos().writeObject("试题库里面没有该试题");
        return questions;
    }
    //试题查询模块
    public static void check(List<Question> questions,Integer id, ServerInitClose sic){
        for(int i=0;i<questions.size();i++){
            if(questions.get(i).getId().equals(id)){
                try {
                    sic.getOos().writeObject(questions.get(i).toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
        try {
            sic.getOos().writeObject("试题库里面没有该编号的试题！");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }
}
