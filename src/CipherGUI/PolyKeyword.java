package CipherGUI;

import java.util.ArrayList;
import java.util.Arrays;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PolyKeyword {

	public static StringBuilder encrypt(String text, String keyword) {
		char[][] encoding = buildencoding(keyword);
		StringBuilder encoded= new StringBuilder(); 
		for (int i=0; i<text.length(); i++) {
        	char c = text.charAt(i);
            if (Character.isUpperCase(c)) {
                encoded.append(encoding[i%keyword.length()][(int)c - (int)'A']); 
            } 
            else
            { 
                encoded.append(Character.toLowerCase(encoding[i%keyword.length()][(int)c - (int)'a']));
            } 
        } 
        return encoded; 
	}
	
	public static StringBuilder decrypt(String text, String keyword) { 
		char[][] encoding = buildencoding(keyword);
		char[][] decoding = new char[keyword.length()][26];
		for(int i = 0; i < keyword.length(); i++) {
			for(int j = 0; j < 26; j++)
				decoding[i][(int)encoding[i][j] - (int)'A'] = (char)((int)'A'+j); 
		}		
		StringBuilder decoded = new StringBuilder();
		for (int i=0; i<text.length(); i++) {
        	char c = text.charAt(i);
            if (Character.isUpperCase(c)) {
            	decoded.append(decoding[i%keyword.length()][(int)c - (int)'A']); 
            } 
            else
            { 
            	decoded.append(Character.toLowerCase(decoding[i%keyword.length()][(int)c - (int)'a']));
            } 
        }
        return decoded; 
    }
	
	public static ArrayList<EncodingPolyPair> getEncoding(String keyword){
		ArrayList<EncodingPolyPair> encoding = new ArrayList<>();
		char[][] encodingArr = buildencoding(keyword);
		for(int i = 0; i < 26; i++) {
			char c = (char)((int)'A'+i);
			ArrayList<String> strls = new ArrayList<String>();
			for(int j = 0; j < keyword.length(); j++) {
				strls.add("" + encodingArr[j][i]);
			}
			encoding.add(new EncodingPolyPair(Character.toString(c),strls));
		}
		return encoding;
	}
	
	public static void displayDiagram(String keyword) {
		String str = "\n";
		char[][] encoding = buildencoding(keyword);
		for(int i = 0; i <26; i++) {
				char c = (char)((int)'A'+i);
				str += c + " ";
		}
		str += "\n ---------------------------------------------------------- \n";
		for(int i = 0; i < keyword.length(); i++) {
			for(int j = 0; j < 26; j++) {
					str += encoding[i][j] + " ";
			}
			str += "\n";
		}
		
		Pane pane = new Pane();
		pane.getChildren().add(new Text(str));

		Scene scene = new Scene(pane);	
		Stage stage = new Stage();
        
		stage.setTitle("Encoding Table");
		stage.setScene(scene);
		stage.show();
	}
	
	private static char[][] buildencoding(String keyword) {
		char[][] encoding = new char[keyword.length()][26];
		for(int i = 0; i < keyword.length(); i++) {
			char c = Character.toUpperCase(keyword.charAt(i));
			for(int j = 0; j < 26; j++) {
				char ch = (char)((int)c +j);
				if(ch > 'Z') {
					ch = (char)((int)ch - 26);
				}
				encoding[i][j] = ch;
			}		
		}
		return encoding;
	}
}
