package beans;


 

public class Adress {
  /**
   * The number is the number of the street.
   */
  private int number;

  /**
   * The name of the street.
   */
  private String street;

  /**
   * The zip code of the adress.
   */
  private int zipCode;

  /**
   * The city of the adress.
   */
  private String city;

  /**
   * The country of the adress.
   */
  private String country;

  public Adress(){
      
     
  }
  public Adress(int zip, String city) {
    // Bouml preserved body begin 0001F502
	  this.zipCode = zip;
	  this.city = city;
    // Bouml preserved body end 0001F502
  }

  public Adress(int number, String street, int zip, String city, String country) {
    // Bouml preserved body begin 0002C182
	  this.number = number;
	  this.street = street;
	  this.zipCode = zip;
	  this.city = city;
	  this.country = country;
    // Bouml preserved body end 0002C182
  }

  public int getNumber() {
    // Bouml preserved body begin 00022783
	  return this.number;
    // Bouml preserved body end 00022783
  }

  public String getStreet() {
    // Bouml preserved body begin 00022803
	  return this.street;
    // Bouml preserved body end 00022803
  }

  public int getZipCode() {
    // Bouml preserved body begin 00022883
	  return this.zipCode;
    // Bouml preserved body end 00022883
  }

  public String getCity() {
    // Bouml preserved body begin 00022903
	  return this.city;
    // Bouml preserved body end 00022903
  }

  public String getCountry() {
    // Bouml preserved body begin 00022983
	  return this.country;
    // Bouml preserved body end 00022983
  }

  public void setNumber(int new_number) {
    // Bouml preserved body begin 00022A03
	  this.number = new_number;
    // Bouml preserved body end 00022A03
  }

  public void setStreet(String new_Street) {
    // Bouml preserved body begin 00022A83
	  this.street = new_Street;
    // Bouml preserved body end 00022A83
  }

  public void setZipCode(int new_ZipCode) {
    // Bouml preserved body begin 00022B03
	  this.zipCode = new_ZipCode;
    // Bouml preserved body end 00022B03
  }

  public void setCity(String new_City) {
    // Bouml preserved body begin 00022B83
	  this.city = new_City;
    // Bouml preserved body end 00022B83
  }

  public void setCountry(String new_Country) {
    // Bouml preserved body begin 00022C03
	  this.country = new_Country;
    // Bouml preserved body end 00022C03
  }

}
