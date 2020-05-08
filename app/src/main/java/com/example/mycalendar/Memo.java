package com.example.mycalendar;

import java.io.Serializable;

public class Memo implements Serializable {
    String maintext;//메모
    String subtext;//날짜
    int isdone;//완료 //int값을 플래그 값으로 활용 ex) 0=시작안함 1=진행중 2=완료 같은 느낌


    public Memo(String maintext, String subtext, int isdone){
        this.maintext=maintext;
        this.subtext=subtext;
        this.isdone=isdone;
    }

    public int getIsdone() {
        return isdone;
    }

    public void setIsdone(int isdone) {
        this.isdone = isdone;
    }

    public String getMaintext() {
        return maintext;
    }

    public void setMaintext(String maintext) {
        this.maintext = maintext;
    }

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }


}
