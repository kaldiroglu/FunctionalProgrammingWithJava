package dev.kaldiroglu.fpj.ch07;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * takeWhile() / dropWhile() örneği (Java 9).
 *
 * takeWhile: akışın başından itibaren predicate TRUE olduğu sürece elemanları ALIR,
 *            ilk FALSE sonucunda DURUR.
 * dropWhile: predicate TRUE olduğu sürece elemanları ATAR, ilk FALSE'tan itibaren
 *            geri kalan TÜM elemanları döndürür.
 *
 * filter ile farkı: filter HER elemanı tek tek sınar; takeWhile/dropWhile ise ilk
 * sınır elemanında durur/başlar. Bu yüzden genellikle DİZİLİ (ordered) akışlarda anlamlıdır.
 */
public class TakeWhileDropWhileExample {

	public static void main(String[] args) {

		System.out.print("takeWhile (çift mi?): ");
		Stream.of(2, 4, 6, 7, 8, 10, 11, 12, 13, 14, 15)
				.takeWhile(n -> n % 2 == 0)
				.forEach(n -> System.out.print(n + " "));
		System.out.println();

		System.out.print("dropWhile (çift mi?): ");
		Stream.of(2, 4, 6, 7, 8, 10, 11, 12, 13, 14, 15)
				.dropWhile(n -> n % 2 == 0)
				.forEach(n -> System.out.print(n + " "));
		System.out.println();

		// filter HER elemanı sınar (sınırda durmaz) -> çift olan tüm elemanlar
		System.out.print("filter    (çift mi?): ");
		Stream.of(2, 4, 6, 7, 8, 10)
				.filter(n -> n % 2 == 0)
				.forEach(n -> System.out.print(n + " "));   // 2 4 6 8 10
		System.out.println();

		// İlkel akışlarda da vardır
		System.out.print("\nIntStream 1..10, takeWhile(n < 6): ");
		IntStream.rangeClosed(1, 10)
				.takeWhile(n -> n < 6)
				.forEach(n -> System.out.print(n + " "));   // 1 2 3 4 5
		System.out.println();
	}
}
