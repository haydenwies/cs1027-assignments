/**
 * Class representing a game of WordLL in which multiple instances of the game can be played
 * @author Hayden Wies
 */
public class WordLL {
    
    private Word mysteryWord;
	private LinearNode<Word> history;


    public WordLL(Word mystery) {
		this.history = new LinearNode<Word>();
        this.mysteryWord = mystery;
	}


    /**
     * Takes in a guess word and compares it to the word in question (mystery word)
     * @param guess Guess word
     * @return Boolean (true if guess is correct)
     */
    public boolean tryWord(Word guess) {
        // Compare to mystery word
        boolean result = guess.labelWord(this.mysteryWord);

        // Update history
        LinearNode<Word> newHist = new LinearNode<Word>(guess);
        newHist.setNext(this.history);
        this.history = newHist;

        return result;
    }


    /**
     * Creates string with all guesses
     * @return History of guesses with each guess on a seperate line
     */
    public String toString() {
        String printStr = "";
        LinearNode<Word> curr = this.history;
         
        // Loop through history and append stringify of word to printStr
        while (curr.getElement() != null) {
            printStr += (curr.getElement().toString() + "\n");
            curr = curr.getNext();
        }

        return printStr;
    }

}
