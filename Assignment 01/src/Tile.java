import java.util.Random;

/**
 * Tile object representing a scrabble tile with a character value.
 * @author Hayden Wies
 */
public class Tile {
    
    private char value;


    /**
     * ---------- (constructor without provided value) ----------
     * Creates new tile object with a blank character.
     */
    public Tile() {
        this.value = ' ';
    }


    /**
     * ---------- (constructor with provided value) ----------
     * Creates new tile object with the provided character.
     * @param value Character to be assigned to the tile
     */
    public Tile(char value) {
        this.value = value;
    }


    /**
     * ---------- pickup ----------
     * Assignes new random character to the tile.
     */
    public void pickup() {
        Random random = new Random();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.value = alphabet.charAt(random.nextInt(alphabet.length()));
    }


    /**
     * ---------- getValues ----------
     * Returns character assigned to tile.
     * @return Character value of the tile.
     */
    public char getValue() {
        return this.value;
    }

}

