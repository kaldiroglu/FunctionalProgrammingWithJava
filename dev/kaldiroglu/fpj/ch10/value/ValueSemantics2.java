package dev.kaldiroglu.fpj.ch10.value;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ValueSemantics2 {

    public static void main(String[] args) {
        LocalDate date = LocalDate.now(); //That's now
        System.out.println("Date: " + date);

        setDate(date);
        System.out.println("Date: " + date);

        setDate(date);
        System.out.println("Date: " + date);
    }

    public static void setDate(LocalDate date){
        LocalDate newDate = date.plus(5, ChronoUnit.DAYS);
    }
}
