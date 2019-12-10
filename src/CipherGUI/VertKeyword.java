package CipherGUI;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VertKeyword {
	
	private static int count = 0;
	
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
	
	public static void displayDiagram(String keyword) {
		String str = "";
		ArrayList<EncodingPair> encoding = getEncoding(keyword);
		int index = 0;
		int row = (int)(26/count) +1;
		int space = row*count - 26;
		int rowIndex = 0;
		for(int i = 0; i < encoding.size(); i++) {
			if(i%count==0)
				str += "\n";
			if(i%count + space < count) {			
				str += encoding.get(index).getPlain() + ": " + encoding.get(index).getEncoding() + "      ";
				index += row;
			}
			else {
				str += encoding.get(index).getPlain() + ": " + encoding.get(index).getEncoding() + "      ";
				index += row -1;
			}
			if(index > 25) {
				rowIndex ++;
				index = rowIndex;
			}
		}
		
		Pane pane = new Pane();
		pane.getChildren().add(new Text(str));

		Scene scene = new Scene(pane);	
		Stage stage = new Stage();
        
		stage.setTitle("Encoding Table");
		stage.setScene(scene);
		stage.show();
	}
	
	private static char[] buildencoding(String keyword) {
		int index = 0;
		count = 0;
		char[] vertEncoding = new char[26];
		char[] horiEncoding = new char[26];
		boolean[] visited = new boolean[26];
		for(int i = 0; i < keyword.length(); i++) {
			char c = keyword.charAt(i);
			if(c >= 'A' && c <= 'Z') {
				if (!visited[c - (int)'A'])  
                { 
					horiEncoding[index] = c; 
					index ++;
					visited[c - (int)'A'] = true;
					count ++;
                } 
			}
			else if(c >= 'a' && c <= 'z') {
				if (!visited[c - (int)'a']) { 
					horiEncoding[index] = Character.toUpperCase(c); 
					index ++;
					visited[c - (int)'a'] = true; 
					count ++;
	            }
			}
		}
		
		for(int i = 0; i < 26; i++) {
			if(!visited[i]) {
				horiEncoding[index] = (char)(i + (int)'A'); 
				index ++;
				visited[i] = true;
			}
		}
		
		index = 0;
		for(int i=0; i < 26; i++) {
			if(index > 25) {
				index = index%count +1;
			}
			vertEncoding[i] = horiEncoding[index];
			index += count;	
		}
		return vertEncoding;
		
	}
	
}
