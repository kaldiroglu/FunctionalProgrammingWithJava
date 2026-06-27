package dev.kaldiroglu.fpj.ch07;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * flatMap() örneği.
 *
 * map() her elemanı TEK bir yeni elemana dönüştürür; flatMap() ise her elemanı
 * bir AKIŞA (Stream) dönüştürür ve bu akışları TEK bir akışta birleştirir
 * (düzleştirme / flatten).
 */
public class FlatMapExample {

	public static void main(String[] args) {

		// 1) Liste içinde liste -> tek düzeyli akış
		List<List<Integer>> nested = List.of(
				List.of(1, 2),
				List.of(3, 4, 5),
				List.of(6));

		System.out.println("flatMap ile düzleştirme:");
		nested.stream()
				.flatMap(List::stream)        // Stream<List<Integer>> -> Stream<Integer>
				.forEach(n -> System.out.print(n + " "));   // 1 2 3 4 5 6
		System.out.println();

		// map() ile YAPILAMAZ: aşağıdaki satır Stream<Stream<Integer>> üretirdi,
		// yani iç içe akış; düzleştirme olmazdı:
		// Stream<Stream<Integer>> wrong = nested.stream().map(List::stream);

		// 2) Cümleleri kelimelere ayırma
		System.out.println("\nCümleleri kelimelere ayırma:");
		Stream.of("Java ile fonksiyonel programlama", "Akışlar güçlüdür")
				.flatMap(cumle -> Arrays.stream(cumle.split(" ")))
				.forEach(System.out::println);

		// 3) İlkel akışa düzleştirme: flatMapToInt
		System.out.println("\nflatMapToInt ile int dizilerini tek akışta birleştirme:");
		Stream.of(new int[] { 1, 2, 3 }, new int[] { 4, 5 })
				.flatMapToInt(Arrays::stream)   // Stream<int[]> -> IntStream
				.forEach(n -> System.out.print(n + " "));   // 1 2 3 4 5
		System.out.println();
	}
}
