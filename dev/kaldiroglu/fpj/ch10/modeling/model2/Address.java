package dev.kaldiroglu.fpj.ch10.modeling.model2;

import dev.kaldiroglu.fpj.ch10.modeling.model2.format.AddressFormat;

import java.util.Objects;

public class Address {
    private String zipCode;
    private String city;
    private String district;
    private String street;
    private String building;

    public boolean in(String city){
        return this.city.equals(city);
    }

    public String format(){
        StringBuilder sb = new StringBuilder("Address");
        sb.append("\nZip Code: ").append(zipCode);
        sb.append("\nCity: ").append(city);
        sb.append("\nDistrict: ").append(district);
        sb.append("\nStreet: ").append(street);
        sb.append("\nBuilding: ").append(building);
        return sb.toString();
    }

    public String format(AddressFormat format){
        StringBuilder sb = new StringBuilder("Address");
        // Use AddressFormat here!
        return sb.toString();
    }

    public boolean isIn(String city) {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(zipCode, address.zipCode) && Objects.equals(city, address.city) && Objects.equals(district, address.district) && Objects.equals(street, address.street) && Objects.equals(building, address.building);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, city, district, street, building);
    }
}
