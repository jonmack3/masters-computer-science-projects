/* Jonathan Mack
 * DrawRabbits.java: this class adds rabbit circles to the frame
 * This class uses RabbitPopulation.java
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.Color;
import java.util.Random;

public class DrawRabbits extends JComponent {

  /* initialize variables
   * @param rabbitPairs the number of rabbit pairs
   * @param frameWidth width of the drawing frame
   * @param frameHeight height of the drawing frame
   */
  
  int rabbitPairs, frameWidth, frameHeight;

  // construct an instance of the DrawRabbits class
  
    public DrawRabbits (int pairsRabbits, int frameX, int frameY) {
      rabbitPairs = pairsRabbits;
      frameWidth = frameX;
      frameHeight = frameY;
    }  // end of constructor DrawRabbits
    
    // @param rand instance of random to create random integers
    
    Random rand = new Random();
  
    /* add rabbits to frame
     * @param g instance of Graphics class
     * @param g2 instance of Graphics2D class
     * @param i counting variable
     */
    
    public void paintComponent(Graphics g) {

      Graphics2D g2 = (Graphics2D) g; 

      // add two circles (one blue, one pink) to frame for each rabbit pair
      int i;
      
      for (i = 1; i<=rabbitPairs; i++) {

        // add male (blue) rabbit circle to frame
      
        Ellipse2D.Double maleRabbitCircle = new Ellipse2D.Double(rand.nextInt(frameWidth-80), rand.nextInt(frameHeight-80), 40, 40);
        g2.setColor(Color.BLUE);
        g2.fill(maleRabbitCircle);
                                                          
        // add female (pink) rabbit to frame
        
        Ellipse2D.Double femaleRabbitCircle = new Ellipse2D.Double(rand.nextInt(frameWidth-80), rand.nextInt(frameHeight-80), 40, 40);
        g2.setColor(Color.PINK);
        g2.fill(femaleRabbitCircle);
                                                          
        } // end rabbitPairs for loop
    } // end paintComponent method
} // end DrawRabbits class
