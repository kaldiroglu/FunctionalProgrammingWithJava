package dev.kaldiroglu.fpj.ch10.generic;

public class GenericTest {

	public static void main(String[] args) {
		GenericRecord r1 = new GenericRecord<String, Integer>("Java", 16);
		System.out.println("T: " + r1.t());
		System.out.println("U: " + r1.u());
		System.out.println("Hash code: " + r1.hashCode());
		System.out.println(r1);
		
		Object record = null;
		
		System.out.println();

		GenericRecord r2 = new GenericRecord<Double, Double>(Math.PI, 5.0);
		System.out.println("Hash code: " + r2.hashCode());
		boolean b = r1.equals(r2);
		System.out.println("Are they equal?" + b);
		System.out.println(r2);
	}
}
