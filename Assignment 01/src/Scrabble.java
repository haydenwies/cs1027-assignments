import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Scrabble object representing a hand of (7) scrabble tiles.
 * @author Hayden Wies
 */
public class Scrabble {
    
    private Tile[] tiles = new Tile[7];


    /**
     * ---------- (constructor without provided tiles) ----------
     * Creates new scrabble object with random tiles. 
     */
    public Scrabble() {
        for (int i = 0; i < 7; i++) {
            Tile tile = new Tile();
            tile.pickup();
            this.tiles[i] = tile;
        }
    }


    /**
     * ---------- (constructor with provided tiles) ----------
     * Creates new scrabble object with provided tiles.
     * @param tiles (7) tiles with pre-assigned character values.
     */
    public Scrabble(Tile[] tiles) {
        this.tiles = tiles;
    }


    /**
     * ---------- getLetters ----------
     * Returns string of tile values.
     * @return String of characters equal to the object's assigned tile values.
     */
    public String getLetters() {
        String tileString = "";
        for (int i = 0; i < tiles.length; i++) {
            tileString += tiles[i].getValue();
        }
        return tileString;
    }


    /**
     * ---------- sort ----------
     * Sorts the object's tiles alphabetically.
     */
    public void sort() {
        // Turn tiles into char[]
        char[] tileCharacters = new char[this.tiles.length];
        for (int i = 0; i < this.tiles.length; i++) {
            tileCharacters[i] = this.tiles[i].getValue();
        }
        // Sort char[]
        Arrays.sort(tileCharacters);
        for (int i = 0; i < tileCharacters.length; i++) {
            this.tiles[i] = new Tile(tileCharacters[i]);
        }
    }


    // ---------- getWords ----------
    public ArrayList<String> getWords() throws FileNotFoundException {

        // ArrayList to hold words that can be made with tiles
        ArrayList<String> validWords = new ArrayList<String>();
        // Convert this.tiles to [char]
        char[] tileValues = new char[this.tiles.length];
        for (int i = 0; i < this.tiles.length; i++) {
            tileValues[i] = this.tiles[i].getValue();
        }
        // Get file of Collins Scrabble Words
        BufferedReader words = new BufferedReader(new FileReader("CollinsScrabbleWords2019.txt"));        

        try {
            // Loop through each word in the list
            String line;
            while ((line=words.readLine()) != null) {

                // Convert wordList to char[] word
                ArrayList<Character> wordList = new ArrayList<Character>();
                for (char ch : line.toCharArray()) {
                    wordList.add(ch);
                }
                char[] word = new char[wordList.size()];
                int charCounter = 0;
                for (int i = 0; i < wordList.size(); i++) {
                    word[charCounter++] = wordList.get(i);
                }

                // Execute evaluation if word is 7 characters or less
                if (word.length <= 7) {

                    // Create tile value array use for this word
                    char[] tempTiles = tileValues;
                    // Create empty list to store tiles that match letters in a word
                    ArrayList<Character> removedTiles = new ArrayList<Character>(0);

                    // Itterate over every letter in the presented word
                    for (int i = 0; i < word.length; i++) {
                        // Itterate over every tile in the scrabble hand
                        innerloop:
                        for (int j = 0; j < tempTiles.length; j++) {

                            // Check if letter of word matches letter of tile
                            if (word[i] == tempTiles[j]) {

                                // If yes, remove tile from tempTiles and add it to removedTiles
                                char[] newTiles = new char[tempTiles.length - 1];
                                int indexLess = 0;
                                for (int k = 0; k < tempTiles.length; k++) {
                                    if (j != k) {
                                        newTiles[indexLess] = tempTiles[k];
                                        indexLess++;
                                    } else {
                                        removedTiles.add(tempTiles[k]);
                                    }
                                }
                                tempTiles = newTiles;
                                // Break out of loop itterating through scrabble letters 
                                // Progress to next letter of word
                                break innerloop;

                            }

                        }
                    }

                    // If length of removed tiles = length of word, word can be made with all tiles in the scrabble hand
                    // Add word to validWords array
                    if (word.length == removedTiles.size()) {
                        validWords.add(line);
                    }
                
                }
                    
            }
        } catch (IOException err) {
            System.out.println(err);
        }

        
        return validWords;
    }


    // ---------- getScores ----------
    public int[] getScores() {

        Map<Character, Integer> values = new HashMap<Character, Integer>();
        values.put('A', 1);
        values.put('B', 3);
        values.put('C', 3);
        values.put('D', 2);
        values.put('E', 1);
        values.put('F', 4);
        values.put('G', 2);
        values.put('H', 4);
        values.put('I', 1);
        values.put('J', 8);
        values.put('K', 5);
        values.put('L', 1);
        values.put('M', 3);
        values.put('N', 1);
        values.put('O', 1);
        values.put('P', 3);
        values.put('Q', 10);
        values.put('R', 1);
        values.put('S', 1);
        values.put('T', 1);
        values.put('U', 1);
        values.put('V', 4);
        values.put('W', 4);
        values.put('X', 8);
        values.put('Y', 4);
        values.put('Z', 10);

        try {

            ArrayList<String> validWords = getWords();
            int[] scores = new int[validWords.size()];

            for (int i = 0; i < validWords.size(); i++) {
                int scoreCounter = 0;
                for (char c : validWords.get(i).toCharArray()) {
                    scoreCounter += values.get(c);
                }
                scores[i] = scoreCounter;
            }
            Arrays.sort(scores);
        
            return scores;

        } catch (FileNotFoundException err) {
            return new int[0];
        }

    }


    // ---------- equals ----------
    public Boolean equals(Scrabble other) {
        this.sort();
        other.sort();

        if (this.getLetters().equals(other.getLetters())) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Scrabble x = new Scrabble();
        Scrabble y = x;
        System.out.println(x.equals(y));
    }

}
