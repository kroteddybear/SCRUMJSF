package beans;

import ManagedBeans.Database;


 

/**
 * This class manages the different fields of an invoice.
 */
public class Invoice {
  private int id;

  private double price;

  /**
   * It is the number of analysis of the order.
   */
  private int numberAnalysis;

  public  Invoice(double price, int numberA, int id) {
    // Bouml preserved body begin 00021A45
	  this.id=id;
	  this.price=price;
	  this.numberAnalysis=numberA;
    // Bouml preserved body end 00021A45
  }

  /**
   * This function permits to print the informations of this invoice.
   */
  public void printInvoice(Database d) {
    // Bouml preserved body begin 00043382
	  System.out.print("Invoice for the order n ?");
	  System.out.println(id);
	  System.out.print("Number of analysis : ");
	  System.out.println(numberAnalysis);
	  
	  
	  System.out.println("Details of analysis : ");
	  Orders o = d.searchOrder(this.id);
	  for (int i=0; i< o.getSamples().size(); i++) {
		  if(o.getSamples().get(i).getAnalysis()!= null){
		  System.out.print(o.getSamples().get(i).getAnalysis().getTypeAnalysis().getType());
		  System.out.print(" : ");
		  System.out.print(o.getSamples().get(i).getAnalysis().getTypeAnalysis().getPrice());
		  System.out.println(" ? ");
	  }
	  }
	  
	  
	  System.out.print("Total price : ");
	  System.out.print(price);
	  System.out.println(" ? ");
    // Bouml preserved body end 00043382
  }

  public double getPrice() {
    // Bouml preserved body begin 00043482
	  return this.price;
    // Bouml preserved body end 00043482
  }

}
