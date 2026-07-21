package dev.kaldiroglu.fpj.ch10.value.employee;

record Name(String firstName, String middleName, String lastName) {
    public String fullName() {
        return firstName + " " + middleName.substring(0, 1) + ". " + lastName;
    }
}
