
/**
 * Write a description of uniqueWords here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class uniqueWords {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public uniqueWords() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public int findMaxInArray(ArrayList<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            // Handle the case when the list is null or empty
            throw new IllegalArgumentException("List is null or empty");
        }

        int maxNumber = numbers.get(0); // Assume the first element is the maximum
        int indexOfMax = 0;
        for (int i = 1; i < numbers.size(); i++) {
            int currentNumber = numbers.get(i);
            if (currentNumber > maxNumber) {
                maxNumber = currentNumber;
                indexOfMax = i;
            }
        }

        return indexOfMax;
    }
    
    
    public void countUniqueWords() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for ( String s : fr.words() ) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if ( index == -1 ) {
                myWords.add(s);
                myFreqs.add(1);
                
            }
            else {
                int value = myFreqs.get(index);
                myFreqs.set(index,value+1);    
            } 
        }
        
        System.out.println("The number of unique words in the file is: " + myWords.size() );
        int maxFreq = findMaxInArray(myFreqs);
        System.out.println("The most frequent number is: " + myWords.get(maxFreq) + " width " + myFreqs.get(maxFreq) ) ;
        //for ( int i = 0; i<myWords.size(); i++ ) {
        //    System.out.println(myWords.get(i) + ": " + myFreqs.get(i));    
        //}
        
        
    }

}
