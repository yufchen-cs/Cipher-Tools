package CipherGUI;

import java.util.ArrayList;

public class KeywordCipher {

	public static StringBuilder encrypt(String text, String keyword) {
		char[] encoding = buildencoding(keyword);
		StringBuilder encoded= new StringBuilder(); 
		for (int i=0; i<text.length(); i++) {
        	char c = text.charAt(i);
            if (Character.isUpperCase(c)) {
                encoded.append(encoding[(int)c - (int)'A']); 
            } 
            else
            { 
                encoded.append(Character.toLowerCase(encoding[(int)c - (int)'a']));
            } 
        } 
        return encoded; 
	}
	
	public static StringBuilder decrypt(String text, String keyword) { 
		char[] encoding = buildencoding(keyword);
		char[] decoding = new char[26];
		for(int i = 0; i < 26; i++) {
			decoding[(int)encoding[i] - (int)'A'] = (char)((int)'A'+i); 
		}
		StringBuilder decoded = new StringBuilder();
		for (int i=0; i<text.length(); i++) {
        	char c = text.charAt(i);
            if (Character.isUpperCase(c)) {
            	decoded.append(decoding[(int)c - (int)'A']); 
            } 
            else
            { 
            	decoded.append(Character.toLowerCase(decoding[(int)c - (int)'a']));
            } 
        }
        return decoded; 
    }
	
	public static ArrayList<EncodingPair> getEncoding(String keyword){
		ArrayList<EncodingPair> encoding = new ArrayList<>();
		char[] encodingArr = buildencoding(keyword);
		for(int i = 0; i < 26; i++) {
			char c = (char)((int)'A'+i);
			char s = encodingArr[i];
			encoding.add(new EncodingPair(Character.toString(c),Character.toString(s)));
		}
		return encoding;
	}
	
	private static char[] buildencoding(String keyword) {
		int index = 0;
		char[] encoding = new char[26];
		boolean[] visited = new boolean[26];
		for(int i = 0; i < keyword.length(); i++) {
			char c = keyword.charAt(i);
			if(c >= 'A' && c <= 'Z') {
				if (!visited[c - (int)'A'])  
                { 
					encoding[index] = c; 
					index ++;
					visited[c - (int)'A'] = true; 
                } 
			}
			else if(c >= 'a' && c <= 'z') {
				if (!visited[c - (int)'a']) { 
					encoding[index] = Character.toUpperCase(c); 
					index ++;
					visited[c - (int)'a'] = true; 
	            }
			}
		}
		
		for(int i = 0; i < 26; i++) {
			if(!visited[i]) {
				encoding[index] = (char)(i + (int)'A'); 
				index ++;
				visited[i] = true;
			}
		}
		
		return encoding;
		
	}
	
}
