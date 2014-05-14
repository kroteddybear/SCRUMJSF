package beans;


 

import java.util.HashMap;
import java.util.Iterator;
/**
 * This class manages the different users.
 */
public class Users {
  private static int countID = 1;

  private int id;

  private String firstName;

  private String lastName;

  /**
   * This attribute shows the administrative authorizations for the user:
   * TRUE if the user has the administrative authorizations.
   * FALSE if the user does not have these rights.
   */
  private boolean administrativeAuthorizations;

  /**
   * The key of this hashmap is the type of analysis and the value is the authorizations level for this type.
   * 
   * There are many levels for the technical authorizations:
   * - Technical authorizations:
   * 	- level 0: if the user has any technical authorizations.
   * 	- level 1: if the user has the right to perform an analysis.
   * 	- level 2:  if the user has the right to perform and validate an analysis.
   * 	- level 3:  if the user has the right to perform, validate and interpret an analysis
   * --> for a type of analysis;
   */
  private HashMap<Types_analysis,Integer> technicalAuthorizations;

  /**
   * If the user wants to access to Internet services, he must have an login (and a password).
   */
  private String login;

  /**
   * To have access to Internet services, the user informs his login and his password.
   */
  private String password;

  /**
   * The email that is informed is the email of the user.
   */
  private String email;

  public Users(String firstName, String lastName) {
    // Bouml preserved body begin 0001F403
	  this.id = countID;
	  countID = countID + 1;
	  this.firstName = firstName;
	  this.lastName=lastName;
	  this.login = this.createLogin();
	  this.password = this.login;
	  this.administrativeAuthorizations=false;
	  this.technicalAuthorizations = new HashMap();
    // Bouml preserved body end 0001F403
  }

  public Users(String firstName, String lastName, String email) {
    // Bouml preserved body begin 0002DB82
	  this.id = countID;
	  countID = countID + 1;
	  this.firstName = firstName;
	  this.lastName=lastName;
	  this.email =email;
	  this.login = this.createLogin();
	  this.password = this.login;
	  this.administrativeAuthorizations=false;
	  this.technicalAuthorizations = new HashMap();
    // Bouml preserved body end 0002DB82
  }

  public int getId() {
    // Bouml preserved body begin 00043582
	  return this.id;
    // Bouml preserved body end 00043582
  }

  public String getLastName() {
    // Bouml preserved body begin 00043602
	  return this.lastName;
    // Bouml preserved body end 00043602
  }

  public String getFirstName() {
    // Bouml preserved body begin 00043702
	  return this.firstName;
    // Bouml preserved body end 00043702
  }

  public boolean getAdministrativeAuth() {
    // Bouml preserved body begin 0002DE02
	  return(this.administrativeAuthorizations);
    // Bouml preserved body end 0002DE02
  }

  public HashMap<Types_analysis, Integer> getTechnicalAuth() {
    // Bouml preserved body begin 00042D82
	  return this.technicalAuthorizations;
    // Bouml preserved body end 00042D82
  }

  public String getLogin() {
    // Bouml preserved body begin 0002DC82
	  return(this.login);
    // Bouml preserved body end 0002DC82
  }

  public String getPassword() {
    // Bouml preserved body begin 00042E82
	  return this.password;
    // Bouml preserved body end 00042E82
  }

  public String getEmail() {
    // Bouml preserved body begin 00043802
	  return this.email;
    // Bouml preserved body end 00043802
  }

  public void setEmail(String mail) {
    // Bouml preserved body begin 00043882
	  this.email = mail;
    // Bouml preserved body end 00043882
  }

  public void setLastName(String name) {
    // Bouml preserved body begin 00043682
	  this.lastName=name;
    // Bouml preserved body end 00043682
  }

  public void setFirstName(String name) {
    // Bouml preserved body begin 00043782
	  this.firstName=name;
    // Bouml preserved body end 00043782
  }

  public void setAdministrativeAuth(boolean adath) {
    // Bouml preserved body begin 0002DD02
	  this.administrativeAuthorizations=adath;
    // Bouml preserved body end 0002DD02
  }

  public void setTechnicalAuth(int level, Types_analysis analysis) {
    // Bouml preserved body begin 0002DD82
	  this.removeTechnicalAuth(analysis);
	  this.addTechnicalAuth(level, analysis);
    // Bouml preserved body end 0002DD82
  }

  public void setPassword(String pwd) {
    // Bouml preserved body begin 0002DF02
	  this.password=pwd;
    // Bouml preserved body end 0002DF02
  }

  /**
   * This function permits to create a login with the first letter of the firstname of the user and his lastname.
   */
  public String createLogin() {
    // Bouml preserved body begin 0002DC02
	  	char p = this.firstName.charAt(0);
		String login = p+(this.lastName);
		return login;
    // Bouml preserved body end 0002DC02
  }

  /**
   * This function permits to add a technical authorization to an user.
   */
  public void addTechnicalAuth(int level, Types_analysis type) {
    // Bouml preserved body begin 00042D02
	  this.technicalAuthorizations.put(type,level);
    // Bouml preserved body end 00042D02
  }

  /**
   * This function permits to remove a technical autorization to an user.
   */
  public void removeTechnicalAuth(Types_analysis type) {
    // Bouml preserved body begin 0002DE82
	  this.technicalAuthorizations.remove(type);
    // Bouml preserved body end 0002DE82
  }

  /**
   * This function permits to list all the technical authorizations of the user (the type of analysis that he can perform and the level (first reading, second reading, validation))
   */
  public void listTechnicalAuth() {
    // Bouml preserved body begin 00042E02
	  Iterator iterator = this.technicalAuthorizations.keySet().iterator();
      while( iterator. hasNext() )
      {
      	 System.out.println(((Types_analysis)iterator.next()).getType());
      }
    // Bouml preserved body end 00042E02
  }

}
