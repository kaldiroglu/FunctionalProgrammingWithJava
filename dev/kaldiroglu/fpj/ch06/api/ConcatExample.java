package dev.kaldiroglu.fpj.ch06.api;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * concat() örneği — araçsal (factory) metot.
 *
 * concat(), iki akışı arka arkaya ekleyerek TEK bir akış üreten STATİK bir üretici metottur
 * (önce a'nın, sonra b'nin elemanları). Bir Stream döndürdüğü için bitirici (terminal) işlem
 * DEĞİLDİR; akış kaynaklarıyla (of/generate/iterate) aynı kategoridedir — bu yüzden Bölüm 6.
 */
public class ConcatExample {

	public static void main(String[] args) {

		// Stream<T> üzerinde
		Stream<String> birlesik = Stream.concat(
				Stream.of("Ada", "Linus"),
				Stream.of("Grace"));
		birlesik.forEach(System.out::println);              // Ada, Linus, Grace

		// İlkel akışlarda da vardır: IntStream / LongStream / DoubleStream
		int[] sayilar = IntStream.concat(
				IntStream.of(1, 2),
				IntStream.of(3, 4)).toArray();
		System.out.println(Arrays.toString(sayilar));        // [1, 2, 3, 4]

		double[] ondalik = DoubleStream.concat(
				DoubleStream.of(1.5),
				DoubleStream.of(2.5, 3.5)).toArray();
		System.out.println(Arrays.toString(ondalik));        // [1.5, 2.5, 3.5]
	}
}
