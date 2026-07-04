package dev.kaldiroglu.fpj.ch04.domain.computer.good;

import java.util.Optional;

public class ComputerFactory {
    Optional<Computer> createComputer() {
        return Optional.of(new Computer());
    }
}
