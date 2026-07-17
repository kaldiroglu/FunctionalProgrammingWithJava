package dev.kaldiroglu.fpj.ch09;

import dev.kaldiroglu.fpj.ch06.domain.CollectionFactory;

import java.time.Instant;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SequentialParallelComparison {
	private static Random random = new Random();

	public static void main(String[] args) {
//		example1();
//		example2();
//		example3();
//		example4();
	}

	public static void example1() {
		System.out.println("\n--------------------- example1() ---------------------");
		IntStream intStream1 = random.ints(100);
		System.out.println("Running sequential");
		runSimple(intStream1.sequential());
		IntStream intStream2 = random.ints(100);
		System.out.println("\nRunning parallel");
		runSimple(intStream2.parallel());
	}
	
	public static void example2() {
		System.out.println("\n--------------------- example2() ---------------------");
		System.out.println("\n--------------------- runFilteringMappingAndCounting() ---------------------");
		IntStream intStream1 = random.ints(10_000_000);
		System.out.println("Running sequential");
		runFilteringMappingAndCounting(intStream1.sequential());
		IntStream intStream2 = random.ints(10_000_000);
		System.out.println("\nRunning parallel");
		runFilteringMappingAndCounting(intStream2.parallel());
		
		System.out.println("\n--------------------- runFilteringMappingAndAveraging() ---------------------");
		IntStream intStream3 = random.ints(10_000_000);
		System.out.println("Running sequential");
		runFilteringMappingAndAveraging(intStream3.sequential());
		IntStream intStream4 = random.ints(10_000_000);
		System.out.println("\nRunning parallel");
		runFilteringMappingAndCounting(intStream4.parallel());
	}

	public static void example3() {
		System.out.println("\n--------------------- example3() ---------------------");
		String[] strings = CollectionFactory.getStringArrayBySize(10_000_000);
		System.out.println("Running sequential");
		runFilteringMappingAndCounting(Arrays.stream(strings));
		System.out.println("\nRunning parallel");
		runFilteringMappingAndCounting(Arrays.stream(strings).parallel());
	}
	
	public static void example4() {
		System.out.println("\n--------------------- example4() ---------------------");
		String[] strings = CollectionFactory.getStringArrayBySize(10_000_000);
		System.out.println("Running sequential");
		runFilteringMappingAndCollecting(Arrays.stream(strings));
		System.out.println("\nRunning parallel");
		runFilteringMappingAndCollecting(Arrays.stream(strings).parallel());
	}

	public static void runSimple(IntStream stream) {
		Instant now = Instant.now();
		stream.forEach(s -> {
			System.out.println(LocalTime.now() + " - value: " + s + " - thread: " + Thread.currentThread().getName());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		Instant then = Instant.now();
		long gap = ChronoUnit.MILLIS.between(now, then);
		System.out.println("Time in ms: " + gap);
	}

	/**
	 * This method is short and includes no context switching.
	 * 
	 * @param stream
	 */
	public static void runShort(Stream<String> stream) {
		System.out.println("");
		Instant now = Instant.now();
		stream.forEach(s -> {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Instant then = Instant.now();
		long gap = ChronoUnit.MILLIS.between(now, then);
		System.out.println("Time in ms: " + gap);
	}

	/**
	 * This method takes longer and includes no context switching.
	 * 
	 * @param stream
	 */
	public static void runLong(Stream<String> stream) {
		Instant now = Instant.now();
		stream.forEach(s -> {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Instant then = Instant.now();
		long gap = ChronoUnit.MILLIS.between(now, then);
		System.out.println("Time in ms: " + gap);
	}

	/**
	 * This method includes very intensive context switching due to writing on the
	 * console.
	 * 
	 * @param stream
	 */
	public static void runWithContextSwitching(Stream<String> stream) {
		Instant now = Instant.now();
		stream.forEach(s -> {
			System.out.println(LocalTime.now() + " - value: " + s + " - thread: " + Thread.currentThread().getName());
			System.out.println(LocalTime.now() + " - value: " + s + " - thread: " + Thread.currentThread().getName());
		});

		Instant then = Instant.now();
		long gap = ChronoUnit.MILLIS.between(now, then);
		System.out.println("Time in ms: " + gap);
	}

	/**
	 * This method includes very intensive context switching due to writing on the
	 * console.
	 * 
	 * @param stream
	 */
	public static void runFilteringMappingAndCounting(Stream<String> stream) {
		Instant now = Instant.now();
		System.out.println("Thread name: " + Thread.currentThread().getName());
		long size = stream.map(s -> Integer.parseInt(s)).filter(i -> i % 2 == 0).map(i -> Math.sqrt(Math.sqrt(i)))
				.map(i -> i * i * i * i).filter(i -> i > 5).count();
		System.out.println("Size: " + size);
		Instant then = Instant.now();
		long gap = ChronoUnit.MILLIS.between(now, then);
		System.out.println("Time in ms: " + gap);
	}
	
	public static void runFilteringMappingAndCounting(IntStream stream) {
		Instant now = Instant.now();
		System.out.println("Thread name: " + Thread.currentThread().getName());
		long count  = stream.filter(i -> i % 2 == 0).map(i -> i * i).filter(i -> i > 5).count();
		System.out.println("Count: " + count);
		Instant then = Instant.now();
		long gap = ChronoUnit.MILLIS.between(now, then);
		System.out.println("Time in ms: " + gap);
	}
	
	public static void runFilteringMappingAndAveraging(IntStream stream) {
		Instant now = Instant.now();
		System.out.println("Thread name: " + Thread.currentThread().getName());
		OptionalDouble average  = stream.filter(i -> i % 2 == 0).map(i -> i * i).filter(i -> i > 5).average();
		System.out.println("Average: " + average.getAsDouble());
		Instant then = Instant.now();
		long gap = ChronoUnit.MILLIS.between(now, then);
		System.out.println("Time in ms: " + gap);
	}

	public static void runFilteringMappingAndCollecting(Stream<String> stream) {
		Instant now = Instant.now();
		Set<Double> set = stream.map(s -> Integer.parseInt(s)).filter(i -> i % 2 == 0).map(i -> Math.sqrt(i))
				.map(i -> i * i * i * i).filter(i -> i > 5).collect(Collectors.toSet());
		System.out.println("Size: " + set.size());
		Instant then = Instant.now();
		long gap = ChronoUnit.MILLIS.between(now, then);
		System.out.println("Time in ms: " + gap);
	}

	/**
	 * This method includes very intensive context switching due to writing on the
	 * console.
	 * 
	 * @param stream
	 */
	public static void runFilteringAndMapping(Stream<String> stream) {
		Instant now = Instant.now();
		stream.forEach(s -> {
			System.out.println(LocalTime.now() + " - value: " + s + " - thread: " + Thread.currentThread().getName());
			System.out.println(LocalTime.now() + " - value: " + s + " - thread: " + Thread.currentThread().getName());
		});

		Instant then = Instant.now();
		long gap = ChronoUnit.MILLIS.between(now, then);
		System.out.println("Time in ms: " + gap);
	}

	/**
	 * This method includes very intensive context switching due to writing on the
	 * console.
	 * 
	 * @param stream
	 */
	public static void runFilteringAndMappingAndSumming(Stream<String> stream) {
		Instant now = Instant.now();
		stream.forEach(s -> {
			System.out.println(LocalTime.now() + " - value: " + s + " - thread: " + Thread.currentThread().getName());
			System.out.println(LocalTime.now() + " - value: " + s + " - thread: " + Thread.currentThread().getName());
		});

		Instant then = Instant.now();
		long gap = ChronoUnit.MILLIS.between(now, then);
		System.out.println("Time in ms: " + gap);
	}
}
