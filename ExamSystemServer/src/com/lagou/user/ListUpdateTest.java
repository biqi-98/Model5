package com.lagou.user;

import com.lagou.model.User;

import java.util.List;
import java.util.Scanner;

public class ListUpdateTest {
    //修改用户密码
    public static List<User> update(List<User> users,int index,String password) {
        Scanner sc = new Scanner(System.in);
        User user= users.get(index);
        user.setPassword(password);
        users.set(index,user);
        return users;
    }
}
