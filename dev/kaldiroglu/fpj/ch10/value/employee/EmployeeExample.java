package dev.kaldiroglu.fpj.ch10.value.employee;

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
