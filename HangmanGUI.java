import javax.swing.*;
import java.awt.*;

public class HangmanGUI extends JFrame {

    private HangmanGame game;

    private JLabel themeLabel;
    private JLabel wordLabel;
    private JLabel guessedLabel;
    private JLabel statusLabel;

    private HangmanDrawing drawing;

    private JButton[] letterButtons = new JButton[26];

    private boolean hintUsed = false;
    private String currentTheme;

    public HangmanGUI() {
        startNewGame();

        setTitle("Hangman Game");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // print the title
        JLabel title = new JLabel("Hangman Game", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // draw the hangman
        drawing = new HangmanDrawing();
        add(drawing, BorderLayout.CENTER);

        // bottom panel
        JPanel bottom = new JPanel(new GridLayout(5, 1));

        themeLabel = new JLabel("Theme: " + currentTheme, JLabel.CENTER);
        themeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        wordLabel = new JLabel(game.getDisplayWord(), JLabel.CENTER);
        wordLabel.setFont(new Font("Monospaced", Font.BOLD, 28));

        guessedLabel = new JLabel("Guessed: " + game.getGuessedLetters(), JLabel.CENTER);

        statusLabel = new JLabel("Click a letter", JLabel.CENTER);

        // display Alphabet in buttons form
        JPanel alphabetPanel = new JPanel(new GridLayout(2, 13));

        for (int i = 0; i < 26; i++) {
            char letter = (char) ('a' + i);
            JButton btn = new JButton(String.valueOf(letter).toUpperCase());

            letterButtons[i] = btn;

            btn.addActionListener(e -> handleLetterGuess(letter, btn));

            alphabetPanel.add(btn);
        }

        bottom.add(themeLabel);
        bottom.add(wordLabel);
        bottom.add(guessedLabel);
        bottom.add(statusLabel);
        bottom.add(alphabetPanel);

        add(bottom, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Start the game
    private void startNewGame() {
        String[] options = {"Animal", "Fruit", "Class Object"};

        int choice = JOptionPane.showOptionDialog(
                this,
                "Choose a theme",
                "Hangman",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        String fileName = "";

        if (choice == 0) {
            fileName = "animal.txt";
            currentTheme = "Animal";
        } else if (choice == 1) {
            fileName = "fruit.txt";
            currentTheme = "Fruit";
        } else {
            fileName = "class_object.txt";
            currentTheme = "Class Object";
        }

        game = new HangmanGame(fileName);
        hintUsed = false;
    }

    // Letter guess
        private void handleLetterGuess(char guess, JButton btn) {
        btn.setEnabled(false);

        boolean correct = game.makeGuess(guess);

        wordLabel.setText(game.getDisplayWord());
        guessedLabel.setText("Guessed: " + game.getGuessedLetters());

        if (correct) statusLabel.setText("Correct!");
        else statusLabel.setText("Wrong!");

        drawing.setWrongGuesses(game.getWrongGuesses());
        drawing.repaint();

        // hint check when it is only 5 lives left
        if (game.getWrongGuesses() == 5 && !hintUsed) {

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Do you want a hint?",
                    "Hint",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                game.useHint();   // No lives lost after hitting yes or no for hint 
                hintUsed = true;

                wordLabel.setText(game.getDisplayWord());
                guessedLabel.setText("Guessed: " + game.getGuessedLetters());
            }
        }

        checkGameOver();
    }

    // GAME OVER
    private void checkGameOver() {
        if (game.isGameOver()) {

            String message;

            if (game.isWin()) {
                message = "You Win! Play again?";
            } else {
                message = "You lost! The word was: " + game.getWord() + "\nPlay again?";
            }

            disableAllButtons();

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    message,
                    "Game Over",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                restartGame();
            } else {
                System.exit(0);
            }
        }
    }

    // disable buttons
    private void disableAllButtons() {
        for (JButton btn : letterButtons) {
            btn.setEnabled(false);
        }
    }

    // restart the game after win or lose
    private void restartGame() {
        startNewGame();

        themeLabel.setText("Theme: " + currentTheme);
        wordLabel.setText(game.getDisplayWord());
        guessedLabel.setText("Guessed: " + game.getGuessedLetters());
        statusLabel.setText("Click a letter");

        for (JButton btn : letterButtons) {
            btn.setEnabled(true);
        }

        drawing.setWrongGuesses(0);
        drawing.repaint();
    }

    public static void main(String[] args) {
        new HangmanGUI();
    }
}