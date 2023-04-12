/**
 * Class representing a word held as a singly linked list of letter objects
 * @author Hayden Wies
 */
public class Word {
    
    private LinearNode<Letter> firstLetter = new LinearNode<Letter>();


    public Word(Letter[] letters) {
        // Set first letter
        firstLetter.setElement(letters[0]);

        // Set the previous node
        LinearNode<Letter> prev = firstLetter;
        for (int i = 1; i < letters.length; i++) {
            // Create temp current node
            LinearNode<Letter> curr = new LinearNode<Letter>(letters[i]);
            // Set the pointer of previous node to current node then set current to previous
            prev.setNext(curr);
            prev = curr;
        }
    }


    /**
     * Decorated and retuns instance word as string
     * @return String of decorated word
     */
    public String toString() {
        String word = "Word: ";

            LinearNode<Letter> curr = firstLetter;
            // Loop through linked list and append decorated letter to word
            while (curr != null) {
                word += (curr.getElement().toString() + " ");
                curr = curr.getNext();
            }

        return word;
    }


    /**
     * Updated letters of word by comparing to provided mystery word
     * @param mystery Word to be compared against instance word
     * @return Boolean determining if provided word is equal to instance word
     */
    public boolean labelWord(Word mystery) {
        LinearNode<Letter> curr = this.firstLetter;
        boolean isEqual = true;

        int thisCounter = 0;
        // Loop thorugh class word
        while (curr != null) {
            LinearNode<Letter> currMystery = mystery.firstLetter;
            // Info variables
            boolean existsIn = false;
            boolean existsAtLocation = false;

            int mysteryCounter = 0;
            // Loop through mystery word
            while (currMystery != null) {

                // If letter of current node equals letter of current mystery node
                if (curr.getElement().equals(currMystery.getElement())) {
                    if (thisCounter == mysteryCounter) {
                        // If in same position, update info
                        existsAtLocation = true;
                    } else {
                        // If not in same position, update info and declare words are not equal
                        existsIn = true;
                        isEqual = false;
                    }
                }

                mysteryCounter++;
                currMystery = currMystery.getNext();
            }

            // Update label depending on info variables
            if (existsAtLocation) {
                curr.getElement().setCorrect();
            } else if (existsIn) {
                curr.getElement().setUsed();
            } else {
                curr.getElement().setUnused();
            }

            thisCounter++;
            curr = curr.getNext();
        }

        return isEqual;
    }

}
