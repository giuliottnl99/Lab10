/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.rivers;


import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxRiver"
    private ComboBox<River> boxRiver; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumMeasurements"
    private TextField txtNumMeasurements; // Value injected by FXMLLoader

    @FXML // fx:id="txtFMed"
    private TextField txtFMed; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
       
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	LinkedList<River> fiumi = ((Model) this.model).getFiumi();
    	this.boxRiver.getItems().setAll(fiumi);

    	
    }
    
    @FXML
    void trovaFiume(ActionEvent event) {
    	River r = boxRiver.getValue();
    	model.aggiungiRive(r);
    	this.txtResult.setText("");
    	if(r.getClass().isInstance(new River())) {
    		if(r.inizializzato == false)
    			r.createInfoFiume();
    		
    		//this.txtResult.setText(r.descriviti());
    		this.txtStartDate.setText(r.getFirstFlowDate().toString());
    		this.txtEndDate.setText(r.getLastFlowDate().toString());
    		this.txtNumMeasurements.setText(String.valueOf(r.getFlows().size()) );
    		this.txtFMed.setText(String.valueOf( r.getFlowAvg() ));
    	}
    }
    
    @FXML
    void simulate(ActionEvent event) {
    	this.txtResult.setText("");
    	double k = Double.valueOf(this.txtK.getText());
    	River f = this.boxRiver.getValue();
    	boolean verifica = model.Startsimulate(k, f);
    	if(verifica==false) {
    		this.txtResult.setText("Inserisci un k maggiore o uguale a zero");
    	}
    	else {
    		String result = model.getResultSimulation();
    		this.txtResult.setText(result);
    	}
    	
    }
}
