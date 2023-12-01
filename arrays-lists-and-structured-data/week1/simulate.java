
/**
 * Write a description of simulate here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
public class simulate {
    public void simulate (int rolls) {
        Random rand = new Random();
        int [] counts = new int [13];
        
        for ( int i = 1; i < rolls;i++ ) {
            int d1 = rand.nextInt(6)+1;
            int d2 = rand.nextInt(6)+1;  
            counts[d1+d2] += 1;
        }
        
        for ( int i = 2; i <= 12; i++ ) {
            System.out.println(i + ": " + counts[i] + ". In %: " + 100.0 * counts[i] / rolls);
        }
        
        
        
    }
    
    
}
