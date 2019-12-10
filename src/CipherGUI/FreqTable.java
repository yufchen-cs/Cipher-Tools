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

public class FreqTable {

	public static void displayTable(ArrayList<EncodingFreq> freqList) {
		TableView table = new TableView();
		TableColumn<EncodingFreq,Integer> freqCol = new TableColumn<>("Frequency");
		TableColumn<EncodingFreq,Character> charCol = new TableColumn<>("Character"); 
		TableColumn<EncodingFreq,ArrayList<String>> listCol = new TableColumn<>("Neighboring"); 

		ObservableList<EncodingFreq> data = FXCollections.observableArrayList();
		for(EncodingFreq e: freqList) {
			data.add(e);
		}
		freqCol.setCellValueFactory(new PropertyValueFactory<>("count"));
		charCol.setCellValueFactory(new PropertyValueFactory<>("ch"));
		listCol.setCellValueFactory(new PropertyValueFactory<>("list"));
		table.setItems(data);
		table.getColumns().addAll(freqCol,charCol,listCol);
		
		Pane pane = new Pane();
		pane.getChildren().add(table);
		table.setPrefSize(500,300);
		Scene scene = new Scene(pane,500,300);	
		Stage stage = new Stage();
		stage.setTitle("Encoding Table");
		stage.setScene(scene);
		stage.show();
	}
	
}
