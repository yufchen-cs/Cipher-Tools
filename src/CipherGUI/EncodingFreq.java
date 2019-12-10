package CipherGUI;

import java.util.ArrayList;

public class EncodingFreq {
	
	private int count = 0;
	private char ch;
	private ArrayList<String> list = new ArrayList<>();
	
	public EncodingFreq(char ch) {
		this.ch = ch;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public char getCh() {
		return ch;
	}

	public void setCh(char ch) {
		this.ch = ch;
	}

	public ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}
	
	public void increaseCount() {
		count++;
	}
	
	public void addToList(String str) {
		list.add(str);
	}
}
