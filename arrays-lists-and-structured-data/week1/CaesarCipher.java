
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarCipher {
    public String abc() {
        return "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    }
    
    public String abcCreator(int encrKey) {
        String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return ABC.substring(encrKey) + ABC.substring(0,encrKey);
    }
    
    public void testAbcCreator() {
        System.out.println("Test with encKey 1: " + abcCreator(1) );
        System.out.println("Test with encKey 5: " + abcCreator(5) );
        System.out.println("Test with encKey 23: " + abcCreator(23) );
    }
    
    public String encrypt(String input, int encrKey) {
        String abc = abcCreator(0);
        String shiftedAbc = abcCreator(encrKey);
        StringBuilder inputSb = new StringBuilder(input);

        for ( int i=0; i<inputSb.length();i++ ) {
            char originalChar = inputSb.charAt(i);
            int abcLoc = abc.indexOf(Character.toUpperCase(originalChar));
            if (abcLoc != -1 ) {
                char newChar = shiftedAbc.charAt(abcLoc);
                if ( Character.isLowerCase(originalChar) ) {
                    newChar = Character.toLowerCase(newChar);
                }
                inputSb.setCharAt(i,newChar);
            }
        }
        return inputSb.toString();    
    }
    
    public void testEncrypt() {
        System.out.println("key 1, input: CiCA. Expected is DJDB. Encrypted: " + encrypt("CiCA",1) );
        System.out.println("key 23, input: FIRST LEGION ATTACK EAST FLANK!. Expected is: CFOPQ IBDFLK XQQXZH BXPQ CIXKH!. Encrypted: " + encrypt("FIRST LEGION ATTACK EAST FLANK!", 23) ) ;
        System.out.println("Encrypt two keys...: " + encryptTwoKeys("Just a test string with lots of eeeeeeeeeeeeeeeees", 23, 2) );
        System.out.println("5: " + encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",15));
        System.out.println("6: " + encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
        System.out.println("Exam 1: " + encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?",15));
        System.out.println("Exam 2 twokeys: " + encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?",21,8));
        
        
    }
    
    public String decrypt(String input, int encrKey) {
        String abc = abcCreator(0);
        String shiftedAbc = abcCreator(encrKey);
        StringBuilder inputSb = new StringBuilder(input);

        for ( int i=0; i<inputSb.length();i++ ) {
            char originalChar = inputSb.charAt(i);
            int shiftedAbcLoc = shiftedAbc.indexOf(Character.toUpperCase(originalChar));
            if (shiftedAbcLoc != -1 ) {
                char newChar = abc.charAt(shiftedAbcLoc);
                if ( Character.isLowerCase(originalChar) ) {
                    newChar = Character.toLowerCase(newChar);
                }
                inputSb.setCharAt(i,newChar);
            }
        }
        return inputSb.toString();    
    }
    
    public void testdecrypt() {
        System.out.println("key 1, input: DjDB. Expected is CiCA . Decrypted: " + decrypt("DjDB",1) );
        System.out.println("key 23, input: CFOpQ IBDFLK XQQXzH BXPQ CIXKH!. Expected is: FIRST LEGION ATTACK EAST FLANK!  Decrypted: " + decrypt("CFOpQ IBDFLK XQQXzH BXPQ CIXKH!", 23) ) ;
    
        }
    
    public void testCaesar() {
    FileResource fr = new FileResource();
    String message = fr.asString();
    int key = 23;
    String encrypted = encrypt(message, key);
    System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        String abc = abcCreator(0);
        String shiftedAbc1 = abcCreator(key1);
        String shiftedAbc2 = abcCreator(key2);
        StringBuilder inputSb = new StringBuilder(input);

        for ( int i=0; i<inputSb.length();i++ ) {
            char originalChar = inputSb.charAt(i);
            int abcLoc = abc.indexOf(Character.toUpperCase(originalChar));
            if (abcLoc != -1 ) {
                char newChar = 'X';
                if ( i % 2 == 0 ) {
                    newChar = shiftedAbc1.charAt(abcLoc);    
                }
                else {
                    newChar = shiftedAbc2.charAt(abcLoc);     
                }
                
                if ( Character.isLowerCase(originalChar) ) {
                    newChar = Character.toLowerCase(newChar);
                }
                inputSb.setCharAt(i,newChar);
            }
        }
        return inputSb.toString();
    }
    
    public void testBreakCaesar(String realInput) {
        String input = "";
        if (realInput.equals("") ) { 
            input = "Men are almost never this brave. But this time, when rebelled, they won.";
        }
        System.out.println(input);
        System.out.println(encrypt(input,11));
        System.out.println(decrypt(input,11));
        System.out.println(breakCaesarCipher(input));
        
    }
    
    public int breakCaesarCipher(String input) {
        countShakespeare cs = new countShakespeare();
        int highestECount = 0;
        int highestEIndex = 0;
        for ( int i = 0; i <= 26; i++ ) {
            int[] abcCount = cs.countAbcInString(decrypt(input,i));
            if ( abcCount[4] > highestECount ) {
                highestECount = abcCount[4];
                highestEIndex = i;
            }
        }
        
        System.out.println("Most likely the resolution is: " + decrypt(input,highestEIndex) );
        
        char[] abc = cs.getAbc();
        int[] abcCount = cs.countAbcInString(input);
        int highestIndex = cs.findHighestInt(abcCount);
        return highestEIndex;
    }
    
    public void Test2keyBREAK() {
        //FileResource fr = new FileResouce();
        System.out.println(breakCaesarCipher2key("Uybi Gfqgykii Jgziegv Uigeixdiex Smiizzin Sei sw klv deec lrpcqrvbw sw fyi jytgvwj yej sivr jiyzxwyc tscprffvrxzsew edsek hzjwiiiex kisltj nmklzr xyi hvtrvkqvrk, azxy iijirvtl kisltj zr sklvv hvtrvkqvrkw ek Uybi, nmkl sklvv mewkmkykij, eeh azxy zruyjxic. Rw av dsmi mexf klv zrwsiqrxzse rkv, xyi jfglw sw jgziegv zw wymwxzrx wvfq xyi hzwtsmiic sw ein zrwsiqrxzse ks xyi gfqgykekmfrrpcc mexvrjmmi xrwb fj tistijwzrx rru rrrppdzrx zrwsiqrxzse. Ni lrzv fykwkeehzrx gvfkiedw me xifqvximt tsdtlxzrx; mexvveik jcjxvqj, rvxnsiozrx, eeh wvglvzxp; fzscsxmtec tsdtlxzrx; qvqfvp jcjxvqj rru dejwzzv ueke qrrrkvqvrk; eeh pveirzrx rru dsuicmek. Klv iijirvtl mexvvvwkw sw fyi wetycxp fzvvceg nmkl xyiji eiirw eeh azxy iijirvtlvv eiirw me fxyii umjgztcmeij jytl ej smfpfkp, iekzrviimek, eeeskitlescsxc, rru vrmmiseqvrkec jgziegvw. GJ Uigx Tysks Av rpjs hf nsio me r eydfvv sw fxyii zqgsixrrk rvvej zrtplhzrx tsdtlxvv kieglzgj rru mmjyrpzdrxzse, wvrjsi eikafvbw, eydiimtec rrrppwzw, jswxneii iekzrviimek, tsdtciomkc xyifvp, eeh vfffxzgj. Klv uigeixdiex mj rvxyrfcc yemhyv zr xyi wpqsmfwzw xyek vbzwkw fvxnivr xyi iuytekmfr kislt eeh xyi vvwveigy wetycxp. Xyi wprvvxc fvxnivr xyid yej sivr e ovc xf klv jytgvwj zr gfrkmeyrpcc vvjfvdmek xyi glvimtycyd rru zrkixvrxzrx iijirvtl eeh iuytekmfr. Klv uigeixdiex mj lwzrx r uyrp egtisrgy ks gfqsmei vvwveigy rru vhlgrxzse. Fimekzrx iijirvtl mexf klv tyivzglplq mj klv sijx arc xf kvrme jxlhvrkw esslx xyi qfwk rhmeegvh xvgyrfpfkp rru ks hzwjidmeeki xyi prxvwk uimicsgqvrkw sw tsdtlxzrx kitlescsxc me jstmvxp. TW Hvtk Glfxf Ni iegfyiexi yehvvxvrhleki wkyuiexj ks kvx mezfpmiu nmkl sekfmek qrnfv vvwveigy gvfnvgkw xyvfyxl xyi G-WLVW gvfkied, yehvvxvrhleki xyijij, Vvwveigy Vbgiimvrti jfv Yehvvxvrhlekij (VVY) jygtfvk, mehvtvruiex wkyumvw, vxt. Wfqv fj slv iogvtkmfrrp jzvjx qrnfvj xvrhleki azxy umjxzrtxzse, aymtl mezfpmij r jmxrzjzgrrk iijirvtl gfqgseiex, rru zr qrrp tejij klv iijirvtl lrw vvwlpkiu zr tlfcmtekmfrj zr pveumek gfrwiiiegvw. Xyi idmeiegv fj slv vvwveigy rru kirgymek jrglpkc mj klv smxkvwk jxiiekkl sw klv uigeixdiex. Deec jrglpkc qvqsiiw lrzv sivr vvgfkemqiu sskl ek lrzzvvjmkc eeh rrxzseec cimicw jfv xyizv iogvpciegv zr vvwveigy, iuytekmfr, rru jiizzgv. Lzkypp mmjmspv, qlpkmumjgztcmeeic tisaitxj rvv sizrx tsehlgkiu, wgsewfvvh fp meimfyj wyehzrx rkvrtmvw. Xyi hvtrvkqvrk gvfzzhvw ee vbkvvqvpp jxzqlprxzrx, tisuytxzzv, eeh jimvrupp vrmmiseqvrk zr xyi jfvd fj gcejwisfq, fjwmti, rru ces jtrgv; gfqgykmek mejiejxiytxlvv; xvetlzrx jygtfvk; eeh kieuyrxv wicpfajlztj rru rwjmjxrrkwymgw. Zx ieespvw jrglpkc eeh wkyuiexj ks etgfqgpzwy klvmi wycp tfxvrkmrp. Klv uigeixdiex mj tsewkvlgkiu ks iegfyiexi merfzrxzzv tscprffvrxzsew edsek xyi wtmvrtij, iekzrviimek, vrmmiseqvrkec jxlhzij, eeh qvhzgzrv."));
    }
    
    public String breakCaesarCipher2key(String input) {
        StringBuilder input1 = new StringBuilder("");
        StringBuilder input2 = new StringBuilder("");
        
        for ( int i = 0; i < input.length(); i++ ) {
            if ( i % 2 == 0 ) {
                input1.append(input.charAt(i)); 
            }
            else {
                input2.append(input.charAt(i));     
            }
            
        }
        System.out.println("input1: " + input1);
        System.out.println("input2: " + input2);
        
        int input1Key = breakCaesarCipher(input1.toString());
        int input2Key = breakCaesarCipher(input2.toString());
        System.out.println("input1Key: " + input1Key);
        System.out.println("input2Key: " + input2Key);
        
        String decrypted1 = decrypt(input1.toString(),input1Key);
        String decrypted2 = decrypt(input2.toString(),input2Key);
        

        
        System.out.println("decrypted1: " + decrypted1);
        System.out.println("decrypted2: " + decrypted2);
        
        
        StringBuilder resultString = new StringBuilder("");
        for ( int i = 0; i < decrypted1.toString().length(); i++ ) {
            resultString.append(decrypted1.charAt(i));
            if ( i+1 <= decrypted2.toString().length() ) {
                resultString.append(decrypted2.charAt(i));    
            }
        }
        
        
        return resultString.toString();
    }
    
    
    
    
    
    
    
    
    
    
    
    

}
