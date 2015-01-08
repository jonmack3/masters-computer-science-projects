/* Jonathan Mack
 * CS221 Programming 
 * Programming Assignment 4
 * Fall 2006
 * October 18, 2006
 */

import java.util.Random;
import java.util.Scanner;

public class Sort
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
    
    // System.out.print("Original array: ");
    // printArray(values);
    
    // Merge sort
    float mergeSortArray[] = values.clone();
    startTime = System.currentTimeMillis();
    mergeSort(mergeSortArray, 0, values.length - 1);
    endTime = System.currentTimeMillis();
    // System.out.print("Array after merge sort: ");
    // printArray(mergeSortArray);
    System.out.println("The merge sort algorithm required " + (endTime - startTime)/1000.0 + " seconds to execute with " + numValues + " entries.");
    
    // Heap sort
    float heapSortArray[] = values.clone();
    startTime = System.currentTimeMillis();
    heapSort(heapSortArray);
    endTime = System.currentTimeMillis();
    // System.out.print("Array after heap sort: ");
    // printArray(heapSortArray);
    System.out.println("The heap sort algorithm required " + (endTime - startTime)/1000.0 + " seconds to execute with " + numValues + " entries.");
  
    // Quick sort
    float quickSortArray[] = values.clone();
    startTime = System.currentTimeMillis();
    quickSort(quickSortArray, 0, quickSortArray.length - 1);
    endTime = System.currentTimeMillis();
    // System.out.print("Array after heap sort: ");
    // printArray(quickSortArray);
    System.out.println("The quick sort algorithm required " + (endTime - startTime)/1000.0 + " seconds to execute with " + numValues + " entries.");
  } // end main
  
  // Sorts array using merge sort algorithm
  public static void mergeSort(float anArray[], int p, int r)
  {
    if (p < r)
    {
      int q = (p + r) / 2;
      mergeSort(anArray, p, q);
      mergeSort(anArray, q + 1, r);
      merge(anArray, p, q, r);
    } // end if
  } // end mergeSort
  
  // Merges left and right subarray into one in ascending order
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
  } // end merge
  
  // sorts an array using the heap sort algorithm
  public static void heapSort(float A[])
  {
    int n;
    float swap;
    n = A.length - 1;
    buildMaxHeap(A);
    while (n >= 1)
    {
      swap = A[0];
      A[0] = A[n];
      A[n] = swap;
      n = n - 1;
      maxHeapify(A, 0, n);
    } // end while
  } // end heapSort
 
  public static void maxHeapify(float A[], int i, int n)
  {
    float swap;
    int largest, left, right;
    left = 2 * i; //L child
    right = 2 * i + 1; //R child
    largest = i;
    if (left <= n && A[left] > A[i])
    {
      largest = left;
    } // end if 
    if (right <= n && A[right] > A[largest])
    {
      largest = right;
    } // end if
    if (largest != i)
    {
      swap = A[i];
      A[i] = A[largest];
      A[largest] = swap;
      maxHeapify(A, largest, n);
    } // end if
  } // end maxHeapify
  
  // builds a max heap from a heap
  public static void buildMaxHeap(float A[])
  {
    int n;
    n = A.length - 1;
    for (int i = n/2; i >= 0; i--)
    {
      maxHeapify(A, i, n);
    } // end for
  } // end buildMaxHeap
  
  // partitions an array on either side of the pivot element
  public static int partition(float A[], int p, int r)
  {
    int i, j;
    float swap;
    i = p + 1;
    j = r;
    do
    {
      while(i < r && A[p] >= A[i]) i++;
      while(j > p && A[p] <= A[j]) j--;
      if(i < j)
      {
        swap = A[i];
        A[i] = A[j];
        A[j] = swap;
      } // end if
    } // end do while
    while (i < j);
    {
      swap = A[p];
      A[p] = A[j];
      A[j] = swap;
    } // end while
    return j;
  } // end partition
  
  public static void quickSort(float A[], int p, int r)
  {
    if (p < r)
    {
      int q;
      q = partition(A, p, r);
      quickSort(A, p, q - 1);
      quickSort(A, q + 1, r);
    } // end if
  } // end quickSort
        
  public static void printArray(float anArray[])
  {
    for (int i = 0; i < anArray.length; i++)
    {
      System.out.print(anArray[i] + "  ");
    } // end for
    System.out.println();
  } // end printArray
} // end Sort