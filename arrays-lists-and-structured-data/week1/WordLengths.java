
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class WordLengths {
    
    public int[] getLNumbers (int size) {
        int[] intArray = new int[size];
        
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = i + 1;
        }
        return intArray;
    }
    
    public boolean isLetter(char inputCh) {
        String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] abc = alph.toCharArray();
        for ( char ch : abc ) {
            if (Character.toUpperCase(inputCh) == Character.toUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }
    
 
    
    public void WordLenghts() {
        FileResource fr = new FileResource("data/manywords.txt");
        int[] lNumbers = getLNumbers(15);
        int[] lCounts = new int[lNumbers.length];
        
        for (String s : fr.words() ) {
            StringBuilder sb = new StringBuilder(s);
            System.out.println(sb);
            if ( !isLetter(sb.charAt(0)) ) {
                sb.deleteCharAt(0);    
            }
            System.out.println(sb);
            if ( sb.length() != 0 && !isLetter(sb.charAt(sb.length()-1)) ) {
                sb.deleteCharAt(sb.length()-1);    
            }
            System.out.println(sb);
            
            if ( sb.length() > lCounts.length ) {
                lCounts[lCounts.length-1]++;
            }
            else {
                //System.out.println(sb.length());
                if ( sb.length() != 0 ) {
                    lCounts[sb.length()-1]++;    
                }   
            }
            
        }
        countShakespeare cs = new countShakespeare();
        int highestIndex = cs.findHighestInt(lCounts);
        System.out.println("This letter had the most: " + lNumbers[highestIndex] + " with this many: " + lCounts[highestIndex]);
        for (int i=0; i < lNumbers.length; i++ ) {
            System.out.println(lNumbers[i] + ": " + lCounts[i]);    
        }
    }

}


//Write a void method countWordLengths that has two parameters, a FileResource named resource and an integer array named counts.
//This method should read in the words from resource and count the number of words of each length for all the words in resource,
//storing these counts in the array counts.


//You may want to consider using the Character.isLetter method that returns true if a character is a letter, and false 
//otherwise. For example, Character.isLetter(‘a’) returns true, and Character.isLetter(‘-’) returns false. 