package dev.kaldiroglu.fpj.ch10.value.employee;

class InvalidTcknException extends RuntimeException {
    public InvalidTcknException(String tckn) {
        super("Invalid TCKN supplied: " + tckn);
    }
}
