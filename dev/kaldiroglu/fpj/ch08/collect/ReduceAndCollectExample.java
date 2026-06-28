package dev.kaldiroglu.fpj.ch08.collect;

import java.util.*;

public class ReduceAndCollectExample {

	public static void main(String[] args) {
		sum();
	}

	public static void sum() {
		List<Integer> list = Arrays.asList(3, 21, 15, 6, 19);
		int sum = list.stream().reduce(0, Integer::sum);
		System.out.println(sum);
	}
}
