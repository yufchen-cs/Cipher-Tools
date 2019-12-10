package CipherGUI;

public class EncodingPair {
	
	private String plain;
	private String encoding;
	
	public EncodingPair(String plain, String encoding) {
		this.setPlain(plain);
		this.setEncoding(encoding);
	}

	public String getPlain() {
		return plain;
	}

	public void setPlain(String plain) {
		this.plain = plain;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
}
