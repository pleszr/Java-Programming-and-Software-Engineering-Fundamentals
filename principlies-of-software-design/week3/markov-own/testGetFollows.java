
/**
 * Write a description of testGetFollows here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class testGetFollows {
      
    
    public void testGetFollowsWithFile() {
        /*
         * Now let’s test getFollows on a file. In the Tester class, write the void method testGetFollowsWithFile with no 
         * parameters. This method should create a MarkovOne object, set the training text to a file the user selects (similar
         * to the methods in MarkovRunner), and then call getFollows. Run your program on confucius.txt and look for the 
         * characters that follow “t”. You should get 11548. 
         */
        
        FileResource fr = new FileResource("data/melville.txt");
        MarkovOne m1 = new MarkovOne(fr.asString());
        ArrayList<String> follows = m1.getFollows("th");
        System.out.println("Size of follows for latter t is: " + follows.size());
        
        
        
    }
    
    public void testGetFollowsMarkovOne() {
        String myText = "this is a test yes this is a testee.";  
        System.out.println("with t: expected result is: ”h”, “e”, “ “, “h”, “e”, “e”");
        MarkovOne m1 = new MarkovOne(myText);
        ArrayList<String> follows = m1.getFollows("t");
        for ( int i=0; i<follows.size(); i++ ) {
            System.out.println(follows.get(i));
        }
        
        System.out.println("---------------");
        follows.clear();
        follows = m1.getFollows("e");
        System.out.println("with e: expected result is: ”s”, “s”, “s“, “e”,”.”");
        for ( int i=0; i<follows.size(); i++ ) {
            System.out.println(follows.get(i));
        }
        
        System.out.println("---------------");
        follows.clear();
        follows = m1.getFollows("es");
        System.out.println("with es: expected result is: “t”, “ “, “t” ");
        for ( int i=0; i<follows.size(); i++ ) {
            System.out.println(follows.get(i));
        }
        
        System.out.println("---------------");
        follows.clear();
        follows = m1.getFollows(" ");
        System.out.println("with ' ': expected result is: i a t ...");
        for ( int i=0; i<follows.size(); i++ ) {
            System.out.println(follows.get(i));
        }
        
        System.out.println("---------------");
        follows.clear();
        follows = m1.getFollows(".");
        System.out.println("expected result is: ””");
        for ( int i=0; i<follows.size(); i++ ) {
            System.out.println(follows.get(i));
        }
        
        System.out.println("---------------");
        follows.clear();
        follows = m1.getFollows("t.");
        System.out.println("expected result is: ””");
        for ( int i=0; i<follows.size(); i++ ) {
            System.out.println(follows.get(i));
        }
    }

}
