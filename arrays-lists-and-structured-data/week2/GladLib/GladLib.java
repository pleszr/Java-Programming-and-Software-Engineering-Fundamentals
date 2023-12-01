import edu.duke.*;
import java.util.*;

public class GladLib {
    /*private ArrayList<String> adjectiveList;
    private ;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;*/
    private HashMap<String,ArrayList<String>> myMap = new HashMap<String,ArrayList<String>>();
    
    private ArrayList<String> usedWordsList = new ArrayList<String>();
    private ArrayList<String> usedLabelsList = new ArrayList<String>();

    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLib(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLib(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String labels[] = {"adjective","noun","color","country","name","animal","timeframe","verb","fruit"};
        
        for ( String s : labels ) {
            ArrayList<String> list = readIt(source + "/" + s + ".txt");
            myMap.put(s,list);
            System.out.println(source + "/" + s + ".txt");
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        
        if ( !myMap.containsKey(label) ) {
            return "**UNKNOWN**";
        }
        
        //used in totalWordsConsidered
        if ( !usedLabelsList.contains(label) ) {
            usedLabelsList.add(label);   
        }
        
        
        
        return randomFrom(myMap.get(label));
    }
    
    private int totalWordsInMap() {
        //Write a new method named totalWordsInMap with no parameters. This method returns the total number of words in all the 
        //ArrayLists in the HashMap. After printing the GladLib, call this method and print out the total number of words that 
        //were possible to pick from.  
        int totalNrOfWords = 0;
        for ( String word : myMap.keySet() ) {
            ArrayList<String> tempList = myMap.get(word);
            totalNrOfWords += tempList.size();            
        }
        return totalNrOfWords;
    }   
    
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = null;
        if ( sub == null ) {
            sub = getSubstitute(w.substring(first+1,last));   
        }
        
        int retryCounter = 0;
        while (usedWordsList.contains(prefix+sub+suffix)) {
            sub = getSubstitute(w.substring(first+1,last));
            if (retryCounter == 10000) {
                sub = "**NO UNIQUE**";
                break;
            }
            retryCounter++;
        }
        usedWordsList.add(prefix+sub+suffix);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {  
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsConsidered() {
        //Write a new method named totalWordsConsidered with no parameters. This method returns the total number of words in the 
        //ArrayLists of the categories that were used for a particular GladLib. If only noun, color, and adjective were the 
        //categories used in a GladLib, then only calculate the sum of all the words in those three ArrayLists. 
        //Hint: You will need to keep track of the categories used in solving the GladLib, then compute this total.    
    
        int totalNrOfWords = 0;
        /*System.out.println("usedLabelsList");
        for ( int i = 0; i < usedLabelsList.size(); i++ ) {
            System.out.println(usedLabelsList.get(i));
        }*/
        
        for ( String word : myMap.keySet() ) {
            if ( usedLabelsList.contains(word) ) {
                ArrayList<String> tempList = myMap.get(word);
                totalNrOfWords += tempList.size();    
            }
                        
        }
        return totalNrOfWords;

    }
    
    
    public void makeStory(){
        usedWordsList.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("totalWordsInMap: " + totalWordsInMap());
        System.out.println("totalWordsConsidered: " + totalWordsConsidered());
        /*for ( int i = 0; i<usedWordsList.size(); i++ ) {
            System.out.println(usedWordsList.get(i));    
        } */       
    }
    


}
