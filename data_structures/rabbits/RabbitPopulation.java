/* Jonathan Mack
 * RabbitPopulation.java: this class computes the total number of rabbits present after a given 
 * number of months using the exponential method.
 */

public class RabbitPopulation {

    // @param rabbitPairs the total number of pair of rabbits
  
    private int rabbitPairs;

    // @param calculateParis calculates the total number of rabbit pairs
    // @param monthsElapsed the total number of months elapsed
    
    public void calculatePairs(int monthsElapsed) {

       /* @param INITIAL_PAIRS initial number of rabbit pairs
       * @param GROWTH_RATE rate of population growth
       */

        final double INITIAL_PAIRS = 1.0;
        final double GROWTH_RATE = .11;
        rabbitPairs = (int)(Math.round(INITIAL_PAIRS * Math.exp(GROWTH_RATE * monthsElapsed)));
    } // end of calculatePairs

    // returns the total number of pairs of rabbits
    // @return rabbitPairs the total number of pairs of rabbits
    
    public int getRabbitPairs() {
      return rabbitPairs;
    } // end of getRabbitPairs

} // end of RabbitPopulation