package com.ionov;


import com.ionov.noRepeatEnter.NoRepeatEnter;
import com.ionov.windows.WindowAppWorked;
import com.ionov.windows.WindowStart;
import com.ionov.windows.WindowStartWithAuto;
import com.ionov.workWithDB.WorkWithDB;

public class RunClass {

    public static void main(String[] args) {
        NoRepeatEnter repeatEnter = new NoRepeatEnter();
            if (!repeatEnter.isExistWorkedApp()) {
                if (!WorkWithDB.getWork().isExistDB()) {
                    WorkWithDB.getWork().createDB();
                }
                if (WorkWithDB.getWork().isExistUsers()) {
                    new WindowStartWithAuto();
                } else {
                    new WindowStart();
                }
            } else {
                new WindowAppWorked();
            }
    }
}
