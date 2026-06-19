import java.io.File;
import java.util.*;

public class WordBank {
//a method to put the word from text file into an array 
    public static String getRandomWord(String fileName) {
        List<String> words = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(new File(fileName));

            while (fileScanner.hasNext()) {
                words.add(fileScanner.next().toLowerCase());
            }

            fileScanner.close();

        } catch (Exception e) {
            System.out.println("File not found: " + fileName);
        }
      //get random word from the text file and put it into an array
        Random rand = new Random();
        return words.get(rand.nextInt(words.size()));
    }
}