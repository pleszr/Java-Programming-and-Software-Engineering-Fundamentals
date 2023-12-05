
/**
 * Write a description of testClass here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Random;
public class testClass {
    public void test() {
        ArrayList<Integer> in = new ArrayList<Integer>();
        ArrayList<Integer> out = new ArrayList<Integer>();
        
        //in.addAll(Arrays.asList(9,-3,0));
        Random random = new Random();
        
        for (int i = 0; i < 500000; i++) {
            int randomNumber = random.nextInt(); // Generates a random integer
            in.add(randomNumber);
        }
        
        // Print the first few elements for verification
        /*for (int i = 0; i < Math.min(10, in.size()); i++) {
            System.out.println(in.get(i));
        }*/
        long endTimeA = System.currentTimeMillis();
        /*System.out.println("in:");
        for (int i=0; i < in.size(); i++ ) {
            System.out.println(in.get(i) + ", ");
        }*/
        
        while ( !in.isEmpty() ) {
            int index = findSmallestIndex(in);
            
            out.add(in.get(index));
            in.remove(index);
        }
        long endTimeB = System.currentTimeMillis();
        System.out.println("Sorted " + out.size() + " elements. It took " + (endTimeB-endTimeA)/1000 + " seconds.");
        System.out.println("Method B started at: " + endTimeA);
        System.out.println("Method B finished at: " + endTimeB);
        
        /*System.out.println("out:");
        for (int i=0; i < out.size(); i++ ) {
            System.out.println(out.get(i) + ", ");
        }*/

    }
    
    public int findSmallestIndex(ArrayList<Integer> in) {
        int smallestSoFar = Integer.MAX_VALUE;
        int smallestIndex = 0;
        
        for (int i=0; i < in.size(); i++ ) {
            if ( smallestSoFar > in.get(i) ) {
                smallestSoFar = in.get(i);
                smallestIndex = i;
            }
        }
        
        return smallestIndex;
    }

}
