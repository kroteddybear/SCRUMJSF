package ManagedBeans;

import Tools.ConnectBDD;
import beans.Customers;
import beans.Orders;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "orderM")
@SessionScoped
/**
 *
 * @author Teddy
 */
public class ManagedOrder {

    private List<Orders> lOrdersNotF;
    private Orders selectedOrder;
    private ManagedSample myManagedSample;

    public ManagedOrder() {
        lOrdersNotF = new ArrayList<>();
        lOrdersNotF = getListOrderNotFinished();
    }

    public ManagedOrder(Orders selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public List<Orders> getlOrdersNotF() {
        return lOrdersNotF;
    }

    public void setlOrdersNotF(List<Orders> lOrdersNotF) {
        this.lOrdersNotF = lOrdersNotF;
    }

    public ManagedSample getMyManagedSample() {
        return myManagedSample;
    }

    public void setMyManagedSample(ManagedSample myManagedSample) {
        this.myManagedSample = myManagedSample;
    }

    public Orders getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(Orders selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    /**
     * This function permits to list all the orders in the database.
     */
    public List<Orders> getListOrder() {
        ConnectBDD b = new ConnectBDD();
        Connection con = b.getMyConnexion();
        List<Orders> list = new ArrayList<Orders>();
        try {
            if (con == null) {
                throw new SQLException("Can't get database connection");
            }
            PreparedStatement ps;
            ps = con.prepareStatement("select * from COMMANDE");
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                Orders pOrder = new Orders();
                pOrder.setIdOrder(result.getInt("ID_COMMANDE"));
                list.add(this.selectedOrder);
            }
        }
        catch (SQLException ex) {
            System.out.println("ma requete");
            System.out.println("SQLException checkAnimal " + ex.getMessage());
            System.out.println("SQLState checkAnimal: " + ex.getSQLState());
            System.out.println("VendorError checkAnimal: " + ex.getErrorCode());
        }
        b.close();
        return (list);
    }

    /**
     * This function permits to list all the orders in the database.
     */
    public List<Orders> getListOrderNotFinished() {
        System.out.println("getListOrderNotFinished debug : ");
        ConnectBDD b = new ConnectBDD();
        Connection con = b.getMyConnexion();
        List<Orders> list = new ArrayList<Orders>();
        System.out.println("getListOrderNotFinished debug liste vide : ");
        try {
            if (con == null) {
                throw new SQLException("Can't get database connection");
            }
            PreparedStatement ps;
            System.out.println("getListOrderNotFinished ps ok : ");
            ps = con.prepareStatement("select ID_COMMANDE from COMMANDE natural join ECHANTILLON natural join TUBE natural join ANALYSE where RES_FINAL is NULL group by ID_COMMANDE ");
            ResultSet result = ps.executeQuery();
            System.out.println("before while : ");
            while (result.next()) {
                System.out.println("While : "+ result.getInt("ID_COMMANDE"));
                Orders pOrder = new Orders();
                pOrder.setIdOrder(result.getInt("ID_COMMANDE"));
//                pOrder.setDateOrder(result.getDate("DATE_COMMANDE"));
                list.add(pOrder);
            }
        }
        catch (SQLException ex) {
            System.out.println("select ID_COMMANDE, DATE_COMMANDE from COMMANDE natural join ECHANTILLON natural join TUBE natural join ANALYSE where RES_FINAL is NULL group by ID_COMMANDE");
            System.out.println("SQLException getListOrderNotFinished " + ex.getMessage());
            System.out.println("SQLState getListOrderNotFinished: " + ex.getSQLState());
            System.out.println("VendorError getListOrderNotFinished: " + ex.getErrorCode());
        }
        b.close();
        return (list);
    }
    
     /**
     * This function permits to list all the orders in the database.
     */
    public List<Orders> getListOrderNotFinished(Customers client) {
        System.out.println("getListOrderNotFinished debug : ");
        ConnectBDD b = new ConnectBDD();
        Connection con = b.getMyConnexion();
        List<Orders> list = new ArrayList<Orders>();
        System.out.println("getListOrderNotFinished debug liste vide : ");
        try {
            if (con == null) {
                throw new SQLException("Can't get database connection");
            }
            PreparedStatement ps;
            System.out.println("getListOrderNotFinished ps ok : ");
            ps = con.prepareStatement("select ID_COMMANDE from COMMANDE natural join ECHANTILLON natural join TUBE natural join ANALYSE natural join ANIMAL natural join CLIENT where ID_CLIENT = " + client.getID() + " and RES_FINAL is NULL group by ID_COMMANDE ");
            ResultSet result = ps.executeQuery();
            System.out.println("before while : ");
            while (result.next()) {
                System.out.println("While : "+ result.getInt("ID_COMMANDE"));
                Orders pOrder = new Orders();
                pOrder.setIdOrder(result.getInt("ID_COMMANDE"));
                try {
                    int idCommande = pOrder.getId();
                    if (con == null) {
                        throw new SQLException("Can't get database connection");
                    }
                    PreparedStatement ps2;
                    ps2 = con.prepareStatement("select count(*) as nbEch from ECHANTILLON where ID_COMMANDE = " + idCommande + "");
                    ResultSet result2 = ps2.executeQuery();
                    while (result2.next()) {
                        pOrder.setNumberSamples(result2.getInt("nbEch"));
                    }
                } catch (SQLException ex) {
                    System.out.println("");
                    System.out.println("SQLException getListOrderNotFinished " + ex.getMessage());
                    System.out.println("SQLState getListOrderNotFinished: " + ex.getSQLState());
                    System.out.println("VendorError getListOrderNotFinished: " + ex.getErrorCode());
                }
                list.add(pOrder);
            }
        }
        catch (SQLException ex) {
            System.out.println("select ID_COMMANDE from COMMANDE natural join ECHANTILLON natural join TUBE natural join ANALYSE natural join ANIMAL natural join CLIENT where ID_CLIENT = " + client.getID() + " and RES_FINAL is NULL group by ID_COMMANDE");
            System.out.println("SQLException getListOrderNotFinished " + ex.getMessage());
            System.out.println("SQLState getListOrderNotFinished: " + ex.getSQLState());
            System.out.println("VendorError getListOrderNotFinished: " + ex.getErrorCode());
        }
        b.close();
        return (list);
    }
    
    public int getNbEchTermine(Orders pOrder){
        ConnectBDD b = new ConnectBDD();
        Connection con = b.getMyConnexion();
        int res = 0;
        try {
                    if (con == null) {
                        throw new SQLException("Can't get database connection");
                    }
                    PreparedStatement ps2;
                    ps2 = con.prepareStatement("select count(ID_ECH) as nbEch from ECHANTILLON where ID_COMMANDE = " + pOrder.getId() + " and ID_ECH not in (select distinct ID_ECH as nbEch from ECHANTILLON natural join TUBE natural join ANALYSE where ID_COMMANDE = " + pOrder.getId() + " and RES_FINAL is NULL)");
                    ResultSet result2 = ps2.executeQuery();
                    while (result2.next()) {
                        res = (result2.getInt("nbEch"));
                    }
                } catch (SQLException ex) {
                    System.out.println("select count(ID_COMMANDE) as nbEch from ECHANTILLON natural join TUBE natural join ANALYSE where ID_COMMANDE = " + pOrder.getId() + " and RES_FINAL is NULL group by ID_COMMANDE");
                    System.out.println("SQLException getListOrderNotFinished " + ex.getMessage());
                    System.out.println("SQLState getListOrderNotFinished: " + ex.getSQLState());
                    System.out.println("VendorError getListOrderNotFinished: " + ex.getErrorCode());
                }
        return res;
    }
    
    public int getNbEchPasTermine(Orders pOrder){
        ConnectBDD b = new ConnectBDD();
        Connection con = b.getMyConnexion();
        int res = 0;
        try {
                    if (con == null) {
                        throw new SQLException("Can't get database connection");
                    }
                    PreparedStatement ps2;
                    ps2 = con.prepareStatement("select count (distinct ID_ECH) as nbEch from ECHANTILLON natural join TUBE natural join ANALYSE where ID_COMMANDE = " + pOrder.getId() + " and RES_FINAL is NULL");
                    ResultSet result2 = ps2.executeQuery();
                    while (result2.next()) {
                        res = (result2.getInt("nbEch"));
                    }
                } catch (SQLException ex) {
                    System.out.println("select count(ID_COMMANDE) as nbEch from ECHANTILLON natural join TUBE natural join ANALYSE where ID_COMMANDE = " + pOrder.getId() + " and RES_FINAL is NULL group by ID_COMMANDE");
                    System.out.println("SQLException getListOrderNotFinished " + ex.getMessage());
                    System.out.println("SQLState getListOrderNotFinished: " + ex.getSQLState());
                    System.out.println("VendorError getListOrderNotFinished: " + ex.getErrorCode());
                }
        return res;
    }
    
    /**
     * This function permits to search the order in the database that have the
     * customer in parameter.
     */
    public Orders searchOrder(Customers customer) {
        return (this.selectedOrder);
    }

    /**
     * This function permits to search the order in the database that has this
     * id.
     */
    public Orders searchOrder(int id) {
        return (this.selectedOrder);
    }

    /**
     * This function permits to save in the database the order in parameter.
     * @param order
     */
    public String saveOrder(Orders order) {
        ConnectBDD b = new ConnectBDD();
        Connection con = b.getMyConnexion();
        String sendRes = "failed";
        System.out.println("sendRes1 : " + sendRes);
        this.selectedOrder = order;
        String dateOrder = dateJavaToSQL(this.selectedOrder.getDateOrder());
        try{
            if (con == null) {
                throw new SQLException("Can't get database connection");
            }
            PreparedStatement ps = con.prepareStatement("INSERT INTO Commande(DATE_COMMANDE, DELAI_RAPIDE, ID_TYPE) VALUES (?,?,?)");
            ps.setString(1, dateOrder);
            ps.setInt(2, this.selectedOrder.getPriorityLevel());
            ps.setInt(3, searchIdTypeAnalyse(this.selectedOrder));
            int statut = ps.executeUpdate();
            b.close();
            sendRes = "success";
            System.out.println("sendRes2 : " + sendRes);
        }
        catch(SQLException ex) {
            System.out.println("INSERT INTO Commande(DATE_COMMANDE, DELAI_RAPIDE, ID_TYPE) VALUES (?,?,?)");
            System.out.println("SQLException saveOrder " + ex.getMessage());
            System.out.println("SQLState saveOrder: " + ex.getSQLState());
            System.out.println("VendorError saveOrder: " + ex.getErrorCode());
            b.close();
        sendRes = "failed";
        System.out.println("sendRes3 : " + sendRes);
        }
        if ("success".equals(sendRes)){
            this.selectedOrder.setIdOrder(searchLastId());
        for(int i = 0; i < this.selectedOrder.getSamples().size(); i++)
    {
        this.myManagedSample = new ManagedSample(this.selectedOrder.getSamples().get(i));
        sendRes = myManagedSample.saveSample(myManagedSample.getSelectedSample(), this.selectedOrder.getDateOrder(), this.selectedOrder.getIdOrder()) ;
        System.out.println("sendRes4 : " + sendRes);
    }
    }
        System.out.println("sendRes5 : " + sendRes);
        return sendRes;
    }
    
    public int searchLastId() {
        ConnectBDD b = new ConnectBDD();
        Connection con = b.getMyConnexion();
        int resultID = 0;
        try {
            if (con == null) {
                throw new SQLException("Can't get database connection");
            }
            PreparedStatement ps;
            ps = con.prepareStatement("select ID_COMMANDE from COMMANDE order by ID_COMMANDE");
            System.out.println("prepareStatement done");
            ResultSet result = ps.executeQuery();
            System.out.println("executeQuery done");
            while (result.next()) {
                resultID = (result.getInt("ID_COMMANDE"));
            }
            System.out.println("ID_TYPE_ECHANTILLON : " + resultID);
        } catch (SQLException ex) {
            System.out.println("select ID_COMMANDE from COMMANDE order by ID_COMMANDE");
            System.out.println("SQLException checkAnimal " + ex.getMessage());
            System.out.println("SQLState checkAnimal: " + ex.getSQLState());
            System.out.println("VendorError checkAnimal: " + ex.getErrorCode());
            b.close();
        }
        return resultID;
    }
    
    public int searchIdTypeAnalyse(Orders order) {
        ConnectBDD b = new ConnectBDD();
        Connection con = b.getMyConnexion();
        int resultID = 0;
        try {
            if (con == null) {
                throw new SQLException("Can't get database connection");
            }
            PreparedStatement ps;
            System.out.println(" type analyse : " + order.getSamples());
            System.out.println(" type analyse : " + order.getSamples().get(1));
            System.out.println(" type analyse : " + order.getSamples().get(1).getAnalysis());
            System.out.println(" type analyse : " + order.getSamples().get(1).getAnalysis().getTypeAnalysis());
            System.out.println(" type analyse : " + order.getSamples().get(1).getAnalysis().getTypeAnalysis().getType());
            ps = con.prepareStatement("select ID_TYPE from TYPE_ANALYSE where TYPE_ANALY = '" + order.getSamples().get(1).getAnalysis().getTypeAnalysis().getType() + "' ");
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                resultID = (result.getInt("ID_TYPE"));
            }
        } catch (SQLException ex) {
            System.out.println("ma requete");
            System.out.println("SQLException checkAnimal " + ex.getMessage());
            System.out.println("SQLState checkAnimal: " + ex.getSQLState());
            System.out.println("VendorError checkAnimal: " + ex.getErrorCode());
            b.close();
        }
        return resultID;
    }
    
    public String dateJavaToSQL(java.util.Date datejava){
        java.sql.Date sqlDate = new java.sql.Date(datejava.getTime());
         SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yy"); 
        String dateFormatee = formatDateJour.format(datejava); 
        return dateFormatee;
    }
    
    public java.util.Date dateSQLToJava(java.sql.Date dateSQL){
      
        int dd = dateSQL.getDate();
        int mm = (dateSQL.getMonth())+1;
        int aaaa = dateSQL.getYear();

        java.util.Date da = new java.util.Date(dd,mm,aaaa);

        return da;
    }
}
