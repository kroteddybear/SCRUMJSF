package beans;


 

/**
 * This class manages the customers.
 */
public class Customers {
  private int ID;

  /**
   * The first name of the customer.
   */
  private String firstName;

  /**
   * The last name of the customer.
   */
  private String lastName;

  /**
   * The phone number of the customer.
   */
  private String phoneNumber;
  private String cellularNumber;
  private String faxNumber;
  private String email;
  
  private String login;
  private String motDePasse;

  private Adress adress;

  public Customers(){
      
  }
  
  public Customers(String first, String last, int num, String rue, String phone, String cellular, String fax, String log, String mdp, int ID) {
    // Bouml preserved body begin 00040A82
	  Adress ad = new Adress(num, rue);
	  this.adress = ad;
	  firstName = first;
	  lastName = last;
	  phoneNumber = phone;
          cellularNumber= cellular;
          faxNumber = fax;
          login = log;
          motDePasse = mdp;
	  this.ID = ID;
    // Bouml preserved body end 00040A82
  }

  /**
   * to modify attribute name
   */
  public void setName(String first, String last) {
    // Bouml preserved body begin 00040F02
	  firstName = first;
	  lastName = last;
    // Bouml preserved body end 00040F02
  }

  /**
   * return last name of customer
   */
  public String getLastName() {
    // Bouml preserved body begin 00040B82
	  System.out.println(lastName);
	  return lastName;

    // Bouml preserved body end 00040B82
  }

  /**
   * accessor to attribute name
   */
  public String getFirstName() {
    // Bouml preserved body begin 00040B02
	  System.out.println(firstName);
	  return firstName;
    // Bouml preserved body end 00040B02
  }

  /**
   * change attribute phone number
   */
  public void setPhone(String phone) {
    // Bouml preserved body begin 00041002
	  this.phoneNumber = phone;
    // Bouml preserved body end 00041002
  }
  
 public void setCellular(String phone) {
    // Bouml preserved body begin 00041002
	  this.cellularNumber = phone;
    // Bouml preserved body end 00041002
  }
 
 
 public void setFax(String phone) {
    // Bouml preserved body begin 00041002
	  this.faxNumber = phone;
    // Bouml preserved body end 00041002
  }

  /**
   * accessor to attribute phone
   */
  public String getPhone() {
    // Bouml preserved body begin 00040C82
	  return phoneNumber;
    // Bouml preserved body end 00040C82
  }
  
  public String getCellular() {
    // Bouml preserved body begin 00040C82
	  return cellularNumber;
    // Bouml preserved body end 00040C82
  }
  
  public String getFax() {
    // Bouml preserved body begin 00040C82
	  return faxNumber;
    // Bouml preserved body end 00040C82
  }
  
  
  public void setLogin(String log) {
    // Bouml preserved body begin 00041002
	  this.login = log;
    // Bouml preserved body end 00041002
  }
  
  public void setMotDePasse(String mdp) {
    // Bouml preserved body begin 00041002
	  this.motDePasse = mdp;
          
    // Bouml preserved body end 00041002
  }

   public String getLogin() {
    // Bouml preserved body begin 00040C82
	  return login;
    // Bouml preserved body end 00040C82
  }
   
    public String getMotDePasse() {
    // Bouml preserved body begin 00040C82
	  return motDePasse;
    // Bouml preserved body end 00040C82
  }
    
  /**
   * accessor to attribute ID
   */
  public int getID() {
    // Bouml preserved body begin 00040D02
	  System.out.println(ID);
	  return ID;
    // Bouml preserved body end 00040D02
  }

  /**
   * accessor to attribute Email
   */
  public String getEmail() {
    // Bouml preserved body begin 00040D82
	  System.out.println(email);
	  return email;
    // Bouml preserved body end 00040D82
  }

  /**
   * change attribute ID
   */
  public void setID(int ID) {
    // Bouml preserved body begin 00041082
	  this.ID=ID;
    // Bouml preserved body end 00041082
  }

  /**
   * change Email
   */
  public void setEmail(String mail) {
    // Bouml preserved body begin 00041102
	  email = mail;
    // Bouml preserved body end 00041102
  }

  /**
   * to modify attribute adress
   */
  public void setAdress(Adress adr) {
    // Bouml preserved body begin 00040F82
	  this.adress = adr;
    // Bouml preserved body end 00040F82
  }

  /**
   * accessor to attribute adress
   */
  public Adress getAdress() {
    // Bouml preserved body begin 00040C02
	  return adress;
    // Bouml preserved body end 00040C02
  }

}
