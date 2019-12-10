package CipherGUI;

import java.util.ArrayList;

import javafx.scene.control.TextField;

public class CustomEncoding {
	
	public static StringBuilder encrypt(String text, TextField[][] cusEncoding) {
		String[] encoding = buildencoding(cusEncoding);
		StringBuilder encoded= new StringBuilder(); 
		for (int i=0; i<text.length(); i++) {
        	char c = text.charAt(i);
                encoded.append(encoding[(int)c - (int)'A']); 
        } 
        return encoded; 
	}
	
	public static StringBuilder decrypt(String text, TextField[][] cusEncoding) { 
        return encrypt(text, cusEncoding); 
    }
	
	public static ArrayList<EncodingPair> getEncoding(TextField[][] cusEncoding){
		ArrayList<EncodingPair> encoding = new ArrayList<>();
		String[] encodingArr = buildencoding(cusEncoding);
		for(int i = 0; i < 26; i++) {
			char c = (char)((int)'A'+i);
			String s = encodingArr[i];
			encoding.add(new EncodingPair(Character.toString(c),s));
		}
		return encoding;
	}
	
	private static String[] buildencoding(TextField[][] cusEncoding) {
		String[] encoding = new String[26];
		int row = 0, col = 0;
		for(int i = 0; i < 26; i++) {
			encoding[i] = cusEncoding[row][col++].getText();
			if(col > 6) {
				row++;
				col = 0;
			}
		}	
		return encoding;
		
	}
	

	
}
