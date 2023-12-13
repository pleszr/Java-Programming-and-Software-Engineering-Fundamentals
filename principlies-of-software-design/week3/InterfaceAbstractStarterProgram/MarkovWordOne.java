
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;
import java.util.*; 

public class MarkovWordOne implements IMarkovModel  {
    private String[] myText;
    private HashMap<String,ArrayList<String>> followsMap;
    private Random myRandom;
    
    
    public MarkovWordOne() {
        followsMap = new HashMap<String,ArrayList<String>>();
        myRandom = new Random();
        
    }

    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.split("\\s+");
    }
    
    public int indexOf(String[] input, String searchTerm, int searchFrom) {
        for ( int i=searchFrom; i < input.length; i++ ) {
            if ( input[i].equals(searchTerm) ) {
                return i;   
            }
        }
        return -1;
    }
    
    private ArrayList<String> getFollows(String key) {
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
            int loc =  indexOf(myText,key,searchFrom);
            //int loc = myText.indexOf(key,searchFrom);
            //System.out.println("seachFrom: " + searchFrom);
            //System.out.println("loc: " + loc);
            if ( loc == -1 || loc + key.length() >= myText.length ) {
                
                break;       
            }
            else { 
                String stringFound = myText[loc+1];
                follows.add(stringFound);
                searchFrom = loc + 1;
                
            }
        }
        //System.out.println("key: " + key + ", follows: " + follows);
        return follows;
    }
    
    public void testIndexOf() {
        String s = "this is just a test yes this is a simple test";
        setTraining(s);
        System.out.println(s);
        int i = indexOf(myText,"this",0);
        System.out.println("this from 0, should be *0*, it is: " + i);
        i = indexOf(myText,"this",3);
        System.out.println("this from 3, should be *6*, it is: " + i);
        i = indexOf(myText,"frog",0);
        System.out.println("frog from 0, should be *-1*, it is: " + i);
        i = indexOf(myText,"frog",5);
        System.out.println("frog from 5, should be *-1*, it is: " + i);
        i = indexOf(myText,"simple",2);
        System.out.println("simple from 2, should be *9*, it is: " + i);
        i = indexOf(myText,"test",5);
        System.out.println("test from 5, should be *10*, it is: " + i);
        
        
    }
    
    
    
    public String getRandomText(int numWords){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        
        //this is finding the first character
        int index = myRandom.nextInt(myText.length-1);
        String key = myText[index];
        //String key = Character.toString(myText.charAt(index));
        sb.append(key);
        
        //this is finding the 2nd character
        for(int k=0; k < numWords-1; k++){
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
            sb.append(" ");
            key = nextString;
            
        }
        
        return sb.toString();
    }
    
    public String toString() {
        return "Markov Word Model of order 1.";
    }
}
