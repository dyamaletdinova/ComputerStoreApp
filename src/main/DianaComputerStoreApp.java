package main;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
import javax.swing.border.*;

/**
  * The Cumputer Store Aplication program with he main method that has a menu 
  * for 3 services and 5 products related to each of the services.
  *
  * @author Diana Yamaletdinova
  * @date 4/22/2015 
  *
  */

public class DianaComputerStoreApp  extends JFrame {

   private JFrame frame;
 // creates objects for the customer tab
// //  private JRadioButton aRadioButton; //aRadioButton object
   
//  // private JCheckBox aCheckBox; //aCheckBox object
   private JButton checkoutButton; //checkoutButton object
   private JButton cancelButtonl; //cancelButtonl object
   private JButton btnFindCustomer; //btnFindCustomer object
   
   // creates customerTabPanel and managerTabPanel
   private JPanel customerTabPanel = new JPanel(new BorderLayout());
   private JPanel managerTabPanel = new JPanel(new GridBagLayout());
   
   // creates  text area on the managerTabPanel
   private JTextArea managerTextArea;
   
   //creates an ArrayList for services
   private ArrayList<Service> services = null;
   // create a HashMap of product panel
   private HashMap<String, JPanel> productsPanels = new HashMap<String, JPanel>();
   
   //creates a ButtonHandler object
   private ButtonHandler phandler = new ButtonHandler();
   // creates changeListener object
   private ChangeListener changeListener;
   //creates JPanel object
   private JPanel selectedProductsPanel = null;
   //creates JTabbedPane object
   private JTabbedPane jtp = new JTabbedPane();

//creates JScrollPane object for the managerTab text area
// private JScrollPane scroll = null;

   
   /**
	 * method that builds the display of the main program 
	 */
    
   public DianaComputerStoreApp() {
      
      
      frame = new JFrame("Diana's Computer Store");//creats a frame 
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//set default close operation, exit when close frame
      frame.setSize(800, 600); // set size
      
      // creates tabs on the frame
      getContentPane().add(jtp);
      jtp.addTab("Customer", customerTabPanel); // adds Customer tab
      jtp.addTab("Manager", managerTabPanel); // adds Manager tab
   
      //implements ChangeListener to the managerTabPanel to log in the manager
      ChangeListener changeListener = 
         new ChangeListener() {
          
            public void stateChanged(ChangeEvent e){
            
               int selectedTab = jtp.getSelectedIndex();
               
               //if ManagerTab is selected then ask to login
               if (selectedTab == 1){ 
               
                  managerTextArea.setText(""); // sets the text area clean every time you log in and out
               
                  ManagerLogin mngLog = new ManagerLogin(jtp);
                  
               // if all three attempt are failed - lock the account  
                  if ( mngLog.loginIsBad(frame) && mngLog.loginIsBad(frame) && mngLog.loginIsBad(frame) ){ 
                     JOptionPane.showMessageDialog(null, "Your account is locked");
                     jtp.setSelectedIndex(0); // if locked out, then set the customer tab as true
                  }                  
               }
            }
         };
      // adds a changeListener to a JTabbedPane   
      jtp.addChangeListener(changeListener);
   
      // method to create product and services view
      createProductsAndServicesView();
      
      // method to create manager options view
      createManagerOptionsView(); 
      
      frame.add(jtp, BorderLayout.CENTER); // adds tabs to the frame
      
      frame.validate();;
      frame.setLocationRelativeTo(null); //center form
           //frame.setResizable(false);//freeze the resizing of the frame
           
      frame.setVisible(true);
      
   }
   
   
    /**
	     * Method that creates the manager options view after the manager logged in
        * using the GridBagLayout
	     * 
	     */

   public void createManagerOptionsView() {
   
      GridBagConstraints c = new GridBagConstraints(); // creates new gridBagConstraints object
      
      // position of area
      c.gridx = 0; 
      c.gridy = 0;
      
      // size of area in rows and columns count
      c.gridwidth = 1;
      c.gridheight = 1;
      
      // specify how much space compare to the other columns and rows this area should take
      c.weightx = 0.0;
      c.weighty = 0.5;
      
      c.fill = GridBagConstraints.BOTH;
      c.insets = new Insets (10, 10, 15, 15);
              
      
      JPanel managerButtonPanel = new JPanel(new GridLayout(6, 1, 20, 20));//creates the managerButtonPanel
      managerButtonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
         
      //adds managerButtonPanel to the manager tab
      managerTabPanel.add(managerButtonPanel, c); 
      managerTabPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
   
      // adds the buttons to the manager panel
      JButton btnFindCustomer = new JButton("Find Customer..."); // Find Customer button     
      btnFindCustomer.addActionListener(phandler); //adds actionListener to Find Customer button          
      managerButtonPanel.add(btnFindCustomer); // adds Find Customer button to the manager button panel  
      JButton btnProductReport = new JButton("Customer Report");
      btnProductReport.addActionListener(phandler);
      managerButtonPanel.add(btnProductReport);
      JButton btnServiceReport = new JButton("Service Report");
      btnServiceReport.addActionListener(phandler);
      managerButtonPanel.add(btnServiceReport);
      JButton btnSalesReport = new JButton("Sales Report");
      btnSalesReport.addActionListener(phandler);
      managerButtonPanel.add(btnSalesReport);
      JButton btnZipBar = new JButton("Zip/Bar Code...");
      btnZipBar.addActionListener(phandler);
      managerButtonPanel.add(btnZipBar);
      JButton btnExit = new JButton("Exit");
      btnExit.addActionListener(phandler);
      managerButtonPanel.add(btnExit);
     
      // specifies constraints for components that are laid out using the GridBagLayout class
      c = new GridBagConstraints();
      
      // Position of area
      c.gridx = 1; 
      c.gridy = 0;
      
      // Size of area in rows and columns count
      c.gridwidth = 4;
      c.gridheight = 1;
      
      // specify how much space compare to the other columns and rows this area should take
      c.weightx = 0.1;
      c.weighty = 0.5;
      
      c.fill = GridBagConstraints.BOTH;
   
      // creates a Text area to display the results of the manager panel options
      managerTextArea = new JTextArea();
      managerTabPanel.add(managerTextArea, c);
      // creates a scroll object to add it to the text area
      JScrollPane scroll = new JScrollPane(managerTextArea);
      managerTabPanel.add(scroll,c);
   
   }
   
   
	/** 
     * Class that handles the button-click event
     * for Exit and Checkout buttons on the main Customer Tab
	  * 
	  */

   class ButtonHandler implements ActionListener {
   
      public void actionPerformed(ActionEvent e){
         managerTextArea.setText("");
      
         if (e.getActionCommand().equals("Exit") ){
            System.exit(0);
         }
         
         // if the Checkout button was choosen, then collect the users choices to calculate the total charges
         if (e.getActionCommand().equals("Checkout") ){
            
            // ArrayList of selected service and associated with it products 
            ArrayList<ServiceWithProducts> customerSelected = new ArrayList<ServiceWithProducts>();
          
           // setting the customer selection from the products panel
            for ( JPanel jp: productsPanels.values()) {
               setCustomerSelection(jp, customerSelected);
            }
                    
            CustomerAccount ca = new CustomerAccount();
            CustomerInfo ci = new CustomerInfo(jtp);
            
            // asks for the name and address and save the data to a file
            boolean saved = ci.saveCustomerInfoToFile(frame, ca);
            
            // if returnes true then continue into the method saveCustomerInfoToFile()
            if ( saved ) {
            // sets the selected services and products
               ca.setSelectedProductsAndServices(customerSelected);
            
            // and generate the bill and display on the screen
               ChargesCalculator cc = new ChargesCalculator();
            
               cc.displayBill(frame, ca);
            
            //BillingWrites class to write the billing data to the file
               BillingWriter bw = new BillingWriter();
               bw.writeBillToFile(ca); // update the billing file 
            }
             
            return;
         }
         
         // if "Find Customer" button was chosen       
         if ( e.getActionCommand().equals("Find Customer...") ){
         
            // prompt for input name
            String inputName = JOptionPane.showInputDialog(frame, "Please Enter Customer's name"); 
            
            // if input name is not null and not empty, then call the getCustomerInfoFromFile() method from CustomerInfo class
            if ( (inputName !=  null && !inputName.isEmpty()) ){
            
               CustomerInfo ci = new CustomerInfo();
               String msg = ci.getCustomerInfoFromFile(inputName);
               managerTextArea.setText(msg);  // set the results in the text area
            }                              
           
            return;
         }
         
         // if "Customer Report" button was chosen
         if ( e.getActionCommand().equals("Customer Report") ){
         
            ReportPrinter rp = new ReportPrinter();//creates ReportPrinter object  
            String msg = rp.printCustomerReport(); // calls the printCustomerReport method to display the customer report
            managerTextArea.setText(msg);// set the results in the text area
            
            return;
         }
         
         // if "Service Report" button was chosen
         if ( e.getActionCommand().equals("Service Report") ){
         
            ReportPrinter rp = new ReportPrinter(); //creates ReportPrinter object
            String msg = rp.printServicesReport(); // calls the printServicesReport method to display the service report
            managerTextArea.setText(msg);// set the results in the text area
            
            return;
         }
         
         // if "Sales Report" button was chosen
         if ( e.getActionCommand().equals("Sales Report") ){
         
            ReportPrinter rp = new ReportPrinter(); //creates ReportPrinter object
            String msg = rp.printSalesReport(); // calls the printSalesReport method to display the sales report
            managerTextArea.setText(msg);// set the results in the text area
            return;
         }
      
         
         
         // if "Zip/Bar Code..." button was chosen
         if ( e.getActionCommand().equals("Zip/Bar Code...") ){
           
            // prompt for input 
            String input = JOptionPane.showInputDialog(frame, "Please Enter Zip Code or Bar Code");
            // if input name is not null and not empty, then call the ZipCode.zipBarConverter() method from ZipCode class
            if ( input != null && !input.isEmpty()){
               String msg = ZipCode.zipBarConverter(input);
               managerTextArea.setText(msg);// set the results in the text area
            
               return;
            }
         } 
         
         // if "Exit" button was chosen, exit the program   
         if (e.getActionCommand().equals("Exit") ){
            System.exit(0);
            return;
         } 
      }
   
   /**
    * Class that sets Customer Selection
    * @param jp the JPanel
    * @param customerSelected the ArrayList of selected product and services
    */
      
      private void setCustomerSelection(JPanel jp,
      	ArrayList<ServiceWithProducts> customerSelected) {
         
         // looping though the checkboxes
         for ( Component c: jp.getComponents() ) {
          
            JCheckBox jcb = (JCheckBox)c;  //and gets checkboxes
            
            //and if they selected then add it to the customer selection
            if ( jcb.isSelected() ) {      
               addCustomerSelection(jcb.getActionCommand(), getNameWithoutPrice(jcb.getText()), customerSelected);
            }
         }
      
      }
   
   
   /**
    * Class that adds Customer Selection
    * @param serviceName the name of the service
    * @param productName the name of the product
    * @param customerSelected the ArrayList of selected product and services
    */
   
      private void addCustomerSelection(String serviceName, String productName,
      	ArrayList<ServiceWithProducts> customerSelected) {
         
         //looping though services check if the service name from InventoryManager class 
         //equals service name from the checkbox(s is a service from the InventoryManager)
         for ( Service s: services ) {
         
            //if the name is equals, then contunue next to line 370, 
            //if not then go back and look again
            if ( !s.getServiceName().equals(serviceName) ){ 
               continue;
            }
            
            //looping though products of particular choosen service
            //check if the product name from the InvManag equals product name of the checkboxe than continue 
            // if not, then go back and keep checking
            for ( Product p: s.getProducts() ) {
               
               if ( !p.getProductName().equals(productName) ) {
                  continue;
               }
               
               ServiceWithProducts alreadySelectedService = null;
            	
               //looping thgough selected products and services
               // get the service and associated products
               for ( ServiceWithProducts swp: customerSelected) {
               
                  //check if the service was already added to the ServiceWithProducts list
                  if ( swp.getService().getServiceName().equals(s.getServiceName())) {
                     alreadySelectedService = swp;
                     break;
                  }
               }
            	
               // adds product to the service already in the list
               if ( alreadySelectedService != null ) {
                  alreadySelectedService.getProducts().add(p);
               } 
               else { 
                  //add the service and product to the list
                  ArrayList<Product> prods = new ArrayList<Product>(Arrays.asList(p));
                  customerSelected.add(new ServiceWithProducts(s, prods));
               }
            
               return;
            }
         }
      }
   
   }
   
   /**
    * Class that creates a service and product panels on the customerTabPanel;
    * creates and adds radio boxes and check boxes to the  servicePanel, productPanel
    *  and Exit and Checkout buttons to buttonPanel
    */
    
   public void createProductsAndServicesView() {
      
      InventoryManager im = new InventoryManager();
      
      try {
         services = im.loadInventory(); // loads the inventory from InventoryManager
         
      } 
      catch ( FileNotFoundException fnfe ) {
         // If this error then put a text message in the customerPanel;
      }
   
      // set the layout of the servicePanel
      JPanel servicePanel = new JPanel();
      // doesn't matter how much products and services in the original file, it just gets the siz
      servicePanel.setLayout(new GridLayout(services.size(), 1)); 
      servicePanel.setBorder(new TitledBorder(new EtchedBorder(), "Services"));   
      customerTabPanel.add(servicePanel, BorderLayout.LINE_START);  //adds the buttonPanel to the tab
      
      ButtonGroup group = new ButtonGroup();//creates button group
            
      //creates radio buttons on a service panel
      if ( services != null ) {
      
         RadioActionListener radioListener = new RadioActionListener(); //creates radioListener object
         
         // looping through services sets the radio buttons with serices names and prices
         for ( Service s: services ) {
            String servicePrice = Double.toString(s.getServicePrice()); // convers double price to string
            JRadioButton aRadioButton = new JRadioButton(s.getServiceName() + "  $" + servicePrice);
            servicePanel.add(aRadioButton);
            group.add(aRadioButton);
            aRadioButton.addActionListener(radioListener);
            
             //creates checkbox product panel
            JPanel productPanel = new JPanel();
            // doesn't matter how much products and services in the original file, it just gets the size
            productPanel.setLayout(new GridLayout(s.getProducts().size(), 1)); 
            productPanel.setBorder(new TitledBorder(new EtchedBorder(), "Products")); 
            
            //adds checkboxes to the checkBox panel  with products names, prices and inventory numbers
            for ( Product p: s.getProducts() ) {
               JCheckBox aCheckBox = new JCheckBox(p.getProductName() + "  $" + p.getProductPrice() + ",   Inventory  #" + p.getInventoryNumber(), false);
               aCheckBox.setActionCommand(s.getServiceName());
               productPanel.add(aCheckBox);
            }
            
            // adds product checkbox panel depending of what service was choosen
            productsPanels.put(s.getServiceName(), productPanel); 
            
         }
      }
    
      // set the layout of the Producs tPanel
      JPanel productPanel = new JPanel();
      productPanel.setLayout(new GridLayout(5, 1));
      productPanel.setBorder(new TitledBorder(new EtchedBorder(), "Products")); // border for the product panel
      selectedProductsPanel = productPanel;      
      customerTabPanel.add(productPanel, BorderLayout.CENTER);//adds the productPanel to the tab
   
     // set the layout of the button Panel
      JPanel buttonPanel = new JPanel();
      buttonPanel.add(Box.createHorizontalGlue());
      JButton checkoutButton = new JButton("Checkout"); // checkout button
      buttonPanel.add(checkoutButton);
      checkoutButton.addActionListener(phandler); // adds action listener object
      JButton exitButton = new JButton("Exit");//cancel button
      buttonPanel.add(exitButton);
      exitButton.addActionListener(phandler); // adds action listener object
      customerTabPanel.add(buttonPanel, BorderLayout.PAGE_END);//adds the buttonPanel to the tab   
   
   }
   
   
   private String getNameWithoutPrice(String itemNameAndPrice) {
      return itemNameAndPrice.substring(0,itemNameAndPrice.indexOf("$")-1).trim();
   }
   
   /**
    * Class that inplements ActionListener for radio buttons;
       * it removes one panel and adds another when customer chooses 
       * different services
       *
    */

   private class RadioActionListener implements ActionListener {
       
       // method to implement the action depending on the services and products selected
      public void actionPerformed(ActionEvent e){
      
         JRadioButton button = (JRadioButton) e.getSource();
        
         if ( selectedProductsPanel != null ) {
            customerTabPanel.remove(selectedProductsPanel);
         }
        
        // gets the names of the services and products from the products_and_services.csv file when starting the program
         selectedProductsPanel = productsPanels.get(getNameWithoutPrice(button.getText()));
         customerTabPanel.add(selectedProductsPanel, BorderLayout.CENTER);
         customerTabPanel.invalidate(); 
       
         // remove one servce panel and add another when clicked on the radio buttons
         customerTabPanel.validate();
         customerTabPanel.repaint();
      
      }
   }
   
   
   /**
    * the main method to display the Computer store app;
    */
     
   public static void main(String args[]) throws FileNotFoundException {
      new DianaComputerStoreApp();
       
   }

}