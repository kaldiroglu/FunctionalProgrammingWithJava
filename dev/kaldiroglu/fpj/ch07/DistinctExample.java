package dev.kaldiroglu.fpj.ch07;

import java.util.List;
import java.util.stream.Stream;

import static dev.kaldiroglu.fpj.ch06.api.StreamUtil.print;
import static dev.kaldiroglu.fpj.ch06.domain.CollectionFactory.getDuplicatedNameList;

public class DistinctExample {

	public static void main(String[] args) {
		List<String> duplicatedNameList = getDuplicatedNameList();
		print(duplicatedNameList);
		
		System.out.println("All names");
		
		Stream<String> stream1 = duplicatedNameList.stream();
		print(stream1);
		
		System.out.println("Distinct names");
		
		Stream<String> stream2 = duplicatedNameList.stream();
		stream2 = stream2.distinct();
		print(stream2);
	}
}
