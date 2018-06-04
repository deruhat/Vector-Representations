import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Task3 {

	static List<Word> wordList = new ArrayList<Word>();

	public static double cosineSim(double[] A, double[] B) {
		if (A.length != B.length)
			return 1;
		double dotProduct = 0;
		double ANorm = 0;
		double BNorm = 0;
		for (int i = 0; i < A.length; i++) {
			dotProduct += A[i] * B[i];
			ANorm += A[i] * A[i];
			BNorm += B[i] * B[i];
		}

		double result = dotProduct / (Math.sqrt(ANorm) * Math.sqrt(BNorm));
		return result;
	}

	public static Word findWord(String wordChosen) {
		for (Word word : wordList) {
			if (word.wordString.equals(wordChosen)) {
				return word;
			}
		}
		return null;
	}

	public static void main(String args[]) throws IOException {
		// Load the input
		// Open the file
		FileInputStream fstream = new FileInputStream("src/glove.6B.300d.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;
		// Read File Line By Line
		while ((strLine = br.readLine()) != null) {
			String arr[] = strLine.split(" ", 2);
			String firstWord = arr[0];
			String numbers = arr[1];

			String[] numbersSplit = numbers.split(" ");

			double[] doubleValues = Arrays.stream(numbersSplit).mapToDouble(Double::parseDouble).toArray();

			wordList.add(new Word(doubleValues, firstWord));
		}

		// Close the input stream
		br.close();

		// Sentences:
		String S_0 = "i love watching movies at night";
		String S_1 = "i love watching movies during the day";
		String S_2 = "donald trump is the president of the united states";

		// split the sentence to words (tokenize):
		String[] wordsSplit0 = S_0.split(" ");
		int wordsFound0 = 0;
		double[] query0 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		for (int j = 0; j < wordsSplit0.length; j++) {

			// check if words exist in data
			Word queryWord = findWord(wordsSplit0[j]);
			if (queryWord == null) {
				System.out.println("Word \"" + wordsSplit0[j] + "\" is not in the data.");
				continue;
			} else {
				System.out.println("Found word \"" + wordsSplit0[j] + "\"");
				wordsFound0++;
			}

			// component-wise sum
			for (int x = 0; x < 300; x++) {
				query0[x] += queryWord.vector[x];
			}
		}

		// take the average

		for (int y = 0; y < 300; y++) {
			query0[y] = query0[y] / wordsFound0;
		}

		// split the sentence to words (tokenize):
		String[] wordsSplit1 = S_1.split(" ");
		int wordsFound1 = 0;
		double[] query1 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		for (int j = 0; j < wordsSplit1.length; j++) {

			// check if words exist in data
			Word queryWord = findWord(wordsSplit1[j]);
			if (queryWord == null) {
				System.out.println("Word \"" + wordsSplit1[j] + "\" is not in the data.");
				continue;
			} else {
				System.out.println("Found word \"" + wordsSplit1[j] + "\"");
				wordsFound1++;
			}

			// component-wise sum
			for (int x = 0; x < 300; x++) {
				query1[x] += queryWord.vector[x];
			}
		}

		// take the average

		for (int y = 0; y < 300; y++) {
			query1[y] = query1[y] / wordsFound1;
		}
		// split the sentence to words (tokenize):
		String[] wordsSplit2 = S_2.split(" ");
		int wordsFound2 = 0;
		double[] query2 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		for (int j = 0; j < wordsSplit2.length; j++) {

			// check if words exist in data
			Word queryWord2 = findWord(wordsSplit2[j]);
			if (queryWord2 == null) {
				System.out.println("Word \"" + wordsSplit2[j] + "\" is not in the data.");
				continue;
			} else {
				System.out.println("Found word \"" + wordsSplit2[j] + "\"");
				wordsFound2++;
			}

			// component-wise sum
			for (int x = 0; x < 300; x++) {
				query2[x] += queryWord2.vector[x];
			}
		}

		// take the average

		for (int y = 0; y < 300; y++) {
			query2[y] = query2[y] / wordsFound2;
		}
		// print the vector representation for the sentence:
		System.out.println();
		System.out.println("Vector representation of the sentence S_0: " + Arrays.toString(query0));
		System.out.println();
		System.out.println("Vector representation of the sentence S_1: " + Arrays.toString(query1));
		System.out.println();
		System.out.println("Vector representation of the sentence S_2: " + Arrays.toString(query2));
		System.out.println();

		// Find Similarities
		System.out.println("Similarity between S_0 and S_1: ");
		double sim = cosineSim(query0, query1);
		System.out.print(sim);
		System.out.println();
		System.out.println("Similarity between S_0 and S_2: ");
		sim = cosineSim(query0, query2);
		System.out.print(sim);
	}

	static class Word {
		double[] vector;
		String wordString;

		public Word(double[] vector, String wordString) {
			this.wordString = wordString;
			this.vector = vector;
		}
	}

	// Modeling the results (similarity)
	static class Result {
		double sim;
		String word;

		public Result(double sim, String word) {
			this.word = word;
			this.sim = sim;
		}
	}

	// Comparator for cosine similarity
	static class SimComparator implements Comparator<Result> {
		@Override
		public int compare(Result a, Result b) {
			if (a.sim < b.sim) {
				return 1;
			} else {
				return -1;
			}
		}
	}

}
