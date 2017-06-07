package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
  * A class that represent services  
  * This is an immutable class
  * @author Diana Yamaletdinova
  *
  * @date 4/7/2015
  */

public class Service {

   private String serviceName;
   private double servicePrice;
   
   private ArrayList<Product> products = new ArrayList<Product>(); // creates an ArrayList of Products
   
   /**
    * Constructs a Service class 
    *
    * @param name the name of the service
    * @param price the price of the product
    * @param ArrayList<Product> products
    */

   public Service(String name, double price, ArrayList<Product> products){
      this.serviceName = name;
      this.servicePrice = price; 
      this.products = products;
   }
   
   /**
    * Gets the name of this service
    * @return the serviceName 
    */
    
   public String getServiceName(){
      return serviceName;
   }
   
   /**
    * Gets the price of this service
    * @return the servicePrice 
    */
    
   public double getServicePrice(){
      return servicePrice;
   }
   
  /**
    * Gets the info on arrays 
    * @return the products 
    */

   public ArrayList<Product> getProducts(){
      return products;
   }
 
}