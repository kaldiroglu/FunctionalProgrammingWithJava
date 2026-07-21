package dev.kaldiroglu.fpj.ch10.shape;

public class TriangleTest {

	public static void main(String[] args) {
		Triangle t1 = new Triangle(3, 4, 5);
		System.out.println("Area: " + t1.calculateArea());
		System.out.println("Area: " + t1.calculateCircumference());

		System.out.println();

		Triangle t2 = new Triangle(3, 3, 5, "Eşkenar ücgen");
		System.out.println("Area: " + t2.calculateArea());
		System.out.println("Area: " + t2.calculateCircumference());

		System.out.println();

		System.out.println("Number of triangle objects: " + Triangle.count());
	}
}
