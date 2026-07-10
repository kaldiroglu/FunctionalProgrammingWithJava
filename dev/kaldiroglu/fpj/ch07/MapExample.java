package dev.kaldiroglu.fpj.ch07;

import dev.kaldiroglu.fpj.ch05.domain.Book;

import static dev.kaldiroglu.fpj.ch05.domain.BookFactory.getBookList;
import static dev.kaldiroglu.fpj.ch06.api.StreamUtil.print;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapExample {
    public static void main(String[] args) {
//        runExamples();
		runBookExamples();
    }

    public static void runExamples() {
        // find the average of the even numbers squared
        System.out.println("Average of numbers");
//        Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 10}).filter(x -> x % 2 == 0).peek(System.out::println).map(n -> n * n).peek(System.out::println).average()
//                .ifPresent(System.out::println);

		Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 10}).filter(x -> x % 2 == 0).map(n -> n * n).average()
				.ifPresent(System.out::println);

        System.out.println("\nMapping doubles to ints");
        // map doubles to ints
        Stream.of(1.5, 2.3, 3.7, 4.1).mapToInt(Double::intValue).forEach(System.out::println);

        System.out.println("\nMapping doubles to ints");
		Stream.iterate(1.0, i -> Math.random() < 0.8, i -> 10 * Math.random()).mapToInt(Double::intValue)
				.forEach(System.out::println);

        System.out.println("\nMapping uppercase");
        List<String> collected = Stream.of("Java", " Rocks").map(String::toUpperCase).collect(toList());
        print(collected);
    }

    private static void runBookExamples() {
        // aggregate author first names into a list
        System.out.println("Mapping to author names");
        List<Book> books = getBookList();
        List<String> list = books.stream().map(Book::getAuthorLName).collect(Collectors.toList());
        print(list);

        System.out.println("Mapping to author names and filtering");
        list = books.stream().map(Book::getAuthorLName).filter((s) -> s.startsWith("S")).distinct().collect(Collectors.toList());
        print(list);
    }
}
