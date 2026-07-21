package dev.kaldiroglu.fpj.ch10.shape;

import java.io.Serializable;
import java.util.Objects;

public record Circle(double r) {

    public Circle {
        if(r < 0)
            throw new IllegalArgumentException("r must be positive: " + r);
    }

//    public Circle(double r) {
//        this.r = r;
//        if(r < 0)
//            throw new IllegalArgumentException("r must be positive: " + r);
//    }

    public Circle(){
        this(10);
    }

    public double r(){
        System.out.println("Returning r.");
        return r;
    }

    // Redundant!
    public double getR(){
        System.out.println("Getting r.");
        return r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.r, r) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r);
    }

    @Override
    public String toString() {
        return "A record: Circle{" +
                "r=" + r +
                '}';
    }
}
