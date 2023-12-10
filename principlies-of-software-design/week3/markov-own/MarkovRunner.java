
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;
import java.util.*;

public class MarkovRunner {
    
    public void runMarkovZero() {
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovZero markov = new MarkovZero();
        markov.setTraining(st);
        markov.setRandom(88);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }
    
    public void runMarkovOne() {
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne m1 = new MarkovOne(st,273);

        for(int k=0; k < 3; k++){
            String text = m1.getRandomText(500);
            printOut(text);
        }
    }
    
    public void runMarkovTwo() {
        FileResource fr = new FileResource("data/romeo.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovTwo m2 = new MarkovTwo(st,5);

        for(int k=0; k < 3; k++){
            String text = m2.getRandomText(500);
            printOut(text);
        }
    }
    
    public void runMarkovFour() {
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovFour m4 = new MarkovFour(st,371);

        for(int k=0; k < 3; k++){
            String text = m4.getRandomText(500);
            printOut(text);
        }
    }
    
    public void runMarkovModel() {
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int seed = 365;
        int markovNr = 8;
        MarkovModel markov = new MarkovModel(st,seed,markovNr);

        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
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
