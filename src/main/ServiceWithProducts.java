package main;
import java.util.ArrayList;

/**
  * A class that stores services and products  
  * that customer selected in the main program
  *
  * @author Diana Yamaletdinova
  * @date 4/30/2015
  *
  */
  
public class ServiceWithProducts {

   private Service service;
   private ArrayList<Product> products;
   private double laborTime;
   private double totalCharge;
	
   /**
    * Constructs a ServiceWithProducts class
    * 
    * @param Service service
    * @param ArrayList<Product> products
    */

   public ServiceWithProducts(Service service, ArrayList<Product> products) {
      this.service = service;
      this.products = products;
   }
	
   /**
    * Gets the selected service
    * @return the service
    */

   public Service getService() {
      return service;
   }
   
   /**
    * Gets the list of selected products
    * @return products
    */

   public ArrayList<Product> getProducts() {
      return products;
   }
	
   /**
    * Gets the Labor Time of selected products
    * @return laborTime
    */
    
   public double getLaborTime() {
      return laborTime;
   }

   /**
    * Sets the Labor Time of selected products
    * @param laborTime
    */

   public void setLaborTime(double laborTime) {
      this.laborTime = laborTime;
   }

   /**
    * Gets the total charge for the selected services and products
    * @return totalCharge  
    */

   public double getTotalCharge() {
      return totalCharge;
   }

   /**
    * Sets the total charge for the selected services and products
    * @param totalCharge 
    */
    
   public void setTotalCharge(double totalCharge) {
      this.totalCharge = totalCharge;
   }		
}