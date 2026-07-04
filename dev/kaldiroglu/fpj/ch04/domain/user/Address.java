package dev.kaldiroglu.fpj.ch04.domain.user;

public class Address {

    private final String city;

    public Address(String city) {
        this.city = city;
    }

    public String city() {
        return city;
    }

    @Override
    public String toString() {
        return "Address[" + city + "]";
    }
}
