import java.util.*;

public class HangmanGame {
    private String word;
    private char[] display;
    private int lives;
    private boolean win;
    private boolean[] usedLetters;
    private String guessedLetters;

    public HangmanGame(String fileName) {
        word = WordBank.getRandomWord(fileName);

        display = new char[word.length()];
        Arrays.fill(display, '_');

        lives = 10;
        win = false;

        usedLetters = new boolean[26];
        guessedLetters = "";
    }

    // player make the guess
    public boolean makeGuess(char guess) {
        guess = Character.toLowerCase(guess);

        if (guess < 'a' || guess > 'z') return false;
        if (usedLetters[guess - 'a']) return false;

        usedLetters[guess - 'a'] = true;
        guessedLetters += Character.toUpperCase(guess) + " ";

        boolean correct = false;

        for (int i = 0; i < word.length(); i++) {
            if (Character.toLowerCase(word.charAt(i)) == guess) {
                display[i] = word.charAt(i);
                correct = true;
            }
        }

        if (!correct) {
            lives--; // only real guesses reduce lives
        }

        checkWin();
        return correct;
    }

    //5 lives left
    public void useHint() {
        Random rand = new Random();
        int index;

        // find hidden letter
        do {
            index = rand.nextInt(word.length());
        } while (display[index] != '_');

        char letter = Character.toLowerCase(word.charAt(index));

        // reveal ALL positions of that letter
        for (int i = 0; i < word.length(); i++) {
            if (Character.toLowerCase(word.charAt(i)) == letter) {
                display[i] = word.charAt(i);
            }
        }

        // mark as used so player cannot guess it again
        usedLetters[letter - 'a'] = true;

        guessedLetters += Character.toUpperCase(letter) + " ";

        checkWin();
    }

    // check if win
        private void checkWin() {
        for (char c : display) {
            if (c == '_') return;
        }
        win = true;
    }

    // display the word
    public String getDisplayWord() {
        StringBuilder sb = new StringBuilder();
        for (char c : display) {
            sb.append(c).append(" ");
        }
        return sb.toString();
    }

    // guess display
    public String getGuessedLetters() {
        return guessedLetters;
    }

    // connect to the drawing and wrong guess
    public int getWrongGuesses() {
        return 10 - lives;
    }

    public boolean isGameOver() {
        return lives <= 0 || win;
    }

    public boolean isWin() {
        return win;
    }

    public String getWord() {
        return word;
    }
}