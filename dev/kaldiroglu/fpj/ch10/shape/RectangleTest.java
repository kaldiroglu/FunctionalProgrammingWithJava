package dev.kaldiroglu.fpj.ch10.shape;

public class RectangleTest {

	public static void main(String[] args) {
		Rectangle r1 = new Rectangle(3, 4);
		System.out.println("Length: " + r1.length());
		System.out.println("Width: " + r1.width());
		System.out.println("Hash code: " + r1.hashCode());
		System.out.println(r1);
		
		Object record = null;
		
		System.out.println();
		
		Rectangle r2 = new Rectangle(3, 4);
		System.out.println("Hash code: " + r2.hashCode());
		boolean b = r1.equals(r2);
		System.out.println("Are they equal? " + b);
		System.out.println(r2);
		
//		r1.length; // length is not visible
	}
}
