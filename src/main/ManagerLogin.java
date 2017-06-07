package main;
import javax.swing.*;


 /**
  * A class for manager login for ManagerTab
  *
  * @author Diana Yamaletdinova
  * @date 4/24/2015
  *
  */

public class ManagerLogin {
   
   private String username = "123";
   private String password = "123";
   private int numberOfTries = 1;
   private JPanel loginPanel = null;
   private JPanel customerTabPanel = null;
   private JTabbedPane jtp = null ;
   
   /**
    * constructor that has no arguments
    *
    */      
   public ManagerLogin(){
   }


   /**
    * constructor that has one arguments
    *
    * @param JTabbedPane jtp the tabbed pane from the main program
    */ 
   public ManagerLogin( JTabbedPane jtp ){
      this.jtp = jtp;
   }
   

   /**
    * method that let the user etrer the pasword and login 
    * and checks if it correct
    *
    * @return true if the password and login is not correct, otherwise return false.
    */ 
    
   public boolean loginIsBad(JFrame frame) {
       
       loginPanel = new JPanel();   //creates JPanel
            
      //creates text fields for password and login
      JTextField userNameField = new JTextField(10); 
      JTextField passwordField = new JTextField(10);
   
      loginPanel.add(new JLabel("Username:"));//adds label
      loginPanel.add(userNameField);//adds field
      loginPanel.add(new JLabel("Password:"));
      loginPanel.add(passwordField);
         
      int selection = JOptionPane.showConfirmDialog(frame, loginPanel, "Please enter your username and password", JOptionPane.OK_CANCEL_OPTION);  
      
      //if user cancels the login panel, then  return to the CustomerTab
      if (selection == JOptionPane.CANCEL_OPTION ) { 
      
            int selectedIndex = jtp.getSelectedIndex();// get the index of the selected Tab
            jtp.setSelectedIndex(0); // set the selected index to 0 (that will return you to CustomerTab)
            
      return  false;

      }

      // if the login and password are wrond   
      if ( !(passwordField.getText().equals(password) && userNameField.getText().equals(username) )){  
              
        // and the number of tries are less then 3, then prompt for input again
         if ( numberOfTries++ < 3 ){
            JOptionPane.showMessageDialog(frame,"The login is invalid, please try again!");
         }
         
         return true;
      }
          
       //if the input is correct - logged in  
      JOptionPane.showMessageDialog(frame,"You logged in");
      
      return false;
   }

}


