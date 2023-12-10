
/**
 * Write a description of class MakovTwo here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;
import java.util.*;

public class MarkovTwo {
    private String myText;
    private Random myRandom;
    private HashMap<String,ArrayList<String>> followsMap;
    
    public MarkovTwo(String trainingInput) {
        myRandom = new Random(42);
        setTraining(trainingInput);
        followsMap = new HashMap<String,ArrayList<String>>();
    }
    public MarkovTwo(String trainingInput,int seed) {
        myRandom = new Random(seed);
        setTraining(trainingInput);
        
        followsMap = new HashMap<String,ArrayList<String>>();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    
    
    public ArrayList<String> getFollows(String key) {
        /*
         *  In the class MakovTwo, write the method getFollows that has one String parameter named key. This method should 
         *  find all the characters from the private variable myText in MakovTwo that follow key and put all these characters
         *  into an ArrayList and then return this ArrayList. This algorithm for this method was described in “Finding Follow
         *  Sets.” For example, if myText were “this is a test yes this is a test.”, then the call getFollows(“t”) should
         *  return an ArrayList with the Strings “h”, “e”, “ “, “h”, “e”, “.” as “t” appears 6 times. The call getFollows(“e”)
         *  should return an ArrayList with the Strings “s”, “s”, “s”. Your method should work even if key is a word. 
         *  Thus, getFollows(“es”) should return an ArrayList with the Strings “t”, “ “, “t”. Next we will write a method to 
         *  test this method.
         *  TLDR: send a string (key), search it in myText and return the next char when found
         */
        
        ArrayList<String> follows = new ArrayList<String>();
        int searchFrom = 0;
        
        while ( true ) {
            int loc = myText.indexOf(key,searchFrom);
            //System.out.println("seachFrom: " + searchFrom);
            //System.out.println("loc: " + loc);
            if ( loc == -1 || loc + key.length() >= myText.length() ) {
                
                break;       
            }
            else {
                //char charFound = myText.charAt(loc + key.length()); 
                String stringFound = myText.substring(loc + key.length(),loc + key.length()+1);
                follows.add(stringFound);
                searchFrom = loc + 1;
                
            }
        }
        System.out.println("key: " + key + " , follows: " + follows);
        return follows;
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        
        //this is finding the first character
        int index = myRandom.nextInt(myText.length()-2);
        String key = myText.substring(index,index+2);
        System.out.println("Initial key: " + key);
        //String key = Character.toString(myText.charAt(index));
        sb.append(key);
        
        //this is finding the 2nd character
        for(int k=0; k < numChars-2; k++){
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
            String endOfGen = sb.substring(sb.length()-1);
            sb.append(nextString);
            key = endOfGen + nextString;
            
        }
        
        return sb.toString();
    }
}
