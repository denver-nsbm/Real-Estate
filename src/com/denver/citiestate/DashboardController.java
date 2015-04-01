package com.denver.citiestate;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardController implements Initializable {

	@FXML
	private Label lblLog;

	@FXML
	private TextField txtSquareFeet, txtLotNumber, txtBedrooms, txtPrice,
			txtLastName, txtFirstName;


	/**
	 * Menu bar quit functionality
	 * 
	 * @param event
	 */
	@FXML
	void quit(ActionEvent event) {

		Stage stage = (Stage) lblLog.getScene().getWindow();
		stage.close();
	}

	/**
	 * Initialization of FXML
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	/**
	 * Clear Textboxes
	 * 
	 * @param event
	 */
	@FXML
	void clearTextboxes(ActionEvent event) {

		clearTextboxes();
		lblLog.setText("Cleared the Textboxes");
	}

	/**
	 * Reset to house list to zero
	 * 
	 * @param event
	 */
	@FXML
	void resetHouseList(ActionEvent event) {
		
	}

	/**
	 * Find record by Lot number
	 * 
	 * @param event
	 */
	@FXML
	void findRecord(ActionEvent event) {

		Listable found = list.find(new ListHouse(Integer.parseInt(txtLotNumber
				.getText()), "", "", 0, 0, 0));
		if (found == null) {
			lblLog.setText("Can't find the House");
		} else {
			setHouse((ListHouse) found);
			lblLog.setText("House Found");
		}
		
	}

	/**
	 * Reset the List to Zero and move to next
	 * 
	 * @param event
	 */
	@FXML
	void findNext(ActionEvent event) {

	}

	/**
	 * Delete a record
	 * 
	 * @param event
	 */
	@FXML
	void deleteRecord(ActionEvent event) {
		
	}

	/**
	 * Insert a new house to list
	 * 
	 * @param event
	 */
	@FXML
	void addNewHouse(ActionEvent event) {
		
	}

	/**
	 * Open the about us FXML
	 * 
	 * @param event
	 */
	@FXML
	void openAbout(ActionEvent event) {
		try {
			Stage aboutUs = new Stage();
			VBox root = (VBox) FXMLLoader.load(getClass().getResource(
					"about.fxml"));
			Scene scene = new Scene(root, 400, 300);
			scene.getStylesheets().add(
					getClass().getResource("application.css").toExternalForm());
			aboutUs.setScene(scene);
			aboutUs.setResizable(false);
			aboutUs.setTitle("Citi Estate");
			aboutUs.getIcons().add(
					new Image(getClass().getResourceAsStream(
							"resource/icon_2x.png")));

			aboutUs.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Separated clear Textboxes funtionality to reuse
	 */
	private void clearTextboxes() {
		txtSquareFeet.clear();
		txtLotNumber.clear();
		txtBedrooms.clear();
		txtPrice.clear();
		txtLastName.clear();
		txtFirstName.clear();
	}

}
