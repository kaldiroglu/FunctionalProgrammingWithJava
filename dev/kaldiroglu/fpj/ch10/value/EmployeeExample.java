package dev.kaldiroglu.fpj.ch10.value;

import java.util.Date;

public class EmployeeExample {

    public static void main(String[] args) {
        Name name1 = new Name("Ali", "Veli", "Ozturk");
        Name name2 = new Name("Ali", "Veli", "Ozturk");

        if(name1.equals(name2))
            System.out.println("The same");
        else
            System.out.println("Different");

        Object o = name1;
        System.out.println(o);

        System.out.printf(name1.fullName());

    }
}
record Tckn1(String tckn){
    public Tckn1(String tckn) {
        boolean valid = validate(tckn);
        if(!valid)
            throw new InvalidTcknException(tckn);
        this.tckn = tckn;
    }

    private boolean validate (String tckn) {
        boolean valid = false;
        if (tckn == null)
            valid = false;
        else if (tckn.length() != 11)
            valid = false;
        else if (tckn.startsWith("0"))
            valid = false;
        else {
            int totalOdd = 0;
            int totalEven = 0;

            for (int i = 0; i < 9; i++) {
                int val = Integer.valueOf(tckn.substring(i, i + 1));

                if (i % 2 == 0) {
                    totalOdd += val;
                } else {
                    totalEven += val;
                }
            }

            int total = totalOdd + totalEven + Integer.valueOf(tckn.substring(9, 10));
            int lastDigit = total % 10;

            if (tckn.substring(10).equals(String.valueOf(lastDigit))) {
                int check = (totalOdd * 7 - totalEven) % 10;
                if (tckn.substring(9, 10).equals(String.valueOf(check))) {
                    valid = true;
                }
            }
        }
        return valid;
    }
}

class InvalidTcknException extends RuntimeException{
    public InvalidTcknException(String tckn){
        super("Invalid TCKN supplied: " + tckn);
    }
}

record Tckn2(String tckn){
    /**
     * Call <code>validate</code> before creating Tckn2 object.
     * @param tckn
     */
    public Tckn2(String tckn) {
        boolean valid = validate(tckn);
        if(!valid)
            throw new InvalidTcknException(tckn);
        this.tckn = tckn;
    }

    public static boolean validate (String tckn) {
        boolean valid = false;
        if (tckn == null)
            valid = false;
        else if (tckn.length() != 11)
            valid = false;
        else if (tckn.startsWith("0"))
            valid = false;
        else {
            int totalOdd = 0;
            int totalEven = 0;

            for (int i = 0; i < 9; i++) {
                int val = Integer.valueOf(tckn.substring(i, i + 1));

                if (i % 2 == 0) {
                    totalOdd += val;
                } else {
                    totalEven += val;
                }
            }

            int total = totalOdd + totalEven + Integer.valueOf(tckn.substring(9, 10));
            int lastDigit = total % 10;

            if (tckn.substring(10).equals(String.valueOf(lastDigit))) {
                int check = (totalOdd * 7 - totalEven) % 10;
                if (tckn.substring(9, 10).equals(String.valueOf(check))) {
                    valid = true;
                }
            }
        }
        return valid;
    }
}

record Name(String firstName, String middleName, String lastName){
    public String fullName(){
        return firstName + " " + middleName.substring(0, 1) + ". " + lastName;
    }
}

record Address(String zipCode, String city, String neighbourhood, String street, String building){}

class Employee1{
    private final Tckn1 tckn;
    private final Name name;
    private final Address address;
    private double salary;

    public Employee1(Tckn1 tckn, Name name, Address address) {
        this.tckn = tckn;
        this.name = name;
        this.address = address;
    }

    public void promote(double promotionPercentage){
        salary *= (1 +  promotionPercentage);
    }
}

class Employee2{
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

    public Employee2(String firstName, String middleName, String lastName, String zipCode, String city, String neighbourhood, String street, String building) {
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

enum Level{
    STARTER, JUNIOR, MEDIUM, SENIOR
}

record EmploymentInfo(Date startDate, Level level, Department department){}

class Department{
    int id;
    String name;
}
