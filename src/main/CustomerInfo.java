package main;
import java.util.*;
import java.io.*;
import javax.swing.*;

/**
  * A class that creates a panel to prompt for the name and address
  * of the customer when the customer is ready to check out  
  *
  * @author Diana Yamaletdinova
  * @date 4/22/2015 
  *
  */

public class CustomerInfo {   

   private String customerName;
   private String customerAddress;
   private JPanel customerInfoPanel = null;
   private CustomerAccount ca = new CustomerAccount();
   private JTabbedPane jtp = null;
    
   /**
    * constructor that has no argument
    *
    */ 
    
   public CustomerInfo( ){
   }
   
   /**
    * constructor that has one argument
    *
    * @param jtp the JTabbedPane from the main program
    */ 
    
   public CustomerInfo( JTabbedPane jtp ){
      this.jtp = jtp;
   }

  /**
   * Method to save the customer info to the file
   *
   * @param the CustomerAccount object with the transaction information
   */
   
   public boolean saveCustomerInfoToFile(JFrame parentWindow, CustomerAccount ca) { 
   
      //creates two fielad for the panel
      JTextField customerNameField = new JTextField(15); 
      JTextField customerAddressField = new JTextField(15);
   
      customerInfoPanel = new JPanel();   //creates JPanel
      customerInfoPanel.add(new JLabel("Name:"));//adds label
      customerInfoPanel.add(customerNameField);//adds field
      customerInfoPanel.add(new JLabel("Address:"));
      customerInfoPanel.add(customerAddressField);
       
      int selection = JOptionPane.showConfirmDialog(parentWindow, customerInfoPanel, "Please enter your name and address", JOptionPane.OK_CANCEL_OPTION);  
      
      // if user choose to close the window, return to the main menu
      if (selection == JOptionPane.CANCEL_OPTION ) { 
      
         int selectedIndex = jtp.getSelectedIndex();// get the index of the selected Tab
         jtp.setSelectedIndex(0); // set the selected index to 0 (that will return you to CustomerTab)
      
         return false;
      }
      
      // sets the input name and address to the customer account
      ca.setName(customerNameField.getText());
      ca.setAddress(customerAddressField.getText());
     
     return true;
   }
   
   
   
   /**
   * method to reads  the custome info from the billing.txt file 
   * when manager ckickes on "Find Customer..". button
   *
   * @param inputName the input name
   * @return sb.toString() concatenamed string result
   */
   
   public String getCustomerInfoFromFile(String inputName) {
      
      Scanner in = null;
      
      StringBuilder sb = new StringBuilder();
      
      String cName = null;
      String cAddress = null;
      boolean printed = false;
      
      try  {
      
         in = new Scanner(new File("billing.txt"));
      
         while ( in.hasNextLine() ){ // reads each input line of services into a string
            
            String dataFromBill = in.nextLine(); 
            
            // checks if the dataElement is null OR productElement is empty
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
               
               // if input is not empty and inpuNname equals to customerName
               // whether the input is contains what is in the file, do the search and print the results
               if ( ( inputName !=  null && !inputName.isEmpty() ) && customerName.trim().toLowerCase().contains(inputName.trim().toLowerCase() ) ){
               
                  cName = customerName;// assign the cName to customerName
                  cAddress = customerAddress;
                  
                  //appent the data to the StringBuilder
                  sb.append("\n\n");
                  
                  if ( !printed ) {
                     sb.append("                 The search result is:\n\n");
                     printed = true;
                  }
                  sb.append("          Customer name:      " + cName + "\n");
                  sb.append("          Customer address:   " + cAddress + "\n");
               
               }
            } 
         }
         
      }
      
      // catch file not fount exception
      catch ( IOException io ) {
         System.out.println("Error: cannot find a file");
      }
      
      in.close(); // close file
      
      //  if there is no such name then inform the user
      if ( cName == null ){
      
         sb.append("Search returned no results");
      }
      
      return sb.toString();
   }

}
