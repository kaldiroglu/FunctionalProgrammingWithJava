package dev.kaldiroglu.fpj.ch10.value.employee;

public class Employee1 {
    private int id;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String zipCode;
    private final String city;
    private final String neighbourhood;
    private final String street;
    private final String building;
    private double salary;

    public Employee1(String firstName, String middleName, String lastName, String zipCode, String city, String neighbourhood, String street, String building) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.zipCode = zipCode;
        this.city = city;
        this.neighbourhood = neighbourhood;
        this.street = street;
        this.building = building;
    }

    public void promote(double promotionPercentage){
        salary *= (1 +  promotionPercentage);
    }
}