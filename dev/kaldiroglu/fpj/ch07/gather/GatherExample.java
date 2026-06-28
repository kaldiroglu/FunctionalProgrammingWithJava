package dev.kaldiroglu.fpj.ch07.gather;

import java.util.stream.Gatherers;
import java.util.stream.Stream;

/**
 * gather() örneği (Java 24, JEP 485) — HAZIR Gatherer'lar ile.
 *
 * gather(Gatherer) genişletilebilir bir ara işlemdir; bitirici işlemlerdeki
 * Collector'ın ara işlemlerdeki karşılığıdır. Kendi Gatherer'ınızı yazmadan,
 * Gatherers sınıfındaki hazır üreticileri kullanabilirsiniz (tıpkı Collectors gibi).
 */
public class GatherExample {

	public static void main(String[] args) {

		// windowFixed(n): elemanları n boyutlu ardışık gruplara böler
		System.out.println("windowFixed(3):");
		Stream.of(1, 2, 3, 4, 5, 6, 7)
				.gather(Gatherers.windowFixed(3))
				.forEach(System.out::println);          // [1, 2, 3] [4, 5, 6] [7]

		// windowSliding(n): n boyutlu KAYAN pencereler üretir
		System.out.println("\nwindowSliding(2):");
		Stream.of(1, 2, 3, 4)
				.gather(Gatherers.windowSliding(2))
				.forEach(System.out::println);          // [1, 2] [2, 3] [3, 4]

		// scan: her adımda biriken ara sonucu YAYAR (running / kümülatif)
		System.out.println("\nscan (kümülatif toplam):");
		Stream.of(1, 2, 3, 4)
				.gather(Gatherers.scan(() -> 0, Integer::sum))
				.forEach(System.out::println);          // 1 3 6 10

		// fold: tüm akışı TEK bir sonuca indirger (tek eleman yayar)
		System.out.println("\nfold (toplam):");
		Stream.of(1, 2, 3, 4)
				.gather(Gatherers.fold(() -> 0, Integer::sum))
				.forEach(System.out::println);          // 10
	}
}
