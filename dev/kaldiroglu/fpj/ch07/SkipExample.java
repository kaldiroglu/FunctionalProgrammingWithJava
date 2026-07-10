package dev.kaldiroglu.fpj.ch07;

import java.util.stream.IntStream;

public class SkipExample {

	public static void main(String[] args) {
		System.out.println("-- skip --");
		IntStream intStream = IntStream.range(1, 15);
		intStream.skip(10).forEach(System.out::println);

		System.out.println("-- parallel skip --");
		IntStream intStream2 = IntStream.range(1, 15);
		intStream2.parallel().skip(10).forEach(System.out::println);

		System.out.println("-- parallel skip ordered --");
		IntStream intStream3 = IntStream.range(1, 15);
		intStream3.parallel().skip(10).forEachOrdered(System.out::println);
	}
}
