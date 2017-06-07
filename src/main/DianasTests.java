package main;

import java.util.ArrayList;

/**
  * A Junit tester to test the InventoryManager class
  * to see if the right quantity of products and servises are loading 
  *
  * @author Diana Yamaletdinova
  * @date 5/2/2015 
  *
  */
  
public class DianasTests {

  /* ArrayList<Service> services = null;
	
   // loads the data from the products_and_services.csv before the @Test
   @Before
   public void loadData() throws Exception {
      InventoryManager im = new InventoryManager();
      services = im.loadInventory();
   }
	
   // does the test to make sure that 3 services are loaded
   @Test
   public void numberOfServiceLoaded() {
      assertEquals("Wrong number of services loaded", 3, services.size());
   }
   
   // does the test to make sure that 15 produsts are loaded
   @Test
   public void numberOfProductsLoaded() {
   	
      int totalNumberOfProducts = 0;
   	
      // looping though services arrayList, take each object one at a time and store it temporarily in s
         // to get the number of products that are loaded 
      for (Service s: services) {
      
         totalNumberOfProducts += s.getProducts().size(); 
      
      }
   
      assertEquals("Wrong number of products loaded", 15 , totalNumberOfProducts);
   }
	*/
}
