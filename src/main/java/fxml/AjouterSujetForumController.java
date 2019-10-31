/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

//import com.jfoenix.controls.JFXDatePicker;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Service.SujetService;
import entits.Sujet;
import entits.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.awt.TrayIcon.MessageType;
import java.awt.*;




/**
 * FXML Controller class
 *
 * @author hp
 */
public class AjouterSujetForumController implements Initializable {

    @FXML
    private VBox pnItem=new VBox();
    @FXML
    private Label subject1 =new Label();
    @FXML
    private TextField title = new TextField();

    @FXML
    private TextArea description=new TextArea();
    @FXML
    private Button addPostButton=new Button();
    @FXML
    private Button send=new Button();
    @FXML
    private Pane pnlCustomer=new Pane();
    @FXML
    private Pane pnlCustomer1=new Pane();
    @FXML 
    private ChoiceBox categorie=new ChoiceBox();
    @FXML
    private Label datepub;
 

    @FXML
    private void Retour(ActionEvent event) throws IOException {
      Parent frontPage = null; 
      frontPage = FXMLLoader.load(getClass().getResource("/fxml/Forom.fxml"));
       Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
    }
 
    @FXML
    void Notify (String message) throws SQLException, AWTException {
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Java AWT Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);
        trayIcon.displayMessage("Hello,",message, MessageType.INFO);
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList cat = categorie.getItems();
            cat.addAll("Plante","Accessoire","Divers");        

    }

    @FXML
    private void Send(ActionEvent event) throws SQLException, AWTException {
      String t, desc, req;
        t = title.getText();
        desc = description.getText();
        Date date = new Date();
        date = Calendar.getInstance().getTime();
        String mDate = date.toString();
        String mCat = categorie.getSelectionModel().getSelectedItem().toString();
        User user = new User();
        Sujet s = new Sujet();
        user.setId(HomeController.idU);
        s.setIdUser(user.getId());
        if((t.trim().isEmpty())&&(desc.trim().isEmpty())){
            Alert fail= new Alert(AlertType.INFORMATION);
            fail.setHeaderText("failure");
        fail.setContentText("you havent typed something");
        fail.showAndWait();
        }
        else if(t.trim().isEmpty()){
            Alert fail= new Alert(AlertType.INFORMATION);
        fail.setHeaderText("failure");
        fail.setContentText("you havent type the title");
        fail.showAndWait();
        }
        else if (desc.trim().isEmpty()) {
                Alert fail= new Alert(AlertType.INFORMATION);
        fail.setHeaderText("failure");
        fail.setContentText("you havent type a description");
        fail.showAndWait();
                
                }
        else if (mCat=="Plante") {
Notify("un sujet a ete ajouté la categorie Plante");
        SujetService ss = new SujetService();
        Sujet p1 = new Sujet(t,s.getIdUser(),mCat,mDate,desc);
        ss.ajouterSujet(p1);  
        title.clear();
        description.clear();

        }
        else if (mCat=="Accessoire") {
Notify("un sujet a ete ajouté la categorie Accessoire");
        SujetService ss = new SujetService();
        Sujet p1 = new Sujet(t,s.getIdUser(),mCat,mDate,desc);
        ss.ajouterSujet(p1);  
        title.clear();
        description.clear();

        }
        else if (mCat=="Divers") {
Notify("un sujet a ete ajouté la categorie Divers");
        SujetService ss = new SujetService();
        Sujet p1 = new Sujet(t,s.getIdUser(),mCat,mDate,desc);
        ss.ajouterSujet(p1);  
        title.clear();
        description.clear();

        }
    }}
