/**
 * Class extending the letter object used to contain abstract "letters" such as symbols
 * @author Hayden Wies
 */
public class ExtendedLetter extends Letter {
    
    final int SINGLETON = -1;

    private String content;
    private int family;
    private boolean related;


    public ExtendedLetter(String s) {
        super('a');
        this.content = s;
        this.family = SINGLETON;
        this.related = false;
    }


    public ExtendedLetter(String s, int fam) {
        super('a');
        this.content = s;
        this.family = fam;
        this.related = false;
    }


    /**
     * Compares an object of Letter class with the instance object
     * @param other Letter / ExtendedLetter to be compared with instance
     * @return Boolean determining whether provided letter is same as instance letter
     */
    public boolean equals(Object other) {
        if (other instanceof ExtendedLetter) {
            ExtendedLetter otherExtLtr = (ExtendedLetter) other;
            // Check if families are the same, if yes set related to true
            if (this.family == otherExtLtr.family) {
                this.related = true;
            }
            // If characters are equal return true else return false
            if (this.content == otherExtLtr.content) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    /**
     * Creates a decoreated string of instance content
     * @return Decoretes instance content and returns a string
     */
    public String toString() {
        if (this.isUnused() && this.related == true) {
            // If unused wrap chaaracter in "."
            return ("." + this.content + ".");
        } else {
            // Else wrap in decorator symbol
            return (this.decorator() + this.content + this.decorator());
        }
    }


    /**
     * Creates an array of letter objects from provided content
     * @param content Array of letters
     * @param codes Optional array of integers used to set family attribute
     * @return Array of letter objects
     */
    public static Letter[] fromStrings(String[] content, int[] codes) {
        Letter[] letterArray = new Letter[content.length];

        // Loop through content array
        for (int i = 0; i < content.length; i++) {
            ExtendedLetter newLetter;
            // If codes is null create extended letter without codes param, else create with codes param
            if (codes == null) {
                newLetter = new ExtendedLetter(content[i]);
            } else {
                newLetter = new ExtendedLetter(content[i], codes[i]);
            }
            // Add to correspoding part of letterArray
            letterArray[i] = newLetter;
        }

        return letterArray;
    }

}
