package main;
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;



/**
  * A class to write the billing data.
  * After the customer clicked to "Checkout" the data from the bill 
  * updated in the customer account and recorded to the billing.txt file 
  *
  * @author Diana Yamaletdinova
  * @date 4/30/2015 
  *
  */
  
public class BillingWriter {

  /**
    *Constructor that takes no arguments
    * 
    */

   public BillingWriter(){
   }


    /**
     * Method that writes the billing data to the file
     * in a sertain format
     *
     * @param ca the Customer account object
     */
     
   public void writeBillToFile(CustomerAccount ca) {
   
      // creates a StringBuilder object
      StringBuilder sb = new StringBuilder();
      // creates the DecimalFormat object
      DecimalFormat df = new DecimalFormat("0.00");
         
      //reading the data from a customer account  
      sb.append("CUSTOMER|" + ca.getName() + "|" + ca.getAddress()+ "|" );
      sb.append(df.format(ca.getTotalCharge()) + "|");
      sb.append(df.format(ca.getHoursWorked()) + "\n");   
      
      // ArrayList of selected products and services
      ArrayList<ServiceWithProducts> customerSelected = new ArrayList<ServiceWithProducts>();
     
     //reading the services data from the SelectedProductsAndServices
      for(ServiceWithProducts swp: ca.getSelectedProductsAndServices()) {
         
         // appand the data to be recorded to the billing.txt file
         sb.append("SERVICE|" + swp.getService().getServiceName() + "|" + swp.getService().getServicePrice() + "\n");
         
         //reading the products data from customer selection
         for ( Product p: swp.getProducts()) {
            
            sb.append("PRODUCT|" + p.getProductName() + "|" + p.getProductPrice() + "|" + p.getLaborTime() + "\n");
         }
      
      }
     
     // write the billing data to a file 
      PrintWriter out = null;
               
      try {
         final String reportFile = "billing.txt";  //name of the text file          
         out = new PrintWriter(new FileWriter(reportFile, true));
                 
         out.println(sb.toString()); // records the data to the billing.txt file
      
      } 
      //catch the exception and notify the user about the error
      catch(IOException io) {
         System.out.println("Error: cannot find a file");
      }
   
      out.close();
    
   }


} 