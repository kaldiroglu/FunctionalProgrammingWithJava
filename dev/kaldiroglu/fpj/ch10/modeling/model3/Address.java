package dev.kaldiroglu.fpj.ch10.modeling.model3;


public record Address(String zipCode, String street, String city, String state, String district, String building) {

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
}
