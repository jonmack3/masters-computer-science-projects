/* Jonathan Mack
 * CS221
 * Programming Assignment 6
 * Fall 2006
 * November 6, 2006
 */

import java.util.Random;
import java.util.Scanner;

public class RadixSort
{
  public static void main(String[] args)
  {
    Random random = new Random();
    Scanner in = new Scanner(System.in);
    
    long startTime, endTime;
    int numDigits;
    
    // Get number of values to be sorted
    System.out.print("Enter number of values: ");
    int numValues = in.nextInt();
    
    // declare array, create randomized values, and insert values into array
    int values[] = new int[numValues];
    
    for(int i = 0; i < numValues; i++ )
    {
      values[i] = (int)(5000000*Math.random());
    } // end for
    
    // Radix sort: 1-digit grouping
    numDigits = 1;
    int sortArray1[] = values.clone();
    startTime = System.currentTimeMillis();
    radixSort(sortArray1, numDigits);
    endTime = System.currentTimeMillis();
    System.out.println("The Counting Sort algorithm required " + (endTime - startTime)/1000.0 + 
                       " seconds to execute with " + numValues + " entries and m = " + numDigits + ".");
    
    // Radix sort: 2-digit grouping
    numDigits = 2;
    int sortArray2[] = values.clone();
    startTime = System.currentTimeMillis();
    radixSort(sortArray2, numDigits);
    endTime = System.currentTimeMillis();
    System.out.println("The Counting Sort algorithm required " + (endTime - startTime)/1000.0 + 
                       " seconds to execute with " + numValues + " entries and m = " + numDigits + ".");
    
    // Radix sort: 4-digit grouping
    numDigits = 4;
    int sortArray4[] = values.clone();
    startTime = System.currentTimeMillis();
    radixSort(sortArray4, numDigits);
    endTime = System.currentTimeMillis();
    System.out.println("The Counting Sort algorithm required " + (endTime - startTime)/1000.0 + 
                       " seconds to execute with " + numValues + " entries and m = " + numDigits + ".");
    
    // print values if small array
    if (values.length <= 10)
    {
      System.out.print("Original array: ");
      printArray(values);
      System.out.print("Array after Radix sort1: ");
      printArray(sortArray1);
      System.out.print("Array after Radix sort2: ");
      printArray(sortArray2);
      System.out.print("Array after Radix sort4: ");
      printArray(sortArray4);
    }
  } // end main
  
  // sorts an array using the radix sort algorithm
  private static void radixSort(int A[], int numDigits)
  {
    int maxIterations = (int)Math.ceil(7.0/(double)(numDigits));
    // System.out.println("MaxIterations: " + maxIterations);
    for(int i = 1; i <= maxIterations; i++)
    {
      countingSort(A, numDigits, i);
    } // end for
  } // end radixSort
  
  // sorts an array using the counting sort algorithm
  private static void countingSort(int A[], int numDigits, int iteration)
  {
    int length = A.length;
    int k = A[0];
    int digits = (int)Math.pow(10.0, (double)((iteration-1)*numDigits));
    
    // create auxillary array C, initialize to 0
    int C[] = new int[(int)Math.pow(10.0, (double)numDigits)];
    for (int i = 0; i < Math.pow(10, numDigits); i++)
    {
      C[i] = 0;
    } // end for
    
    //populate entries in C
    for (int i = 0; i < length; i++)
    {
      int index = (A[i]/digits)%(int)Math.pow(10, numDigits);
      C[index]++;
    } // end for
    
    //sum entries in C
    for (int i = 1; i < Math.pow(10, numDigits); i++)
    {
      C[i] = C[i] + C[i-1];
    } // end for
    
    // populate array B
    int B[] = new int[length];
    for (int i = length - 1; i >= 0; i--)
    {
      int index = (A[i]/digits)%(int)Math.pow(10, numDigits);
      // System.out.println(index + " " + A[i] + " " + " " + C[(index)]);
      B[--C[index]] = A[i];
    } // end for
    
    // repopulate array A
    for (int i = 0; i < length; i++)
    {
      A[i] = B[i];
    } // end for
  } // end counting sort
  
  public static void printArray(int A[])
  {
    for (int i = 0; i < A.length; i++)
    {
      System.out.print(A[i] + "  ");
    } // end for
    System.out.println();
  } // end printArray
} // end Sort