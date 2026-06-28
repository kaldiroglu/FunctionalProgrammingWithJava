package dev.kaldiroglu.fpj.ch08;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static dev.kaldiroglu.fpj.ch05.domain.BookFactory.getBookList;
import static dev.kaldiroglu.fpj.ch06.api.StreamUtil.print;
import static dev.kaldiroglu.fpj.ch06.domain.CollectionFactory.getNameList;
import dev.kaldiroglu.fpj.ch05.domain.Book;

public class ForEachExample {

	public static void main(String[] args) {
//		runNamesExamples();
		runBooksExamples();
	}
	
	public static void runNamesExamples() {
		Consumer<String> printConsumer = s -> System.out.println(s);
		Consumer<String> printLengthConsumer = s -> System.out.println(s + " - " + s.length());

		List<String> nameList = getNameList();
		print(nameList);

		Stream<String> stream = nameList.stream();
		stream.forEach(printConsumer);
		
		System.out.println();
		
		stream = nameList.stream();
		stream.forEach(printLengthConsumer);
	}

	public static void runBooksExamples() {
		Consumer<Book> printConsumer = b -> System.out.println(b);
		Consumer<Book> printPageCountConsumer = b -> System.out.println(b.getTitle() + " - " + b.getPages());
		
		List<Book> books = getBookList();
		System.out.println("\n* * * Print consumer * * *");
		books.stream().forEach(printConsumer);
		System.out.println("\n* * * Print page count consumer * * *");
		books.stream().forEach(printPageCountConsumer);
		System.out.println("\n* * * Print page count consumer * * *");
		books.stream().forEach(b -> System.out.println(b.getTitle() + " - " + b.getPages())); // Either way can be used!
	}
}
