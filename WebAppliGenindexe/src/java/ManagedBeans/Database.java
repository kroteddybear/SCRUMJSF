package ManagedBeans;

/**
 * This class manage all the methods of the different classes which interact
 * with the database.
 */
import Tools.ConnectBDD;
import Tools.DateTools;
import beans.Adress;
import beans.Analysis;
import beans.Animals;
import beans.Category;
import beans.Customers;
import beans.Orders;
import beans.Samples;
import beans.Species;
import beans.Storage;
import beans.Types_analysis;
import beans.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class Database {

    private Animals animal;

    private Samples sample;

    private Types_analysis typeAna;

    private Analysis analysis;

    private Users user;

    private Date d1;

    private Date d2;

    private Customers customer;

    private Orders order;
    

    /**
     * The adress of a customer.
     */
    private Adress adress;

    public Storage storage;

    private ConnectBDD b;
    private Connection con;

    public Database() {
        // Bouml preserved body begin 00043002
        b = new ConnectBDD();
        con = b.getMyConnexion();

        // Bouml preserved body end 00043002
    }
    
    public void Close(){
        b.close();
    }

    public List<Animals> searchAnimal(String specie) throws SQLException {
        List<Animals> list = new ArrayList<>();
        if (con == null) {
            throw new SQLException("Can't get database connection");
        }
        PreparedStatement ps;
        ps = con.prepareStatement("select * from Animal natural join Espece where Nom_Espece = " + specie + "");
        //get customer data from database
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            Animals pAnimal = new Animals();
            DateTools d = new DateTools();
            pAnimal.setNumberBirthday(d.dateSQLToJava(result.getDate("Date_Naissance")));
            pAnimal.setNom(result.getString("Nom_Animal"));
            pAnimal.setSpecie(specie);
            list.add(pAnimal);
        }
        return (list);
        // Bouml preserved body end 00043282
    }
    
//rechercher selon un id_client de tout ces animaux
        public List<Animals> getListAnimalCustomer(Integer id_customer,String chaine) throws SQLException {
        List<Animals> listA = new ArrayList<>();
        if (con == null) {
            throw new SQLException("Can't get database connection");
        }
        chaine+="%";
        PreparedStatement ps;
        ps = con.prepareStatement("select * from Animal WHERE nom_animal=" + chaine +" and ID_CLIENT="+id_customer);
        //get customer data from database
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            Animals pAnimals= new Animals();
            pAnimals.setNom(result.getString("NOM_ANIMAL"));
            pAnimals.setID(result.getInt("ID_CLIENT"));
            
            listA.add(pAnimals);
        }
        return (listA);
        // Bouml preserved body end 000236C5
    }
        
        public JList getJListAnimalCustomer(Integer id_customer, String chaine) throws SQLException {
        JList jList = new JList();
        DefaultListModel dlm = new DefaultListModel();
        // Bouml preserved body begin 000236C5
        if (con == null) {
            throw new SQLException("Can't get database connection");
        }
        
        if (chaine.length()==0){
            chaine="";
                    }
        else{
            chaine="nom_animal like '"+chaine+"%' and ";
        }
        
        PreparedStatement ps;
        ps = con.prepareStatement("select * from Animal WHERE " + chaine +" ID_CLIENT="+id_customer);
        //get animal data from database
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            Animals pAnimals = new Animals();
            pAnimals.setNom(result.getString("NOM_ANIMAL"));
            pAnimals.setID(result.getInt("ID_ANIMAL"));
            dlm.addElement(pAnimals.getID()+": "+pAnimals.getNom());
        }
        jList.setModel(dlm);
        return (jList);
        // Bouml preserved body end 000236C5
    }  
        
    /**
     *
     * @param id_animal id de l'animal selectione si id_animal=0 methode classique
     * @return liste d'animal du même propriétaire et de la même espece
     * @throws SQLException
     */
    public JList getJListAnimalCustomer(Integer id_animal) throws SQLException {
        JList jList = new JList();
        DefaultListModel dlm = new DefaultListModel();
        // Bouml preserved body begin 000236C5
        if (con == null) {
            throw new SQLException("Can't get database connection");
        }
        
        
        PreparedStatement ps;
        PreparedStatement ps1;
        
        ps= con.prepareStatement("SELECT ID_CLIENT, ID_ESPECE FROM ANIMAL WHERE ID_ANIMAL='" + id_animal + "'");
        //ps.setInt(1, id_animal );
        ResultSet result = ps.executeQuery();
        int id_espece = 0;
        int id_client = 0;
        while (result.next()) {
        id_espece =  result.getInt("ID_ESPECE");
        id_client = result.getInt("ID_CLIENT");
        }
        
        if (id_animal==null){
            return getJListAnimalCustomer(id_client,"");
        }
        else{
        
        if (result!= null){
        ps1= con.prepareStatement("SELECT * FROM Animal WHERE ID_CLIENT='"+id_client+"' AND ID_ESPECE='"+id_espece+"' AND ID_ANIMAL !="+id_animal+"");
        //get animal data from database
        ResultSet result1 = ps1.executeQuery();
        while (result1.next()) {
            Animals pAnimals = new Animals();
            pAnimals.setNom(result1.getString("NOM_ANIMAL"));
            pAnimals.setID(result1.getInt("ID_ANIMAL"));
            dlm.addElement(pAnimals.getID()+": "+pAnimals.getNom());
        }
          }
        jList.setModel(dlm);
        return (jList);
        }
        // Bouml preserved body end 000236C5
    } 

    /**
     * This function permits to get the user that use this session.
     */
    public Users getUser() {
        // Bouml preserved body begin 00043502
        return user;
        // Bouml preserved body end 00043502
    }

    public List<Customers> getListCustomers() throws SQLException {
        // Bouml preserved body begin 000234C5
        List<Customers> listC = new ArrayList<>();
        if (con == null) {
            throw new SQLException("Can't get database connection");
        }
        PreparedStatement ps;
        ps = con.prepareStatement("select * from Client natural join Adresse");
        //get customer data from database
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            Customers pCustomers = new Customers();
            Adress pAdress = new Adress();
            pAdress.setCity(result.getString("Ville"));
            pAdress.setCountry(result.getString("Pays"));
            pAdress.setNumber(result.getInt("Num_Rue"));
            pAdress.setStreet(result.getString("Nom_Rue"));
            pAdress.setZipCode(result.getInt("CP"));
            pCustomers.setAdress(pAdress);
            pCustomers.setEmail(result.getString("Mail"));
            pCustomers.setID(result.getInt("ID_Client"));
            pCustomers.setName(result.getString("Nom_Client"), result.getString("Prenom_Client"));
            pCustomers.setPhone(result.getString("Tel"));
            listC.add(pCustomers);
        }
        return (listC);
        // Bouml preserved body end 000234C5
    }
    
    /**
     * methode precedente avec le nom du client en paramètre 
     * @param name nom 
     * @return liste des clients qui correspondent au critere de recherche
     * @throws SQLException
     */
    public JList getListCustomers(String name) throws SQLException {
        System.out.println("methode getListCustomers");
        // Bouml preserved body begin 000234C5

        JList jList = new JList();
        DefaultListModel dlm = new DefaultListModel();

        if (con == null) {
            throw new SQLException("Can't get database connection");
        }
        name += '%';
        PreparedStatement ps;
        ps = con.prepareStatement("SELECT * FROM CLIENT WHERE NOM_CLIENT LIKE '" + name + "'");
        //get customer data from database
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            String Name = result.getString("id_client");
            Name += ": ";
            Name += result.getString("Nom_Client");
            Name += ' ';
            Name += result.getString("Prenom_Client");
            Name += ' ';
            Name += result.getString("CP");
            Name += ' ';
            Name += result.getString("Ville");
            Name += ' ';
            dlm.addElement(Name);
            jList.setModel(dlm);
        }
        return (jList);
        // Bouml preserved body end 000234C5
    }

    

    public Customers searchCustomerName(String name) throws SQLException {
        // Bouml preserved body begin 00023545
        Customers client = new Customers();
        PreparedStatement ps = con.prepareStatement("Select * FROM Client where nom_client=" + name);
        ResultSet result = ps.executeQuery();
        client.setName(result.getString("Nom_Client"), result.getString("Prenom_Client"));
        client.setID(result.getInt("ID_Client"));
        client.setAdress(new Adress(result.getInt("Cdp"), result.getString("Ville")));
        client.setPhone(result.getString("Tel"));
        if (name.equals(client.getLastName())) {
            return client;
        } else {
            Customers cust = new Customers("jean", "dupond", 86000, "Poitiers", "090909", "090909", "090909","jdupond","1234",  1);
            return cust;
        }
        // Bouml preserved body end 00023545
    }

    public Customers searchCustomerID(int ID) throws SQLException {
        // Bouml preserved body begin 000235C5
        Customers client = new Customers();
        PreparedStatement ps = con.prepareStatement("Select * FROM Client where ID_Client=" + ID);
        ResultSet result = ps.executeQuery();
        client.setName(result.getString("Nom_Client"), result.getString("Prenom_Client"));
        client.setID(result.getInt("ID_Client"));
        client.setAdress(new Adress(result.getInt("Cdp"), result.getString("Ville")));
        client.setPhone(result.getString("Tel"));
        if (client.getID() == ID) {
            return client;
        } else {
            Customers cust = new Customers("jean", "dupond", 86000, "Poitiers", "090909", "090909", "090909","jdupond","1234", 1);
            return cust;
        }
        // Bouml preserved body end 000235C5
    }

    public String saveCustomer(Customers cust) {
        // Bouml preserved body begin 00023645
        try {
            Statement state = b.getMyStatement();
            String query = "Insert Into Client(Nom_Client, Prenom_Client, Num_Rue, Nom_Rue, CP, Ville, Tel, Tel_Port, Fax, Mail, Pays, Login, Mdp) Values('" + cust.getFirstName() + "', '" + cust.getLastName() + "', '" + Integer.toString(cust.getAdress().getNumber()) + "', '" + cust.getAdress().getStreet() + "', '" + cust.getAdress().getZipCode() + "', '" + cust.getAdress().getCity() + "', '" + cust.getPhone() + "', '" + cust.getCellular() + "', '" + cust.getFax() + "', '" + cust.getEmail() + "', '" + cust.getAdress().getCountry()+"', '"+cust.getLogin()+"', '"+cust.getMotDePasse()+"')";
                state.executeUpdate(query);
                return "success";

//            }
        } catch (SQLException ex) {
            System.out.println("SQLException saveCustomer: " + ex.getMessage());
            System.out.println("SQLState saveCustomer: " + ex.getSQLState());
            System.out.println("VendorError saveCustomer: " + ex.getErrorCode());
            return "failed";
        }
        // Bouml preserved body end 00023645
    }
    
    public String saveCustomer(Customers cust, Adress entreprise, String nom_ent, String nom_contact, String nom_dept, String secteur_activite) {
        try{
            Statement state = b.getMyStatement();

            /*
            Insertion de l'entreprise
            */
            String queryInsertEnt = ("Insert Into Entreprise(NomEntr, depSpecial, Secteur_Activite, Nom_Rue_Fac, NUM_RUE_FAC, CP_FAC, Ville_FAC, Contact_Fac) Values('"+nom_ent+"','"+nom_dept+"', '"+secteur_activite+"', '"+entreprise.getStreet()+"', '"+Integer.toString(entreprise.getNumber())+"', '"+Integer.toString(entreprise.getZipCode())+"', '"+entreprise.getCity()+"', '"+nom_contact+"')");
            state.executeQuery(queryInsertEnt);
            
            
            /*
            Recupere le dernier ID d'entreprise
            */
            String queryGetId = ("select MAX(ID_ENTR) from entreprise ");
            PreparedStatement ps=con.prepareStatement(queryGetId);
            ResultSet ID = ps.executeQuery();
            ID.next();
            /*
            insertion du client avec l'id d'entreprise.
            */
            String queryInsertCustomer = "Insert Into Client(ID_ENTR, Nom_Client, Prenom_Client, Num_Rue, Nom_Rue, CP, Ville, Tel, Tel_Port, Fax, Mail, Pays) Values('"+ID.getString("MAX(ID_ENTR)")+"', '" + cust.getFirstName() + "', '" + cust.getLastName() + "', '" + Integer.toString(cust.getAdress().getNumber()) + "', '" + cust.getAdress().getStreet() + "', '" + cust.getAdress().getZipCode() + "', '" + cust.getAdress().getCity() + "', '" + cust.getPhone() + "', '" + cust.getCellular() + "', '" + cust.getFax() + "', '" + cust.getEmail() + "', '" + cust.getAdress().getCountry()+"')";
            state.executeUpdate(queryInsertCustomer);
            
              if(cust.getEmail()==null){    
            
            /*
            Recuperation de l'id du client ainsi insere
            */
            queryGetId = ("select MAX(ID_CLIENT) from CLIENT ");
            ps=con.prepareStatement(queryGetId);
            ID = ps.executeQuery();
            ID.next();
            
            String queryInssertConnexion = "Insert into connexion(Login, Mdp, Id_Client) Values('"+cust.getLogin()+"', '"+cust.getMotDePasse()+"', '"+ID.getString("MAX(ID_CLIENT)")+"')";
            state.executeQuery(queryInssertConnexion);
              }
            
            return "success";
        }
        catch (SQLException ex) {
            System.out.println("SQLException saveCustomer: " + ex.getMessage());
            System.out.println("SQLState saveCustomer: " + ex.getSQLState());
            System.out.println("VendorError saveCustomer: " + ex.getErrorCode());
            return "failed";
        }
    }

    public List<Types_analysis> getListAnalysisType() {
        // Bouml preserved body begin 000236C5
        List<Types_analysis> listTA = new ArrayList<>();
        try{
        PreparedStatement ps;
        ps = con.prepareStatement("select * from TypeAnalyse ");
        //get customer data from database
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            Types_analysis pTypes_analysis = new Types_analysis();
            pTypes_analysis.setPrice(result.getInt("Prix_Unitaire"));
            pTypes_analysis.setType(result.getString("Type_Analy"));
            listTA.add(pTypes_analysis);
        }
        return (listTA);
        }
        catch(SQLException ex){
            System.out.println("SQLException getListAnalysisType: " + ex.getMessage());
            System.out.println("SQLState getListAnalysisType: " + ex.getSQLState());
            System.out.println("VendorError getListAnalysisType: " + ex.getErrorCode());
            return null;
        }
        
    
        // Bouml preserved body end 000236C5
    }

    public Analysis searchAnalysis(Types_analysis type) {
        // Bouml preserved body begin 00023745
        if (this.analysis.getTypeAnalysis() == type) {
            return this.analysis;
        } else {
            Analysis ana = new Analysis(2, typeAna, d2);
            return ana;
        }
        // Bouml preserved body end 00023745
    }

    public Analysis searchAnalysisID(int ID) {
        // Bouml preserved body begin 000237C5
        if (this.analysis.getID() == ID) {
            return this.analysis;
        } else {
            Analysis ana = new Analysis(2, typeAna, d2);
            return ana;
        }
        // Bouml preserved body end 000237C5
    }

    public void saveAnalysis(Analysis analysis) {
        // Bouml preserved body begin 00023845
        if (analysis.getID() == this.analysis.getID()) {
            this.analysis = analysis;
        } else {
            System.out.println("new data record");
        }
        // Bouml preserved body end 00023845
    }

    public Types_analysis searchTypesAnalysis(String name) throws SQLException {
        Types_analysis pTypes_analysis = new Types_analysis();
        if (con == null) {
            throw new SQLException("Can't get database connection");
        }
        PreparedStatement ps;
        ps = con.prepareStatement("select * from TypeAnalyse where Type_Analy = " + name +"");
        //get customer data from database
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            
            pTypes_analysis.setPrice(result.getInt("Prix_Unitaire"));
            pTypes_analysis.setType(result.getString("Type_Analy"));
        }
        return pTypes_analysis;
    }

    public void saveAnalysisType(Types_analysis typeAnalysis) {
        // Bouml preserved body begin 00023945
        if (typeAnalysis.getType().equals(this.typeAna.getType())) {
            this.typeAna = typeAnalysis;
        } else {
            System.out.println("new data record");
        }
        // Bouml preserved body end 00023945
    }

    public String saveBddCustomer() throws SQLException {
        if (con == null) {
            throw new SQLException("Can't get database connection");
        }
        //etu.saveNewAdherent();
        try {
            /* Récupération des paramètres d'URL saisis par l'utilisateur */
            String paramfirstName = this.customer.getFirstName();
            String paramLastName = this.customer.getLastName();
            String paramEmail = this.customer.getEmail();
            String paramPhone = this.customer.getPhone();
            int paramID = this.customer.getID();

            /* Création de l'objet gérant les requêtes préparées */
            PreparedStatement ps = con.prepareStatement("INSERT INTO Client(ID_CLIENT, Nom_Client, Tel) VALUES (?,?,?)");
            /*
             * Remplissage des paramètres de la requête grâce aux méthodes
             * setXXX() mises à disposition par l'objet PreparedStatement.
             */
            ps.setInt(1, paramID);
            ps.setString(2, paramLastName);
            ps.setString(3, paramPhone);
            /* Exécution de la requête */
            int statut = ps.executeUpdate();
            return "success";
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return "failed";
        }
    }
    
    /**
     * Méthode renvoyant false si l'espece existe deja dans la BDD et true sinon
     * @param specie
     * @return 
     * @throws java.sql.SQLException
     */
    public Boolean checkSpecie (String specie) throws SQLException{
        Boolean result = true;
        int paramNb = 0;
        PreparedStatement ps0 = con.prepareStatement("select count(*) as nb from ESPECE where NOM_ESPECE = '" + specie + "'");
        ResultSet result0 = ps0.executeQuery();
        while (result0.next()) {
            paramNb = (result0.getInt("nb"));
        }
        if (paramNb > 0) {
            result = false;
        }
        return result;
    }
    
    /**
     * Méthode permettant d'enregistrer une nouvelle espèce
     * @param specie
     * @param pcat
     * @return "success" si l'espèce est sauvegardée, sinon "failed"
     * @throws java.sql.SQLException
     */
    public String saveSpecie(String specie, String pcat) throws SQLException {
        if(checkSpecie(specie)){
        System.out.println(specie);
        System.out.println(pcat);
        Category paramCat = new Category(pcat);
        
        PreparedStatement ps0 = con.prepareStatement("select * from CATEGORIE where NOM_CATEGORIE = '" + pcat + "'");
        System.out.println("ps0 ok");
        ResultSet result0 = ps0.executeQuery();
        System.out.println("rslt0 ok");
        while (result0.next()) {
            paramCat.setID(result0.getInt("ID_CATEGORIE"));
        }
        System.out.println("while ok");
        Species paramSpecie = new Species(specie, paramCat);
        
        System.out.println(paramSpecie.getCategory().getID());
        
        if (con == null) {
            throw new SQLException("Can't get database connection");
        }
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Espece(Nom_Espece, ID_CATEGORIE) VALUES (?,?)");
            ps.setString(1, paramSpecie.getNameSpecie());
            ps.setInt(2, paramSpecie.getCategory().getID());
            /* Exécution de la requête */
            int statut = ps.executeUpdate();
            return "success";
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return "failed";
        }
        }
        else {
            return "failed";
        }
    }
    
    /**
     * Methode qui renvoie une liste de catégories contenant les catégories existante dans la BDD
     * @return 
     * @throws java.sql.SQLException
     */
    public List<Category> getListCategory() throws SQLException {
        // Bouml preserved body begin 000236C5
        List<Category> listC = new ArrayList<>();
        if (con == null) {
            throw new SQLException("Can't get database connection");
        }
        PreparedStatement ps;
        ps = con.prepareStatement("select * from Categorie");
        //get customer data from database
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            Category pCategory = new Category();
            pCategory.setID(result.getInt("ID_CATEGORIE"));
            pCategory.setNameCategory(result.getString("NOM_CATEGORIE"));
            listC.add(pCategory);
        }
        return (listC);
        // Bouml preserved body end 000236C5
    }
   
    /**
     * Methode qui renvoie une Jlist contenant les catégories existante dans la BDD
     * @return 
     * @throws java.sql.SQLException
     */
    public JList getJListCategory() throws SQLException {
        JList jList = new JList();
        DefaultListModel dlm = new DefaultListModel();
        // Bouml preserved body begin 000236C5
        if (con == null) {
            throw new SQLException("Can't get database connection");
        }
        PreparedStatement ps;
        ps = con.prepareStatement("select * from Categorie");
        //get customer data from database
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            Category pCategory = new Category();
            pCategory.setID(result.getInt("ID_CATEGORIE"));
            pCategory.setNameCategory(result.getString("NOM_CATEGORIE"));
            dlm.addElement(pCategory.getNameCategory());
        }
        jList.setModel(dlm);
        return (jList);
        // Bouml preserved body end 000236C5
    }
     
 
    public String saveBddUser() throws SQLException {
        ConnectBDD con = new ConnectBDD();
        Connection b = con.getMyConnexion();
        if (b == null) {
            throw new SQLException("Can't get database connection");
        }
        //etu.saveNewAdherent();
        try {
            /* Récupération des paramètres d'URL saisis par l'utilisateur */
            String paramIdentifiant = this.user.getLogin();
            String paramMotDePasse = this.user.getPassword();
            /* Création de l'objet gérant les requêtes préparées */
            PreparedStatement ps = b.prepareStatement("INSERT INTO Connexion(ID_CLIENT, MAIL, Login, Mdp) VALUES (1,'teddy@gmail.com',?,?)");
            /*
             * Remplissage des paramètres de la requête grâce aux méthodes
             * setXXX() mises à disposition par l'objet PreparedStatement.
             */
            ps.setString(1, paramIdentifiant);
            ps.setString(2, paramMotDePasse);
            /* Exécution de la requête */
            int statut = ps.executeUpdate();
            return "success";
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return "failed";
        }

    }

    
    
    /**
     * Retourne une liste de string contenant les nom de categorie dans la BDD
     * @return
     * @throws SQLException 
     */
    public List<String> getListCategorie() throws SQLException{
    	List<String> LC = new ArrayList<>();
    	PreparedStatement ps;
    	ps=con.prepareStatement("Select * from Categorie");
    	ResultSet result = ps.executeQuery();
    	while(result.next()){
    		LC.add(result.getString("Nom_categorie"));
    	}
    	return LC;
    }
    
    /**
     * Methode de verif doublons customer
     * @throws SQLException 
     * @return true si pas de doublon, false sinon.
     */
    
    /**
     * Methode de verif doublons customer
     *
     * @throws SQLException
     * @return true si pas de doublon, false sinon.
     */
    public Boolean IsDoublonCustomer(String nom, String prenom, Adress adresse) {
        try {
            PreparedStatement ps;
            ps = con.prepareStatement("Select Nom_Client, Prenom_Client, Num_Rue, Nom_Rue, CP, Ville From Client");
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                if (nom.equals(result.getString("Nom_Client"))) {
                    if (prenom.equals(result.getString("Prenom_Client"))) {
                        if (adresse.getCity().equals(result.getString("Ville"))) {
                            if (adresse.getZipCode() == Integer.parseInt(result.getString("CP"))) {
                                if (adresse.getStreet().equals(result.getString("Nom_Rue"))) {
                                    if (adresse.getNumber() == Integer.parseInt(result.getString("Num_Rue"))) {
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return true;
        } catch (SQLException ex) {
            System.out.println("SQLException IsDoublon: " + ex.getMessage());
            System.out.println("SQLState IsDoublon: " + ex.getSQLState());
            System.out.println("VendorError IsDoublon: " + ex.getErrorCode());
            return false;
        }
    }
  
}

    



