
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected HashMap<String,ArrayList<String>> followsMap;
    
    
    
    protected ArrayList<String> getFollows(String key) {
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
        //System.out.println("key: " + key + ", follows: " + follows);
        return follows;
    }
    
    public AbstractMarkovModel() {
        myRandom = new Random();
        followsMap = new HashMap<String,ArrayList<String>>();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
 
    abstract public String getRandomText(int numChars);

}
