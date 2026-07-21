package dev.kaldiroglu.fpj.ch10.modeling.model3;

public class SalaryInfo {
    private double baseSalary;
    private double educationPayment;
    private double handicappedPayment;

    double calculateSalary(){
        //For the simplest case
        return baseSalary + educationPayment + handicappedPayment;
    }
}
