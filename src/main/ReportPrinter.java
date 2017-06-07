package main;
import java.util.*;
import java.io.*;


/**
  * A class that print the reports: 
  * Services report, Sales report, Customer report
  *
  * @author Diana Yamaletdinova
  * @date 4/17/2015 
  *
  */

public class ReportPrinter {

  /**
   * Method to print the Service report,
   * the information is read from the billing.txt
   *
   * @return sb.toString() concatenamed string result
   */
      
   public static String printServicesReport() {
      
      Scanner in = null;
      
      // for more effective way of concatenating Strings use StringBuilder
      StringBuilder sb = new StringBuilder();
      sb.append("\n\n");
      sb.append("                  *********   Service report   *********\n");
      //System.out.println("Services report:\n");
      
      try  {
      
         in = new Scanner(new File("billing.txt"));
         
         // reads each input line of services into a string
         while ( in.hasNextLine() ){ 
            
            String dataFromBill = in.nextLine(); 
            
            // checks if the dataFromBill is null OR is empty
            // then breaks out of the the loop and exits
            if ( dataFromBill == null || dataFromBill.isEmpty( )) { 
               continue;                                                
            }
            
            // returns an array of strings, using a split method
            String[] billingInformation = dataFromBill.split("\\|");
            
            //if the position 0 is Service
            if ( billingInformation[0].equals("SERVICE") ) {
            
               String serviceName = billingInformation[1]; //assign the position 1 to serviceName
               Double servicePrice = Double.parseDouble(billingInformation[2]); //assign the position 2 to servicePrice
               
               //appent the data to the StringBuilder 
               sb.append("\n");
               sb.append("                Service: " + serviceName + " - $" + servicePrice + "\n");
               
            }
            
            else
               //if the position 0 is Product
               if (billingInformation[0].equals("PRODUCT") ) {
               
                  String productName = billingInformation[1]; //assign the position 1 to productName
                  Double productPrice = Double.parseDouble(billingInformation[2]); //assign the position 2 to productPrice
                  Double laborTime = Double.parseDouble(billingInformation[3]); //assign the position 3 to laborTime
                  
                  //appent the data to the StringBuilder
                  sb.append("                     - Product: " + productName + " - $" + productPrice + "\n");
                  sb.append("                         - Labor time: " + (int)(laborTime/60));
                  sb.append("  hour(s) and " + (int)(laborTime%60) + "  minute(s)\n");
                  
               }
         
         }
         
         //System.out.println(sb.toString());
      }
      
      // catch file not fount exception
      catch ( IOException io ) {
         System.out.println("Error: cannot find a file");
      }
      
      in.close(); // close file
   
      return sb.toString();
   }
   
   /**
   * Method to print the Sales Report,
   * the information is read from the billing.txt
   *
   * @return sb.toString() concatenamed string result
   */
   
   public static String printSalesReport() {
      
      Scanner in = null;
      
      // for more effective way of concatenating Strings use StringBuilder
      StringBuilder sb = new StringBuilder();
      sb.append("\n\n");
      sb.append("             *********   Sales report   *********\n");
      
      try  {
      
         in = new Scanner(new File("billing.txt"));
         
         // reads each input line of services into a string
         while ( in.hasNextLine() ){ 
            
            String dataFromBill = in.nextLine();     
            
            // checks if the dataElement is null OR is empty
            // then breaks out of the the loop and exits
            if ( dataFromBill == null || dataFromBill.isEmpty() ) { 
               continue;                                                
            }
            
            // returns an array of strings, using a split method
            String[] billingInformation = dataFromBill.split("\\|");
            
            // if the position 0 in the array is CUSTOMER, than set the data
            if ( billingInformation[0].equals("CUSTOMER") ) {
            
               String customerName = billingInformation[1]; //assign the position 1 to customerName
               String customerAddress = billingInformation[2]; //assign the position 2 to customerAddress
               Double grandTotal = Double.parseDouble(billingInformation[3]); //assign the position 3 to grandTotal
               Double hoursworked = Double.parseDouble(billingInformation[4]); //assign the position 4 to total hoursworked
               
               //appent the data to the StringBuilder
               sb.append("       ___________________________________________\n\n");
               sb.append("              Customer name and address: " + customerName + ", " + customerAddress + "\n");
               sb.append("              The total charge: $" + grandTotal + "\n");
               sb.append("              The hours worked: " + (int)(hoursworked/60));
               sb.append("  hour(s) and " + (int)(hoursworked%60) + "  minute(s)\n");
               
               
               sb.append("\n");
               sb.append("          . . . . . . .Details of the purchase. . . . . . ." + "\n");
            
            } 
            else
               // if the position 0 in the array is SERVICE, than set the data
               if (billingInformation[0].equals("SERVICE") ) {
               
                  String serviceName = billingInformation[1]; //assign the position 1 to serviceName
                  Double servicePrice = Double.parseDouble(billingInformation[2]); //assign the position 2 to servicePrice
               
                  //appent the data to the StringBuilder
                  sb.append("            Services: " + serviceName + "\n");
                  sb.append("                  - Price: $" + servicePrice + "\n");
               
               } 
               else
                  // if the position 0 in the array is PRODUCT, than set the data
                  if (billingInformation[0].equals("PRODUCT") ) {
                  
                     String productName = billingInformation[1]; //assign the position 1 to productName
                     Double productPrice = Double.parseDouble(billingInformation[2]); //assign the position 2 to productPrice
                     Double laborTime = Double.parseDouble(billingInformation[3]); //assign the position 3 to laborTime
                  
                     //appent the data to the StringBuilder
                     sb.append("                Product: " + productName + "\n");
                     sb.append("                  - Price: $" + productPrice + "\n");
                     sb.append("                         - Labor time: " + (int)(laborTime/60));
                     sb.append("  hour(s) and " + (int)(laborTime%60) + "  minute(s)\n");
                  
                  }
         }
         
         //System.out.println(sb.toString());
      }
      
      // catch file not fount exception
      catch ( IOException io ) {
         System.out.println("Error: cannot find a file");
      }
      
      in.close(); // close file
   
      return sb.toString();
   }
 
    /**
   * Method to print the Customer Report,
   * the information is read from the billing.txt
   *
   * @return sb.toString() concatenamed string result
   */     
     
   public static String printCustomerReport() {
         
      Scanner in = null;
      
      // for more effective way of concatenating Strings use StringBuilder
      StringBuilder sb = new StringBuilder();
      sb.append("\n\n");
      sb.append("        *********   Customer Report   *********\n");
      
      try  {
      
         in = new Scanner(new File("billing.txt"));
         
         // reads each input line of services into a string
         while ( in.hasNextLine() ){ 
            
            String dataFromBill = in.nextLine(); 
            
            // checks if the dataFromBill is null OR is empty
            // then breaks out of the the loop and exits
            if ( dataFromBill == null || dataFromBill.isEmpty()) { 
               continue;                                                
            }
            
            // returns an array of strings, using a split method
            String[] billingInformation = dataFromBill.split("\\|");
            
            // if the position 0 in the array is CUSTOMER, than set the data
            if ( billingInformation[0].equals("CUSTOMER") ) {
            
               String customerName = billingInformation[1]; //assign the position 1 to customerName
               String customerAddress = billingInformation[2]; //assign the position 2 to customerAddress
               Double grandTotal = Double.parseDouble(billingInformation[3]); //assign the position 3 to grandTotal
               Double hoursworked = Double.parseDouble(billingInformation[4]); //assign the position 4 to hoursworked
               
               //appent the data to the StringBuilder
               sb.append("       _____________________________\n\n");
               sb.append("              Customer name: " + customerName + "\n");
               sb.append("              Customer address: " + customerAddress + "\n");
               sb.append("              The total charge: $" + grandTotal + "\n");
               sb.append("\n");
               
            } 
         
         }
         
      }
      
      // catch file not fount exception
      catch ( IOException io ) {
         System.out.println("Error: cannot find a file");
      }
      
      
      in.close(); // close file
   
      return sb.toString();
   }

}   
