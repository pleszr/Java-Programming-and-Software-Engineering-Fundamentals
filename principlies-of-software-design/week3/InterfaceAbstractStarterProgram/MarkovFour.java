
/**
 * Write a description of class MakovTwo here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;
import java.util.*;

public class MarkovFour extends AbstractMarkovModel implements IMarkovModel {
    
    public MarkovFour(String trainingInput) {

    }
    public MarkovFour() {
        myRandom = new Random(42);
        followsMap = new HashMap<String,ArrayList<String>>();
    }
    public MarkovFour(String trainingInput,int seed) {
        myRandom = new Random(seed);
        setTraining(trainingInput);
        
        followsMap = new HashMap<String,ArrayList<String>>();
    }
    
    public String toString() {
        return "MarkovModel of order 4.";
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    
    
    
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        
        //this is finding the first character
        int index = myRandom.nextInt(myText.length()-4);
        String key = myText.substring(index,index+4);
        //System.out.println("Initial key: " + key);
        //String key = Character.toString(myText.charAt(index));
        sb.append(key);
        
        //this is finding the 2nd character
        for(int k=0; k < numChars-4; k++){
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
            String endOfGen = sb.substring(sb.length()-4+1);
            sb.append(nextString);
            key = endOfGen + nextString;
            
        }
        
        return sb.toString();
    }
}
