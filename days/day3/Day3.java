package days.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
	public static void main(String[] args) {
		StringBuilder inputBuilder = new StringBuilder();

		try (Scanner scanner = new Scanner(new File("days/day3/input3.txt"))) {
			while (scanner.hasNextLine()) {
				inputBuilder.append(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			System.err.println("Input file not found");
		}

		String corruptedInput = inputBuilder.toString();

		// Puzzle 1
		Pattern mulPattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
		// Puzzle 2
		Pattern conditionalPattern = Pattern.compile("do\\(\\)|don't\\(\\)");
		Pattern combinedPattern = Pattern.compile(mulPattern.pattern() + "|" + conditionalPattern.pattern());

		Matcher matcher = combinedPattern.matcher(corruptedInput);

		int total = 0;
		boolean isEnabled = true;

		while (matcher.find()) {
			String match = matcher.group();

			if (match.equals("do()")) {
				isEnabled = true;
			} else if (match.equals("don't()")) {
				isEnabled = false;
			} else if (match.startsWith("mul(") && isEnabled) {
				String extracted = match.substring(4, match.length() - 1);
				String[] numbers = extracted.split(",");
				int x = Integer.parseInt(numbers[0]);
				int y = Integer.parseInt(numbers[1]);
				total += x * y;
			}
		}

		System.out.println("Total sum: " + total);
	}
}
