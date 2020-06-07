package com.lagou.write;

import com.lagou.question.Paper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class WriteFile {
    public static void writePaper(List<Paper> papers,String filePath) {
        String fileName=filePath+"/"+System.currentTimeMillis()+"paper.txt";
        File f1 = new File(fileName);
        ObjectOutputStream oos = null;
        if (!f1.exists()) {
            try {
                f1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeBytes(papers.toString());
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
