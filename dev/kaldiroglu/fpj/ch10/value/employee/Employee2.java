package dev.kaldiroglu.fpj.ch10.value.employee;

public class Employee2{
    private final Tckn1 tckn;
    private final Name name;
    private final Address address;
    private double salary;

    public Employee2(Tckn1 tckn, Name name, Address address) {
        this.tckn = tckn;
        this.name = name;
        this.address = address;
    }

    public void promote(double promotionPercentage) {
        salary *= (1 + promotionPercentage);
    }
}
