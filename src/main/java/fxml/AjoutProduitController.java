/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import entits.Categorie;
import entits.Produit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Service.CategorieService;
import Service.ProduitService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AjoutProduitController implements Initializable {

   
    @FXML
    private TextField nomprod;
    @FXML
    private TextField prixprod;
    @FXML
    private TextField description;
    @FXML
    private TextField quantite;
    @FXML
    private TextField image;
    
    @FXML
    private ComboBox<Categorie> combobox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategorieService c=new CategorieService();
        ObservableList<Categorie> list  = FXCollections.observableArrayList(c.affichercategories());
        combobox.setItems(list);
        combobox.getSelectionModel().select(0);
    }    

    @FXML
    private void AjouterProduit(ActionEvent event) {
        Categorie categorie = combobox.getValue();
        System.out.println(categorie);
       Produit p = new Produit(nomprod.getText(),Float.parseFloat(prixprod.getText()),description.getText(),Integer.parseInt(quantite.getText()),image.getText(),categorie.getId());
       
        ProduitService ps=new ProduitService();
        ps.creerProduit(p);
        
        
    }

    @FXML
    private void retour(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Gestionproduit.fxml"));
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
