import java.io.*;
import java.util.*;

public class Wordle {
    private final String[] dict;
    private final String WoD;
    private int TRY;
    public Wordle(String[] args) throws Exception {
        // This part of the code provides to load the dictionary from the file on which will be based.

        Scanner scanner = new Scanner(new File("dict.txt"));
        List<String> words = new ArrayList<>();
        while (scanner.hasNextLine()) {
            words.add(scanner.nextLine().toUpperCase());
        }
        dict = words.toArray(new String[0]);
        scanner.close();


        // Select a random keyword as a WoD from the dictionary.
        Random random = new Random();
        WoD = dict[random.nextInt(dict.length)];

        // Initialize the number of tries.
        TRY = 0;

        // Start the game.
        for (String word : args) {
            start(word.toUpperCase());
        }
    }
    private void start(String word) {
        TRY++;
        if (word.length() != 5) {
            System.out.println("Try" + (TRY) + " (" + word + "): The length of word must be five!");
        } else if (!Arrays.asList(dict).contains(word)) {
            System.out.println("Try" + (TRY) + " (" + word + "): The word does not exist in the dictionary!");
        } else if (word.equals(WoD)) {
            System.out.println("Congratulations! You guessed right in " + ord(TRY) + " shot!");
            TRY = 6;
            // Terminate the loop
        } else {
            // This part of the code provides that compares the letters of the guessed words with the WoD which is the keyword.
            StringBuilder feedback = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                char c = word.charAt(i);
                if (c == WoD.charAt(i)) {
                    feedback.append((i+1) + ". letter exists and located in right position.\n");
                } else if (WoD.indexOf(c) != -1) {
                    feedback.append((i+1) + ". letter exists but located in wrong position.\n");
                } else {
                    feedback.append((i+1) + ". letter does not exist.\n");
                }
            }
            System.out.println("Try" + (TRY) + " (" + word + "):\n" + feedback.toString().trim());
            if (TRY == 6) {
                System.out.println("You exceeded maximum try shot!\nYou failed! The key word is " + WoD + ".");
            }
        }
    }
    private String ord(int k) {
        // This part of the code provides that the in the situation of guessing the WoD correctly, shows how many attempts did it take to guess the WoD.
        if (k == 1) {
            return "1st";
        } else if (k == 2) {
            return "2nd";
        } else if (k == 3) {
            return "3rd";
        } else {
            return k + "th";
        }
    }
    public static void main(String[] args) throws Exception {
        new Wordle(args);
    }
}
