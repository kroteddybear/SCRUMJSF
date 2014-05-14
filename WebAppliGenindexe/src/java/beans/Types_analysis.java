package beans;


 

import java.util.HashMap;
/**
 * This class has been created to manage the different types of analysis.
 */
public class Types_analysis {
  private String type;

  /**
   * The price of one type of analysis for one sample.
   */
  private double price;

  public  Types_analysis(){
      
  }
  
  /**
   * constructor of types
   */
  public  Types_analysis(String name, int price) {
    // Bouml preserved body begin 0001FE45
	  this.type = name;
	  this.price = price;
    // Bouml preserved body end 0001FE45
  }

  /**
   * accessor to name
   */
  public String getType() {
    // Bouml preserved body begin 0001FEC5
	  return this.type;
    // Bouml preserved body end 0001FEC5
  }

  public void setType(String name) {
    // Bouml preserved body begin 0001FF45
	  this.type=name;
    // Bouml preserved body end 0001FF45
  }

  public double getPrice() {
    // Bouml preserved body begin 0001FFC5
	  return this.price;
    // Bouml preserved body end 0001FFC5
  }

  public void setPrice(int price) {
    // Bouml preserved body begin 00020045
	  this.price=price;
    // Bouml preserved body end 00020045
  }

}
