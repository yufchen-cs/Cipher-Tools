package CipherGUI;

import java.util.ArrayList;

public class EncodingPolyPair {

	private String plain;
	private ArrayList<String> encoding;
	
	public EncodingPolyPair(String plain, ArrayList<String> encoding) {
		this.setPlain(plain);
		this.setEncoding(encoding);
	}

	public String getPlain() {
		return plain;
	}

	public void setPlain(String plain) {
		this.plain = plain;
	}

	public ArrayList<String> getEncoding() {
		return encoding;
	}

	public void setEncoding(ArrayList<String> encoding2) {
		this.encoding = encoding2;
	}
}
