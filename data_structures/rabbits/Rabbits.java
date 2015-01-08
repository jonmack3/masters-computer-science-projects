/* Jonathan Mack
 * Project2.java: this class starts with an initial pair of rabbits, inputs a total number of months elapsed,
 * and a frame size, then draws rabbits on the frame, with pink circles as
 * female rabbits, and blue circles as male rabbits.
 * This class uses RabbitPopulation.java, and DrawRabbits.java
 */

import java.util.Scanner;
import javax.swing.JFrame;

public class Project2 {

  public static void main(String args[]) {

    /* initialize variables
     * @param RabbitPairs the method in class RabbitPopulation that calculates the total number of rabbit pairs
     * @param frameX the width of the drawing frame in pixels
     * @param frameY the height of the drawing frame in pixels
     */
    
    int RabbitPairs, frameX, frameY, monthsElapsed;
    
    // assign variables
    
    frameX= -1;
    frameY = -1;
    monthsElapsed = -1;
    
    // create new Scanner object to read in data
    
    Scanner in = new Scanner(System.in);

    // get months elapsed, and repeat until a correct value is typed
    
    while (monthsElapsed < 0) {
      System.out.println("How many months have elapsed?");
      monthsElapsed = in.nextInt();
    } //end of elapsed months while loop

    // get width of screen, and repeat until a correct value is typed
    
    while (frameX < 200) {
      System.out.println("What screen width (in pixels) would you like? (Must be at least 200)");
      frameX = in.nextInt();
    } // end of frame width while loop
    
    // get height of screen, and repeat until a correct value is typed
    
    while (frameY < 200) {
      System.out.println("What screen height (in pixels) would you like? (Must be at least 200)");
      frameY = in.nextInt();
    } // end of frame height while loop
    
    // constructs a new instance rabbitPairs of the RabbitPopulation class
    
    RabbitPopulation rabbitPairs = new RabbitPopulation();
    rabbitPairs.calculatePairs(monthsElapsed);

    // constructs a new frame, and draws the rabbits in it
    // @param frame the drawing frame

    JFrame frame = new JFrame();
    frame.setSize(frameX, frameY);
    DrawRabbits rabbitTotal = new DrawRabbits(rabbitPairs.getRabbitPairs(), frameX, frameY);
    frame.add(rabbitTotal);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  } // end of main method
} // end of class Project2
