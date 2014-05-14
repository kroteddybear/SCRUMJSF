package beans;


 

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Date;
public class GestionDate {
  private Date date;

  /**
   * This function permits to check if the first date is before the second.
   * Return TRUE if the first date is before the second, FALSE if not.
   */
  public boolean check_dates(Date date1, Date date2) {
    // Bouml preserved body begin 0001F582
	  return(date1.before(date2));
    // Bouml preserved body end 0001F582
  }

  public GestionDate(int day, int month, int year) {
    // Bouml preserved body begin 00020E03
    // Bouml preserved body end 00020E03
  }

}
