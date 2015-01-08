/* Jonathan Mack
 * CS221 Programming 
 * Programming Assignment 1
 * Fall 2006
 * August 30, 2006
 */

import java.util.Random;
import java.util.Scanner;

public class CompareSorts
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
    
    // Bubble sort
    float bubbleSortArray[] = values.clone();
    startTime = System.currentTimeMillis();
    bubbleSort(bubbleSortArray);
    endTime = System.currentTimeMillis();
    System.out.println("Bubble sort execution time: " + (endTime - startTime));

    // Improved bubble sort
    float improvedBubbleSortArray[] = values.clone();
    startTime = System.currentTimeMillis();
    improvedBubbleSort(improvedBubbleSortArray);
    endTime = System.currentTimeMillis();
    System.out.println("Enhanced bubble sort execution time: " + (endTime - startTime));
    
    // Insertion sort
    float insertionSortArray[] = values.clone();
    startTime = System.currentTimeMillis();
    insertionSort(insertionSortArray);
    endTime = System.currentTimeMillis();
    System.out.println("Insertion sort execution time: " + (endTime - startTime));
    
  } // end main
  
  /** Sorts array using bubble sort algorithm
   * Pre: array of float values of at least size 2
   * Post: array is sorted in ascending order
   * @param anArray array to be sorted
   */
  public static void bubbleSort(float anArray[])
  {
    for(int i = 0; i < anArray.length - 1; i++)
    {
      for(int j = 0; j < anArray.length - 1; j++)
      {
        if(anArray[j] > anArray[j + 1])
        {
          float temp = anArray[j];
          anArray[j] = anArray[j + 1];
          anArray[j + 1] =  temp;
        } // end if
      } // end j for
    } // end i for
  } // end bubbleSort
  
  /** Sorts array using the improved bubble sort algorithm
   * Pre: array of float values of at least size 2
   * Post: array is sorted in ascending order
   * @param anArray array to be sorted
   */
  public static void improvedBubbleSort(float anArray[])
  {
    for(int i = 0; i < anArray.length - 1; i++)
    {
      for(int j = 0; j < anArray.length - i - 1; j++)
      {
        if(anArray[j] > anArray[j + 1])
        {
          float temp = anArray[j];
          anArray[j] = anArray[j + 1];
          anArray[j + 1] =  temp;
        } // end if
      } // end j for
    } // end i for
  } // end improvedBubbleSort
  
  /** Sorts array using insertion sort algorithm
   * Pre: array of float values of at least size 2
   * Post: array is sorted in ascending order
   * @param anArray array to be sorted
   */
  public static void insertionSort(float anArray[])
  {
    for(int j = 1; j < anArray.length; j++)
    {
      float currentValue = anArray[j];
      int i = j - 1;
      while(i >= 0 && currentValue < anArray[i])
      {
        anArray[i + 1] = anArray[i];
        i--;
      } // end while
      anArray[i + 1] = currentValue;
    } // end for
  } // end insertionSort
} // end CompareSorts