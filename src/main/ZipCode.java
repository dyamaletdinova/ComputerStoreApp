package main;
import java.util.Scanner;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
  * A class that convert the zip code into the bar code 
  * and reverce.
  *
  * @author Diana Yamaletdinova
  * @date 4/17/2015 
  *
  */
  
public class ZipCode{
   // numbers of the weight table converted into bar code, starts with 0
   private static final String[] BAR = new String[] 
      {"||:::", ":::||", "::|:|", "::||:", ":|::|", ":|:|:",
   			":||::", "|:::|", "|::|:", "|:|::"}; 
   
   /**
	 * Method that check if the zip code input is correct,
    * and calls the correct method to get the bar code for this zip code,
    * or calls the method to get the zip code for the bar code
    * based on the length of the input.
    *
    * @param input users input
    * @return result the result of the convertion
	 */

   public static String zipBarConverter(String input){
   
      String result = null;
      
         // if the input length equals to 5 , get the bar code     
      if ( input.length() == 5 ){ 
         result = returnBar(input); 
         
         // else if the input length equals to 32, get the zip code   
      } 
      else if ( input.length() == 32 ){
         result = getZipCode(input);
         
         // else if the input nor equals any of the above, notify the user
      } 
      else {   
         result = "\n\n\n\n     ******   The bar code/zip code converter   ******\n" + "\n\n\n" + 
                        "                        Invalid input. Please try again.";      
      }
   
      return result;
   }
  
  
    /**
	 * Method that adds up all digits of provided zip code,
    *  and choose the check digit to make the sum a multiple of 10.
    *
    * @param zipCode the zip code input
    * @return checkDigit the check digit
	 */
    
   public static int getCheckDigit (String zipCode){
      
      int sum =0; 
      
      // for each character in the input, separate and sum up
      for ( int i = 0; i < zipCode.length(); i++ ){  
         
         sum = sum + ( zipCode.charAt(i) - '0' ); // get i-th character in the string and add them up   
      }
   
      int checkDigit = 0;
      
      //while the sum + checkDigit is not multiple of 10 and not equal to 0
      // then increment checkDigit
      while ( (sum + checkDigit ) % 10 != 0 ){ 
      
         checkDigit++;                        
      }
      
      return checkDigit;
   }


   /**
	 * Method that converts the zip code into a bar code
    *
    * @param zipCode the zip code
    * @return barCode the bar code
	 */

   public static String returnBar (String zipCode){
     
      String barCode = "|"; // border frame
      
      String result = null;
      
      // check if the input value is an integer object
      try {
         Integer.valueOf( zipCode );        
      }
      
      // catch an exception is the previous checks failed and inform the user 
      catch ( NumberFormatException exception ) {
      
         result = "\n\n\n\n     ******   The bar code/zip code converter   ******\n" + "\n\n" + 
                           "                        Invalid input. Please try again.";
         
         return result;
      }
      
      // for each element in the zipcode take the barcode from the String[] BAR
      for ( int i = 0; i < zipCode.length(); i++){
      
         barCode = barCode + BAR[zipCode.charAt(i) - '0'];
      }
      
      // add a check digit bar code to the bar code total
      barCode = barCode + BAR[getCheckDigit(zipCode)];
       
      barCode = barCode + "|"; //second border frame
      
      return  "\n\n\n\n     ******   The bar code/zip code converter   ******\n" + "\n\n\n" + 
                     "                        The bar code is:\n\n" + "                        " + barCode;
   }

   
   /**
	 * Method that converts the bar code into a zip code
    *
    * @param barCode the bar code
    * @return zipCode the zip code
	 */

   public static String getZipCode (String barCode){
     
      String zipCode = "";
      barCode = barCode.substring (1, barCode.length() - 1); //remove the borders
    
      // for each element in the bar code locate the barcode from the String[] BAR
      for ( int i = 0, j = 5; j < barCode.length(); i = j, j+=5 ){
            
         String code = barCode.substring ( i, j );//extract substring bar code from string input
            
         for ( int k = 0; k < BAR.length; k++ ){ //for each element in the array BAR take the element
             
            if ( code.equals(BAR[k])) {          //and compare it to the code
               
               zipCode += k;                    // add the element to the zip code
            }
         }
      } 
      return "\n\n\n\n     ******   The bar code/zip code converter   ******\n" + "\n\n\n" +   
                  "                        The zipCode is:\n\n"  + "                              " + zipCode;
   }


}