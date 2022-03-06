package com.bally.src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @author anonymous
 *
 */
public class Starter {

//	Assumptions:
//	1. Lower limit is 1 and highest random number is a million
//	2. Numbers need not to be unique
//	3. All number are positive

	final static public String OUTPUT_DIR = "./output";
	final static public String OUTPUT_FILE = "/result.txt";
	final static public String OUTPUT_FILE_SORTED = "/result_sorted.txt";
	final static public int MAX_NUMBERS = 1000000;

	public static void main(String[] args) throws IOException {

		try {
			makeDir(OUTPUT_DIR);

			Path path = Paths.get(OUTPUT_DIR + "/" + OUTPUT_FILE);
			Path pathSorted = Paths.get(OUTPUT_DIR + "/" + OUTPUT_FILE_SORTED);
			List<Integer> randNums = new ArrayList<>();

			try (BufferedWriter writer = Files.newBufferedWriter(path)) {
				for (int x = 0; x < MAX_NUMBERS; x++) {
					int randNum = ThreadLocalRandom.current().nextInt(1, MAX_NUMBERS + 1);
					randNums.add(randNum);
					writer.write(randNum + System.lineSeparator());
				}
				writer.close();

				sortAndWrite(randNums, pathSorted);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Creates a directory if does not exist
	 * 
	 * @param output_dir A directory which needs to be created
	 */
	public static void makeDir(String output_dir) {
		File theDir = new File(output_dir);
		if (!theDir.exists()) {
			theDir.mkdirs();
		}
	}

	/**
	 * Sorts a list of integers and stores the output to a text file
	 * 
	 * @param randNums   List of random integers to sort
	 * @param pathSorted Location of the file to save the output
	 * @throws IOException Throws IOException
	 */
	public static void sortAndWrite(List<Integer> randNums, Path pathSorted) throws IOException {
		randNums = randNums.stream().sorted(Collections.reverseOrder()).collect((Collectors.toList()));
		BufferedWriter sortedWriter = Files.newBufferedWriter(pathSorted);
		for (int rn : randNums) {
			sortedWriter.write(rn + System.lineSeparator());
		}
		sortedWriter.close();
	}
}
