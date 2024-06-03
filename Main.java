/*
Description: The program plays the game of Hangman. The program picks a random word or phrase from a random category(coded in
an array in the program). The program displays it on the screen in the format: ---- where each - represents a letter of the
word or phrase. The program allows the user to guess a single letter. The program displays the characters of the hangman after.
each guess.
*/

//importing Scanner
import java.util.Scanner;

// public class
public class Main {


	/**
	 * @param args the command line arguments
	 */

	// making the Scanner
	static Scanner input = new Scanner(System.in);

	// main method
	public static void main(String[] args) {

		// String arrays with elements in it
		String[] sports = { "SPORTS", "BOBBY ORR", "BASKETBALL", "RACQUETBALL", "SIDNEY CROSBY" };
		String[] movies = { "MOVIES", "AVATAR", "STAR WARS", "THE GODFATHER", "TERIMINATOR" };
		String[] tvShows = { "TV SHOWS", "SPONGEBOB SQUAREPANTS", "FAMILY GUY", "LOST", "HOUSE" };

		String[][] words = { sports, movies, tvShows };

		// generates a random number between 0 to 2 to pick the category
		int randomCat = (int) ((2 - 0 + 1) * Math.random()) + 0;

		// generates a random number between 1 and the category's length to pick a word in it
		int randomWord = (int) (((words[randomCat].length - 1) - 1 + 1) * Math.random()) + 1;

		// saving the word inside of a variable called word
		String word = words[randomCat][randomWord];

		// declares a array string with the amount of elements in the word
		String[] guessTheWord = new String[word.length()];

		// for loop that runs for how many elements are in the word
		for (int i = 0; i < guessTheWord.length; i++) {

			// runs if there are no spaces in that element
			if (!word.substring(i, i + 1).equals(" ")) {
				// rewrites that element as a -
				guessTheWord[i] = "-";
			}

			// runs if there is a space in that element
			else {
				// rewrites that element as a " "
				guessTheWord[i] = " ";
			}
		}

		// displays the category
		System.out.println("Category: " + words[randomCat][0]);

		// displays the number of words in that word by using the method numWords
		System.out.println("Number of Words: " + numWords(words, randomCat, randomWord));

		// calls the guessTheWord function to actually play the game
		guessTheWord(words, randomCat, randomWord, guessTheWord);

	}

	/**
	 * Method, numWords will check to see how many words are in the word. Method
	 * will return an int for how many words there are.
	 * 
	 * @param words, category, randomWord
	 *               returns int, numWords
	 */

	public static int numWords(String[][] words, int category, int randomWord) {

		// variable for int
		int numWords = 1;
		// String array that stores the word in the words array into the String variable called word
		String word = words[category][randomWord];

		// for loop that runs for how many elements are in the word
		for (int i = 0; i < word.length(); i++) {
			// runs if statement if there is a space in that substring of the word
			if (word.substring(i, i + 1).equals(" ")) {
				// adds one to numWords
				numWords++;
			}

		}

		// returns the number of words in the word to the method
		return numWords;

	}

	/**
	 * Method, guessTheWord will actually play the game and handle user input. It
	 * will display the game and handle guessing the word. It will return nothing.
	 * 
	 * @param words, category, randomWord, guessTheWord
	 *               returns nothing
	 */

	public static void guessTheWord(String[][] words, int category, int randomWord, String[] guessTheWord) {

		// String variables
		String userLetter;

		String wordGuessed = "";
		// boolean variables
		boolean exist = false;
		
		// int variables
		int guesses = 7;
		// String array that stores the word in the words array into the String variable called word
		String word = words[category][randomWord];

		// runs for loop for the total number of elements in the guessTheWord array
		for (int i = 0; i < guessTheWord.length; i++) {
			// prints the element in the array guessTheWord
			System.out.print(guessTheWord[i]);
		}

		// skips a line
		System.out.println();

		// runs the loop at least once
		do {
			
			// resets exists to false
			exist = false;
			// sets the wordGuessed to ""
			wordGuessed = "";
      
			// Gets the user input for the letter they want to guess
			System.out.println("");
			userLetter = input.next();

			// prints out the category
			System.out.println("Category: " + words[category][0]);
			
			// displays the number of words in that word by using the method numWords
			System.out.println("Number of Words: " + numWords(words, category, randomWord));

			// runs for loop for the length of the word
			for (int i = 0; i < word.length(); i++) {

				// runs if the user guessed the correct letter in the word
				if (word.substring(i, i + 1).equalsIgnoreCase(userLetter)) {
					// sets the element in the guessTheWord array equal to what the user entered
					guessTheWord[i] = userLetter.toUpperCase();
					exist = true;
				}

				// prints the entire list of elements in the array guessTheWord
				System.out.print(guessTheWord[i]);

			}

			// runs if the letter is not in the word
			if (exist == false) {
				// subtracts one to guesses
				guesses--;
				// runs the hangMan method to display the hangMan
				
			}
			hangMan(guesses);
			// runs for loop for the total number of elements in the guessTheWord array
			for (int i = 0; i < guessTheWord.length; i++) {
				// adds each element in the guessTheWord array to the variable wordGuessed
				wordGuessed += guessTheWord[i];
			}

			// runs if the user got the entire word right and congratulates them
			if (wordGuessed.equals(word)) {
				System.out.println();
				System.out.println("Congratulations! You guessed correctly!");
				// breaks out of the loop
				break;
			}

			// skips a line
			System.out.println();

			// do while loop that will continue running until the user is out of guesses
		} while (guesses > 0);

		// runs if the user is out of guesses
		if (guesses < 1) {
      // skips a line
			System.out.println();
			// displays a message saying you have lost
			System.out.println("Game Over! You have lost!");
		}

	}

	/**
	 * Method, hangMan will display the Hangman every round.
	 *  Method will return nothing.
	 * 
	 * @param guesses
	 *                returns nothing
	 */

	public static void hangMan(int guesses) {

		// skips two lines
		System.out.println();
		System.out.println();

		// A string array that stores the hangman elements in it
		String[] hangMan = { "   O\n", "  /", "|", "\\\n", "   |\n", "  /", " \\" };

		// prints out each hangman element as the user gets more guesses wrong
		for (int i = 0; i < 7 - guesses; i++) {
			// prints the hangman element out
			System.out.print(hangMan[i]);
		}

	}

}