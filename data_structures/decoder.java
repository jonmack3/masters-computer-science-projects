/* Jonathan Mack
 * Assignment6.java: this class reads text from a file, reverses it, removes #'s, and prints the results
 */

import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

public class Assignment6
{
  public static void main(String[] args)
  {

    /* @param i counting variable
     * @param j counting variable
     * @param stringLength length of the original string from the file
     * @param arrayPosition position inside the array
     */
    
    int i, j, stringLength, arrayPosition;
    String secretMessage;
    j = 0;
    
    secretMessage = "";
    
    // get the data from file secret.txt, and check for errors
    
    try {
      FileReader reader = new FileReader("secret.txt");
      Scanner in = new Scanner(reader);
      secretMessage += in.next();
    } // end of try
    
    catch (IOException exception) {  
      System.out.println("Error processing file: " + exception);
    } // end of catch  
    
    /* create the arrays
     * @array secret original text
     * @array decoded decoded text
     */
    
    stringLength = secretMessage.length();
    char[] secret = new char[stringLength];
    char[] decoded = new char[stringLength];
    secret = secretMessage.toCharArray();
    
    arrayPosition = stringLength - 1;
    
    // decode the message, and place in decoded
    
    for (i = arrayPosition; i >= 0 ; i--) {
      if ((secret[i] != '#') && (secret[i] != ' ')) {
        decoded[j] = secret[i];
        System.out.printf("%1.1s", decoded[j]);
        j = j + 1;
      } // end of while loop
    } // end of for loop
    System.out.println();
  } // end of main method
} // end of Assignment 6 class