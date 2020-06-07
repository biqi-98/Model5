package com.lagou;

import com.lagou.model.User;
import com.lagou.question.Paper;
import com.lagou.question.Question;
import com.lagou.student.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class writeNew {
    //初始化User
    private static List<User> users=new ArrayList<>();
    private static List<Student> students=new ArrayList<>();
    private static List<Question> questions=new ArrayList<>();
    private static List<Paper> papers=new ArrayList<>();
    private static ObjectOutputStream oos = null;
    public static void User() {
        File f1 = new File("./studentUsers.txt");
        if (!f1.exists()) {
            try {
                f1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            oos = new ObjectOutputStream(new FileOutputStream("./studentUsers.txt"));
            users.add(new User("aaa","111","student"));
            users.add(new User("bbb","222","student"));
            oos.writeObject(users);
            System.out.println("写入对象成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (null != oos) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void Question() {
        File f1 = new File("./question.txt");
        if (!f1.exists()) {
            try {
                f1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            oos = new ObjectOutputStream(new FileOutputStream("./question.txt"));
            oos.writeObject(questions);
            System.out.println("写入对象成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (null != oos) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void Paper() {
        File f1 = new File("./paper.txt");
        if (!f1.exists()) {
            try {
                f1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            oos = new ObjectOutputStream(new FileOutputStream("./paper.txt"));
            oos.writeObject(papers);
            System.out.println("写入对象成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (null != oos) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void Student() {
        File f1 = new File("./student.txt");
        if (!f1.exists()) {
            try {
                f1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            oos = new ObjectOutputStream(new FileOutputStream("./student.txt"));
            oos.writeObject(students);
            System.out.println("写入对象成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (null != oos) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        User();
        Question();
        Paper();
        Student();
    }
}
