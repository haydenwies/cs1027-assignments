/**
 * Class representing a letter
 * @author Hayden Wies
 */
public class Letter {

    private final int UNSET = 0;
    private final int UNUSED = 1;
    private final int USED = 2;
    private final int CORRECT = 3;


    private char letter;
    private int label;


    public Letter(char letter) {
        this.label = UNSET;
        this.letter = letter;
    }


    /**
     * Gets the letter of this object instance
     * @return Letter value of character type
     */
    public char getLetter() {
        return this.letter;
    }


    /**
     * Compares an object of Letter class with the instance object
     * @param other Letter / ExtendedLetter to be compared with instance
     * @return Boolean determining whether provided letter is same as instance letter
     */
    public boolean equals(Object other) {
        // If object other is an instance of Letter class
        if(other instanceof Letter) {
            Letter otherLetter = (Letter)other;
            // If characters are equal return true else return false
            if (this.letter == otherLetter.getLetter()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    
    /**
     * Gets the proper sypbol to be used to decorate the instance letter depending on whether letter is unused, used, or correct
     * @return Symbol used as decorator for instance letter
     */
    public String decorator() {
        switch (label) {
            case UNSET: return " ";
            case UNUSED: return "-";
            case USED: return "+";
            case CORRECT: return "!";
            default: return "LABEL_OUT_OF_BOUNDS";
        } 
    }


    /**
     * Creates decorated version of letter with decorator() function
     * @returns String of letter surrounded by decorator
     */
    public String toString() {
        // Wrap letter in decorator symbols
        return (decorator() + this.letter + decorator());
    }


    /**
     * Sets instance label to unused
     */
    public void setUnused() {
        this.label = UNUSED;
    }


    /**
     * Sets instance label to used
     */
    public void setUsed() {
        this.label = USED;
    }


    /**
     * Sets instance label to correct
     */
    public void setCorrect() {
        this.label = CORRECT;
    }


    /**
     * Determines if instance letter is unused
     * @return Boolean determining if letter is unused
     */
    public boolean isUnused() {
        if (this.label == UNUSED) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Creates an array of letter objects from a provided string
     * @param s String that will be converted to Letter array
     * @return Letter array in question
     */
    public static Letter[] fromString(String s) {
        Letter[] letterArray = new Letter[s.length()];

        // Loop through characters of string
        for (int i = 0; i < s.length(); i++) {
            // Create letter object and set to corresponding area of letterArray
            Letter l = new Letter(s.charAt(i));
            letterArray[i] = l;
        }

        return letterArray;
    }

}