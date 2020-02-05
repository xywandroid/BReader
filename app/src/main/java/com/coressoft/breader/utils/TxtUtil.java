package com.coressoft.breader.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by xing.yw on 2017/7/25.
 */

public class TxtUtil {
    private static final String TEMP_PATH = Environment.getExternalStorageDirectory()+"";
    public static File writeTxtFile(String content){
//        makeRootDirectory(TEMP_PATH);
        File file = new File(TEMP_PATH + "/temp.txt");
        if (file.exists()) {
            System.out.print("文件存在");
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(file);
                fileWriter.write("");
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.print("文件不存在");
            try {
                file.createNewFile();// 不存在则创建
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        RandomAccessFile mm=null;
        boolean flag=false;
        FileOutputStream o=null;
        try {
            o = new FileOutputStream(file);
            o.write(content.getBytes());
            o.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            if(mm!=null){
                try {
                    mm.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

//    public static void makeRootDirectory(String filePath) {
//        File file = new File(filePath);
//        if (!file.exists()){
//            file.mkdir();
//        }
//    }

}
