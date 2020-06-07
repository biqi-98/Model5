package com.lagou.question;

import java.util.ArrayList;
import java.util.List;

public class PaperList {
    public static List<Paper> check(List<Paper> papers,Integer studentId){
        List<Paper> paperList =new ArrayList<>();
        for (Paper paper:papers) {
            if(paper.getStudentId().equals(studentId)){
                paperList.add(paper);
            }
        }
        return paperList;
    }
}
