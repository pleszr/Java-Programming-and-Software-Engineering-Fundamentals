
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 1; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 
    
    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 1; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 
    
    public void testGetFollows() {
        String s = "this is just a test yes this is a simple test yes just a it is a test yet test only yes";
        MarkovWorld mw1 = new MarkovWorld(2);
        mw1.setTraining(s);
        
        System.out.println("Test case #1");
        String[] t1 = {"just","a"};
        
        WordGram wg1 = new WordGram(t1,0,2);
        ArrayList<String> f1 = mw1.getFollows(wg1);
        System.out.println(f1);
        String[] o1 = {"test","it"};
        System.out.println(Arrays.equals(o1,f1.toArray()));
        System.out.println("------------------");
        
        System.out.println("Test case #2");
        String[] t2 = {"test","yes"};
        WordGram wg2 = new WordGram(t2, 0, 2);
        ArrayList<String> f2 = mw1.getFollows(wg2);
        System.out.println(f2);
        String[] o2 = {"this", "just"};
        System.out.println(Arrays.equals(o2, f2.toArray()));
        System.out.println("------------------");
    
        
        
        System.out.println("Test case #3");
        String[] t3 = {"just","a","yes"};
        WordGram wg3 = new WordGram(t3, 0, 3);
        ArrayList<String> f3 = mw1.getFollows(wg3);
        System.out.println(f3);
        String[] o3 = {};
        System.out.println(Arrays.equals(o3, f3.toArray()));
        System.out.println("------------------");
        
        System.out.println("Test case #4");
        String[] t4 = {"simple", "test"};
        WordGram wg4 = new WordGram(t4, 0, 2);
        ArrayList<String> f4 = mw1.getFollows(wg4);
        System.out.println(f4);
        String[] o4 = {"yes"};
        System.out.println(Arrays.equals(o4, f4.toArray()));
        System.out.println("------------------");
        
        System.out.println("Test case #6");
        String[] t6 = {"this"};
        WordGram wg6 = new WordGram(t6, 0, 1);
        ArrayList<String> f6 = mw1.getFollows(wg6);
        System.out.println(f6);
        String[] o6 = {"is", "is"};
        System.out.println(Arrays.equals(o6, f6.toArray()));
        System.out.println("------------------");
        
        System.out.println("Test case #7");
        String[] t7 = {"test"};
        WordGram wg7 = new WordGram(t7, 0, 1);
        ArrayList<String> f7 = mw1.getFollows(wg7);
        System.out.println(f7);
        String[] o7 = {"yes", "yes", "yet","only"};
        System.out.println(Arrays.equals(o7, f7.toArray()));
        System.out.println("------------------");
        
        
        
        
    }
    
    public void testIndexOf() {
        String s = "this is just a test yes this is a simple test";
        String[] myText = s.split("\\s+");
        //FileResource fr = new FileResource(); 
        //String st = fr.asString(); 
        //st = st.replace('\n', ' '); 
        MarkovWorld mw = new MarkovWorld(3);
        mw.setTraining(s);
        
        System.out.println("***Test case 1***");
        String[] t1 = {"just","a"};
        WordGram wg1 = new WordGram(t1,0,2);
        System.out.println(Arrays.toString(myText));
        System.out.println(Arrays.toString(t1));
        System.out.println("indexOf: " + mw.indexOf(myText,wg1,0));
        System.out.println("Should be: 2");
        System.out.println("------------------");
        
        
        System.out.println("***Test case 2***");
        String[] t2 = {"test","yes",};
        WordGram wg2 = new WordGram(t2,0,2);
        System.out.println(Arrays.toString(t2));
        System.out.println("indexOf: " + mw.indexOf(myText,wg2,0));
        System.out.println("Should be: 4");
        System.out.println("------------------");
        
        System.out.println("***Test case 3***");
        String[] t3 = {"is"};
        WordGram wg3 = new WordGram(t3,0,1);
        System.out.println(Arrays.toString(t3));
        System.out.println("indexOf: " + mw.indexOf(myText,wg3,0));
        System.out.println("Should be: 1");
        System.out.println("------------------");
        
        System.out.println("***Test case 4***");
        String[] t4 = {"just","a","yes"};
        WordGram wg4 = new WordGram(t4,0,3);
        System.out.println(Arrays.toString(t4));
        System.out.println("indexOf: " + mw.indexOf(myText,wg4,0));
        System.out.println("Should be: -1");
        System.out.println("------------------");
        
        System.out.println("***Test case 5***");
        String[] t5 = {"just"};
        WordGram wg5 = new WordGram(t4,0,1);
        System.out.println(Arrays.toString(t5));
        System.out.println("indexOf: " + mw.indexOf(myText,wg5,5));
        System.out.println("Should be: -1");
        System.out.println("------------------");
        
        System.out.println("***Test case 6***");
        String[] t6 = {"is", "a", "test"};
        WordGram wg6 = new WordGram(t6, 0, 3);
        System.out.println(Arrays.toString(t6));
        System.out.println("indexOf: " + mw.indexOf(myText, wg6, 0));
        System.out.println("Should be: -1");
        System.out.println("------------------");
        
        System.out.println("***Test case 8***");
        String[] t7 = {"yes", "this", "is"};
        WordGram wg7 = new WordGram(t7, 0, 3);
        System.out.println(Arrays.toString(t7));
        System.out.println("indexOf: " + mw.indexOf(myText, wg7, 0));
        System.out.println("Should be: 5");
        System.out.println("------------------");
        
        System.out.println("***Test case 8***");
        String[] t8 = {"simple", "test"};
        WordGram wg8 = new WordGram(t8, 0, 2);
        System.out.println(Arrays.toString(t8));
        System.out.println("indexOf: " + mw.indexOf(myText, wg8, 0));
        System.out.println("Should be: 8");
        System.out.println("------------------");
    } 

    

    public void runMarkovWorld() { 
        FileResource fr = new FileResource("data/confucius.txt"); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        
        MarkovWorld mw1 = new MarkovWorld(3); 
        runModel(mw1,st, 200,643); 
        //failure. The sense of his wasted powers may well have tempted
    } 
    
    public void FinalQuiz() {
        //file selection & prep work
        FileResource fr = new FileResource("data/confucius.txt"); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        int randomSeed = 0;
        int myOrder = 0;   
        
        //exercise 9
        myOrder = 6;
        randomSeed = 792;
        MarkovWorld mw9 = new MarkovWorld(myOrder); 
        runModel(mw9,st, 200,randomSeed); 
        mw9.printHashMapInfo();
        
        //exercise 10
        myOrder = 5;
        randomSeed = 531;
        MarkovWorld mw10 = new MarkovWorld(myOrder); 
        runModel(mw10,st, 200,randomSeed); 
        mw10.printHashMapInfo();
    }
    
    public void PractiveQuiz() { 
        //file selection & prep work
        FileResource fr = new FileResource("data/confucius.txt"); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        int randomSeed = 0;
        int myOrder = 0;
        
        //exercise 1
        myOrder = 3;
        randomSeed = 621;
        MarkovWorld mw1 = new MarkovWorld(myOrder); 
        runModel(mw1,st, 200,randomSeed); 
        //you must return the medium with your written explanation. The
        
        //exercise 2
        myOrder = 5;
        randomSeed = 844;
        MarkovWorld mw2 = new MarkovWorld(myOrder); 
        runModel(mw2,st, 200,randomSeed); 
        //The last part of the music, when all the instruments were played
        
        //exercise 3
        myOrder = 3;
        randomSeed = 371;
        MarkovWorld mw3 = new MarkovWorld(myOrder); 
        runModel(mw3,st, 50,randomSeed); 
        mw3.printHashMapInfo();
        
        //exercise 4
        myOrder = 2;
        randomSeed = 65;
        MarkovWorld mw4 = new MarkovWorld(myOrder); 
        runModel(mw4,st, 200,randomSeed); 
        mw4.printHashMapInfo();
        
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            //System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                //System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

}
