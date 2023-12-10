
/**
 * Write a description of class MakovTwo here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;
import java.util.*;

public class MarkovModel extends AbstractMarkovModel implements IMarkovModel {
    private int markovNr;
    
    public MarkovModel(String trainingInput, int markovNumber) {
        this(trainingInput,5,markovNumber);

    }
    public MarkovModel(int markovNumber) {
        markovNr = markovNumber;

    }
    
    public void printSizeOfLargestArrayList() {
        int maxSize = 0;

        for (ArrayList<String> list : followsMap.values()) {
            int currentSize = list.size();
            if (currentSize > maxSize) {
                maxSize = currentSize;
            }
        }

        System.out.println("Size of the largest ArrayList<String>: " + maxSize);
    }
    
    public void printKeyNumber() {
        System.out.println("Map has " + followsMap.size() + " keys.");
    }
    public MarkovModel(String trainingInput,int seed, int markovNumber) {
        myRandom = new Random(seed);
        setTraining(trainingInput);
        markovNr = markovNumber;
        followsMap = new HashMap<String,ArrayList<String>>();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String toString() {
        return "MarkovModel of order " + markovNr;
    }
    
    
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        
        //this is finding the first character
        int index = myRandom.nextInt(myText.length()-markovNr);
        String key = myText.substring(index,index+markovNr);
        //System.out.println("Initial key: " + key);
        //String key = Character.toString(myText.charAt(index));
        sb.append(key);
        
        //this is finding the 2nd character
        for(int k=0; k < numChars-markovNr; k++){
            ArrayList<String> follows = new ArrayList<String>();
            if ( followsMap.containsKey(key)) {
                follows.clear();
                follows.addAll(followsMap.get(key));                
            }
            else {
                follows.clear();
                follows.addAll(getFollows(key));
                followsMap.put(key,follows);
            }
            if ( follows.size() == 0 ) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String nextString = follows.get(index);
            String endOfGen = sb.substring(sb.length()-markovNr+1);
            sb.append(nextString);
            key = endOfGen + nextString;
            
        }
        
        return sb.toString();
    }
}
