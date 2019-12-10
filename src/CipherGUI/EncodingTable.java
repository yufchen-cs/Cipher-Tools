package CipherGUI;

import javafx.stage.*;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class EncodingTable {

	public static void displayTable(ArrayList<EncodingPair> encodingList) {
		TableView table = new TableView();
		TableColumn<EncodingPair,String> plainChar = new TableColumn<>("Plain Text");
		TableColumn<EncodingPair,String> encodeChar = new TableColumn<>("Encoding Text"); 

		ObservableList<EncodingPair> data = FXCollections.observableArrayList();
		for(EncodingPair e: encodingList) {
			data.add(e);
		}
		plainChar.setCellValueFactory(new PropertyValueFactory<>("plain"));
		encodeChar.setCellValueFactory(new PropertyValueFactory<>("encoding"));
		table.setItems(data);
		table.getColumns().addAll(plainChar,encodeChar);
		
		Pane pane = new Pane();
		pane.getChildren().add(table);

		Scene scene = new Scene(pane);	
		Stage stage = new Stage();
        
		stage.setTitle("Encoding Table");
		stage.setScene(scene);
		stage.show();
	}
	
}
