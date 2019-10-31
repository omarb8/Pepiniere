/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import entits.ObjetPanierProduit;
import entits.Produit;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import entits.Commande;
import entits.LigneCommande;
import java.util.Date;
import Service.CommandeService;
import Service.LigneCommandeService;

import entits.LigneService;
import Service.LigneServiceService;
import Iservice.ILigneService;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entits.Commandable;
import entits.LignePack;
//import entits.MailCommande;
import entits.ObjetPanierService;
import entits.OffrePromotion;
import entits.PackDecoration;
import entits.User;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;
import java.util.Properties;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import Service.PackService;

import Service.ProduitService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class MonPanierController implements Initializable {
 public static List<Commandable> panier = new ArrayList();
    @FXML
    private TableView<Commandable> tableView1;
    @FXML
    private TableColumn<Commandable,Integer> id;
    @FXML
    private TableColumn<Commandable, String> nom_produit;
    @FXML
    private TableColumn<Commandable, Integer> quantite;
    @FXML  
    private TableColumn<Commandable, Double> prix_total;
    @FXML
    private TableColumn<Commandable, String> type;
    @FXML
    private AnchorPane SupprimerProduit;
    
    ObservableList<Commandable> list  ;
    @FXML
    private Button ajouter;

    private CommandeService commandeService = new CommandeService();
    private LigneCommandeService ligneCommandeService = new LigneCommandeService();
    private LigneServiceService ligneServiceService = new LigneServiceService();
    private ProduitService produitService = new ProduitService();
    private PackService packService = new PackService();
    @FXML
    private Button One;
    @FXML
    private Button modifier;
    @FXML
    private TableColumn<Commandable, String> descriptionObjetPanier;
    @FXML
    private Button Vider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list  = FXCollections.observableArrayList(panier);
        tableView1.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<Commandable,Integer>("idObjet"));
        nom_produit.setCellValueFactory(new PropertyValueFactory<Commandable,String>("nom"));
        quantite.setCellValueFactory(new PropertyValueFactory<Commandable,Integer>("quantite"));
        prix_total.setCellValueFactory(new PropertyValueFactory<Commandable,Double>("prixTotale"));
        type.setCellValueFactory(new PropertyValueFactory<Commandable,String>("type"));
        
        quantite.setCellFactory(TextFieldTableCell.<Commandable, Integer>forTableColumn(new IntegerStringConverter()));
        quantite.setOnEditCommit(event->{
            Integer newValue = event.getNewValue()==null ? event.getOldValue():
                event.getNewValue();
            Commandable commandable = ((Commandable) event.getTableView().getItems()
                .get(event.getTablePosition().getRow()));
            commandable.setQuantite(newValue);
            tableView1.refresh();
        });
        descriptionObjetPanier.setCellValueFactory(new PropertyValueFactory<Commandable,String>("description"));
    
    }    

    @FXML
    private void Boutique(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Boutique.fxml"));
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
    private void monCompte(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/MonCompte.fxml"));
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
    private void notreService(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/NotreService.fxml"));
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
    private void mesCommandes(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/MesCommandes.fxml"));
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
    private void notreOffre(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/NosPacks.fxml"));
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
    private void monPanier(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/MonPanier.fxml"));
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
    private void evenment(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Evenement.fxml"));
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
    private void forom(ActionEvent event) {
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

    @FXML
    private void deconnectee(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
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
    private void SupprimerAction(ActionEvent event) {

                panier.clear();
                tableView1.getItems().clear();
                panier.removeAll(panier);
                 Alert alert = new Alert(AlertType.ERROR);
        
alert.setTitle("Votre Panier a été vidé ");
alert.setHeaderText("Vous avez décidé de vider votre panier");
alert.setContentText("Que voulez vous faire maintenant ?");

ButtonType buttonTypeOne = new ButtonType("Retourner vers la boutique !");
ButtonType buttonTypeTwo = new ButtonType("Voulez-vous voir nos offres ?");
ButtonType buttonTypeThree = new ButtonType("Consulter vos commande");
ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);



alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == buttonTypeOne){
    Parent frontPage = null; 
            try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Boutique.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
} else if (result.get() == buttonTypeTwo) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/NotreOffre.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
} else if (result.get() == buttonTypeThree) {
            Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/MesCommandes.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
} else {
    // ... user chose CANCEL or closed the dialog
}
        }

    
        @FXML
    private void One(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Boutique.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
    }

    private void createLigneCommande(Commandable commandable,Commande commande) {
        LigneCommande ligneCommande = new LigneCommande();
        ligneCommande.setIdCommande(commande);
        ligneCommande.setIdProduit(new Produit(commandable.getIdObjet()));
        ligneCommande.setQte(commandable.getQuantite());
        ligneCommandeService.creeLigneCommande(ligneCommande);
    }
    private void createLigneService(Commandable commandable,Commande commande) {
        ObjetPanierService service = (ObjetPanierService) commandable;
        LigneService ligneService = new LigneService();
        ligneService.setIdCommande(commande);
        ligneService.setIdUser(service.getUser());
        ligneService.setDateDebut(service.getDateDebut());
        ligneService.setDateFin(service.getDateFin());
        ligneServiceService.creerLigneService(ligneService);
    }
    
    
    @FXML
    private void ValiderPanier(ActionEvent event) {
        double prixTotale = 0 ;
        double prixTotale1 = 0 ;
        /////////////
        //Produit
        Commande commande = new Commande(0, "", 0);
        commande.setIdUser(new User(HomeController.idU));
        ProduitService ps = new ProduitService();
        int id = commandeService.creerCommande(commande);
        commande.setId(id);
        
        for(Commandable c : panier) {
            switch (c.getType()) {
                case "produit": {
                    System.out.println("produit"+c);
                    createLigneCommande(c, commande);
                    prixTotale+=c.getPrixTotale();
                    ObjetPanierProduit objetPanierProduit = (ObjetPanierProduit) c;
                    produitService.updateQuantite(objetPanierProduit.getProduit(), c.getQuantite());
                    
                }break;
                case "user": {
                    System.out.println("user"+c);
                    createLigneService(c, commande);
                    prixTotale+=c.getPrixTotale();
                }break;
                case "pack": {
                    System.out.println("pack"+c);
                    for(LignePack lignePack:packService.getLignePackByPack((PackDecoration) c)  ) {
                        ObjetPanierProduit objetPanierProduit =
                                new ObjetPanierProduit(lignePack.getIdProduit(),lignePack.getQuantite());
                        createLigneCommande(objetPanierProduit, commande);
                        produitService.updateQuantite(objetPanierProduit.getProduit(), objetPanierProduit.getQuantite());
                    }
                    prixTotale+=c.getPrixTotale();
                }break;
                
            }
        }
                Document document = new Document();
                                 PdfPCell cell = null;
                                 
                 PdfPTable table = new PdfPTable(4);
                

                 
                table.addCell(getCell("Nom",PdfPCell.ALIGN_CENTER));
                table.addCell(getCell("Quantite",PdfPCell.ALIGN_CENTER));
                table.addCell(getCell("Prix",PdfPCell.ALIGN_CENTER));
                table.addCell(getCell("Type",PdfPCell.ALIGN_CENTER));
                
                PdfPTable table1 = new PdfPTable(1);
                table1.addCell(getCell("Prix Total",PdfPCell.ALIGN_CENTER));
        try{
            PdfWriter.getInstance(document, new FileOutputStream("HelloWorld6.pdf"));
            document.open();
            Paragraph p1 = new Paragraph("Commande validée avec succès");  
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);
            Phrase p2 = new Phrase("Votre commande a été enregistré, ci-dessous vous trouverez les produits que vous avez demandé");
          
            Paragraph p5 = new Paragraph(""); 
            document.add(p2);
            document.add(p5);
            document.add(p5);
            document.add(p5);
            document.add(p5);
            for(Commandable c : panier) {
             //for(int i = 0; i <=82 ; i++) {
                    
            //table.addCell(getCell(tableView1.getItems().get(c).ge;
            //table.addCell(getCell(String.toString(tableView1.getItems().get(i).getNom())+"%",PdfPCell.ALIGN_CENTER ));
           
            table.addCell(getCell(c.getNom(),PdfPCell.ALIGN_CENTER ));          
            table.addCell(getCell(String.valueOf(c.getQuantite()),PdfPCell.ALIGN_CENTER ));
            table.addCell(getCell(String.valueOf(c.getPrixTotale()),PdfPCell.ALIGN_CENTER ));
            table.addCell(getCell(c.getType(),PdfPCell.ALIGN_CENTER ));
            prixTotale1+=c.getPrixTotale();
            
            //table.addCell(getCell(Integer.toString(tableView1.getItems().get(i).getQuantite()),PdfPCell.ALIGN_CENTER ));
            //table.addCell(getCell(Double.toString(tableView1.getItems().get(i).getPrixTotale()),PdfPCell.ALIGN_CENTER ));
       //t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        /*PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 2"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 3"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        table.addCell(c.getDescription());
        
        //table.addCell("1.1");
        //table.addCell("1.2");
        //table.addCell("2.1");
        //table.addCell("2.2");
        //table.addCell("2.3");*/


        

        
        }
        table1.addCell(getCell(String.valueOf(prixTotale1),PdfPCell.ALIGN_CENTER ));
        document.add(table);
        //table1.addCell(getCell(String.valueOf(prixTotale/2),PdfPCell.ALIGN_CENTER ));
        document.add(table1);
        

        Paragraph p3 = new Paragraph("Nous espérons vous revoir dans les plus bref délai");    
        Paragraph p4 = new Paragraph("                                                                                                         Merci pour votre confiance"); 
        
        document.add(p5);
        document.add(p5);
        document.add(p5);
        document.add(p3);
        document.add(p4);
        

        
           /* for(Commandable c : panier) {
                Paragraph p = new Paragraph();
                p.add(c.getNom());
                p.add(c.getDescription());
                //p.add(c.getQuantite());
                p.add(c.getType());                                         
                        //System.out.println(lc.getIdProduit());
            } */
            //document.add((panier);
 }       
        catch(Exception e){
            System.out.println(e);
        }
        document.close();
        commande.setPrixTotal((int)prixTotale);
        commande.setDateValidation(new Date().toString());
        commandeService.modifierCommande(commande);
        panier.clear();
        tableView1.setItems(FXCollections.observableArrayList(panier));

        Alert alert = new Alert(AlertType.CONFIRMATION);
        
alert.setTitle("Panier validé");
alert.setHeaderText("Votre Commande a été enregistré, elle sera traitée dans les plus bref delai, Merci pour votre confance ! ");
alert.setContentText("Que voulez vous faire maintenant ?");

ButtonType buttonTypeOne = new ButtonType("Retourner vers la boutique !");
ButtonType buttonTypeTwo = new ButtonType("Voulez-vous voir nos offres ?");
ButtonType buttonTypeThree = new ButtonType("Consulter vos commande");
ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);



alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == buttonTypeOne){
    Parent frontPage = null; 
            try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Boutique.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
} else if (result.get() == buttonTypeTwo) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/NotreOffre.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
} else if (result.get() == buttonTypeThree) {
            Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/MesCommandes.fxml"));
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

    @FXML
    private void ModifierAction(ActionEvent event) {
        
    }
    private void Recette(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Recette.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
    }

    private void gestedumois(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/GesteFront.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
    }

    private void reclamation(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Reclamation.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
    }
    private PdfPCell getCell(String text, int alignment) {
    PdfPCell cell = new PdfPCell(new Phrase(text));
    cell.setPadding(0);
    cell.setHorizontalAlignment(alignment);
    cell.setBorder(PdfPCell.NO_BORDER);
    return cell;
}    
}
