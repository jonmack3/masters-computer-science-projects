/* Jonathan Mack
 * 7/25/06
 * generates a 3D array and prints the results */

import java.util.*;

public class SalesTables
{
  public static void main(String[] args)
  {
    // 2 salesmen, 3 models, 12 months
    Random generator = new Random();
    int salesByMonth[][][] = new int[12][3][2];
    for (int month = 0; month < salesByMonth.length; month++)
    {
      for (int model = 0; model < salesByMonth[0].length; model++)
      {
        for (int salesperson = 0; salesperson < salesByMonth[0][0].length; salesperson++)
        {
          salesByMonth[month][model][salesperson] = generator.nextInt(10000);
        } // end salesperson for
      } // end model for
    } // end month for
    printSales(salesByMonth);
  } // end main

  public static void printSales(int salesByMonth[][][]) 
  {
    for (int month = 0; month < salesByMonth.length; month++) 
    {
      switch(month)
      {
        case 0: System.out.println("January"); break;
        case 1: System.out.println("February"); break;
        case 2: System.out.println("March"); break;
        case 3: System.out.println("April"); break;
        case 4: System.out.println("May"); break;
        case 5: System.out.println("June"); break;
        case 6: System.out.println("July"); break;
        case 7: System.out.println("August"); break;
        case 8: System.out.println("September"); break;
        case 9: System.out.println("October"); break;
        case 10: System.out.println("November"); break;
        case 11: System.out.println("December"); break;
      } // end switch
      System.out.println("       Moe    Larry");
      for (int model = 0; model < salesByMonth[0].length; model++)
      {
        switch (model)
        {
          case 0: System.out.print("Pinto  "); break;
          case 1: System.out.print("Neon   "); break;
          case 2: System.out.print("Ram    "); break;
        } // end switch
        for (int salesperson = 0; salesperson < salesByMonth[0][0].length; salesperson++)
        {
          System.out.print(salesByMonth[month][model][salesperson] + "  ");
        } // end for                           
        System.out.println();
      } // end for
      System.out.println();
    } // end for
  } // end printSales
} // end SalesTables