package days.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Day1 {
	public static void main(String[] args) {
		ArrayList<Integer> leftList = new ArrayList<>();
		ArrayList<Integer> rightList = new ArrayList<>();
		
		try (Scanner scanner = new Scanner(new File("input.txt"))) {
			while (scanner.hasNextInt()) {
				leftList.add(scanner.nextInt());
				rightList.add(scanner.nextInt());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("Input file not found");
		}
		
		System.out.println("Total Distance (Puzzle 1): " + calculateTotalDistance(leftList, rightList));
		System.out.println("Similarity Score (Puzzle 2): " + calculateSimilarityScore(leftList, rightList));
	}

	// Puzzle 1
	private static int calculateTotalDistance(ArrayList<Integer> left, ArrayList<Integer> right) {
		Collections.sort(left);
		Collections.sort(right);
		
		int totalDistance = 0;

		for (int i = 0; i < left.size(); i++) {
			totalDistance += Math.abs(left.get(i) - right.get(i));
		}

		return totalDistance;
	}

	// Puzzle 2
	private static int calculateSimilarityScore(ArrayList<Integer> leftList, ArrayList<Integer> rightList) {
		HashMap<Integer, Integer> rightCounts = new HashMap<>();
		for (int num : rightList) {
			rightCounts.put(num, rightCounts.getOrDefault(num, 0) + 1);
		}
		
		int similarityScore = 0;

		for (int num : leftList) {
			int count = rightCounts.getOrDefault(num, 0);
			similarityScore += num * count;
		}

		return similarityScore;
	}
}
