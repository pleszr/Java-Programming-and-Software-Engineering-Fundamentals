
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 
import java.util.Random;

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);    
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkovExercise() {
        //exercise 3
        System.out.println("Exercise 3:");
        FileResource fr = new FileResource("data/romeo.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int currRandom = 615;
        
        MarkovModel m3 = new MarkovModel(5);
        runModel(m3, st, size,currRandom);
        m3.printKeyNumber();
          
        
        
        
        //exercise 4
        System.out.println("Exercise 3:");
        m3.printSizeOfLargestArrayList();
        System.out.println("-------------------");
        
        
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        
        Random myRandom = new Random();
        //int currRandom = myRandom.nextInt(500);
        int currRandom = 38;
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size,currRandom);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size,currRandom);
        
        MarkovModel mThree = new MarkovModel(6);
        runModel(mThree, st, size,currRandom);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size,currRandom);

    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
}
