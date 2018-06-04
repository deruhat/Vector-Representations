import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Task1 {

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

		int k = 5;// # of neighbors

		// list to save similarity result
		List<Result> resultList = new ArrayList<Result>();

		// Load the input
		// Open the file
		FileInputStream fstream = new FileInputStream("src/glove.6B.300d.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;
		// Read File Line By Line
		while ((strLine = br.readLine()) != null) {
			String arr[] = strLine.split(" ", 2);
			String firstWord = arr[0]; // the
			String numbers = arr[1]; // quick brown fox

			String[] numbersSplit = numbers.split(" ");

			double[] doubleValues = Arrays.stream(numbersSplit).mapToDouble(Double::parseDouble).toArray();

			wordList.add(new Word(doubleValues, firstWord));

		}

		// Close the input stream
		br.close();

		// Word to find 5 nearest neighbors of (I chose "beyonce" because my name is not
		// in the list)
		Scanner reader = new Scanner(System.in); // Reading from System.in
		System.out.println("Enter a word (all in lower case): ");
		String input = reader.nextLine(); // Scans the next token of the input as an int.

		reader.close();

		// search for the word:
		Word queryWord = findWord(input);
		double[] query = queryWord.vector;
		String wordChosen = queryWord.wordString;
		System.out.println("Word chosen: " + wordChosen);

		// Find Similarities
		for (Word word : wordList) {
			double sim = cosineSim(word.vector, query);
			resultList.add(new Result(sim, word.wordString));
		}

		Collections.sort(resultList, new SimComparator());
		System.out.println("5 Nearest Neighbors: ");
		for (int x = 1; x < k+1; x++) {
			System.out.println(resultList.get(x).word + ",\t\t Cosine similarity: " + resultList.get(x).sim);
		}
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
