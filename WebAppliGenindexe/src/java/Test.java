
import Tools.ConnectBDD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jeremygillet
 */
public class Test {
  public static void main(String[] args){
      
      
        ConnectBDD b = new ConnectBDD();

        try {

         //---------------------------------------------------------------------------------   
         // requete "en live"
         /* Exécution d'une requête de modification de la BD (INSERT, UPDATE, DELETE, CREATE, etc.). */
         b.getMyStatement().executeUpdate("UPDATE CLIENT SET Actif=0 WHERE Identifiant=0"); 
            
          /* Exécution d'une requête d'affichage de la BD (SELECT). */   
         b.getMyStatement().executeQuery("SELECT * FROM ARC");
         
         //---------------------------------------------------------------------------------
         //requete préparées
         Connection con = b.getMyConnexion();
         PreparedStatement ps = con.prepareStatement("SELECT NOM_CLIENT FROM CLIENT");
         
         
         ResultSet result = ps.executeQuery();
         
         while (result.next()){
             
             String nom = result.getString("NOM_CLIENT");
             System.out.println(nom);
   
         }
         
         
         
         
         
         
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
      
      
      
      
  }      
        
        
        }