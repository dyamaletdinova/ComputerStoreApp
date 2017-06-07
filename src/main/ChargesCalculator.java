package main;
import java.util.Scanner;
import java.util.ArrayList;
import java.text.DecimalFormat;
import javax.swing.*;
import java.awt.*;

/**
  * A class to calculate the totalCharge for the customer account.
  * This class calculates the totals for selected products and services, 
  * and displays the total charges delailed bill in the main program 
  *
  * @author Diana Yamaletdinova
  * @date 4/13/2015 
  *
  */
  
public class ChargesCalculator {  

   /**
	 * Method updateTotalCharge that calculates the total charges 
	 * of selected services and associated with it products,
    * and updates the totals in CustomerAccount
    *
    * @param selectedService the selected service
    * @param ArrayList<Product> selectedProducts products asociated with the choosen service
    * @param ServiceWithProducts swp the services and products
	 */
   
   public static void updateTotalCharge (Service selectedService, ArrayList<Product> selectedProducts, ServiceWithProducts swp){
   
      double productTotal = 0; // the product total for the products associated with selected service
      double laborTime = 0; // labor time of the products associated with selected service
      double totalCharge = 0; // total charge for schoosen service and associated with it products 
      
      // looping though selectedProducts list, take each object one at a time and store it temporarily in p
      // to get the product prices and labor time for each of the products   
      for ( Product p : selectedProducts ){ 
         
         productTotal = productTotal + p.getProductPrice(); // calculates the total for all products
         laborTime = laborTime + p.getLaborTime(); // calculates the total hours of work for all products
      }
      
      totalCharge = productTotal + ((laborTime / 60.00) * selectedService.getServicePrice()); // total charges for choosen service and products
      
      swp.setTotalCharge (totalCharge); //sets the updated totalCharge in CustomerAccount
      swp.setLaborTime (laborTime); //sets the updated hoursWorked in CustomerAccount
      
   }  

    
   /**
	 * Method calculateUserCharges that calculates the charges 
	 * of selected products and service from the Main method,
    * prints the total delailed bill,
    * and updates the totals in CustomerAccount
    *
    * @param CustomerAccount customerAccount the customer account
    * @return sb.toString() the total bill 
	 */
   
   public static String calculateUserCharges(CustomerAccount customerAccount){
   
   
      double grandTotal = 0; // grand total for few services and it's products from main method
      double hoursWorked = 0;
      
      // for more effective way of concatenating Strings use StringBuilder
      StringBuilder sb = new StringBuilder();
      sb.append("\n\n");
      sb.append("               *****THE TOTAL BILL*****\n\n");
      sb.append("                  Name:  " + customerAccount.getName() + "\n");
      sb.append("                  Address:  " + customerAccount.getAddress() + "\n"); 
      sb.append("\n");
    
      // looping though SelectedProductsAndServices in customer account take each object one at a time and store it temporarily in swp
      // to get the choosen services and their prices
      for(ServiceWithProducts swp: customerAccount.getSelectedProductsAndServices()){
         
         // append the results
         sb.append("\n" + "         Service:  " + swp.getService().getServiceName() + "\n");
         sb.append("                     Price:  $" + swp.getService().getServicePrice()+ "\n");
      
         ArrayList<ServiceWithProducts> customerSelected = new ArrayList<ServiceWithProducts>();
         
         // to get the choosen products and their prices
         for ( Product p: swp.getProducts() ) {
            
            sb.append("               Product:   " + p.getProductName() + "\n");
            sb.append("                  Price:   $" + p.getProductPrice());
            sb.append("\n");               
         }
         
         // updates the total charges for selected products and services
         updateTotalCharge( swp.getService(), swp.getProducts(), swp );
         
         //calculates the grand total and total hours worked 
         //summing upp charges of all products and services
         hoursWorked = hoursWorked + swp.getLaborTime();
         grandTotal = grandTotal + swp.getTotalCharge();
      
      }
      
      // updates the grand total in totalCharges in customer account
      customerAccount.setTotalCharge (grandTotal);
      // updates the hoursWorked in customer account
      customerAccount.setHoursWorked (hoursWorked);
         
      //formating the grandTodal to two decimal places
      DecimalFormat df = new DecimalFormat("0.00");
      sb.append("\n");
      sb.append("            Total Hours Worked:   " + (int)(hoursWorked/60));
      sb.append("  hour(s) and " + (int)(hoursWorked%60) + "  minute(s)\n");
      sb.append("            GRAND TOTAL IS:      $" + df.format(grandTotal) + "\n");
      
      String totalBill = sb.toString();
      
      return totalBill;
      
   }
   
   public static void displayBill(JFrame parentWindow, CustomerAccount ca) { 
   
      //creates bill panel
      JPanel billPanel = new JPanel(new GridLayout(1, 1));
      JTextArea billTextArea  = new JTextArea(25, 30);
      JScrollPane scroll = new JScrollPane();
      billPanel.add(billTextArea);
      scroll = new JScrollPane(billTextArea);
      billPanel.add(scroll);
      
      String totalBill = ChargesCalculator.calculateUserCharges(ca);
      billTextArea.setText(totalBill);
      
      JOptionPane.showConfirmDialog(parentWindow, billPanel, "YOUR BILL", JOptionPane.CLOSED_OPTION);
  
   }
 
 
 /*
      
   public static void main(String args[])  {
      
       ChargesCalculator cc = new ChargesCalculator();
       CustomerAccount ca = new CustomerAccount();
       cc.calculateUserCharges(ca);
       cc.displayBill(ca);
   }*/
}
