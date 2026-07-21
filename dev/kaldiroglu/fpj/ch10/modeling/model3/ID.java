package dev.kaldiroglu.fpj.ch10.modeling.model3;

public record ID(int id) {

    public static boolean validate(int id){
        return id >= 0; // A simple validation
    }
}
