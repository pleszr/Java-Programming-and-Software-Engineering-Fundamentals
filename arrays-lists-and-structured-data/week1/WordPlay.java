
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {



    public boolean isVowel(char ch) {
        //Write a method isVowel that has one Char parameter named ch. This method returns true if ch is a vowel 
        //(one of 'a', 'e', 'i', 'o', or 'u' or the uppercase versions) and false otherwise. You should write a tester method to 
        //see if this method works correctly. For example, isVowel(‘F’) should return false, and isVowel(‘a’) should return true.
        char currentCh = Character.toLowerCase(ch);
        boolean isVowel = false;
        if ( currentCh == 'a' || currentCh == 'e' || currentCh == 'i' || currentCh == 'o' || currentCh == 'u' ) {
            isVowel = true;
        } 
        return isVowel;
    }
    
    public void testIsVowel() {
        if ( !isVowel('F') && isVowel('A') ) {
            System.out.println("isVowel test passed");
        }
        else {
            System.out.println("isVowel test DID NOT pass");    
        }
    }
    
    public String replaceVowels(String phrase, char ch) {
        //Write a method replaceVowels that has two parameters, a String named phrase and a Char named ch. This method 
        //should return a String that is the string phrase with all the vowels (uppercase or lowercase) replaced by ch. 
        //For example, the call replaceVowels(“Hello World”, ‘*’) returns the string “H*ll* W*rld”. Be sure to call 
        //the method isVowel that you wrote and also test this method.
        StringBuilder input = new StringBuilder(phrase);
        for ( int i = 0; i < phrase.length(); i++ ) {      
            char currentChar = input.charAt(i);
            if  ( isVowel(currentChar) ) {
                input.replace(i,i+1,"*");        
            }

        }
        String output = input.toString();
        return output;
    }
    
    public void testReplaceVowels() {
        System.out.println("output for Hello world and *: " + replaceVowels("Hello World",'*') );   
    }






}
