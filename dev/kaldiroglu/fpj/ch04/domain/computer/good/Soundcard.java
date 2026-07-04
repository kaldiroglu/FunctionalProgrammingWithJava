package dev.kaldiroglu.fpj.ch04.domain.computer.good;

import java.util.Optional;
public class Soundcard {
    private USB usb;
    public Optional<USB> getUSB() { return Optional.ofNullable(usb); }
}