/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import entits.Commentaire;
import entits.Sujet;
import entits.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.persistence.Table;
import Service.CommentaireService;
import Service.SujetService;
import java.awt.TrayIcon.MessageType;
import java.awt.*;



/**
 * FXML Controller class
 *
 * @author hp
 */
public class ListeCommentairesController implements Initializable {

   @FXML
    private TableColumn<Commentaire, String> Commentaire;
    @FXML
    private TableColumn<Commentaire, Integer>DatePublication ;
    @FXML
    private TextField comment = new TextField();
    @FXML
    private TableView<Commentaire> table;
    @FXML
    private Button add=new Button();
    @FXML
    private Button ret =new Button();
    @FXML
    private Button refresh =new Button();
    @FXML
    private Pane pnlCustomer=new Pane();
    @FXML
    private Label datepub=new Label();
    
    public static int mId;
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
    @FXML
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
      frontPage = FXMLLoader.load(getClass().getResource("/fxml/Forom.fxml"));
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
        user.setId(HomeController.idU);
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
    
}
