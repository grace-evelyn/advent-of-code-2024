package days.day2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day2 {
	public static void main(String[] args) {
		List<List<Integer>> reports = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader("days/day2/input2.txt"))) {
			String report;

			while ((report = reader.readLine()) != null) {
				List<Integer> levels = new ArrayList<>();
				for (String num : report.split(" ")) {
					levels.add(Integer.valueOf(num));
				}

				reports.add(levels);
			}
		} catch (FileNotFoundException e) {
			System.err.println("input.txt not found");
		} catch (IOException e) {
			System.err.println("Error reading input.txt");
		}

		int safeCount = 0;
		for (List<Integer> report : reports) {
			if (isSafe(report) || isSafeWithDampener(report)) {
				safeCount++;
			}
		}

		System.out.println("Number of safe reports: " + safeCount);
	}

	// Puzzle 1
	private static boolean isSafe(List<Integer> report) {
		boolean isIncreasing = true;
		boolean isDecreasing = true;

		for (int i = 1; i < report.size(); i++) {
			int difference = report.get(i) - report.get(i - 1);

			// Rule 1: The levels are either all increasing or all decreasing.
			if (difference > 0) {
				isDecreasing = false;
			} else if (difference < 0) {
				isIncreasing = false;
			}

			// Rule 2: Any two adjacent levels differ by at least one and at most three.
			if (Math.abs(difference) < 1 || Math.abs(difference) > 3) {
				return false;
			}
		}

		return isIncreasing || isDecreasing;
	}

	// Puzzle 2
	private static boolean isSafeWithDampener(List<Integer> report) {
		for (int i = 0; i < report.size(); i++) {
			List<Integer> modifiedReport = new ArrayList<>(report);
			modifiedReport.remove(i);

			if (isSafe(modifiedReport)) {
				return true;
			}
		}

		return false;
	}
}
