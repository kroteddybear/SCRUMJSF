package Tools;

import ManagedBeans.Database;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Teddy
 */
public class DateTools {
    
    private Date maDate;
    
    public DateTools(){
        maDate = new Date();
    }

    public Date getMaDate() {
        return maDate;
    }

    public void setMaDate(java.util.Date maDate) {
        this.maDate = maDate;
    }
    
    public String dateJavaToSQL(java.util.Date datejava){
        System.out.println(datejava);
        java.sql.Date sqlDate = new java.sql.Date(datejava.getTime());
         System.out.println(sqlDate);
         SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yy"); 
        String dateFormatee = formatDateJour.format(datejava); 
        System.out.println("Date formatée : " + dateFormatee);
        return dateFormatee;
    }
    
    public static java.util.Date stringToDate(String sDate, String sFormat) { 
        SimpleDateFormat sdf = new SimpleDateFormat(sFormat); 
        try { 
            return sdf.parse(sDate);
        } catch (ParseException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public java.util.Date dateSQLToJava(java.sql.Date dateSQL){
      
        int dd = dateSQL.getDate();
        int mm = (dateSQL.getMonth())+1;
        int aaaa = dateSQL.getYear();

        java.util.Date da = new java.util.Date(dd,mm,aaaa);

        return da;
    }
    
    public void printDate() {
	  System.out.print(this.maDate);
  }
    
    public boolean compareDate(Date pdate) {
        return (this.maDate == pdate);
}
}
