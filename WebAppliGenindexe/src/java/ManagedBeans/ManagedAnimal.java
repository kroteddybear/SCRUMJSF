/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBeans;

import Tools.ConnectBDD;
import Tools.DateTools;
import beans.Animals;
import beans.Species;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author Teddy
 */
public class ManagedAnimal {
    private Animals selectedAnimal;



    public ManagedAnimal() {
    }
    
    /**
     * Methode qui sauvegarde un nouvel animal
     * @param animal
     * @param idClient
     * @param pSpe
     * @return 
     */
    public String saveAnimal(Animals animal, int idClient, String pSpe) {
        ConnectBDD b = new ConnectBDD();
        Connection con = b.getMyConnexion();
        System.out.println(pSpe);
        Species paramSpe = new Species(pSpe);
        DateTools d = new DateTools();
        if (checkAnimal(animal, idClient)){
        try {
        PreparedStatement ps0 = con.prepareStatement("select * from ESPECE where NOM_ESPECE = '" + pSpe + "'");
        ResultSet result0 = ps0.executeQuery();
        while (result0.next()) {
            paramSpe.setID(result0.getInt("ID_ESPECE"));
        }
            Statement state = b.getMyStatement();
            int sexe = 0;
            if (animal.getSexe()){sexe = 1;}
            String query = "Insert Into ANIMAL(NOM_ANIMAL, DATE_NAISSANCE, SEXE, ID_ESPECE, ID_CLIENT) Values('" + animal.getNom() + "', '" + d.dateJavaToSQL(animal.getNumberBirthday()) + "', " + sexe + ", " + paramSpe.getID() + ", " + idClient +")";
                state.executeUpdate(query, state.RETURN_GENERATED_KEYS);
                return "success";
        } catch (SQLException ex) {
            int sexe = 0;
            if (animal.getSexe()){sexe = 1;}
            System.out.println("Insert Into ANIMAL(NOM_ANIMAL, DATE_NAISSANCE, SEXE, ID_ESPECE, ID_CLIENT) Values('" + animal.getNom() + "', '" + d.dateJavaToSQL(animal.getNumberBirthday()) + "', " + sexe + ", " + paramSpe.getID() + ", " + idClient +")");
            System.out.println("SQLException saveAnimal celle la et pas une autre : " + ex.getMessage());
            System.out.println("SQLState saveAnimal: " + ex.getSQLState());
            System.out.println("VendorError saveAnimal: " + ex.getErrorCode());
            return "failed";
        }
        
        }
        else { return "failed"; }
    }
    
    /**
     * Methode qui regarde si l'animal existe déjà ou pas
     * @param animal
     * @param idClient
     * @return 
     */
    public Boolean checkAnimal(Animals animal, int idClient) {
        ConnectBDD b = new ConnectBDD();
        Connection con = b.getMyConnexion();
        Boolean rslt = false;
        DateTools d = new DateTools();
        try {
        int nbResult = 0;
        PreparedStatement ps1 = con.prepareStatement("select count(*) as resultCount from ANIMAL where NOM_ANIMAL = '" + animal.getNom() + "' and DATE_NAISSANCE = '" + d.dateJavaToSQL(animal.getNumberBirthday()) + "' and ID_CLIENT = " + idClient +"");
        ResultSet result1 = ps1.executeQuery();
        while (result1.next()) {
            nbResult = result1.getInt("resultCount");
        }
        if (nbResult == 0){rslt = true;}
        } catch (SQLException ex) {
            System.out.println("select count(*) as resultCount from ANIMAL where NOM_ANIMAL = '" + animal.getNom() + "' and DATE_NAISSANCE = '" + d.dateJavaToSQL(animal.getNumberBirthday()) + "' and ID_CLIENT = " + idClient +"");
            System.out.println("SQLException checkAnimal " + ex.getMessage());
            System.out.println("SQLState checkAnimal: " + ex.getSQLState());
            System.out.println("VendorError checkAnimal: " + ex.getErrorCode());
        }
        b.close();
        return (rslt);
        
    }
    
    /**
     * Methode qui renvoie une Jlist contenant les catégories existante dans la BDD
     * @return 
     */
    public JList getJListEspeces() {
        ConnectBDD b = new ConnectBDD();
        Connection con = b.getMyConnexion();
        JList jList = new JList();
        DefaultListModel dlm = new DefaultListModel();
        try{
        if (con == null) {
            throw new SQLException("Can't get database connection");
        }
        PreparedStatement ps;
        ps = con.prepareStatement("select * from ESPECE");
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            Species pSpecie = new Species();
            pSpecie.setNameSpecie(result.getString("NOM_ESPECE"));
            dlm.addElement(pSpecie.getNameSpecie());
        }
        jList.setModel(dlm);
        }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        b.close();
        return (jList);
    }
        /**
     * Methode qui renvoie une Jlist contenant les id_animal: nom_animal selon un liste contenant les id_des animaux existante dans la BDD
     * @return 
     */
    
    public JList getJListAnimals(List<Integer> laListe) {
        JList jList = new JList();
        DefaultListModel dlm = new DefaultListModel();
        
        if (laListe.size()!=0){
            ConnectBDD b = new ConnectBDD();
            Connection con = b.getMyConnexion();
            try{
                if (con == null) {
                throw new SQLException("Can't get database connection");
                }
        
        for(int i = 0; i < laListe.size(); i++){// On balaye la liste 
            PreparedStatement ps;
            ps = con.prepareStatement("select * from ANIMAL where ID_ANIMAL="+laListe.get(i));
            ResultSet result = ps.executeQuery();
            while (result.next()) {
            Animals pAnimals = new Animals();
            pAnimals.setNom(result.getString("NOM_ANIMAL"));
            pAnimals.setID(result.getInt("ID_ANIMAL"));
            dlm.addElement(pAnimals.getID()+": "+pAnimals.getNom());
            jList.setModel(dlm);
        }
        jList.setModel(dlm);
        }
            }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        b.close();
        
        
        }
        jList.setModel(dlm);
        return (jList);
        
    }
}
