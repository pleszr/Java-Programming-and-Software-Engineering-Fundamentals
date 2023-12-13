
/**
 * Write a description of MarkovWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWorld implements IMarkovModel {
    private String[] myText;
    Random myRandom;
    int myOrder;
    HashMap<WordGram,ArrayList<String>> followsMap;
    
    
    public MarkovWorld (int order) {
        myOrder = order;
        myRandom = new Random();
        followsMap = new HashMap<WordGram,ArrayList<String>>();
        
    }

    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.split("\\s+");
    }
    
    public int indexOf(String[] words, WordGram target, int start) {
        /*
         * The indexOf method has three parameters, a String array of all the words in the training text named words, a 
         * WordGram named target, and an integer named start indicating where to start looking for a WordGram match in words. 
         * This method should return the first position from start that has words in the array words that match the WordGram 
         * target. If there is no such match then return -1.
         */
        
        int indexFound = -1;
        //System.out.println("target.length(): " + target.length());

        for ( int i=start; i < words.length-1; i++ ) {
            for ( int k = 0; k<target.length(); k++ ) {
                if ( !words[i+k].equals(target.wordAt(k)) ) {
                    //System.out.println("no match: i: " + i + ", k: " + k);
                    //System.out.println(words[k] + " + " + target.wordAt(k));
                    break;
                }
                else if ( k == target.length()-1 ) {
                    //System.out.println("match: i: " + i + ", k: " + k);

                    indexFound = i;    
                }
            }
            if ( indexFound != -1 ) {
                break;
            }
        }
        return indexFound;
    }
    
    
    

    
    public ArrayList<String> getFollows(WordGram kGram) {
        /*
         * The getFollows method has one WordGram parameter named kGram. This method returns an ArrayList of all the single
         * words that immediately follow an instance of the WordGram parameter somewhere in the training text. This method 
         * should call indexOf to find these matches.
         */
        
        ArrayList<String> follows = new ArrayList<String>();
        int searchFrom = 0;
        while ( true ) {
            int loc = indexOf(myText,kGram,searchFrom);
            if ( loc == -1 || loc + kGram.length() >= myText.length ) {
                break;       
            }
            else { 
                String stringFound = myText[loc+kGram.length()];
                follows.add(stringFound);
                searchFrom = loc + 1;
            }
        }        
        return follows;
    }
    
    public void printHashMapInfo() {
        int maxSize = 0;
        for (ArrayList<String> followsList : followsMap.values()) {
            int currentSize = followsList.size();
            maxSize = Math.max(maxSize, currentSize);
        }
        
        System.out.println("Size of HashMap is: " + followsMap.size());    
        System.out.println("Max size is: " + maxSize);
    }
    
    public String getRandomText(int numWords){
        ///int myOrder dictates the number of words required
        //followsMap is the hashmap used
        
        if (myText == null){
            return "";
        }
        
        
        StringBuilder sb = new StringBuilder();
        
        //adds myOrder amount of words to the sb and creates WordGram
        
        int index = myRandom.nextInt(myText.length-myOrder);
        for ( int i=0; i<myOrder; i++ ) {
            String iKey = myText[index+i];
            sb.append(iKey);
            sb.append(" ");    
        }
        WordGram key = new WordGram(myText,index,myOrder);
        System.out.println(key);
        //generating the rest
        for(int k=0; k < numWords-1; k++){
            //System.out.println("k: " + k + ", numWords: " + numWords);
            //System.out.println("Current key is: '" + key + "' and it's hashcode is: " + key.hashCode());
            ArrayList<String> follows = new ArrayList<String>();
            if ( followsMap.containsKey(key)) {
                follows.addAll(followsMap.get(key));                
            }
            else {
                follows = getFollows(key);
                followsMap.put(key,follows);
            }
            if ( follows.size() == 0 ) {
                //System.out.println("break happened! k: " + k + ", sb length: " + sb.length());
                break;
            }
            index = myRandom.nextInt(follows.size());
            String nextString = follows.get(index);
            sb.append(nextString);
            sb.append(" ");
            key = key.shiftAdd(nextString);
            //System.out.println(follows);
            //System.out.println(key);
            

        }
        
        return sb.toString();
    }
    
    public String toString() {
        return "MarkovWorld with order of: " + myOrder + ".";
    }
    
    
    
    
    
    


}
