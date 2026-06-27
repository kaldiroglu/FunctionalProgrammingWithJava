package dev.kaldiroglu.fpj.ch07;

import java.util.stream.*;

public class LimitExample {
	public static void main(String[] args) {
		Stream<Integer> stream = Stream.iterate(1, i -> i + 1);
		stream.limit(30).forEach(System.out::println);
		
		DoubleStream doubleStream = DoubleStream.iterate(1, i -> Math.random());
		doubleStream.limit(20).forEach(System.out::println);
	}
}
