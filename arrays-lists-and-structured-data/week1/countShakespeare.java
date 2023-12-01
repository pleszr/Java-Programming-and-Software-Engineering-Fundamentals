
/**
 * Write a description of BreakTheCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class countShakespeare {
    //import common 
    //import testPlay
    // count common in testPlay
    //import shakespear
    public char[] getAbc() {
        String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] abc = alph.toUpperCase().toCharArray();
        return abc;
    }
    
    public int[] countAbcInString(String input) {
        char[] abc = getAbc();
        int[] abcCount = new int[abc.length];
        for ( int i = 0; i < abc.length; i++ ) {
            abcCount[i] = countCharInString(abc[i],input);
        }
        return abcCount;    
    }
    
    public void testAbc() {
        String input = "lots of words are here the the the a and and of";
        char[] abc = getAbc();
        int[] abcCount = countAbcInString(input);
        int highestIndex = findHighestInt(abcCount);
        System.out.println("This letter had the highest: " + abc[highestIndex] + " with this many: " + abcCount[highestIndex]);
        
        for ( int i = 0; i < abc.length; i++ ) {
            System.out.println(abc[i] + ": " + abcCount[i]);
        }
        
    }
    

    
    public int findHighestInt(int[] intarray) {
        int greatestSoFar = Integer.MIN_VALUE;
        int highestIndex = 0;
        for ( int i=0; i<intarray.length;i++ ) {
            if ( intarray[i] > greatestSoFar ) {
                greatestSoFar = intarray[i];
                highestIndex = i;
            }
        }
        return highestIndex;
    }
    
    
    public int countCharInString(char letter, String word) {
        int counter = 0;
        for ( int i = 0; i < word.length(); i++ ) {
            if ( word.toUpperCase().charAt(i) == letter ) {
                counter ++;   
            }
        }
        return counter;
    }
    
    public void countShakespeare() {
        //String [] plays = {"caesar.txt","errors.txt","hamlet.txt","likeit.txt","macbeth.txt","romeo.txt"};
        String [] plays = {"romeo.txt"};
        String[] common = getCommon();
        int[] counts = new int[20];
        FileResource testPlay = new FileResource("data/small.txt");
        
        for ( String filename : plays ) {
            FileResource fr = new FileResource("data/" + filename);
            countWords(fr,common,counts);
        }
        
        for ( int i = 0; i < common.length; i++ ) {
            System.out.println(common[i] + ": " + counts[i] );
        }
    }
    
    public void countWords(FileResource fr, String[] common, int[] counts) {
        for ( String s : fr.words() ) {
            int index = indexOf(common,s);
            if ( index != -1 ) {
                counts[index] += +1;
            }  
        }   
    }
    
    public int indexOf(String[] searchArray, String searchString) {
        for ( int i = 0; i < searchArray.length; i++ ) {
            if ( searchArray[i].equals(searchString.toLowerCase()) ) {
                return i;    
            }
        }
        return -1;
    }
    
    public String[] getCommon() {
        String[] common = new String[20];
        FileResource fr = new FileResource("data/common.txt");
        int index = 0;
        
        for (CSVRecord currentRow : fr.getCSVParser(false) ) {
            common[index] = currentRow.get(0).toLowerCase();
            index += +1;   
        }
        return common;
    }
    
    
    // 1) counting the twenty most common words from Shakespeare’s plays
}
