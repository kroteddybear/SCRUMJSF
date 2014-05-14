/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Tools.ConnectBDD;
import beans.Customers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "utilisateurM")
@SessionScoped
/**
 *
 * @author teddy
 */
public class UtilisateurManaged {

    private Customers selectedUtilisateur;
    private boolean validationComplete = false;

    public UtilisateurManaged(){
        selectedUtilisateur = new Customers();
    }
    public boolean isValidationComplete() {
        return validationComplete;
    }

    public void setValidationComplete(boolean validationComplete) {
        this.validationComplete = validationComplete;
    }

    public Customers getSelectedUtilisateur() {
        return selectedUtilisateur;
    }

    public void setSelectedUtilisateur(Customers selectedUtilisateur) {
        this.selectedUtilisateur = selectedUtilisateur;
    }

    public String connexion() throws SQLException {
        ConnectBDD b = new ConnectBDD();
        Connection con = b.getMyConnexion();
        if (con == null) {
            throw new SQLException("Can't get database connection");
        }
        PreparedStatement ps = con.prepareStatement("select count(*) as nbutil from CONNEXION where LOGIN = '" + this.selectedUtilisateur.getLogin() + "' and MDP = '" + this.selectedUtilisateur.getMotDePasse() + "'");
        //get customer data from database
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            int nbutil = result.getInt("nbutil");
            if (nbutil > 0) {
                this.validationComplete = true;
                
            }
        }
        if (this.validationComplete) {
            try{
        if (con == null) {
            throw new SQLException("Can't get database connection");
        }
        PreparedStatement ps2;
        ps2 = con.prepareStatement("SELECT NOM_CLIENT, PRENOM_CLIENT FROM Client NATURAL JOIN Connexion WHERE LOGIN='" + this.selectedUtilisateur.getLogin() + "'");
        //get customer data from database
        ResultSet result2 = ps2.executeQuery();
        while (result2.next()) {
            System.out.println(result2.getString("NOM_CLIENT"));
            System.out.println(result2.getString("PRENOM_CLIENT"));
        this.selectedUtilisateur.setName((result2.getString("NOM_CLIENT")), (result2.getString("PRENOM_CLIENT")));
        }
        }
        catch (SQLException ex) {
            System.out.println("SELECT NOM_CLIENT, PRENOM_CLIENT FROM Client NATURAL JOIN Connexion WHERE LOGIN='" + this.selectedUtilisateur.getLogin() + "'");
            System.out.println("SQLException searchSample " + ex.getMessage());
            System.out.println("SQLState searchSample: " + ex.getSQLState());
            System.out.println("VendorError searchSample: " + ex.getErrorCode());
        }
            b.close();
            return "tableauBord";
        } else {
            b.close();
            return "index";
        }
        
    }

    public String deconnexion() throws SQLException {
        this.selectedUtilisateur = new Customers();
            return "index";
    }
    
}
