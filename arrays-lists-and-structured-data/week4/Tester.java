
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Tester {
    
    public void testCaeserCipher() {
        FileResource fr = new FileResource("data/titus-small.txt");
        String s = fr.asString();
        CaesarCipher cc = new CaesarCipher(4);
        String encrypted = cc.encrypt(s);
        System.out.println("encrypted: " + encrypted);
        CaesarCipher cc2 = new CaesarCipher(4);
        String decrypted = cc2.decrypt(encrypted);
        System.out.println("decrypted: " + decrypted);  
    }
    
    public void testCaeserCracker() {
        //test case 1
        FileResource fr = new FileResource("data/titus-small_key5.txt");
        String s = fr.asString(); 
        CaesarCracker cca = new CaesarCracker();
        System.out.println("original string (titus-small_key5.txt):");
        System.out.println(s);
        System.out.println("Encrypted (key: " + cca.getKey(s));
        System.out.println("decrypted:");
        System.out.println(cca.decrypt(s));
        
        //testcase 2
        FileResource fr2 = new FileResource("data/oslusiadas_key17.txt");
        String s2 = fr2.asString(); 
        CaesarCracker cca2 = new CaesarCracker('a');
        System.out.println("original string (oslusiadas_key17.txt):");
        System.out.println(s2);
        System.out.println("Encrypted (key: " + cca2.getKey(s2));
        System.out.println("decrypted:");
        System.out.println(cca2.decrypt(s2));
    }
    
    public void testSlideString() {
            VigenereBreaker vb = new VigenereBreaker();
            if ( vb.sliceString("abcdefghijklm", 0, 3).equals("adgjm")) {
                System.out.println("abcdefghijklm - Passed");    
            }
            if ( vb.sliceString("abcdefghijklm", 1, 3).equals("behk")) {
                System.out.println("abcdefghijklm - Passed");    
            }
            if ( vb.sliceString("abcdefghijklm", 2, 3).equals("cfil")) {
                System.out.println("abcdefghijklm - Passed");    
            }
            if ( vb.sliceString("abcdefghijklm", 0, 4).equals("aeim")) {
                System.out.println("abcdefghijklm - Passed");    
            }
            if ( vb.sliceString("abcdefghijklm", 1, 4).equals("bfj")) {
                System.out.println("abcdefghijklm - Passed");    
            }
               
    }
    
    
    public void testTryKeyLength() {
        FileResource fr = new FileResource("data/secretmessage2.txt");
        String s = fr.asString();  
        VigenereBreaker vb = new VigenereBreaker();
        int[] tempIntArray = vb.tryKeyLength(s,5,'e');
        for ( int i = 0; i < tempIntArray.length; i++ ) {
            System.out.println(tempIntArray[i]);    
        }
        
        
    }
    
    public void testVigenereCipher() {
        int[] rome = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(rome);
        
        FileResource fr = new FileResource("data/titus-small.txt");
        String s = "Coal-black is better than another hue,";
        //String s = fr.asString();
        
        System.out.println("key: " + vc.toString());
        System.out.println("Original string: " + s);
        System.out.println("encrypted: " + vc.encrypt(s));
        System.out.println("decrypted: " + vc.decrypt(vc.encrypt(s)));
        
        
        
        
         
        
        
    }

}
