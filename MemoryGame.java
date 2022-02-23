/**
 * Project 3.6.5
 *
 * The Memory Game shows a random sequence of "memory strings" in a variety of buttons.
 * After wathcing the memory strings appear in the buttons one at a time, the
 * player recreates the sequence from memory.
 */
public class MemoryGame
{
  public static void main(String[] args) {

    // Create the "memory strings" - an array of single character strings to 
    // show in the buttons, one element at a time. This is the sequence
    // the player will have to remember.
    String[] memoryString = {"a", "b", "c"};

    boolean quit = false;
    int score = 0;
    int rounds = 0;
    double delay = 1;

    // Create the game and gameboard. Configure a randomized board with 3 buttons.
    MemoryGameGUI memory = new MemoryGameGUI();
    memory.createBoard(3, true);
    // Play the game until user wants to quit.
    while (quit != true)
    {
      // Create a new array that will contain the randomly ordered memory strings.
      String[] currentMemoryString = new String[3];
      
      // Takes the memoryString array and randomizes the elements, with no repeats.
      currentMemoryString = RandomPermutation.next(memoryString);

      // Play one sequence, delaying half a second for the strings to show
      // in the buttons. Save the player's guess. 
      // (Later, you can speed up or slow down the game.)
      String guess = memory.playSequence(currentMemoryString, delay);
      // Determine if player's guess matches all elements of the random sequence.
      // Cleanup the guess - remove commas and spaces.
      String cleanGuess = "";
      for (int i = 0; i < guess.length(); i++)
      {
        if (!guess.substring(i, i+1).equals(",") && !guess.substring(i, i+1).equals(" "))
        {
          cleanGuess += guess.substring(i, i+1);
        }
      }
      // iterate to determine if guess matches sequence
      boolean matched = true;
      int x = 0;
      for (String i : currentMemoryString)
      {
        if (!i.equals(cleanGuess.substring(x, x+1)))
        {
          matched = false;
          break;
        }
        x++;
      }

      // If match, increase score, and signal a match, otherwise, try again.
      if (matched == true)
      {
        memory.matched();
        score++;
      }
      else
        memory.tryAgain();
      // Ask if user wants to play another round of the game 
      // and track the number of games played.
      boolean again = memory.playAgain();
      rounds++;
      // Subtracts from the delay used when displaying the memory string, 
      // making it harder to get it correct as you go
      delay-= 0.2;

      if (again == false)
        quit = true;
    }
      
    // When done playing, show score and end the game.
    memory.showScore(score, rounds);
    memory.quit();
  }
}