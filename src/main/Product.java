package main;
/**
  * A class that represent a product  
  * This is an immutable class
  *
  * @author Diana Yamaletdinova
  * @date 4/7/2015
  *
  */

public class Product {

   private String productName; 
   private double productPrice;
   private String inventoryNumber; 
   private double laborTime;
   
  /**
   * Constructs a Product class,
   * this is an immutable class
   * 
   * @param name the name of the product
   * @param price the price of the product
   * @param inventoryNumber the number of the inventory
   * @param laborTime the labor time
   */

   public Product(String name, double price, String inventoryNumber, double laborTime) {
      this.productName = name;
      this.productPrice = price; 
      this.inventoryNumber = inventoryNumber;
      this.laborTime = laborTime;
   }
   
   /**
    * Gets the name of this product
    * @return the productName 
    */
    
   public String getProductName() {
      return productName;
   }
   
   /**
    * Gets the price of this product
    * @return the productPrice
    */
    
   public double getProductPrice() {
      return productPrice;
   }   
   
   /**
    * Gets the inventory number of this product
    * @return the inventoryNumber 
    */
    
   public String getInventoryNumber() {
      return inventoryNumber;
   }
   
   /**
    * Gets the hours worked of this product
    * @return the inventoryNumber 
    */
    
   public double getLaborTime() {
      return laborTime;
   }
}