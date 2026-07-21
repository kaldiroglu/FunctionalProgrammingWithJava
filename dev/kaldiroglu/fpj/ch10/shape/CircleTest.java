package dev.kaldiroglu.fpj.ch10.shape;

public class CircleTest {

	public static void main(String[] args) {
		Circle c1 = new Circle(5);
		System.out.println("Radius: " + c1.r());
		System.out.println("Radius: " + c1.getR());
		System.out.println(c1);

		Circle c2 = new Circle(5);

		System.out.println();

		System.out.println("Are they equal? " + c1.equals(c2));
		System.out.println(c1.hashCode());
		System.out.println(c2.hashCode());

		System.out.println();
		Circle c3 = new Circle();
	}
}
