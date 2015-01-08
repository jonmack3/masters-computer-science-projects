/* Jonathan Mack
 * CS221
 * Programming Assignment 10
 * Class GraphTester
 * Fall 2006
 * December 6, 2006 */

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GraphTester
{
  public static void main (String[] args)
  {
    int start, end;
    // get data from files
    try
    {
      // adjacency list file
      FileReader inFile = new FileReader("graph.txt");
      Scanner in = new Scanner(inFile);
      
      // initialize ArrayLists
      int length = in.nextInt();
      ArrayList[] adjacencyList = new ArrayList[length];
      ArrayList[] adjacencyListTranspose = new ArrayList[length];
      for (int i = 0; i < length; i++)
      {
        adjacencyList[i] = new ArrayList();
        adjacencyListTranspose[i] = new ArrayList();
      } // end for
      
      // create edge array
      ArrayList <Integer> edgeArray = new ArrayList<Integer>();

      // populate adjacencyList, edgeArray
      while(in.hasNext())
      {
        start = in.nextInt();
        end = in.nextInt();
        adjacencyList[start].add(end);
        edgeArray.add(start);
        edgeArray.add(end);
      } // end while
      
      // populate transpose array
      for (int i = 0; i < edgeArray.size(); i = i + 2)
      {
        adjacencyListTranspose[edgeArray.get(i+1)].add(edgeArray.get(i));
      } // end for
      
      Graph graph1 = new Graph(adjacencyList);
      Graph graph2 = new Graph(adjacencyListTranspose);
      
      int fSaved[] = new int[adjacencyList.length];
      graph1.depthFirstSearch();
      fSaved = graph1.returnArray();
      graph2.stronglyConnectedComponents(fSaved);
      
      graph2.printSCCs();
        
      in.close();
    } // end try
    catch (IOException exception)
    {
      System.out.println("File processing error: " + exception);
    } // end catch
  } // end main
} // end GraphTester