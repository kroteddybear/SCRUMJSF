package Tools;


import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.*;                  
//Packages JDBC standard
//BigDecimal et BigInteger classes.
//Extensions Oracle à JDBC. (optionnel)
//OracleDataSource


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jeremygillet
 */
public class ConnectBDD {
    private Connection myConnexion;
    private Statement myStatement;
    
    private final String MYURL = "jdbc:oracle:thin:@//192.168.24.3/pfpbs";
    private final String MYUSER= "gp1";
    private final String MYPASSWORD= "gp1";
    
    
    public ConnectBDD(){
        if (testDriver()) {
            try {
                myConnexion = DriverManager.getConnection(MYURL, MYUSER, MYPASSWORD);
                myStatement = myConnexion.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectBDD.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        else
        {
            System.out.println("testdriver false");
        }

    }

    public boolean testDriver() {
        //   Chargement du driver JDBC pour MySQL */
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("Le driver n'a pas été chargé");
            return false;
        }
    
    }
    
public Connection getMyConnexion() {
        return myConnexion;
    }

    public Statement getMyStatement() {
        return myStatement;
    }
    
    public void close() {
        try {
            if (this.myStatement != null) {
                this.myStatement.close();
            }
            if (this.myConnexion != null) {
                this.myConnexion.close();
            }
        } catch (SQLException ex) {
            System.out.println("SQL connection fermeture failed !!!");
        }
    }

    public PreparedStatement prepareStatement(String insert_into_ConnexionID_CLIENT_MAIL_Login) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
     
    
}
