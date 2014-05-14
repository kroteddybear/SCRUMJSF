package beans;

import Tools.DateTools;
import java.util.Date;


 

public class Analysis {
  /**
   * This attribute is TRUE if the analysis has been validated and FALSE if it has not.
   */
  private boolean validate = false;

  /**
   * This attribute contains the different results of the analysis.
   */
  private String report;

  private int ID;

  private Users secondReading;

  private Users firstReading;

  private Date dateAnalysis;

  private Types_analysis typeAnalysis;

  /**
   * constructor of analysis
     * @param ID
   */
  public Analysis(int ID, Types_analysis ta, Date date) {
    // Bouml preserved body begin 00040602
	  this.ID=ID;
	  this.typeAnalysis=ta;
	  this.dateAnalysis=date;
    // Bouml preserved body end 00040602
  }
  
  /**
   * constructor of analysis
     * @param ID
     * @param ta
   */
  public Analysis(int ID, Types_analysis ta) {
	  this.ID=ID;
	  this.typeAnalysis=ta;
  }
  
  public Analysis(){
	  
  }

  public void generateReport() {
    // Bouml preserved body begin 0002C102
            DateTools d = new DateTools();
            d.setMaDate(dateAnalysis);
	  d.printDate();
	  System.out.print(typeAnalysis.getType());
	  System.out.println(ID);
	  System.out.print("first reading :");
	  if(firstReading == null)
	  {
		  System.out.println("not done");
	  }
	  else
	  {
		  System.out.print(firstReading.getFirstName()+" ");
		  System.out.println(firstReading.getLastName());
	  }
	  System.out.print("second reading :");
	  if(secondReading == null)
	  {
		  System.out.println("not done");
	  }
	  else
	  {
		  System.out.print(secondReading.getFirstName()+" ");
		  System.out.println(secondReading.getLastName());
		  System.out.print("validate : ");
		  System.out.println(validate);
		  System.out.println(report);
	  }
	  System.out.print(typeAnalysis.getPrice());
	  System.out.println("?");
    // Bouml preserved body end 0002C102
  }

  /**
   * name of the scientist who validate first reading
   */
  public void firstReading(Users user) {
    // Bouml preserved body begin 00040902
	  firstReading=user;
    // Bouml preserved body end 00040902
  }

  /**
   * name of the scientist who validate the second reading
   */
  public void secondReading(Users user, boolean validate, String com) {
    // Bouml preserved body begin 00040982
	  if(firstReading!=null)
	  {
		  secondReading = user;
		  report = com;
		  validation(validate);
	  }
	  else
	  {
		  System.out.println("first reading not done");
	  }
    // Bouml preserved body end 00040982
  }

  /**
   * accessor to ID
   */
  public int getID() {
    // Bouml preserved body begin 00040682
	  //System.out.println(ID);
	  return ID;
    // Bouml preserved body end 00040682
  }

  /**
   * change ID
   */
  public void setID(int ID) {
    // Bouml preserved body begin 00040A02
	  this.ID=ID;
    // Bouml preserved body end 00040A02
  }

  /**
   * to validated the analysis
   */
  private void validation(boolean validate) {
    // Bouml preserved body begin 00040702
	this.validate=validate;
    // Bouml preserved body end 00040702
  }

  public Types_analysis getTypeAnalysis() {
    // Bouml preserved body begin 00043302
	  return this.typeAnalysis;
    // Bouml preserved body end 00043302
  }

  /**
   * get the first reader
   */
  public Users getFirstReader() {
    // Bouml preserved body begin 000239C5
	  return firstReading;
    // Bouml preserved body end 000239C5
  }

  /**
   * get the second reader
   */
  public Users getSecondReader() {
    // Bouml preserved body begin 00023A45
	  return secondReading;
    // Bouml preserved body end 00023A45
  }

  public boolean getValidate() {
    // Bouml preserved body begin 00023AC5
	  return validate;
    // Bouml preserved body end 00023AC5
  }

  public String getReport() {
    // Bouml preserved body begin 00023B45
	  return report;
    // Bouml preserved body end 00023B45
  }

}
