
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;


    

public class CharactersInPlay {
    private ArrayList<String> wordList;
    private ArrayList<Integer> wordFreqs;
    public CharactersInPlay() {
        wordList = new ArrayList<String>();
        wordFreqs = new ArrayList<Integer>();
    }
    
    public void testing() {
        countCharsInPlay();    
        charactersWithNumParts(10,15);
    }
    
    public void charactersWithNumParts(int min, int max) {
        for ( int i=0; i < wordList.size(); i++ ) {
            if ( wordFreqs.get(i) >= min && wordFreqs.get(i) <= max ) {
                System.out.println(wordList.get(i) + ": " + wordFreqs.get(i) );       
            }
        }    
    }
    
    
    public void countCharsInPlay() {
        //FileResource fr = new FileResource("data/macbethSmall.txt");
        FileResource fr = new FileResource("data/errors.txt");
        
        for (String line : fr.lines() ) {
            //System.out.println(line);
            String stringBeforeDot = stringBeforeDot(line);
            if ( stringBeforeDot != null ) {
                
                int charIndex = wordList.indexOf(stringBeforeDot);
                if ( charIndex == -1 ) {
                    wordList.add(stringBeforeDot); 
                    wordFreqs.add(1);
                }
                else {
                    int currentFreq = wordFreqs.get(charIndex);
                    wordFreqs.set(charIndex,currentFreq+1);
                }
            }
        }
        
        for ( int i=0; i < wordList.size(); i++ ) {
            //System.out.println(wordList.get(i) + ": " + wordFreqs.get(i) );
        }
        
    }
    
    public static String stringBeforeDot(String input) {
        if (input == null || input.isEmpty()) {
            return null; // Return empty string for null or empty input
        }
        int dotIndex = input.indexOf(".");
        if (dotIndex != -1) {
            // Return substring before the first dot
            return input.substring(0, dotIndex);
        } else {
            // Return empty string if there is no dot
            return null;
        }
    }
        

}
