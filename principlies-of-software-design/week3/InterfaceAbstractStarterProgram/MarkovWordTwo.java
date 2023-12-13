
/**
 * Write a description of class MarkovWordTwo here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;
import java.util.*; 

public class MarkovWordTwo implements IMarkovModel  {
    private String[] myText;
    private HashMap<String,ArrayList<String>> followsMap;
    private Random myRandom;
    
    
    public MarkovWordTwo() {
        followsMap = new HashMap<String,ArrayList<String>>();
        myRandom = new Random();
        
    }

    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.split("\\s+");
    }
    
    public int indexOf(String[] input, String key1, String key2, int searchFrom) {
        for ( int i=searchFrom; i < input.length-1; i++ ) {
            if ( input[i].equals(key1) && input[i+1].equals(key2) ) {
                return i;   
            }
        }
        return -1;
    }
    
    private ArrayList<String> getFollows(String key1, String key2) {

        
        ArrayList<String> follows = new ArrayList<String>();
        int searchFrom = 0;
        
        while ( true ) {
            //find location of first key
            int loc =  indexOf(myText,key1,key2,searchFrom);
            
            if ( loc == -1 || loc + 1 + 1 >= myText.length ) {
                break;       
            }
            else { 
                String stringFound = myText[loc+1+1];
                follows.add(stringFound);
                searchFrom = loc + 1;
            }
        }
        //System.out.println("key1: " + key1 + ", key2: " + key2 + ", follows: " + follows);
        return follows;
    }
    
    public void testGetFollows() {
        //ArrayList<String> follows = new ArrayList<String>();
        String s = "this is just a test yes this is a simple test";
        setTraining(s);
        String key1 = "this";
        String key2 = "is";
        getFollows(key1,key2);
        key1 = "test";
        key2 = "yes";
        getFollows(key1,key2);
        key1 = "yes";
        key2 = "is";
        getFollows(key1,key2);
        key1 = "cica";
        key2 = "just";
        getFollows(key1,key2);
    }
    
    public void testGetFollowsWithFile() {
        //ArrayList<String> follows = new ArrayList<String>();
        
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        setTraining(st);
        String key1 = "courtesy;";
        String key2 = "they";
        getFollows(key1,key2);
        key1 = "daily";
        key2 = "I";
        getFollows(key1,key2);
        key1 = "a";
        key2 = "thousand";
        getFollows(key1,key2);
        key1 = "cica";
        key2 = "just";
        getFollows(key1,key2);
    }
    
    public void testRandom() {
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        setTraining(st);
        for ( int r=0; r<100000;r++ ) {
            Random currRandom= new Random(r);
            int index = currRandom.nextInt(myText.length-2);
            String key1 = myText[index];
            String key2 = myText[index+1];
            if ( key1.equals("the") && key2.equals("minister")) {
                System.out.println(r); 
            }
            
        }
        

    }
    
    
    
    
    
    
    
    public String getRandomText(int numWords){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        
        //first generate the first word
        int index = myRandom.nextInt(myText.length-2);
        String key1 = myText[index];
        String key2 = myText[index+1];
        sb.append(key1);   
        sb.append(" "); 
        sb.append(key2); 
        sb.append(" ");

        //loop for each word to be generated
        for(int k=0; k < numWords-2; k++){
            ArrayList<String> follows = new ArrayList<String>();
            if ( followsMap.containsKey(key1+"+"+key2)) {
                follows.addAll(followsMap.get(key1+"+"+key2));                
            }
            else {
                follows.addAll(getFollows(key1,key2));
                followsMap.put(key1+"+"+key2,follows);
            }
            if ( follows.size() == 0 ) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String nextString = follows.get(index);
            sb.append(nextString);
            sb.append(" ");
            key1 = key2;
            key2 = nextString;
        }        
        return sb.toString();
    }
    
    public String toString() {
        return "Markov Word Model of order 2.";
    }
}
