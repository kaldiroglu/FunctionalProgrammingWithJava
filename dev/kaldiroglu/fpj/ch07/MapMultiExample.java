package dev.kaldiroglu.fpj.ch07;

import java.util.stream.Stream;

/**
 * mapMulti() örneği (Java 16).
 *
 * flatMap gibi BİRE-ÇOK (one-to-many) bir ara işlemdir; ama her eleman için yeni bir
 * Stream OLUŞTURMAK yerine, üretilen elemanları doğrudan bir Consumer'a (downstream) İTER.
 * Az sayıda (ya da sıfır) eleman üretiliyorsa ara Stream nesnesi oluşturmadığı için
 * daha verimli olabilir.
 *
 * Not: R tipini derleyici BiConsumer'dan çıkaramadığı için tip tanığı (type witness)
 * gerekir: stream.<R>mapMulti(...)
 */
public class MapMultiExample {

	public static void main(String[] args) {

		// 1) Her elemanı kendisi ve 10 katına genişlet (bire-iki)
		System.out.println("Her sayıyı kendisi ve 10 katına genişletme:");
		Stream.of(1, 2, 3)
				.<Integer>mapMulti((n, downstream) -> {
					downstream.accept(n);
					downstream.accept(n * 10);
				})
				.forEach(System.out::println);          // 1, 10, 2, 20, 3, 30

		// 2) Koşullu genişletme: yalnızca çiftler için iki değer, tekler için HİÇ (0 eleman)
		System.out.println("\nKoşullu (yalnızca çiftler -> n ve n*n):");
		Stream.of(1, 2, 3, 4)
				.<Integer>mapMulti((n, downstream) -> {
					if (n % 2 == 0) {
						downstream.accept(n);
						downstream.accept(n * n);
					}
				})
				.forEach(System.out::println);          // 2, 4, 4, 16

		// 3) İlkel akışa: mapMultiToInt (downstream bir IntConsumer'dır)
		System.out.println("\nmapMultiToInt ile metni sayılara ayırma:");
		Stream.of("1,2", "3,4,5")
				.mapMultiToInt((s, intConsumer) -> {
					for (String part : s.split(","))
						intConsumer.accept(Integer.parseInt(part));
				})
				.forEach(n -> System.out.print(n + " "));   // 1 2 3 4 5
		System.out.println();
	}
}
