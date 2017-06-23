package it.polito.tdp.flight;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.flight.model.Airport;
import it.polito.tdp.flight.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FlightController {

	private Model model;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField txtDistanzaInput;

	@FXML
	private TextField txtPasseggeriInput;

	@FXML
	private TextArea txtResult;

	@FXML
	void doCreaGrafo(ActionEvent event) {
		String num = txtDistanzaInput.getText();

		if (num.length() == 0) {
			txtResult.appendText("ERRORE: devi inserire un numero compreso fra 600 e 800 (km) \n");
			return;
		}

		int numero;
		try {
			numero = Integer.parseInt(num);
		} catch (NumberFormatException e) {
			txtResult.appendText("ERRORE: il numero deve essere in formato numerico\n");
			return;
		}
		txtResult.clear();
		
		Airport airport = model.creaGrafo2(numero);
		boolean connesso = model.isConnected();
		txtResult.appendText("l'aeroportopiù lontano da Roma Fiumicino è : "+airport.toString()+"\n" );
		if(connesso == true){
			txtResult.appendText("Il grafo è connesso");
		}else{
			txtResult.appendText("il grafo non è connesso");
		}
		
	}

	@FXML
	void doSimula(ActionEvent event) {
		
	}

	@FXML
	void initialize() {
		assert txtDistanzaInput != null : "fx:id=\"txtDistanzaInput\" was not injected: check your FXML file 'Untitled'.";
		assert txtPasseggeriInput != null : "fx:id=\"txtPasseggeriInput\" was not injected: check your FXML file 'Untitled'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Untitled'.";

	}

	public void setModel(Model model) {
		this.model = model;
	}
}
