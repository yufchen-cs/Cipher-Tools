package CipherGUI;

import javafx.stage.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.*;

import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CipherGUI extends Application{

	public void start(Stage primaryStage) {
	
		Label inputLable = new Label("Input text:");
		Label outputLable = new Label("Encrypted/Decrypted text:");
		TextField inputText = new TextField();
		Text outputText = new Text("");
		Button encryptBt = new Button("Encrypt");
		Button decryptBt = new Button("Decrypt");
		Button copyBt = new Button("Copy");
		TabPane tabs = new TabPane();
		
		Tab tab1 = new Tab("Ceaser");
		tab1.setClosable(false);
		ChoiceBox<Integer> shifts = new ChoiceBox<>();
		for(int i = 0; i < 26; i ++) {
			shifts.getItems().add(i);
		}
		VBox ceaserBox = new VBox(5);
		ceaserBox.setPadding(new Insets(20,20,20,20));
		Label numShift = new Label("Indicate the number of shifts:");
		ceaserBox.getChildren().addAll(numShift, shifts);
		shifts.setValue(0);
		tab1.setContent(ceaserBox);
		tabs.getTabs().add(tab1);
		
		Tab tab2 = new Tab("Keyword");
		tab2.setClosable(false);
		Label kwLable = new Label("KeyWord:");
		TextField keyword = new TextField();
		VBox kwBox = new VBox(5);
		kwBox.setPadding(new Insets(20,20,20,20));
		kwBox.getChildren().addAll(kwLable, keyword);
		tab2.setContent(kwBox);
		tabs.getTabs().add(tab2);
		
		Tab tab3 = new Tab("Keyword - vert");
		tab3.setClosable(false);
		Label vkwLable = new Label("KeyWord:");
		TextField vkeyword = new TextField();
		Button vDia = new Button("Encoding Diagram");
		vDia.setOnAction((ActionEvent e) -> {
			VertKeyword.displayDiagram(vkeyword.getText().toString());
		});
		VBox vkwBox = new VBox(5);
		vkwBox.setPadding(new Insets(20,20,20,20));
		vkwBox.getChildren().addAll(vkwLable,vkeyword,vDia);
		tab3.setContent(vkwBox);
		tabs.getTabs().add(tab3);
		
		Tab tab4 = new Tab("Keyword - poly");
		tab4.setClosable(false);
		Label pkwLable = new Label("KeyWord:");
		TextField pkeyword = new TextField();
		Button pDia = new Button("Encoding Diagram");
		pDia.setOnAction((ActionEvent e) -> {
			PolyKeyword.displayDiagram(pkeyword.getText().toString());
		});
		VBox pkwBox = new VBox(5);
		pkwBox.setPadding(new Insets(20,20,20,20));
		pkwBox.getChildren().addAll(pkwLable,pkeyword,pDia);
		tab4.setContent(pkwBox);
		tabs.getTabs().add(tab4);
		
		Tab tab5 = new Tab("Custom");
		tab5.setClosable(false);
		GridPane gPane = new GridPane();
		gPane.setPadding(new Insets(20,20,20,20));
		int col = 0, row = 0;
		TextField[][] cusEncoding = new TextField[4][7];
		for(int i =0; i < 26; i ++) {
			char c = (char)((int)'A'+i);
			gPane.add(new Text(""+c), col++, row);
			gPane.add(cusEncoding[row][col/2] = new TextField(), col++, row);
			if(col > 13) {
				row++;
				col = 0;
			}
		}
		tab5.setContent(gPane);
		tabs.getTabs().add(tab5);
		
		encryptBt.setOnAction((ActionEvent e) -> {
			if(tab1.isSelected())
				outputText.setText(CeaserCipher.encrypt(inputText.getText(), shifts.getValue()).toString());
			else if(tab2.isSelected())
				outputText.setText(KeywordCipher.encrypt(inputText.getText(), keyword.getText()).toString());
			else if(tab3.isSelected())
				outputText.setText(VertKeyword.encrypt(inputText.getText(), vkeyword.getText()).toString());
			else if(tab4.isSelected())
				outputText.setText(PolyKeyword.encrypt(inputText.getText(), pkeyword.getText()).toString());
			else if(tab5.isSelected())
				outputText.setText(CustomEncoding.encrypt(inputText.getText(), cusEncoding).toString());
		});	
		
		decryptBt.setOnAction((ActionEvent e) -> {
			if(tab1.isSelected())
				outputText.setText(CeaserCipher.decrypt(inputText.getText(), shifts.getValue()).toString());
			else if(tab2.isSelected())
				outputText.setText(KeywordCipher.decrypt(inputText.getText(), keyword.getText()).toString());
			else if(tab3.isSelected())
				outputText.setText(VertKeyword.decrypt(inputText.getText(), vkeyword.getText()).toString());
			else if(tab4.isSelected())
				outputText.setText(PolyKeyword.decrypt(inputText.getText(), pkeyword.getText()).toString());
			else if(tab5.isSelected())
				outputText.setText(CustomEncoding.decrypt(inputText.getText(), cusEncoding).toString());
		});	
		
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		copyBt.setOnAction((ActionEvent e) -> {
			StringSelection str = new StringSelection(outputText.getText());
			clipboard.setContents(str, null);
		});
		
		Button showtb = new Button("Show Encoding Table");
		showtb.setOnAction((ActionEvent e) -> {
			if(tab1.isSelected())
				EncodingTable.displayTable(CeaserCipher.getEncoding(shifts.getValue()));
			else if(tab2.isSelected())
				EncodingTable.displayTable(KeywordCipher.getEncoding(keyword.getText()));
			else if(tab3.isSelected())
				EncodingTable.displayTable(VertKeyword.getEncoding(vkeyword.getText()));
			else if(tab4.isSelected())
				PolyKwTable.displayTable(PolyKeyword.getEncoding(pkeyword.getText()));
			else if(tab5.isSelected())
				EncodingTable.displayTable(CustomEncoding.getEncoding(cusEncoding));
		});
		
		Button showFreq = new Button("Show Frequency Table");
		showFreq.setOnAction((ActionEvent e) -> {
				FreqTable.displayTable(getFreq(inputText.getText()));
		});
		
		HBox box1 = new HBox(5);
		box1.setPadding(new Insets(20,20,20,20));
		box1.getChildren().addAll(encryptBt,decryptBt,copyBt,showtb,showFreq);
		box1.setAlignment(Pos.CENTER);

		VBox box = new VBox(10);	
		box.setPadding(new Insets(20,20,20,20));
		box.getChildren().addAll(tabs,inputLable,inputText,outputLable,outputText,box1);
		Scene scene = new Scene(box);
		primaryStage.setTitle("Cipher Tools");
		primaryStage.setScene(scene);
		primaryStage.setHeight(500);
		primaryStage.setWidth(700);
		primaryStage.show(); 
	}
	
	public ArrayList<EncodingFreq> getFreq(String text){
		ArrayList<EncodingFreq> freq = new ArrayList<>();
		for(int i = 0; i < 26; i++) {
			char c = (char)((int)'A'+i);
			freq.add(new EncodingFreq(c));
		}
		for(int i = 0; i < text.length(); i++) {
			char c = Character.toUpperCase(text.charAt(i));
			if(i == 0) {
				freq.get((int)c -(int)'A').increaseCount();
				String s = "_" + text.charAt(i+1);
				freq.get((int)c -(int)'A').addToList(s);
			}
			else if (i == text.length() -1) {
				freq.get((int)c -(int)'A').increaseCount();
				String s = text.charAt(i-1) + "_";
				freq.get((int)c -(int)'A').addToList(s);
			}
			else {
				freq.get((int)c -(int)'A').increaseCount();
				String s = "" + text.charAt(i-1) + text.charAt(i+1);
				freq.get((int)c -(int)'A').addToList(s);
			}
		}
		return freq;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
