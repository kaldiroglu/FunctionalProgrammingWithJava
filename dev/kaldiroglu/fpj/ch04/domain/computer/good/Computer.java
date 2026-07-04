package dev.kaldiroglu.fpj.ch04.domain.computer.good;

import java.util.Optional;

public class Computer {
    private Soundcard soundcard;

    public Optional<Soundcard> getSoundcard() {
        return Optional.ofNullable(soundcard);
    }
}