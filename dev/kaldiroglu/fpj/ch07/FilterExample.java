package dev.kaldiroglu.fpj.ch07;

import dev.kaldiroglu.fpj.ch05.domain.Book;

import static dev.kaldiroglu.fpj.ch05.domain.BookFactory.getBookList;
import static dev.kaldiroglu.fpj.ch06.api.StreamUtil.print;
import static dev.kaldiroglu.fpj.ch06.domain.CollectionFactory.getDuplicatedNameList;
import static dev.kaldiroglu.fpj.ch06.domain.CollectionFactory.getNameList;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FilterExample {

	public static void main(String[] args) {
//		runNamesExamples();
		runBooksExamples();
	}

	public static void runNamesExamples() {
		Predicate<String> lengthPredicate = s -> s.length() <= 5;

		List<String> nameList = getNameList();
		print(nameList);

		System.out.println("--- Length predicate --- ");
		Stream<String> stream1 = nameList.stream();
		stream1 = stream1.filter(lengthPredicate);
		print(stream1);

		List<String> duplicatedNameList = getDuplicatedNameList();
		print(duplicatedNameList);

		System.out.println("--- Length predicate with distinct ---");
		Stream<String> stream2 = duplicatedNameList.stream();
		stream2 = stream2.distinct().filter(lengthPredicate);
		print(stream2);
	}

	public static void runBooksExamples() {
		List<Book> books = getBookList();
		print(books);

		Predicate<Book> bookNameFirstCharPredicate = (b) -> b.getTitle().startsWith("C");
		Predicate<Book> bookPagePredicate = (b) -> b.getPages() > 600;
		
		System.out.println("\nbookNameFirstCharPredicate");
		books.stream().filter(bookNameFirstCharPredicate).forEach(b -> System.out.println(b.getTitle() + " " + b.getPages()));
	
		System.out.println("\nbookPagePredicate");
		books.stream().filter(bookPagePredicate).forEach(b -> System.out.println(b.getTitle() + " " + b.getPages()));
	
		System.out.println("\nbookPagePredicate & bookNameFirstCharPredicate");
		books.stream().filter(bookPagePredicate).filter(bookNameFirstCharPredicate).forEach(b -> System.out.println(b.getTitle() + " " + b.getPages()));
		long count = books.stream().filter(bookPagePredicate).filter(bookNameFirstCharPredicate).count();
		System.out.println("\nCount: " + count);
	}
}