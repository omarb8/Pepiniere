/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Service.SujetService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aisce
 */
public class StatController implements Initializable {
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private Button retour=new Button();
    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SujetService s = new SujetService();
        XYChart.Series<String, Integer> series = s.TopSujet();
        barChart.setData(FXCollections.observableArrayList(series));
        // TODO
    }    
    @FXML
    private void retour(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Forom.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
    }
    
}
