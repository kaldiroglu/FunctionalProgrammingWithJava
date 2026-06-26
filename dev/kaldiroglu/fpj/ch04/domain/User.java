package dev.kaldiroglu.fpj.ch04.domain;

import dev.kaldiroglu.fpj.ch04.ProcessingWithOptional;

import java.util.Optional;

public class User {
    private final String name;
    private final boolean active;
    private final Address address;          // null olabilir

    public User(String name, boolean active, Address address) {
        this.name = name;
        this.active = active;
        this.address = address;
    }

    public String name() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public Optional<Address> address() {        // Optional DÖNDÜRÜR (alan değil)
        return Optional.ofNullable(address);
    }

    @Override
    public String toString() {
        return "User[" + name + "]";
    }
}