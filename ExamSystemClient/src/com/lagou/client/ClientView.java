package com.lagou.client;

import com.lagou.model.ExamTest;
import com.lagou.model.User;
import com.lagou.model.UserMessage;
import com.lagou.question.MyQuestion;
import com.lagou.question.Paper;
import com.lagou.question.PaperList;
import com.lagou.question.Question;
import com.lagou.student.AgeException;
import com.lagou.student.IdException;
import com.lagou.student.Student;
import com.lagou.write.WriteFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * 编程实现客户端的主界面绘制和相应功能的实现
 */
public class ClientView {
    /**
     * 为了可以使用输入输出流，采用合成复用原则实现
     */
    private ClientInitClose cic;

    /**
     * 通过构造方法实现成员变量的初始化
     *
     * @param cic
     */
    public ClientView(ClientInitClose cic) {
        this.cic = cic;
    }

    /**
     * 自定义成员方法实现客户端主界面的绘制
     */
    public void clientMainPage() throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("  \n\n\t\t   在线考试系统");
            System.out.println("-------------------------------------------");
            System.out.print("   [1] 学员系统");
            System.out.println("     [2] 管理员系统");
            System.out.println("   [0] 退出系统");
            System.out.println("-------------------------------------------");
            System.out.println("请选择要进行的业务编号：");

            int choose = ClientScanner.getScanner().nextInt();
            cic.getOos().writeObject(choose);
            switch (choose) {
                case 1:
                    clientStudentLogin();
                    break;
                case 2:
                    clientManagerLogin();
                    break;
                case 0:
                    System.out.println("正在退出系统...");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    return;
                default:
                    System.out.println("输入错误，请重新选择！");
            }
        }
    }

    /**
     * 自定义成员方法实现客户端管理员登录的功能
     */
    private void clientManagerLogin() throws IOException, ClassNotFoundException {
        // 1.准备管理员登录的相关数据
        System.out.println("请输入管理员的账户信息：");
        String userName = ClientScanner.getScanner().next();
        System.out.println("请输入管理员的密码信息：");
        String password = ClientScanner.getScanner().next();
        UserMessage tum = new UserMessage("managerCheck", new User(userName, password, "admin"));
        // 2.将UserMessage类型的对象通过对象输出流发送给服务器
        cic.getOos().writeObject(tum);
        System.out.println("客户端发送管理员账户信息成功！");
        // 3.接收服务器的处理结果并给出提示
        tum = (UserMessage) cic.getOis().readObject();
        if ("success".equals(tum.getType())) {
            System.out.println("登录成功，欢迎使用！");
            clientManager();
        } else {
            System.out.println("用户名或密码错误！");
        }
    }

    /**
     * 自定义成员方法实现修改密码
     */
    private void updateUserPassword() throws IOException, ClassNotFoundException {
        System.out.println("请输入想要修改的密码：");
        String password = ClientScanner.getScanner().next();

        // 2.将UserMessage类型的对象通过对象输出流发送给服务器
        cic.getOos().writeObject(password);
        System.out.println("客户端发送密码成功！");

    }

    /**
     * 自定义成员方法实现客户端管理员模块的功能
     */
    private void clientManager() throws IOException, ClassNotFoundException {
        // 1.绘制管理员模块
        while (true) {
            System.out.println("  \n\n\t\t   管理员系统");
            System.out.println("-------------------------------------------");
            System.out.print("   [1] 增加学员");
            System.out.println("     [2] 删除学员");
            System.out.print("   [3] 修改学员");
            System.out.println("     [4] 查找学员");
            System.out.print("   [5] 增加考题");
            System.out.println("     [6] 删除考题");
            System.out.print("   [7] 修改考题");
            System.out.println("     [8] 查找考题");
            System.out.println("   [9] 导入考题（暂无此功能）");
            System.out.println("     [0] 退出系统");
            System.out.println("-------------------------------------------");
            System.out.println("请选择要进行的业务编号：");

            int choose = ClientScanner.getScanner().nextInt();
            Student student = null;
            Integer id;
            Question question = null;
            cic.getOos().writeObject(choose);
            switch (choose) {
                case 1:
                    try {
                        student = new Student();
                    } catch (AgeException e) {
                        e.printStackTrace();
                    } catch (IdException e) {
                        e.printStackTrace();
                    }
                    cic.getOos().writeObject(student);
                    System.out.println(cic.getOis().readObject());
                    break;
                case 2:
                    System.out.println("请输入学生学号删除学生信息:");
                    id = ClientScanner.getScanner().nextInt();
                    cic.getOos().writeObject(id);
                    System.out.println(cic.getOis().readObject());
                    break;
                case 3:
                    System.out.println("请输入需要修改信息的这位学生的学号:");
                    try {
                        Integer number = ClientScanner.getScanner().nextInt();
                        student = new Student(number);
                    } catch (AgeException e) {
                        e.printStackTrace();
                    } catch (IdException e) {
                        e.printStackTrace();
                    }
                    cic.getOos().writeObject(student);
                    System.out.println(cic.getOis().readObject());
                    break;
                case 4:
                    System.out.println("请输入学生学号查询学生信息:");
                    Integer number = ClientScanner.getScanner().nextInt();
                    cic.getOos().writeObject(number);
                    System.out.println(cic.getOis().readObject());
                    break;
                case 5:
                    question = new Question();
                    cic.getOos().writeObject(question);
                    System.out.println(cic.getOis().readObject());
                    break;
                case 6:
                    System.out.println("请输入试题编号删除试题:");
                    id = ClientScanner.getScanner().nextInt();
                    cic.getOos().writeObject(id);
                    System.out.println(cic.getOis().readObject());
                    break;
                case 7:
                    System.out.println("请输入需要修改信息的试题编号:");
                    id = ClientScanner.getScanner().nextInt();
                    question = new Question(id);
                    cic.getOos().writeObject(question);
                    System.out.println(cic.getOis().readObject());
                    break;
                case 8:
                    System.out.println("请输入试题编号查询试题信息:");
                    id = ClientScanner.getScanner().nextInt();
                    cic.getOos().writeObject(id);
                    System.out.println(cic.getOis().readObject());
                    break;
                case 9:
                    System.out.println("暂未开发此功能");
                    break;
                case 0:
                    System.out.println("正在退出系统...");
                    return;
                default:
                    System.out.println("输入错误，请重新选择！");
            }
        }
    }

    /**
     * 自定义成员方法实现客户端学生模块的功能
     */
    private void clientStudent() throws IOException, ClassNotFoundException {
        // 1.绘制学生模块
        while (true) {
            System.out.println("  \n\n\t\t   学员系统");
            System.out.println("-------------------------------------------");
            System.out.print("   [1] 修改密码");
            System.out.println("     [2] 开始考试");
            System.out.print("   [3] 查询成绩");
            System.out.println("     [4] 导出成绩");
            System.out.println("   [0] 退出系统");
            System.out.println("-------------------------------------------");
            System.out.println("请选择要进行的业务编号：");

            int choose = ClientScanner.getScanner().nextInt();
            cic.getOos().writeObject(choose);
            Integer id;
            List<Paper> paperList;
            switch (choose) {
                case 1:
                    updateUserPassword();
                    System.out.println(cic.getOis().readObject());
                    break;
                case 2:
                    List<Question> questions= (List<Question>) cic.getOis().readObject();
                    List<MyQuestion> myQuestions=ExamTest.question(questions);
                    System.out.println("请输入你的学号");
                    id=ClientScanner.getScanner().nextInt();

                    Paper paper=new Paper(new Date(),myQuestions,id);
                    cic.getOos().writeObject(paper);
                    System.out.println(cic.getOis().readObject());
                    break;
                case 3:
                    System.out.println("请输入你的学号");
                    id=ClientScanner.getScanner().nextInt();
                    cic.getOos().writeObject(id);
                    paperList= (List<Paper>) cic.getOis().readObject();
                    System.out.println(paperList);
                    break;
                case 4:
                    System.out.println("请输入你的学号");
                    id=ClientScanner.getScanner().nextInt();
                    cic.getOos().writeObject(id);
                    paperList= (List<Paper>) cic.getOis().readObject();
                    System.out.println("请输入想要导出的位置");
                    String filePath=ClientScanner.getScanner().next();
                    WriteFile.writePaper(paperList,filePath);
                    break;
                case 0:
                    System.out.println("正在退出系统...");
                    return;
                default:
                    System.out.println("输入错误，请重新选择！");
            }
        }
    }

    /**
     * 自定义成员方法实现客户端学生登录的功能
     */
    private void clientStudentLogin() throws IOException, ClassNotFoundException {
        // 1.准备学生登录的相关数据
        System.out.println("请输入学生的账户信息：");
        String userName = ClientScanner.getScanner().next();
        System.out.println("请输入学生的密码信息：");
        String password = ClientScanner.getScanner().next();
        UserMessage tum = new UserMessage("managerCheck", new User(userName, password, "student"));
        // 2.将UserMessage类型的对象通过对象输出流发送给服务器
        cic.getOos().writeObject(tum);
        System.out.println("客户端发送学生账户信息成功！");
        // 3.接收服务器的处理结果并给出提示
        tum = (UserMessage) cic.getOis().readObject();
        if ("success".equals(tum.getType())) {
            System.out.println("登录成功，欢迎使用！");
            clientStudent();
        } else {
            System.out.println("用户名或密码错误！");
        }
    }
}
