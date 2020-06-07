package com.lagou.question;

import java.io.Serializable;
import java.util.Scanner;

/**
 * 试题类，题目，选项，正确答案
 */
public class Question implements Serializable {
    private static final long serialVersionUID = -882300242286464757L;
    Integer id;
    //题目
    String content;

    //选项
    String sa;

    String sb;

    String sc;

    String sd;

    //正确答案
    String answer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSa() {
        return sa;
    }

    public void setSa(String sa) {
        this.sa = sa;
    }

    public String getSb() {
        return sb;
    }

    public void setSb(String sb) {
        this.sb = sb;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public String getSd() {
        return sd;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Question(Integer id, String content, String sa, String sb, String sc, String sd, String answer) {
        this.id = id;
        this.content = content;
        this.sa = sa;
        this.sb = sb;
        this.sc = sc;
        this.sd = sd;
        this.answer = answer;
    }

    public Question(){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入试题编号");
        this.setId(sc.nextInt());
        sc.nextLine();//用来接收编号后的回车
        System.out.println("请输入试题内容");
        this.setContent(sc.nextLine());
        System.out.println("请输入选项A");
        this.setSa(sc.nextLine());
        System.out.println("请输入选项B");
        this.setSb(sc.nextLine());
        System.out.println("请输入选项C");
        this.setSc(sc.nextLine());
        System.out.println("请输入选项D");
        this.setSd(sc.nextLine());
        System.out.println("请输入正确选项");
        this.setAnswer(sc.nextLine());
    }

    public Question(Integer id){
        Scanner sc=new Scanner(System.in);
        this.setId(id);
        System.out.println("请输入试题内容");
        this.setContent(sc.nextLine());
        System.out.println("请输入选项A");
        this.setSa(sc.nextLine());
        System.out.println("请输入选项B");
        this.setSb(sc.nextLine());
        System.out.println("请输入选项C");
        this.setSc(sc.nextLine());
        System.out.println("请输入选项D");
        this.setSd(sc.nextLine());
        System.out.println("请输入正确选项");
        this.setAnswer(sc.nextLine());
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", sa='" + sa + '\'' +
                ", sb='" + sb + '\'' +
                ", sc='" + sc + '\'' +
                ", sd='" + sd + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    public String show() {
        return content + '\n' +
                "A." + sa +'\t' +
                "B." + sb +'\t' +
                "C." + sc +'\t' +
                "D." + sd;
    }
}
