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

	private SortedList list = new SortedList(20);

	/**
	 * On Close Listener to save to file
	 */
	private void setOnCloseListener() {
		Stage stage = (Stage) lblLog.getScene().getWindow();

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent window) {

				list.reset();
				// Store info from list into house file
				for (int i = 0; i < list.getMaxSpace(); i++) {
					ListHouse house = (ListHouse) list.next();
					save.addToStorage(house);
				}

				save.writeToFile();

			}
		});
	}


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

		Platform.runLater(new Runnable() {
			@Override
			public void run() {

				if (new File("records.txt").exists()) {
					list = save.readFromFile(list);
					list.reset();
					ListHouse house = (ListHouse) list.next();
					if (house != null)
						setHouse(house);
				}
				setOnCloseListener();
			}
		});
		
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
		
		ListHouse house;
		list.reset();
		if (list.size() == 0)
			clearTextboxes();
		else {
			house = (ListHouse) list.next();
			if (house != null)
				setHouse(house);
		}
		lblLog.setText("Reset the House List");

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

		Listable house = list.next();
		if (house != null) {
			setHouse((ListHouse) house);
			lblLog.setText("Next House Found");
		} else {
			clearTextboxes();
			lblLog.setText("No Record Found!");
		}

	}

	/**
	 * Delete a record
	 * 
	 * @param event
	 */
	@FXML
	void deleteRecord(ActionEvent event) {

		Listable house = getHouse();
		list.delete(house);
		clearTextboxes();
		resetHouseList(event);
		lblLog.setText("House Deleted");

	}

	/**
	 * Insert a new house to list
	 * 
	 * @param event
	 */
	@FXML
	void addNewHouse(ActionEvent event) {
		
		try {

			ListHouse house = getHouse();
			if (list.recordExist(house))
				lblLog.setText("Lot number already exists");
			else {
				list.insert(house);
				lblLog.setText("House added to list");
			}
		} catch (NumberFormatException e) {
			// Text field info incorrectly formated
			lblLog.setText("Number? " + e.getMessage());
		}
		
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
	 * Get the current house from textfields
	 * 
	 * @return
	 */
	private ListHouse getHouse() {

		return new ListHouse(Integer.parseInt(txtLotNumber.getText()),
				txtFirstName.getText(), txtLastName.getText(),
				Integer.parseInt(txtPrice.getText()),
				Integer.parseInt(txtSquareFeet.getText()),
				Integer.parseInt(txtBedrooms.getText()));
	}

	/**
	 * Set the ListHouse object to textfields
	 * 
	 * @param house
	 */
	private void setHouse(ListHouse house) {
		txtLotNumber.setText(Integer.toString(house.getLotNumber()));
		txtFirstName.setText(house.getFirstName());
		txtLastName.setText(house.getLastName());
		txtPrice.setText(Integer.toString(house.getPrice()));
		txtSquareFeet.setText(Integer.toString(house.getSquareFeet()));
		txtBedrooms.setText(Integer.toString(house.getBedRooms()));
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
