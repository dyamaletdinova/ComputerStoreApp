package main;
import java.util.ArrayList;

/**
  * A class that represents a customerAccount class. 
  *
  * @author Diana Yamaletdinova
  * @date 4/13/2015
  *
  */
  
public class CustomerAccount{

   private double totalCharge;
   private double hoursWorked;
   private String name;
   private String address;
   private ArrayList<ServiceWithProducts> selectedProductsAndServices;   
   
   
   /**
   * Constructs a CustomerAccount class 
   *
   * that takes no arguments
   */
   
   public CustomerAccount(){
   }
   
   
   /**
    * Gets the total charge for the selected services and products
    *
    * @return totalCharge  
    */
   public double getTotalCharge(){
      return totalCharge;
   }
   
   
   /**
    * Sets the total charge for the selected services and products
    * @param totalCharge 
    */
    
   public void setTotalCharge(double customerChages){
      this.totalCharge = customerChages;
   }
   
   
   /**
    * Gets the hours worked of selected products
    * @return hoursWorked 
    */
    
   public double getHoursWorked(){
      return hoursWorked;
   }
   
   
   /**
    * Sets the hours worked of selected products
    * @param hoursWorked the total hours worked 
    */
    
   public void setHoursWorked(double hoursWorked){
      this.hoursWorked = hoursWorked;
   }
   
   
    /**
     * Gets the name of the customer
     * @return the name the customer's name
     */
     
   public String getName(){
      return name;
   }
   
   
    /**
     * Sets the name of the customer
     * @param name the name of the customer
     */
     
   public void setName(String name){
      this.name = name;
   }
   
   
   /**
    * Sets the adress of the customer
    * @param address the customer's address
    */
    
   public void setAddress(String address){
      this.address = address;
   }
   
   
    /**
     * Gets the adress of the customer
     * @return the address the customer's address
     */
     
   public String getAddress(){
      return address;
   }
   
   
  /**
    * Gets the service and associated with it products
    * @return selectedProductsAndServices the service and associated with it products
    */
    
   public ArrayList<ServiceWithProducts> getSelectedProductsAndServices() {
      return selectedProductsAndServices;
   }
   
   
  /**
    * Sets the service and associated with it products
    * @param selectedProductsAndServices the service and associated with it products
    */
    
   public void setSelectedProductsAndServices(
   		ArrayList<ServiceWithProducts> selectedProductsAndServices) {
      this.selectedProductsAndServices = selectedProductsAndServices;
   }

}