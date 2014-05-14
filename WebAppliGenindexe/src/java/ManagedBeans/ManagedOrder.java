package ManagedBeans;

import Tools.ConnectBDD;
import beans.Customers;
import beans.Orders;
import beans.Samples;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Teddy
 */
public class ManagedOrder {

    private Orders selectedOrder;
    private ManagedSample myManagedSample;

    public ManagedOrder() {
    }

    public ManagedOrder(Orders selectedOrder) {
        this.selectedOrder = selectedOrder;
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
}
