package com.lagou.server;

import com.lagou.model.User;
import com.lagou.question.Paper;
import com.lagou.question.Question;
import com.lagou.student.Student;
import com.lagou.user.ListUpdateTest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 编程实现数据的存取
 */
public class ServerDao {

    List<User> users=new ArrayList<>();
    List<Question> questions=new ArrayList<>();
    List<Student> students=new ArrayList<>();
    List<Paper> papers=new ArrayList<>();
    ObjectInputStream ois = null;
    ObjectOutputStream oos = null;

    /**
     * 编程实现管理员账号和密码的校验并将结果返回出去
     * @param user
     * @return
     */
    public boolean serverManagerCheck(User user) {
        if ("admin".equals(user.getUserName()) && "123456".equals(user.getPassword())) {
            return true;
        }
        return false;
    }

    /**
     * 编程实现学生账号和密码的校验并将结果返回出去
     * @param user
     * @return
     */
    public int serverStudentCheck(User user) {
        int i=0;
        for(User user1:users){
            if (user1.getUserName().equals(user.getUserName()) && user1.getPassword().equals(user.getPassword())) {
                return i;
            }
            i++;
        }

        return -1;
    }

    public void readUser() {
        File f1 = new File("./studentUsers.txt");

        // 若文件存在则获取文件,读取文件中所有学生信息到集合中
        if (!f1.exists()) {
            try {
                f1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
//            FileInputStream fileInputStream = new FileInputStream("./studentUsers.txt");
            InputStream fileInputStream = new FileInputStream("./studentUsers.txt");
            ois = new ObjectInputStream(fileInputStream);
            Object obj = ois.readObject();
            users = (List<User>) obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != ois) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void writeUser() {

        //当系统退出时将 List 集合中所有用户信息写入到文件中
        try {
            oos = new ObjectOutputStream(new FileOutputStream("./studentUsers.txt"));
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

    public void readStudent() {
        File f1 = new File("./student.txt");
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        // 若文件存在则获取文件,读取文件中所有学生信息到集合中
        if (f1.exists()) {
            try {
                ois = new ObjectInputStream(new FileInputStream("./student.txt"));
                Object obj = ois.readObject();
                students= (List<Student>) obj;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (null != ois) {
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public void writeStudent() {

        //当系统退出时将 List 集合中所有用户信息写入到文件中
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
    public void readQuestions() {
        File f1 = new File("./question.txt");

        // 若文件存在则获取文件,读取文件中所有试题
        if (!f1.exists()) {
            try {
                f1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            InputStream fileInputStream = new FileInputStream("./question.txt");
            ois = new ObjectInputStream(fileInputStream);
            Object obj = ois.readObject();
            questions = (List<Question>) obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != ois) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void writeQuestions() {

        //当系统退出时将 List 集合中所有信息写入到文件中
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

    public void updateUsers(int userIndex,String password, ServerInitClose sic) throws IOException {

        users=ListUpdateTest.update(users,userIndex,password);
        sic.getOos().writeObject("修改密码成功！");
    }

    public void readPapers() {
        File f1 = new File("./paper.txt");

        // 若文件存在则获取文件,读取文件中所有内容
        if (!f1.exists()) {
            try {
                f1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            InputStream fileInputStream = new FileInputStream("./paper.txt");
            ois = new ObjectInputStream(fileInputStream);
            Object obj = ois.readObject();
            papers = (List<Paper>) obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != ois) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void writePapers() {

        //当系统退出时将 List 集合中所有信息写入到文件中
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


}
