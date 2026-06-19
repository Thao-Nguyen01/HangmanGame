import java.util.Scanner;
import java.util.Random;

public class Hangman {
   public static void main(String [] args){
      Scanner scanner = new Scanner(System.in);
      
      //array of theme
      String[] themes = {"animal", "fruit", "class object"};
      
      //print the question to start the game
      System.out.print("Chose the theme:\n");
      for (int i = 0; i < themes.length; i++) {
         System.out.println((i + 1) + ". " + themes[i]);
      }
      System.out.print("Enter choice (1-3): ");
      int choice = scanner.nextInt();
      
      String selectedTheme = themes[choice - 1];
      System.out.println("You chose: " + selectedTheme);
      
      String fileName = "";
      //Condition for choosing the theme
      if (choice == 1) {
         fileName = "animal.txt";
      }else if (choice == 2) {
         fileName = "fruit.txt";
      }else if (choice == 3) {
         fileName = "class_object.txt";
      }else {
         System.out.println("Invalid choice");
         return;
      } 
      
      //get the random word from the text file     
      String word = WordBank.getRandomWord(fileName);
     
     //nicely display each letter
      char[] display = new char[word.length()];
      
      //Display the random word in '_' form for the player to guess
      for (int i = 0; i < display.length; i++) {
         display[i] = '_';
      }
      
      //lives for the player 
      int lives = 10;
      
      char[] array = {'a','b','c','d','e','f','g','h','i','j',
    'k','l','m','n','o','p','q','r','s','t',
    'u','v','w','x','y','z'};
    
      //used letter array
      char [] used = new char[26];
      int usedCount = 0;
      boolean hintUsed = false;
      
      
      while(lives > 0){
         //Ask the player if they want to use the hint
         Random rand = new Random();
         int chooseHint = 0;
         if(lives == 5){
            System.out.print("Use hint? (1) Yes or (2) No: ");
            chooseHint = scanner.nextInt();
            
            System.out.println("Hint: ");
         }
         
      if (chooseHint == 1){
      int index;
      

    // find a random hidden position
    do {
        index = rand.nextInt(word.length());
    } while (display[index] != '_');

    // get the letter
    char letter = word.charAt(index);

    // reveal ALL occurrences of that letter
    for (int i = 0; i < word.length(); i++) {
        if (word.charAt(i) == letter) {
            display[i] = letter;
        }
    }
}
         
         //print out the display _ _ _
         for (char c : display){
            System.out.print(c + " ");
         }
         //print the alphabet
         System.out.print("\n");
         for (char c : array) {
            System.out.print(c + " ");
         }
         
         
         //get input, only 1 letter at a time
         System.out.print("Guess the letter: ");
         char guess = scanner.next().charAt(0);
         
         boolean correct = false;
         
                      
               
         //check the guess letter if it is correct then the letter will replace the display(_)
         for (int i = 0; i < word.length(); i++){
            if (word.charAt(i) == guess){
               display[i] = guess;
               correct = true;
            }
         }
         
         for (int i = 0; i < array.length; i++) {
             if (array[i] == guess) {
               array[i] = ' ';   // remove it (replace with space)
            }
         }                 
              
         if (!correct) {
            lives --;
            System.out.printf("Wrong! Lives left: %d\n", lives);
         }
         boolean alreadyUsed = false;

         for (int i = 0; i < usedCount; i++) {
            if (used[i] == guess) {
               alreadyUsed = true;
               break;
            }
         }

         if (alreadyUsed) {
            System.out.println("Invalid: already guessed!");
            continue; // continue guessing 
         }
         used[usedCount] = guess;
         usedCount++;
                         
         
         //check if the player win or lose
         boolean win = true;
         for (char c : display) {
            if (c == '_'){
               win = false;
               break;
            }
         }
         if (win) {
            System.out.printf("You win! The word is: %s", word);
            break;
         }
      }
      
      if (lives == 0){
         System.out.printf("You lose! The word is: %s\n", word);
      }
      
      scanner.close();
   }
}

         


   