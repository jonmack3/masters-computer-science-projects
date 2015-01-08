/* Jonathan Mack
 * CS22 
 * Programming Assignment 5
 * Fall 2006
 * November 1, 2006
 */

import java.util.Random;
import java.util.Scanner;

public class CountingSort
{
  public static void main(String[] args)
  {
    Random random = new Random();
    Scanner in = new Scanner(System.in);
    
    long startTime, endTime;
    
    // Get number of values to be sorted
    System.out.print("Enter number of values: ");
    int numValues = in.nextInt();
    System.out.println(numValues);
    
    // declare array, create randomized values, and insert values into array
    int values[] = new int[numValues];
    
    for(int i = 0; i < numValues; i++ )
    {
      values[i] = (int)(numValues*Math.random());
    } // end for
    
    // Simple counting sort
    int sortArray1[] = values.clone();
    startTime = System.currentTimeMillis();
    simpleCountingSort(sortArray1, sortArray1.length);
    endTime = System.currentTimeMillis();
    System.out.println("The Simple Counting Sort algorithm required " + (endTime - startTime)/1000.0 + " seconds to execute with " + numValues + " entries.");
    
    // Counting sort
    int sortArray2[] = values.clone();
    startTime = System.currentTimeMillis();
    countingSort(sortArray2, sortArray2.length);
    endTime = System.currentTimeMillis();
    System.out.println("The Counting Sort algorithm required " + (endTime - startTime)/1000.0 + " seconds to execute with " + numValues + " entries.");
    
    // print values if small array
    if (values.length <= 10)
    {
      System.out.print("Original array: ");
      printArray(values, values.length);
      System.out.print("Array after simple counting sort: ");
      printArray(sortArray1, values.length);
      System.out.print("Array after counting sort: ");
      printArray(sortArray2, values.length);
    }
  } // end main
  
  // Sorts array using simple counting sort algorithm
  public static void simpleCountingSort(int A[], int length)
  {
    // find largest value in A
    int k = A[0];
    for (int i = 1; i < length; i++)
    {
      if (k < A[i]) k = A[i];
    } // end for
    
    // create auxillary array C, initialize to 0
    int C[] = new int[k+1];
    for (int i = 0; i <= k; i++)
    {
      C[i] = 0;
    } // end for
    
    //populate entries in C
    for (int i = 0; i < length; i++)
    {
      C[A[i]]++;
    } // end for
    
    //repopulate array A
    int m = 0;
    int i = 0;
    while (m <= k)
    {
      if (C[m] != 0)
      {
        A[i] = m;
        i++;
        C[m]--;
      } // end if
      else m++;
    } // end while
  } // end simpleCountingSort
    
  // sorts an array using the counting sort algorithm
  public static void countingSort(int A[], int length)
  {
    int k = A[0];
    for (int i = 1; i < length; i++)
    {
      if (k < A[i]) k = A[i];
    } // end if
    
    // create auxillary array C, initialize to 0
    int C[] = new int[k+1];
    for (int i = 0; i <= k; i++)
    {
      C[i] = 0;
    } // end for
    
    //populate entries in C
    for (int i = 0; i < length; i++)
    {
      C[A[i]]++;
    } // end for
    
    //sum entries in C
    for (int i = 1; i <= k; i++)
    {
      C[i] = C[i] + C[i-1];
    } // end for
    
    int B[] = new int[length];
    for (int i = length - 1; i >= 0; i--)
    {
      B[C[A[i]]-1] = A[i];
      C[A[i]]--;
    } // end for
    
    for (int i = 0; i < length; i++)
    {
      A[i] = B[i];
    } // end for
    
  } // end counting sort

  public static void printArray(int A[], int length)
  {
    for (int i = 0; i < A.length; i++)
    {
      System.out.print(A[i] + "  ");
    } // end for
    System.out.println();
  } // end printArray
} // end Sort