package dev.kaldiroglu.fpj.ch06.api;

import dev.kaldiroglu.fpj.ch06.domain.CollectionFactory;

import java.util.List;
import java.util.stream.Stream;

public class StreamWithClosingHandler {

	public static void main(String[] args) {
		List<String> names = CollectionFactory.getNameList();

		Stream<String> sequentialStream = names.stream();
		Stream<String> sequentialStreamWithHandler1 = sequentialStream
				.onClose(() -> System.out.println("Handler on sequentialStreamWithHandler1"));
		Stream<String> sequentialStreamWithHandler2 = sequentialStream
				.onClose(() -> System.out.println("Handler on sequentialStreamWithHandler2"));
		
//		sequentialStream.close();
//		sequentialStreamWithHandler1.close();
//		sequentialStreamWithHandler2.close();

		if (sequentialStream == sequentialStreamWithHandler1)
			System.out.println("The same");

		if (sequentialStream == sequentialStreamWithHandler2)
			System.out.println("The same");

		System.out.println(sequentialStream.isParallel());

	}
}
