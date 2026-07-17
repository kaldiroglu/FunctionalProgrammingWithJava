package dev.kaldiroglu.fpj.ch09;

import java.time.Duration;
import java.util.List;
import java.util.stream.Gatherers;

/**
 * Gatherers.mapConcurrent example (Java 24, JEP 485) — for the Parallel Streams chapter.
 *
 * mapConcurrent(maxConcurrency, mapper) runs 'mapper' for each element on its own VIRTUAL
 * THREAD (Java 21+); at most 'maxConcurrency' tasks run at the same time, and it PRESERVES
 * the encounter order of the output.
 *
 * It is the right tool for BLOCKING I/O work (remote service, file, DB). Speeding such work
 * up with stream().parallel() is the wrong choice: blocking tasks would tie up the shared
 * ForkJoinPool. mapConcurrent uses virtual threads instead, which are cheap to block.
 */
public class MapConcurrentExample {

	public static void main(String[] args) {
		List<Integer> userIds = List.of(1, 2, 3, 4, 5, 6, 7, 8);

		// --- Sequential: plain map(), one blocking call after another (no mapConcurrent) ---
		long t0 = System.currentTimeMillis();
		List<String> sequentialProfiles = userIds.stream()
				.map(MapConcurrentExample::fetchProfile)
				.toList();
		long sequentialMs = System.currentTimeMillis() - t0;

		System.out.println("Sequential:");
		sequentialProfiles.forEach(System.out::println);
		System.out.println("Sequential time: " + sequentialMs + " ms  (8 x 500 ms, one at a time)");

		System.out.println();

		// --- Concurrent: mapConcurrent runs up to 4 blocking calls at the same time ---
		long t1 = System.currentTimeMillis();
		List<String> concurrentProfiles = userIds.stream()
				.gather(Gatherers.mapConcurrent(4, MapConcurrentExample::fetchProfile)) // at most 4 at a time
				.toList();
		long concurrentMs = System.currentTimeMillis() - t1;

		System.out.println("Concurrent (mapConcurrent, max 4):");
		concurrentProfiles.forEach(System.out::println);    // order preserved: User-1 ... User-8
		System.out.println("Concurrent time: " + concurrentMs + " ms  (two waves of 4)");

		System.out.println();
		System.out.printf("Speedup: %.1fx%n", (double) sequentialMs / concurrentMs);
	}

	// Simulates a slow, I/O-bound task (like a call to a remote service).
	static String fetchProfile(int userId) {
		try {
			Thread.sleep(Duration.ofMillis(500));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return "User-" + userId + " (virtual=" + Thread.currentThread().isVirtual() + ")";
	}
}
