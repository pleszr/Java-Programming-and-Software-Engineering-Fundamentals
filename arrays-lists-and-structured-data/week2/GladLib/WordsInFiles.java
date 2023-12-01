
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;
public class WordsInFiles {
    private HashMap<String,ArrayList<String>> fileNames;
    private HashMap<String,Integer> wordList;
    
    private void printHashMaps() {
        System.out.println("**Printing out all the maps**:");
        System.out.println("wordList:");
        for ( String word : wordList.keySet() ) {
            System.out.println(word + ": " + wordList.get(word));
        }
        
        System.out.println("fileList:");
        for ( String file : fileNames.keySet() ) {
            ArrayList<String> tempList = fileNames.get(file);
            System.out.println(file + ": ");
            for ( int i = 0; i < tempList.size(); i++ ) {
                System.out.println(tempList.get(i));    
            }
        }
    }
    
    private ArrayList<String> wordsInNumFiles(int nr) {
        //Write the method wordsInNumFiles that has one integer parameter called number. This method returns an ArrayList of words 
        //that appear in exactly number files. In the example above, the call wordsInNumFiles(3) would return an ArrayList with 
        //the words  “cats” and “and”, and the call wordsInNumFiles(2) would return an ArrayList with the words “love”, “are”, 
        //and “dogs”, all the words that appear in exactly two files.   
        ArrayList<String> tempList = new ArrayList<String>();
        
        for ( String word : fileNames.keySet() ) {
            if ( fileNames.get(word).size() == nr ) {
                tempList.add(word); 
            }
        }
        
        return tempList;
    }
    
    private void printFilesIn(String word){
        //Write the void method printFilesIn that has one String parameter named word. This method prints the names of the files 
        //this word appears in, one filename per line. For example, in the example above, the call printFilesIn(“cats”) would 
        //print the three filenames: brief1.txt, brief3.txt, and brief4.txt, each on a separate line.
        
        
        ArrayList<String> tempList = fileNames.get(word);
        System.out.println(word + ":");
        for ( int i = 0; i < tempList.size(); i++ ) {
                System.out.println(tempList.get(i));    
            }
    }
    


    
    
    public WordsInFiles() {
        fileNames = new HashMap<String, ArrayList<String>>(); 
        wordList = new HashMap<String, Integer>();
    }
    
    private int maxNumber() {
        int maxNumber = 0;
        for ( String word : fileNames.keySet() ) {
            if ( fileNames.get(word).size() > maxNumber ) {
                maxNumber = fileNames.get(word).size(); 
            }
        }
        
        return maxNumber;
    }
    
    
    
    public void wordsInFilesTester() {
         //Write the void method tester that has no parameters. This method should call buildWordFileMap to select a group of 
         //files and build a HashMap of words, with each word mapped to an ArrayList of the filenames this word appears in, 
         //determine the maximum number of files any word is in, considering all words, and determine all the words that are 
         //in the maximum number of files and for each such word, print the filenames of the files it is in. (optional) If 
         //the map is not too big, then you might want to print out the complete map, all the keys, and for each key its 
         //ArrayList. This might be helpful to make sure the map was built correctly.
         buildWordFileMap();
         int maxNumber = maxNumber();
         System.out.println("maxNumber: " + maxNumber);
         int counter = 0;
         for ( String word : fileNames.keySet() ) {
             if  ( fileNames.get(word).size() == 4 ) {
                 //printFilesIn(word);  
                 counter++;
             }
         }
         System.out.println("counter: " + counter);
         //printHashMaps();
         printFilesIn("tree");
         
         
        
    }
    
    
    public void buildWordFileMap() {
        fileNames.clear();
        wordList.clear();
        DirectoryResource dr = new DirectoryResource();
        for ( File f : dr.selectedFiles()) {
            addWordsFromFile(f);        
        }
        //printHashMaps();
    }
    
    private void addWordsFromFile(File f) {
        //Write a private void method named addWordsFromFile that has one parameter f of type File. This method should add all 
        //the words from f into the map. 
        //If a word is not in the map, then you must create a new ArrayList of type String with this word, and have the word
        //map to this ArrayList. If a word is already in the map, then add the current filename to its ArrayList, unless 
        //the filename is already in the ArrayList. You can use the File method getName to get the filename of a file. 
        FileResource fr = new FileResource(f);
        for ( String word : fr.words() ) {
            if ( wordList.containsKey(word) ) {
                wordList.put(word,wordList.get(word)+1);
            }
            else {
                wordList.put(word,1);    
            }
            
            //if word already exists in filename map, then add the name of the file to the arraylist inside
            if ( fileNames.containsKey(word) ) {
                ArrayList<String> tempList = fileNames.get(word);
                if ( !tempList.contains(f.getName()) ) {
                    tempList.add(f.getName());       
                }
                
            }
            else {
                ArrayList<String> tempList = new ArrayList<String>();    
                tempList.add(f.getName());
                fileNames.put(word,tempList);
            }
            
        }
        
        
        
    }
    

}
