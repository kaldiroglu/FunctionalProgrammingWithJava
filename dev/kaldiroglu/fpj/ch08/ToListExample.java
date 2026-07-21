package dev.kaldiroglu.fpj.ch08;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream.toList() örneği (Java 16).
 *
 * toList(), collect(Collectors.toList()) için kısa bir yoldur — Collectors import etmeye
 * gerek kalmaz. ÖNEMLİ FARK: toList() DEĞİŞTİRİLEMEZ (unmodifiable) bir liste döndürür;
 * collect(Collectors.toList()) ise DEĞİŞTİRİLEBİLİR (genelde ArrayList) bir liste döndürür.
 */
public class ToListExample {

	public static void main(String[] args) {

		// 1) toList(): kısa ve okunur (Java 16)
		List<String> upper = Stream.of("ada", "linus", "grace")
				.map(String::toUpperCase)
				.toList();
		System.out.println("toList(): " + upper);              // [ADA, LINUS, GRACE]

		// 2) toList() DEĞİŞTİRİLEMEZ bir liste döndürür
//		try {
			upper.add("KEN");
//		} catch (UnsupportedOperationException e) {
//			System.out.println("  -> değiştirilemez: " + e.getClass().getSimpleName());
//		}

		// 3) collect(Collectors.toList()) ise DEĞİŞTİRİLEBİLİR bir liste döndürür
		List<String> modifiable = Stream.of("ada", "linus", "grace")
				.map(String::toUpperCase)
				.collect(Collectors.toList());
		modifiable.add("KEN");
		System.out.println("collect(toList()): " + modifiable); // [ADA, LINUS, GRACE, KEN]

		// 4) toList() null elemana izin verir; toUnmodifiableList() İZİN VERMEZ (NPE)
		System.out.println("toList() null: " + Stream.of("a", (String) null).toList());
	}
}
