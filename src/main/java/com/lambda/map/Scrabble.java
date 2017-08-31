package com.lambda.map;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class Scrabble {

	public static void main(final String[] args) throws IOException {
		// http://introcs.cs.princeton.edu/java/data/words.shakespeare.txt
		// http://introcs.cs.princeton.edu/java/data/ospd.txt

		Set<String> shakespeareWords = Files.lines(Paths.get("files/words.shakespeare.txt")).map(word -> word.toLowerCase()).collect(Collectors.toSet());

		Set<String> scrabbleWords = Files.lines(Paths.get("files/ospd.txt")).map(word -> word.toLowerCase()).collect(Collectors.toSet());

		System.out.println("# words of Shakespeare : " + shakespeareWords.size());
		System.out.println("# words of Scrabble : " + scrabbleWords.size());

		final int[] scrabbleENScore = {
				// 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
				// a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z
				1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10 };

		System.out.println("h".charAt(0) - 'a');
		System.out.println("value: " + scrabbleENScore["z".charAt(0) - 'a']);

		Function<String, Integer> score = word -> word.chars().map(letter -> scrabbleENScore[letter - 'a']).sum();

		ToIntFunction<String> intScore = word -> word.chars().map(letter -> scrabbleENScore[letter - 'a']).sum();

		System.out.println("Score of hello: " + intScore.applyAsInt("hello"));

		String bestWord = shakespeareWords.stream().filter(word -> scrabbleWords.contains(word)).max(Comparator.comparing(score)).get();

		System.out.println("Best word: " + bestWord);

		IntSummaryStatistics summaryStatistics = shakespeareWords.stream().parallel().filter(scrabbleWords::contains).mapToInt(intScore).summaryStatistics();

		System.out.println("Stats: " + summaryStatistics);

		// build histograms of words
		Map<Integer, List<String>> histogramOfWords = shakespeareWords.stream().filter(scrabbleWords::contains).collect(Collectors.groupingBy(score));
		System.out.println("#numeber of pairs: " + histogramOfWords.size());

		histogramOfWords.entrySet().stream().sorted(Comparator.comparing(entry -> -entry.getKey())).limit(3)
				.forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));

		// letter -> letter <=> Function.identity()
		Function<String, Map<Integer, Long>> histoWord = word -> word.chars().boxed()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		Function<String, Long> numberOfBlanks = word -> histoWord.apply(word) // Map<Integer, Long>
				.entrySet().stream() // Map.Entry<Integer, Long>
				.mapToLong(entry -> Long.max(entry.getValue() - scrabbleENScore[entry.getKey() - 'a'], 0L)).sum();

		System.out.println("# number of blanks: " + numberOfBlanks.apply("whizzing"));

	}
}
