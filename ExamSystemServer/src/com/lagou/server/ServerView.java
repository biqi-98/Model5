package com.lagou.server;

import com.lagou.client.ClientScanner;
import com.lagou.model.UserMessage;
import com.lagou.question.*;
import com.lagou.student.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 编程实现服务器的主功能
 */
public class ServerView {

    /**
     * 使用合成复用原则
     */
    private ServerInitClose sic;
    private ServerDao sd;
    private int userIndex;


    /**
     * 通过构造方法实现成员变量的初始化
     * @param sic
     */
    public ServerView(ServerInitClose sic, ServerDao sd) {
        this.sic = sic;
        this.sd = sd;
    }

    /**
     * 自定义成员方法实现客户端发来消息的接收并处理
     */
    public void serverReceive() throws IOException, ClassNotFoundException {
        sd.readUser();
        sd.readQuestions();
        sd.readPapers();
        sd.readStudent();
        UserMessage tum = new UserMessage();
        while(true) {
            int choose = (int) sic.getOis().readObject();
            if(choose==0){
                sd.writeUser();
                sd.writeStudent();
                sd.writeQuestions();
                sd.writePapers();
                return;
            }
            tum = (UserMessage) sic.getOis().readObject();
            System.out.println("接收到的消息是：" + tum);
            // 调用方法实现管理员账号和密码信息的校验
            if ("admin".equalsIgnoreCase(tum.getUser().getType())) {
                if (sd.serverManagerCheck(tum.getUser())) {
                    tum.setType("success");
                } else {
                    tum.setType("fail");
                }
            } else {
                userIndex = sd.serverStudentCheck(tum.getUser());
                if (userIndex >= 0) {
                    tum.setType("success");
                } else {
                    tum.setType("fail");
                }
            }
            // 将校验结果发送给客户端
            sic.getOos().writeObject(tum);
            System.out.println("服务器发送校验结果成功！");

            if ("success".equalsIgnoreCase(tum.getType())&& "student".equalsIgnoreCase(tum.getUser().getType())) {
                serverStudentReceive();
            } else if ("success".equalsIgnoreCase(tum.getType()) && "admin".equalsIgnoreCase(tum.getUser().getType())) {
                serverManagerReceive();
            }
        }



    }

    public void serverManagerReceive() throws IOException, ClassNotFoundException {
        Integer number;
        Integer id;
        Question question;
        while(true) {
            int choose = (int) sic.getOis().readObject();
            switch (choose) {
                case 1:
                    try {
                        Student st=(Student) sic.getOis().readObject();
                        StudentList.add(sd.students,st,sic);
                    } catch (AgeException | IdException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    number= (Integer) sic.getOis().readObject();
                    StudentList.delete(sd.students,number,sic);
                    break;
                case 3:
                    try {
                        Student st=(Student) sic.getOis().readObject();
                        StudentList.update(sd.students,st,sic);
                    } catch (AgeException | IdException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    number= (Integer) sic.getOis().readObject();
                    StudentList.check(sd.students,number,sic);
                    break;
                case 5:
                    question=(Question) sic.getOis().readObject();
                    QuestionList.add(sd.questions,question,sic);
                    break;
                case 6:
                    id= (Integer) sic.getOis().readObject();
                    QuestionList.delete(sd.questions,id,sic);
                    break;
                case 7:
                    question=(Question) sic.getOis().readObject();
                    QuestionList.update(sd.questions,question,sic);
                    break;
                case 8:
                    id= (Integer) sic.getOis().readObject();
                    QuestionList.check(sd.questions,id,sic);
                    break;
                case 9:
                    System.out.println("此功能暂未开发");
                    break;
                case 0:
                    System.out.println("正在退出管理员系统...");
                    return;
                default:
                    System.out.println("输入错误，请重新选择！");
            }
        }
    }

    public void serverStudentReceive() throws IOException, ClassNotFoundException {
        Integer studentId;
        List<Paper> paperList;
        while(true) {
            int choose = (int) sic.getOis().readObject();
            switch (choose) {
                case 1:
                    String password = (String) sic.getOis().readObject();
                    sd.updateUsers(userIndex,password,sic);
                    break;
                case 2:
                    List<Question> questionList=ExamTest.questionRandom(sd.questions,4);
                    sic.getOos().writeObject(questionList);
                    Paper paper= (Paper) sic.getOis().readObject();
                    sd.papers.add(paper);
                    sic.getOos().writeObject("试卷已上传");
                    break;
                case 3:
                    studentId= (Integer) sic.getOis().readObject();
                    paperList=PaperList.check(sd.papers,studentId);
                    sic.getOos().writeObject(paperList);
                    break;
                case 4:
                    studentId= (Integer) sic.getOis().readObject();
                    paperList=PaperList.check(sd.papers,studentId);
                    sic.getOos().writeObject(paperList);
                    break;
                case 0:
                    System.out.println("正在退出系统...");
                    return;
                default:
                    System.out.println("输入错误，请重新选择！");
            }
        }
    }
}
