package com.ionov.noRepeatEnter;

import java.io.File;
import java.io.IOException;

/**
 * Created by user on 10.07.2016.
 * можно придумать что то новое ну или использовать идею паши
 * все сделал просто. если неправильно выйти из приложения
 * больше не зайдет в него.
 */
public class NoRepeatEnter {
    private static File file = new File("checkFile.txt");
    /**
     *
     * @return true если уже открыто приложение.
     */
    public  boolean isExistWorkedApp() {
        boolean isExist = true;
        if(file.exists()){
            isExist = true;
            System.out.println(1);
        }else{
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            isExist =  false;
        }
        return isExist;
    }

    public  void deleteFile() {
        file.delete();
    }
}
