
/**
 * Write a description of class MarkovOne here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;
import java.util.*; 

public class MarkovOne extends AbstractMarkovModel implements IMarkovModel  {
    
    
    
    public MarkovOne() {
        
    }
    public MarkovOne(String trainingInput) {
        myRandom = new Random();
        followsMap = new HashMap<String,ArrayList<String>>();
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
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index,index+1);
        //String key = Character.toString(myText.charAt(index));
        sb.append(key);
        
        //this is finding the 2nd character
        for(int k=0; k < numChars-1; k++){
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
            sb.append(nextString);
            key = nextString;
            
        }
        
        return sb.toString();
    }
    
    public String toString() {
        return "MarkovModel of order 1.";
    }
}
