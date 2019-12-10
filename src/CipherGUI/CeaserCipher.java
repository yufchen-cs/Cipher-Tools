package CipherGUI;

import java.util.ArrayList;

public class CeaserCipher {

	public static StringBuilder encrypt(String text, int shift) { 
		StringBuilder encoded= new StringBuilder(); 
  
        for (int i=0; i<text.length(); i++) {
        	char c = text.charAt(i);
            if (Character.isUpperCase(c)) {
            	c = (char)(((int)c - (int)'A' + shift) % 26 + (int)'A'); 
                encoded.append(c); 
            } 
            else
            { 
                c = (char)(((int)c - (int)'a' + shift) % 26 + (int)'a'); 
                encoded.append(c);
            } 
        } 
        return encoded; 
    }
	
	public static StringBuilder decrypt(String text, int shift) { 
		StringBuilder decoded = encrypt(text,(26 - shift) % 26);
        return decoded; 
    }
	
	public static ArrayList<EncodingPair> getEncoding(int shift){
		ArrayList<EncodingPair> encoding = new ArrayList<>();
		for(int i = 0; i < 26; i++) {
			char c = (char)((int)'A'+i);
			char s = (char)((i + shift) % 26 + (int)'A');
			encoding.add(new EncodingPair(Character.toString(c),Character.toString(s)));
		}
		return encoding;
	}

}
