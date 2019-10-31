/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Service.CommentaireService;
import Service.SujetService;
import entits.Commentaire;
import entits.Sujet;
import entits.User;
import static fxml.ListeCommentairesController.mId;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aisce
 */
public class ListeCommentaireAdminsController implements Initializable {

   @FXML
    private TableColumn<Commentaire, String> Commentaire;
    @FXML
    private TableColumn<Commentaire, Integer>DatePublication ;
    @FXML
    private TextField comment = new TextField();
    @FXML
    private TableView<Commentaire> table;
    @FXML
    private Button ret =new Button();
    @FXML
    private Pane pnlCustomer=new Pane();
    @FXML
    private Label datepub=new Label();
    
    public static int mId;
    @FXML
    private Button Add;
    @FXML
    private Button supprimer;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     * @throws java.sql.SQLException
     */
    public void transferMessage(int receivedId) {
        //Display the message
        mId = receivedId;
        //System.out.println("passed id is: "+mId);
        
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Commentaire.setCellValueFactory(new PropertyValueFactory<>("descriptionCommentaire"));
        DatePublication.setCellValueFactory(new PropertyValueFactory<>("DatePub"));

        System.out.println("passed id is: "+mId);

    }
    private void Refresh (ActionEvent event) throws IOException {
    CommentaireService ss = new CommentaireService();
        ArrayList<Commentaire> arrayList = (ArrayList<Commentaire>)ss.afficherCommentaire(mId);
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        table.setItems(obs);
        Commentaire s = new Commentaire();
        datepub.setText(s.getDatePublication());
        
        table.refresh();
    }
    @FXML
    private void Ret (ActionEvent event) throws IOException {
      Parent frontPage = null; 
      frontPage = FXMLLoader.load(getClass().getResource("/fxml/ForomAdmin.fxml"));
       Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
    }
    @FXML
    private void Add(ActionEvent event) throws SQLException {
      String t, req;
        t = comment.getText();
        Date date = new Date();
        date = Calendar.getInstance().getTime();
        String mDate = date.toString();
        User user = new User();
        Sujet s = new Sujet();
        Commentaire c = new Commentaire();
        user.setId(1);
        c.setIdClient(user.getId());
        c.setIdSujet(mId);
        CommentaireService ss = new CommentaireService();
        Commentaire p1 = new Commentaire(t,c.getIdClient(),mDate,c.getIdSujet());
        ss.ajouterCommentaire(p1);  
        ArrayList<Commentaire> arrayList = (ArrayList<Commentaire>)ss.afficherCommentaire(mId);
        ObservableList obs = FXCollections.observableArrayList(arrayList);
    
    
        table.setItems(obs);
        table.refresh(); 
        comment.clear();
    }
    @FXML
    private void supprimer(ActionEvent event) {

       Sujet s =new Sujet(mId);
       
        SujetService ss=new SujetService();
        ss.supprimerSujet(s);
    }
        
    
  
}
