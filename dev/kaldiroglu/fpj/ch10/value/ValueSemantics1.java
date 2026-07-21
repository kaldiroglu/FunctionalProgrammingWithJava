package dev.kaldiroglu.fpj.ch10.value;

import java.util.Date;

public class ValueSemantics1 {

    public static void main(String[] args) {
        Date date = new Date(); //That's now
        System.out.println("Date: " + date);

        setDate(new Date(date.getTime()));
        System.out.println("Date: " + date);

        setDate(date);
        System.out.println("Date: " + date);
    }

    public static void setDate(Date date){
        date.setDate(5);
    }
}
