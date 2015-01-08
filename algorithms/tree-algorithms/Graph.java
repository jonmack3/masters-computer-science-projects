/* Jonathan Mack
 * CS221
 * Programming Assignment 10
 * Class Graph
 * Fall 2006
 * December 6, 2006 */

import java.util.*;
import java.io.FileReader;
import java.io.IOException;

public class Graph
{
  private char[] color;
  private int[] d, pi;
  private int[] s, f;
  private int[] fSaved;
  private Queue<Integer> Q;
  private int v, time;
  private ArrayList<Integer>[] adjacencyList;
  private boolean acyclic;
  
  public Graph(ArrayList<Integer>[] list)
  {
    adjacencyList = list;
    color = new char[list.length];
    d = new int[list.length];
    pi = new int[list.length];
    Q = new LinkedList<Integer>();
    s = new int[list.length];
    f = new int[list.length];
  } // end constructor
  
  public void breadthFirstSearch(int s)
  {
    //initialize all data structures
    for (int i = 0; i < adjacencyList.length; i++)
    {
      color[i] = 'w';
      d[i] = -1;
      pi[i] = -1;
    } // end for

    // process designated vertex
    color[s] = 'g';
    d[s] = 0;
    pi[s] = -1;
    // enqueue s into Q
    Q.offer(s);
    
    // process all vertices
    while (Q.peek() != null)
    {
      v = Q.poll();
      for (int i = 0; i < adjacencyList[v].size(); i++)
      {
        if (color[adjacencyList[v].get(i)] == 'w')
        {
          color[adjacencyList[v].get(i)] = 'g';
          d[adjacencyList[v].get(i)] = d[v] + 1;
          pi[adjacencyList[v].get(i)] = v;
          Q.offer(adjacencyList[v].get(i));
        } // end if
      } // end while
      color[v] = 'b';
    } // end while
  } // end breadthFirstSearch
  
  public void depthFirstSearch()
  {
      time = 0;
      for (int i = 0; i < adjacencyList.length; i++)
      {
        //initialize all data structures
        color[i] = 'w';
        pi[i] = -1;
      } // end for
      
      // initialize time to 0
      int time = 0;

      for (int i = 0; i < adjacencyList.length; i++)
      {
        if (color[i] == 'w') DFSVisit(i);
      } // end for
  } // end depthFirstSearch
  
  private void DFSVisit(int v)
  {
    color[v] = 'g';
    s[v] = ++time;
    
    // follow each vertex in the adjacency list
    for (int i = 0; i < adjacencyList[v].size(); i++)
    {
      if (color[adjacencyList[v].get(i)] == 'w')
      {
        pi[adjacencyList[v].get(i)] = v;
        DFSVisit(adjacencyList[v].get(i));
      } // end if
    } // end for
    
    // vertex v has been processed
    color[v] = 'b';
    f[v] = ++time;
  } // end depthFirstSearchVisit

  public void topologicalSort()
  {
    acyclic = true;
    // DFS starting at each node
    for (int j = 0; j <= adjacencyList.length; j++)
    {
      time = 0;
      for (int i = 0; i < adjacencyList.length; i++)
      {
        //initialize all data structures
        color[i] = 'w';   
        pi[i] = -1;
      } // end for
      
      // initialize time to 0
      int time = 0;

      // start from starting node, go to end
      for (int i = j; i < adjacencyList.length; i++)
      {
        if (color[i] == 'w') isAcyclic(i);
      } // end for
      
      // start from beginning, go to starting node
      for (int i = 0; i < j; i++)
      {
        if (color[i] == 'w') isAcyclic(i);
      } // end for
    } // end for
    
    // graph is acyclic
    if (acyclic) 
    {
      System.out.println("The graph is acyclic.");
      depthFirstSearch();
      
      // create and populate aList
      int aList[] = new int[2*adjacencyList.length];
      for (int i = 0; i < aList.length; i++)
      {
        aList[i] = -1;
      } // end for
      
      for (int i = 0; i < adjacencyList.length; i++)
      {
        aList[f[i] - 1] = i;
      } // end for

      // invert aList
      int aListLength = adjacencyList.length * 2;
      int temp[] = new int[aListLength];
      for (int i = 0; i < aList.length / 2 + 1; i++)
      {
        temp[i] = aList[i];
        aList[i] = aList[aList.length - 1 - i];
        aList[aList.length - 1 - i] = temp[i];
      } // end for
      
      // put nils at end
      int counter = 0;
      for (int i = 0; i < aList.length / 2 - 1; i++)
      {
        while (aList[i] < 0)
        {
          for (int j = i; j < aList.length - 1; j++)
          {
            aList[j] = aList[j + 1];
          } // end for
        } // end while
      } // end for
      
      // print array
      for (int i = 0; i < aList.length / 2; i++)
      {
        System.out.print(aList[i] + " ");
      } // end for
      System.out.println();
    } // end if

    // graph is not acyclic 
    else System.out.println("The graph is not acyclic.");
  } // end topologicalSort

  private void isAcyclic(int v)
  {
    color[v] = 'g';
    s[v] = ++time;
    
    // follow each vertex in the adjacency list
    for (int i = 0; i < adjacencyList[v].size(); i++)
    {
      if (color[adjacencyList[v].get(i)] == 'g') acyclic = false;
      if (color[adjacencyList[v].get(i)] == 'w')
      {
        pi[adjacencyList[v].get(i)] = v;
        isAcyclic(adjacencyList[v].get(i));
      } // end if
    } // end for
    
    // vertex v has been processed
    color[v] = 'b';
    f[v] = ++time;
  } // end depthFirstSearchVisit
 
  public void stronglyConnectedComponents(int[] fSaved)
  {
    // run modified DFS on transpose graph
    time = 0;
    
    //initialize all data structures
    for (int i = 0; i < adjacencyList.length; i++)
    {
      color[i] = 'w';
      pi[i] = -1;
    } // end for

    // run DFSVisit in reverse order of finish times
    for (int i = adjacencyList.length * 2; i > 0; i--)
    {
      for (int j = 0; j < adjacencyList.length; j++)
      {
        if(fSaved[j] == i && color [j] == 'w')
        {
          DFSVisit(j);
        } // end if
      } // end for
    } // end for
  } // end stronglyConnectedComponents

  public void printSCCs()
  {
    int[] values = new int[adjacencyList.length * 2];
    System.out.println("Strongly Connected Components (as parenthesis structure):");
    boolean used[] = new boolean[adjacencyList.length];
    int SCCstart = 0;
    int k = 0;
    time = 1;
    for (int i = 1; i <= 2 * adjacencyList.length; i++)
    {
      for (int j = 0; j < adjacencyList.length; j++)
      {
        if (s[j] == i)
        {
          System.out.print("(" + j + " ");
        } // end if
        if (f[j] == i)
        {
          System.out.print(j + ") ");
        } // end if
      } // end for
    } // end for
    System.out.println();
  } // end printSCCs

  public int[] returnArray()
  {
    return f;
  } // end returnArray
  
  public void printBFSStructures()
  {
    System.out.println("Breadth First Search:");
    // print nodes
    System.out.print("Node:  ");
    for (int i = 0; i < adjacencyList.length; i++)
    {
      System.out.printf("%4d", i);
    } // end for
    System.out.println();
    
    // print color array
    System.out.print("Color: ");
    for (int i = 0; i < adjacencyList.length; i++)
    {
      System.out.printf("%4c", color[i]);
    } // end for
    System.out.println();
    
    // print d array
    System.out.print("d:     ");
    for (int i = 0; i < adjacencyList.length; i++)
    {
      System.out.printf("%4d", d[i]);
    } // end for
    System.out.println();
    
    // print pi array
    System.out.print("pi:    ");
    for (int i = 0; i < adjacencyList.length; i++)
    {
      System.out.printf("%4d", pi[i]);
    } // end for
    System.out.println();
    
    // print Q queue
    System.out.print("Q:     ");
    do
    {
      System.out.printf("%4d", Q.poll());
    } while (Q.peek() != null);
    System.out.println();
  } // end printBFSStructures
  
  public void printDFSStructures()
  {
    System.out.println("\nDepth First Search:");
    // print nodes
    System.out.print("Node:  ");
    for (int i = 0; i < adjacencyList.length; i++)
    {
      System.out.printf("%4d", i);
    } // end for
    System.out.println();
    
    // print color array
    System.out.print("Color: ");
    for (int i = 0; i < adjacencyList.length; i++)
    {
      System.out.printf("%4c", color[i]);
    } // end for
    System.out.println();
    
    // print pi array
    System.out.print("pi:    ");
    for (int i = 0; i < adjacencyList.length; i++)
    {
      System.out.printf("%4d", pi[i]);
    } // end for
    System.out.println();
    
    // print start array
    System.out.print("Start: ");
    for (int i = 0; i < adjacencyList.length; i++)
    {
      System.out.printf("%4d", s[i]);
    } // end for
    System.out.println();
    
    // print finish array
    System.out.print("Finish:");
    for (int i = 0; i < adjacencyList.length; i++)
    {
      System.out.printf("%4d", f[i]);
    } // end for
    System.out.println();
    
    // print parenthesis structure
    System.out.print("Parenthesis structure: ");
    time = 1;
    for (int i = 1; i <= 2 * adjacencyList.length; i++)
    {
      for (int j = 0; j < adjacencyList.length; j++)
      {
        if (s[j] == i)
        {
          System.out.print("(" + j + " ");
        } // end if
        if (f[j] == i)
        {
          System.out.print(j + ") ");
        } // end if
      } // end for
    } // end for
    System.out.println();
  } // end printDFSStructures
  
  public void printPath(int s, int v)
  {
    // s and v are the same
    if (s == v) System.out.print(s);
    
    // s and v aren't connected
    else if (color[v] == 'w') System.out.print("s and v aren't connected");
      
    // s and v are connected
    else
    {
      printPath(s, pi[v]);
      System.out.print(" -> " + v);
    } // end else
  } // end printPath
} // end Graph