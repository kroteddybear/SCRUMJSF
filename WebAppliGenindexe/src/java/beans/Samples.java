package beans;


 

import java.util.ArrayList;
/**
 * This class "samples" is about the different samples of the analysis.
 * We can manage the samples with the methods: modifySample(), printSample().
 * We have different accessors and we can return many informations with the methods "get...".
 */
public class Samples {
  /**
   * The identifiant of the sample. It is automatically incremented.
   * This identifiant is unique.
   */
  private String id;

  /**
   * It is the type of the sample (blood, DNA...)
   */
  private String type;

  /**
   * number of analysis for this sample
   */
  private int analysisCount = 0;

  /**
   * If TRUE the sample is already analyzed, if not it is FALSE.
   */
  protected boolean analyzed;

  private Animals animal;

  private java.util.Date dateSampling;

  private ArrayList<Analysis> analysis;

  private java.util.Date dateStorage;

  /**
   * Sample is a constructor.
   * It permits to create a new sample with the different fields:
   * - the identifier (integer).
   * - the type of sample (string).
   * - the date of creation of this sample (date).
   * - the date of the storage of this sample (date).
     * @param Identifier
     * @param Type_sample
     * @param D_sampling
     * @param D_storage
     * @param anim
   */
  public Samples(String Identifier, String Type_sample, java.util.Date D_sampling, java.util.Date D_storage, Animals anim) {
    // Bouml preserved body begin 0002A282
	  this.id = Identifier;
	  this.type = Type_sample;
	  this.dateSampling = D_sampling;
	  this.dateStorage = D_storage;
	  this.animal = anim;
	  this.analyzed = false; // default: the sample is not analyzed
	  this.analysis = new ArrayList<Analysis>();
    // Bouml preserved body end 0002A282
  }
  
  public Samples(String Type_sample, java.util.Date D_sampling, java.util.Date D_storage, Animals anim) {

	  this.type = Type_sample;
	  this.dateSampling = D_sampling;
	  this.dateStorage = D_storage;
	  this.animal = anim;
	  this.analyzed = false; // default: the sample is not analyzed
	  this.analysis = new ArrayList<Analysis>();
    // Bouml preserved body end 0002A282
  }
  
  public Samples(){
	  
  }

  /**
   * This method permits to display a sample on the screen.
     * @param sam
   */
  public void printSample(Samples sam) {
    // Bouml preserved body begin 0002A382
	  System.out.println("Identifier: "+ sam.id);
	  System.out.println("Type of sample: "+ sam.type);
	  System.out.println("Date of the creation of the sample: "+ sam.dateSampling);
	  System.out.println("Date of the storage of the sample: " + sam.dateStorage);
	  System.out.println("Analyzed? : " + sam.analyzed);
    // Bouml preserved body end 0002A382
  }

  /**
   * This method has been created to modify the type of the sample.
     * @param type
   */
  public void setType(String type) {
    // Bouml preserved body begin 0002A402
	  this.type = type;
    // Bouml preserved body end 0002A402
  }

  /**
   * This method has been created to modify the date of the creation of the sample.
     * @param d
   */
  public void setDatesampling(java.util.Date d) {
    // Bouml preserved body begin 0002A482
	  this.dateSampling = d;
    // Bouml preserved body end 0002A482
  }

  /**
   * This method has been created to modify the date when the sample was storaged.
     * @param d2
   */
  public void setDatestorage(java.util.Date d2) {
    // Bouml preserved body begin 0002A502
	  this.dateStorage = d2;
    // Bouml preserved body end 0002A502
  }

  /**
   * This method has been created to modify the animal of the sample.
     * @param anim
   */
  public void setAnimal(Animals anim) {
    // Bouml preserved body begin 0002A582
	  this.animal = anim;
    // Bouml preserved body end 0002A582
  }

  /**
   * This "get" permits to return the identifier of the animal.
     * @return 
   */
  public String getId() {
    // Bouml preserved body begin 0002A602
	  return id;
    // Bouml preserved body end 0002A602
  }

  /**
   * This method return the type of the sample.
     * @return 
   */
  public String getType() {
    // Bouml preserved body begin 0002A682
	  return this.type;
    // Bouml preserved body end 0002A682
  }

  /**
   * The method getDatesampling return the date when the sample was created.
     * @return 
   */
  public java.util.Date getDatesampling() {
    // Bouml preserved body begin 0002A702
	  return this.dateSampling;
    // Bouml preserved body end 0002A702
  }

  /**
   * This method return the date of the storage of the sample.
     * @return 
   */
  public java.util.Date getDatestorage() {
    // Bouml preserved body begin 0002A782
	  return this.dateStorage;
    // Bouml preserved body end 0002A782
  }

  /**
   * This method has been created to modify the boolean:
   *  FALSE to TRUE when the sample is analyzed.
   * !! WARNING !! : TRUE -> FALSE is not possible.
   */
  public void setAnalyzed() {
    // Bouml preserved body begin 0002C202
	  if (analyzed == false){
		  analyzed = true;
	  }
    // Bouml preserved body end 0002C202
  }

  public Analysis getAnalysis() {
    // Bouml preserved body begin 00037982
		  for(int i=0; i<analysis.size(); i++)
		  {
			  if(i==(analysis.size()-1))
			  {
				  return analysis.get(i);
			  }
			  else
			  {
				  analysis.get(i).generateReport();
			  }
		  }
	  return null;
    // Bouml preserved body end 00037982
  }

  public Animals getAnimal() {
    // Bouml preserved body begin 00037A02
	  return this.animal;
    // Bouml preserved body end 00037A02
  }

  /**
   * add an analysis to the sample, 1 to 3 if the analysis is not validate
     * @param ana
   */
  public void addAnalysis(Analysis ana) {
    // Bouml preserved body begin 000233C5
	  if(analysisCount==3)
	  {
		  System.out.println("3 analysis were done, please sent new sample. ");
	  }
	  else
	  {
		  analysis.add(analysisCount, ana);
		  analysisCount=analysisCount+1;
	  }
    // Bouml preserved body end 000233C5
  }

  /**
   * get the number of analysis for this sample
     * @return 
   */
  public int getCount() {
    // Bouml preserved body begin 00023445
	  return analysisCount;
    // Bouml preserved body end 00023445
  }

}
