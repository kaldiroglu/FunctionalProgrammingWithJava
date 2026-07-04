package dev.kaldiroglu.fpj.ch06.source;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class StreamFromFile {

	private static String path = "dev/kaldiroglu/fpj/ch06/source/FirstMemory.txt";

	public static void main(String[] args) {
		streamOutOfFile();
//		streamWithHandlerOutOfFile();
	}

	public static void streamOutOfFile() {
		try (BufferedReader br = new BufferedReader(
				new FileReader(path));
				Stream<String> fileLines = br.lines()) {
			fileLines.forEach(element -> System.out.printf("%s\n", element));
//			System.out.printf("Number of lines in the file: %d\n\n", fileLines.count());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("\n\n");

		BufferedReader br = null;
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(path);
			br = new BufferedReader(fileReader);
			Stream<String> fileLines = br.lines();
			fileLines.forEach(element -> System.out.printf("%s\n", element));
//			System.out.printf("Number of lines in the file: %d\n\n", fileLines.count());
			fileReader.close();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			try {
				fileReader.close();
				br.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
			try {
				fileReader.close();
				br.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Notice handler is called when the stream is closed.
	 */
	public static void streamWithHandlerOutOfFile() {
		try (BufferedReader br = new BufferedReader(
				new FileReader(path));
				Stream<String> fileLines = br.lines()) {
			Stream<String> newFileLines = fileLines.onClose(() -> System.out.println("\nHandler on fileLines"));
			fileLines.forEach(element -> System.out.printf("%s\n", element));
//			System.out.printf("Number of lines in the file: %d\n\n", fileLines.count());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
