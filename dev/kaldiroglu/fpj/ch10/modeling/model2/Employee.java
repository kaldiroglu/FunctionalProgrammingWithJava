package dev.kaldiroglu.fpj.ch10.modeling.model2;

import java.util.Objects;

public class Employee {
    private ID id;
    private Tckn tckn;
    private Name name;
    private Address address;
    private PhoneInfo phoneInfo;
    private SalaryInfo salaryInfo;
    private EmploymentInfo employmentInfo;


    public Employee(ID id, Tckn tckn, Name name, EmploymentInfo employmentInfo, SalaryInfo salaryInfo, PhoneInfo phoneInfo, Address address) {
        this.employmentInfo = employmentInfo;
        this.salaryInfo = salaryInfo;
        this.phoneInfo = phoneInfo;
        this.address = address;
        this.tckn = tckn;
        this.id = id;
    }

    public boolean areYouIn(String city){
        return address.isIn(city);
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
