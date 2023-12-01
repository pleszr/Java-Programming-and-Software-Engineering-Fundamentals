import java.util.*;
import edu.duke.*;



    public class VigenereBreaker {
        private String[] availableLanguages;
        private HashMap<String,Integer> wordCounterMap;
        
    public VigenereBreaker() {
        availableLanguages = new String[]{"Danish","Dutch","English","French","German","Italian","Portuguese","Spanish"};
        //availableLanguages = new String[]{"English"};
        wordCounterMap = new HashMap<String,Integer>();
        breakVigenere();    
        
    }
        
    
    public void breakVigenere() {
        //first read the file and then convert it to a string
        FileResource fr = new FileResource("data/secretmessage4.txt");
        String encryptedMessage = fr.asString();
        String decryptedMessage = null;
        int maxWordCounter = 0;
        String finalDecryptedMsg = null;
        String finalLang = null;
        
        //itarate through languages
        for ( String lang : availableLanguages ) {
            //read the dictionary and use readDictionary to convert it to a HashSet<String>
            FileResource dictFr = new FileResource("dictionaries/" + lang);
            HashSet<String> dict = readDictionary(dictFr);

            //use breakForLanguage to decrypt the file
            decryptedMessage = breakForLanguage(encryptedMessage,dict);
            int currentWords = countWords(decryptedMessage,dict);
            
            if ( maxWordCounter < currentWords ) {
                maxWordCounter = currentWords;
                finalDecryptedMsg = decryptedMessage;
                finalLang = lang;
            }
            
    
        }
        
        //
        
        //print the outcome
        System.out.println("Language: " + finalLang + ", maxWordCounter: " + maxWordCounter + ", finalDecryptedMsg:");
        System.out.println(finalDecryptedMsg);
        
        
        
        
    }
    
    public char mostCommonCharIn(HashSet<String> dict) {

        
        HashMap<Character,Integer> tempMap = new HashMap<Character,Integer>();
        
        for ( String s : dict ) {
            String ls = s.toLowerCase();
            for ( int i=0; i<ls.length();i++ ) {
                char currentChar = ls.charAt(i);
                if (! tempMap.keySet().contains(currentChar)) {
                    tempMap.put(currentChar,1);
                }
                else {
                    tempMap.put(currentChar,tempMap.get(currentChar)+1);    
                }
            }
        }
        
        char mostCommonChar = Collections.max(tempMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        
        return mostCommonChar;
    }
    
    public String sliceString(String message, int whichSlice, int totalSlices) {   
        /*
         * Write the public method sliceString, which has three parameters—a String message, representing the encrypted message,
         * an integer whichSlice, indicating the index the slice should start from, and an integer totalSlices, indicating the 
         * length of the key. This method returns a String consisting of every totalSlices-th character from message, starting 
         * at the whichSlice-th character.
         * sliceString("abcdefghijklm", 0, 3) should return "adgjm"
         */
        StringBuilder sb = new StringBuilder();
        for ( int i = whichSlice; i < message.length(); i = i + totalSlices ) {
            sb.append(message.charAt(i));  
        }
        return sb.toString();
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        /*
         * In the VigenereBreaker class, write the public method readDictionary, which has one parameter—a FileResource fr. This 
         * method should first make a new HashSet of Strings, then read each line in fr (which should contain exactly one word 
         * per line), convert that line to lowercase, and put that line into the HashSet that you created. The method should then 
         * return the HashSet representing the words in a dictionary. All the dictionary files, including the English dictionary 
         * file, are included in the starter program you download. They are inside the folder called ‘dictionaries’.
         */
        HashSet<String> dict = new HashSet<>();
        
        for ( String line : fr.lines() ) {
            dict.add(line.toLowerCase());
        }
        
        System.out.println("MOST COMMON CHAR IS: " + mostCommonCharIn(dict));
        return dict;
    }
    
    public int countWords(String message,HashSet<String> dict) {
        /*
         * In the VigenereBreaker class, write the public method countWords, which has two parameters—a String message, and a 
         * HashSet of Strings dictionary. This method should split the message into words (use .split(“\\W+”), which returns a 
         * String array), iterate over those words, and see how many of them are “real words”—that is, how many appear in the 
         * dictionary. Recall that the words in dictionary are lowercase. This method should return the integer count of how 
         * many valid words it found.
         */
        
        String[] msgArray = message.split("\\W+");
        
        int wordCounter = 0;
        for ( int i = 0; i < msgArray.length; i++ ) {
            String tempString = msgArray[i].toLowerCase();
            if ( dict.contains(tempString) ) {
                wordCounter++;
            }
        }
        return wordCounter;
    }
    
    public String breakForLanguage(String encrypted,HashSet<String> dict) {
        return breakForLanguage(encrypted,dict,"English");   
    }
    
    public String breakForLanguage(String encrypted,HashSet<String> dict, String lang) {
        /*
         * In the VigenereBreaker class, write the public method breakForLanguage, which has two parameters—a String encrypted, 
         * and a HashSet of Strings dictionary. This method should try all key lengths from 1 to 100 (use your tryKeyLength method
         * to try one particular key length) to obtain the best decryption for each key length in that range. 
         * For each key length, your method should decrypt the message (using VigenereCipher’s decrypt method as before), and count how many of the 
         * “words” in it are real words in English, based on the dictionary passed in (use the countWords method you just wrote). 
         * This method should figure out which decryption gives the largest count of real words, and return that String decryption.
         * Note that there is nothing special about 100; we will just give you messages with key lengths in the range 1–100. 
         * If you did not have this information, you could iterate all the way to encrypted.length(). Your program would just take
         * a bit longer to run.
         */    
        int maxWordCounter = 0;
        String finalMessage = null;
        int correctKeyLength = 0;
        char mostCommonChar = mostCommonCharIn(dict);
        for ( int i = 1; i <=100; i++) {
            //trying with diffenent length of keys. tryKeyLength gives back an int array with the possible keys 
            int[] tempKey = tryKeyLength(encrypted,i,mostCommonChar);
            //initiate VigenereCipher object with the int[] receive from tempkey
            VigenereCipher vc = new VigenereCipher(tempKey);
            //use decrypt on VigenereCipher on the incoming message. VigenereCipher has tempKey from the tryKeyLength and message is a paramter
            String tempEncrypt = vc.decrypt(encrypted);
            //comparing the decrypted message with a dictionary to find how many are found
            int nrOfWords = countWords(tempEncrypt,dict);
            //looking for the message which has the most match with the dictiornary passed
            //correctKeyLenght is not used, just added for futureproof & debugging
            if ( nrOfWords > maxWordCounter ) {
                maxWordCounter = nrOfWords;
                correctKeyLength = i;
                finalMessage = tempEncrypt;
            }
        }
        System.out.println("correctKeyLength: " + correctKeyLength);
        System.out.println("maxWordCounter: " + maxWordCounter);
        wordCounterMap.put(lang,maxWordCounter);  

            return finalMessage;
    }
    
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        /*
         * Write the public method tryKeyLength, which takes three parameters—a String encrypted that represents the encrypted 
         * message, an integer klength that represents the key length, and a character mostCommon that indicates the most common 
         * character in the language of the message. 
         * This method should make use of the CaesarCracker class, as well as the 
         * sliceString method, to find the shift for each index in the key. You should fill in the key (which is an array of 
         * integers) and return it. Test this method on the text file athens_keyflute.txt, which is a scene from A Midsummer 
         * Night’s Dream encrypted with the key “flute”, and make sure you get the key {5, 11, 20, 19, 4}.
         */
        
        CaesarCracker cca = new CaesarCracker(mostCommon);
        int[] key = new int[klength];
        
        for ( int i=0; i<klength; i++) {
            String tempString = sliceString(encrypted,i,klength);
            //System.out.println("tempString: " + tempString);
            int tempInt = cca.getKey(tempString);
            key[i] = tempInt;
        }
        return key;
    }
    
    

   
    
}
