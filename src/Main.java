import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) {

		// creating the Map
		Map<String, String> hm = new HashMap<>();

		/* getting words from the file */
		String englishText = loadFromFile(new File("English.in.txt")); // extension should be .txt !!!
		System.out.println(englishText);

		/* splitting words inside the file */
		String[] arrayOne = englishText.split(" "); // arrayOne !!!

		addWordsToDictionary(hm);

		translateTheText(hm, arrayOne);
		System.out.println();
		System.out.println();
		System.out.println("The program work is finished.");
	}

	public static void addWordsToDictionary(Map<String, String> hm) {

		Scanner sc = new Scanner(System.in);
		boolean k = true;
		while (k == true) {
			System.out.println("Would you like to make an entry to the dictionary? Y/N " + " ");
			String userAnswerStart = sc.nextLine();
			if (userAnswerStart.equals("Y")) {
				System.out.println("Please, enter English word: ");
				String englishWord = sc.nextLine();
				if (!englishWord.equals(null)) {
					System.out.println("Please, enter Ukrainian word: ");
					String ukrainianWord = sc.nextLine();
					hm.put(englishWord, ukrainianWord);
				}
			} else {
				System.out.println("Well, Good-bye!");
				k = false;
			}
		}
		sc.close();
	}

	public static void translateTheText(Map<String, String> hm, String[] arrayOne) {
		try (PrintWriter pw = new PrintWriter("Ukrainian.out.txt")) {

			// printing to the console the content of arrayOne
			System.out.println(Arrays.toString(arrayOne));
			System.out.println();

			// printing the translation: arrayOne -> Map -> print
			for (String key : arrayOne) {
				System.out.print(hm.get(key) + " ");
				pw.print(hm.get(key) + " ");
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR FILE WRITE");
		}
	}

	public static String loadFromFile(File file) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String text = "";
			for (; (text = br.readLine()) != null;) {
				sb.append(text).append(System.lineSeparator());
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		return sb.toString();
	}
}
