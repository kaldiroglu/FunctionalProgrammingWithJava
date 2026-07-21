package dev.kaldiroglu.fpj.ch10.modeling.model2.ex;

public class InvalidTcknException extends RuntimeException {
    public InvalidTcknException(String tckn) {
        super("Invalid TCKN supplied: " + tckn);
    }
}
