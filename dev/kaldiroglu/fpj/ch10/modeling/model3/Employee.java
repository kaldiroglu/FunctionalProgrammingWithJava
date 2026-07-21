package dev.kaldiroglu.fpj.ch10.modeling.model3;

import java.util.Objects;

public class Employee {
    private ID id;
    private Address address;
    private PhoneInfo phoneInfo;
    private SalaryInfo salaryInfo;
    private EmploymentInfo employmentInfo;

    public boolean areYouIn(String city){
        return address.isIn(city);
    }

    public int employmentPeriodInYears(){
        return employmentInfo.employmentPeriodInYears();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
