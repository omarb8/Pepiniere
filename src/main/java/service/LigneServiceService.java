/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entits.LigneService;
import Iservice.ILigneService;
import entits.Commande;
import entits.Produit;
import entits.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Singleton;
import entits.ObjetPanierService;

/**
 *
 * @author abdelli
 */
public class LigneServiceService implements ILigneService {
   Connection c = Singleton
           .getInstancesingleton()
           .getConnection();
    Statement ste;
    //PreparedStatement pstm;
public LigneServiceService() {
               try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
}

    @Override    
    public int creerLigneService(LigneService ls) {
        try {
            String req1="INSERT INTO `ligne_service`(`DateDebut`, `DateFin`,`IdUser`,`IdCommande`) VALUES (?,?,?,?)" ;
            PreparedStatement pstm = c.prepareStatement(req1,Statement.RETURN_GENERATED_KEYS);
                    pstm.setString(1,ls.getDateDebut());
                    pstm.setString(2,ls.getDateFin());
                    pstm.setInt(3,ls.getIdUser().getId());
                    pstm.setInt(4,ls.getIdCommande().getId());
                    pstm.executeUpdate();                     
                    ResultSet generatedKeys = pstm.getGeneratedKeys();
                    generatedKeys.next();
                    return (int) generatedKeys.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0 ;
}

    @Override
    public void modifierLigneService(LigneService ls) {
        String req2="UPDATE ligne_service SET DateDebut=?,DateFin=? WHERE id =?" ; 
        try {
            PreparedStatement pstm = c.prepareStatement(req2);
                    pstm.setString(1,ls.getDateDebut());
                    pstm.setString(2,ls.getDateFin());
                    pstm.setInt(3,ls.getId());
            pstm.executeUpdate();            
            
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerLigneService(LigneService ls){
              try {
            String req3="delete from ligne_service where"
                    + " id=?";
       
      PreparedStatement ps = c.prepareStatement(req3);
            ps.setInt(1, ls.getId());
            //ps.setPanid(1, p.getPanid());
            ps.executeUpdate();
         
           
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<LigneService> afficherLigneService() {
          
      List<LigneService> ligneservices = new ArrayList<>();
      entits.LigneService ls = null ;
      String req4="select * from ligne_service";
      //String req4 = "select  from  "
      try {
         
         
          ResultSet res=  ste.executeQuery(req4);
          while (res.next()) { 
              ls = new entits.LigneService();
                      ls.setId( res.getInt("id"));
                      ls.setDateDebut( res.getString("DateDebut") );
                      ls.setDateFin(res.getString("DateFin"));   
                      //p.setIdUser(res.get("idUser"));
              ligneservices.add(ls);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return ligneservices;  
    }
    
       @Override
    public List<LigneService> getLigneService(Commande commande) {
               List<LigneService> getLigneService = new ArrayList<>();
      LigneService ls = null ;
      String req4="select * from ligne_service l join commande c on l.IdCommande = c.id join user u on l.IdUser = u.id where c.id=?";
      //String req4 = "select  from  "
      try {
          PreparedStatement ste = c.prepareStatement(req4);
          ste.setInt(1, commande.getId());
          ResultSet res=  ste.executeQuery();
          
          while (res.next()) { 
              ls = new LigneService();
              User user = new User();         
              
                      user.setId( res.getInt("u.id"));
                      user.setUsername(res.getString("u.username"));
                      user.setRoles(res.getString("u.roles"));
                      ls.setDateDebut(res.getString("dateDebut"));
                      ls.setDateFin(res.getString("dateFin"));
                      ls.setIdUser(user);
              getLigneService.add(ls);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return getLigneService;
    }
    }

