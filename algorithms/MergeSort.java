/* Jonathan Mack
 * CS221 Programming 
 * Programming Assignment 2
 * Fall 2006
 * September 6, 2006
 */

import java.util.Random;
import java.util.Scanner;

public class MergeSort
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
    float values[] = new float[numValues];
    for(int i = 0; i < numValues; i++ )
    {
      values[i] = random.nextFloat();
    } // end for
    
    // printArray(values);
    
    // Merge sort
    startTime = System.currentTimeMillis();
    mergeSort(values, 0, values.length - 1);
    endTime = System.currentTimeMillis();
    // printArray(values);
    System.out.println("Merge sort execution time: " + (endTime - startTime));
  } // end main
  
  /** Sorts array using merge sort algorithm
   * Pre: array of float values of at least size 2
   * Post: array is sorted in ascending order
   * @param anArray array to be sorted
   * @param p anArray index of the start of the array portion to be sorted
   * @param r anArray index of the end of the array portion to be sorted
   */
  public static void mergeSort(float anArray[], int p, int r)
  {
    if (p < r)
    {
      int q = (p + r) / 2;
      mergeSort(anArray, p, q);
      mergeSort(anArray, q + 1, r);
      merge(anArray, p, q, r);
    } // end if
  } // end mergeSort method
  
  /** Merges left and right subarray into one in ascending order
   * Pre: two sorted subarrays
   * Post: subarrays merged into one in ascending order
   * @param anArray total array of values to be sorted
   * @param p anArray index of the start of the left subarray
   * @param q anArray index of the end of the left subarray
   * @param r anArray index of the end of the right subarray
   */
  public static void merge(float anArray[], int p, int q, int r)
  {
    // calculate lengths of sorted subarrays
    int n = q - p + 1; //left subarray
    int m = r - q; // right subarray
    
    // create two subarrays of length n, m
    float lArray[] = new float[n]; 
    float rArray[] = new float[m];
    
    // populate left subarray
    for (int j = 0; j < n; j++)
    {
      lArray[j] = anArray[p+j];
    } // end for
    
    // populate right subarray
    for (int j = 0; j < m; j++)
    {
      rArray[j] = anArray[q+j+1];
    } // end for
    // create indices for the arrays
    int i = 0; // left index
    int j = 0; // right index
    int k = p; // anArray index
    
    // write left/right subarrays back to anArray
    while (i < n && j < m) // while both subarrays still have entries
    {
      if (lArray[i] < rArray[j])
      {
        anArray[k] = lArray[i];
        i++;
      } // end if
      else
      {
        anArray[k] = rArray[j];
        j++;
      } // end else
      k++;
    } // end while
    
    // add tail of whichever array left over
    if (i == n)
    {
      for (int u = j; u < m; u++)
      {
        anArray[k] = rArray[u];
        k++;
      } // end for
    } // end if
    else
    {
      for (int u = i;  u < n; u++)
      {
        anArray[k] = lArray[u];
        k++;
      } // end for
    } // end else
   /* System.out.print("left: ");
    printArray(lArray);
    System.out.print("right: ");
    printArray(rArray);
    System.out.print("total: ");
    printArray(anArray); */
  } // end merge
  
  public static void printArray(float anArray[])
  {
    for (int i = 0; i < anArray.length; i++)
    {
      System.out.print(anArray[i] + "  ");
    } // end for
    System.out.println();
  } // end printArray
} // end mergeSort class