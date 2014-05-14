package beans;


 

import java.util.ArrayList;
/**
 * This class manages the storage of the different samples.
 */
public class Storage {
  /**
   * Each refrigerator or freezer has an unique identifier.
   * 
   */
  private int identifier;

  /**
   * The type of the storage: if the storage is a refrigerator or freezer.
   */
  private String type;

  /**
   * The list contains many samples.
   */
  ArrayList<Samples> listSamples;

  /**
   * This attribute describes the size, the capacity of the container.
   */
  private int size;

  public Storage(String type, int size) {
    // Bouml preserved body begin 00024803
	  this.identifier = 0;
	  this.type = type;
	  this.size = size;
	  this.listSamples = new ArrayList<Samples>();
    // Bouml preserved body end 00024803
  }

  /**
   * This method permits to return the identifier of the storage.
   */
  public int getIdentifier() {
    // Bouml preserved body begin 00038902
	  return this.identifier;
    // Bouml preserved body end 00038902
  }

  /**
   * This method return the type of the container.
   */
  public String getType() {
    // Bouml preserved body begin 00038982
	  return this.type;
    // Bouml preserved body end 00038982
  }

  /**
   * The method "getSize" returns the size of the storage.
   */
  public int getSize() {
    // Bouml preserved body begin 00038A02
	  return this.size;
    // Bouml preserved body end 00038A02
  }

  /**
   * To modify the identifier.
   */
  public void setIdentifier(int id) {
    // Bouml preserved body begin 00038A82
	  this.identifier = id;
    // Bouml preserved body end 00038A82
  }

  public void setType(String newType) {
    // Bouml preserved body begin 00038B02
	  this.type = newType;
    // Bouml preserved body end 00038B02
  }

  public void setSize(int newSize) {
    // Bouml preserved body begin 00038B82
	  this.size = newSize;
    // Bouml preserved body end 00038B82
  }

  /**
   * This function permits to list all the sample in the storage.
   */
  public ArrayList<Samples> listSample() {
    // Bouml preserved body begin 00048F82
	  return (this.listSamples);
    // Bouml preserved body end 00048F82
  }

  /**
   * This function permits to add a sample in the storage.
   */
  public void addSample(Samples sample) {
    // Bouml preserved body begin 00048E82
	  this.listSamples.add(sample);
    // Bouml preserved body end 00048E82
  }

  /**
   * This function permits to remove a sample from a storage.
   */
  public void removeSample(Samples sample) {
    // Bouml preserved body begin 00048F02
	  this.listSamples.remove(this.listSamples.indexOf(sample));
    // Bouml preserved body end 00048F02
  }

}
