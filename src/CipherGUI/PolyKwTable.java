package CipherGUI;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PolyKwTable {

	public static void displayTable(ArrayList<EncodingPolyPair> encodingList) {
		TableView table = new TableView();
		TableColumn<EncodingPolyPair,String> plainChar = new TableColumn<>("Plain Text");
		TableColumn<EncodingPolyPair,ArrayList<String>> encodeChar = new TableColumn<>("Encoding Text"); 

		ObservableList<EncodingPolyPair> data = FXCollections.observableArrayList();
		for(EncodingPolyPair e: encodingList) {
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
