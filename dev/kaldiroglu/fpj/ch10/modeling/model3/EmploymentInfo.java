package dev.kaldiroglu.fpj.ch10.modeling.model3;

import java.time.LocalDate;
import java.time.Period;

public record EmploymentInfo(LocalDate startDate, EmploymentLevel level, Department department) {

    public Period employmentPeriod(){
        return Period.between(LocalDate.now(), startDate);
    }

    public int employmentPeriodInYears(){
        return employmentPeriod().getYears();
    }
}
