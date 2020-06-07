package com.lagou.student;

import com.lagou.server.ServerInitClose;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class StudentList {
    //学生信息添加模块
    public static List<Student> add(List<Student> students, Student st, ServerInitClose sic) throws AgeException, IdException, IOException {

        for(int i=0;i<students.size();i++){
            if(students.get(i).getNumber().equals(st.getNumber())){
                try {
                    sic.getOos().writeObject("学生信息输入重复！");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return students;
            }
        }
        students.add(st);
        sic.getOos().writeObject("学生信息添加成功！");
        return students;

    }
    //删除学生信息模块
    public static List<Student> delete(List<Student> students,Integer number, ServerInitClose sic){

        for(int i=0;i<students.size();i++){
            if(students.get(i).getNumber().equals(number)){
                students.remove(i);
                try {
                    sic.getOos().writeObject("学生信息删除成功！");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return students;
            }
        }
        try {
            sic.getOos().writeObject("学生档案库里面没有该学号！");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
    //学生查询模块
    public static void check(List<Student> students,Integer number, ServerInitClose sic){
        for(int i=0;i<students.size();i++){
            if(students.get(i).getNumber().equals(number)){
                try {
                    sic.getOos().writeObject(students.get(i).toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
        try {
            sic.getOos().writeObject("学生档案库里面没有该学号！");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }
    //修改学生信息模块
    public static List<Student> update(List<Student> students,Student st,ServerInitClose sic) throws AgeException, IdException, IOException {
        Scanner sc = new Scanner(System.in);

        Integer number=st.getNumber();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getNumber().equals(number)) {
                students.set(i, st);
                sic.getOos().writeObject("修改成功！");
                return students;
            }
        }
        sic.getOos().writeObject("学生档案库里面没有该学号");
        return students;
    }
}
