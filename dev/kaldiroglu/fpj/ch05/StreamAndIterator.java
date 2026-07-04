package dev.kaldiroglu.fpj.ch05;

import dev.kaldiroglu.fpj.ch05.domain.CollectionFactory;

import java.util.*;
import java.util.stream.Stream;

public class StreamAndIterator {
	
	public static void main(String[] args) {
		List<String> names = CollectionFactory.getNameList();
		
		System.out.println("Iterating by Iterator");
		Iterator<String> iterator1 = names.iterator();
		while (iterator1.hasNext()) {
			String element = iterator1.next();
			if(element.length() > 5)
				System.out.println(element);
		}

		System.out.println("\nIterating by ListIterator");

		ListIterator<String> listIterator = names.listIterator(names.size());
		while (listIterator.hasPrevious())
			System.out.println(listIterator.previous());

		System.out.println("\nIterating by Stream's forEach method");

		Stream<String> stream = names.stream();
		stream.forEach(s -> System.out.println(s));
		
		System.out.println("\nIterating by Stream's iterator after filtering");
		stream = names.stream();
		Iterator<String> iterator2 = stream.filter(s -> s.length() > 5).iterator();
		while (iterator2.hasNext())
			System.out.println(iterator2.next());
		
		if(iterator1 == iterator2)
			System.out.println("\nThe same");
		else
			System.out.println("\nDifferent");
	}
}
