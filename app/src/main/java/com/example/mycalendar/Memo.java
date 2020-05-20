package com.example.mycalendar;

import java.io.Serializable;

public class Memo implements Serializable {

    int seq; //DB에서의 primary key
    String maintext;//메모
    String subtext;//날짜
    String timetext; //시간
    int isdone;//완료 //int값을 플래그 값으로 활용 ex) 0=시작안함 1=진행중 2=완료 같은 느낌


    public Memo(int seq,String maintext,String subtext,String timetext,int isdone){
        this.seq=seq;
        this.maintext=maintext;
        this.subtext=subtext;
        this.timetext=timetext;
        this.isdone=isdone;

    }

    public Memo(String maintext, String subtext, String timetext, int isdone){
        this.maintext=maintext;
        this.subtext=subtext;
        this.timetext=timetext;
        this.isdone=isdone;

    }

    public String getTimetext() {
        return timetext;
    }

    public void setTimetext(String timetext) {
        this.timetext = timetext;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
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
