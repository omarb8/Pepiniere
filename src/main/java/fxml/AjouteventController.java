/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import entits.Evenement;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Service.Eventservice;
import fxml.HomeController;

/**
 * FXML Controller class
 *
 * @author FARAH
 */
public class AjouteventController implements Initializable {
 @FXML
    private Button retour;
    @FXML
    private Button valider;
    @FXML
    private TextField nbr;
    @FXML
    private DatePicker datedeb;
    @FXML
    private DatePicker datefin;
    @FXML
    private TextField desc;
    @FXML
    private TextField lieu;
    @FXML
    private TextField prix;
    @FXML
    private TextField image;
    @FXML
    private TextField nom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backAction(ActionEvent event) {
          Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/GestionEvenAdm.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
    }

    @FXML
    private void ajoutAction(ActionEvent event) {

        
           
       Evenement e;
        e = new  Evenement (datedeb.getValue().toString(),datefin.getValue().toString(), desc.getText(),lieu.getText(), Integer.parseInt(nbr.getText()),Integer.parseInt(prix.getText()), image.getText(),nom.getText());
        Eventservice d = new Eventservice(); 
         d.creerevent(e);
        
        

        
    }
    
}
