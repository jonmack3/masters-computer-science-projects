/* Jonathan Mack
 * This class reads student data (name and homework/test scores) from a file, constructs an array of class 
 * Student, calculates a final grade and assigns a letter grade for each student, sorts them by last name,
 * and prints the result. It uses Student.java for methods and project3input.txt for data.
 */

import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

/** This class reads student data (name and homework/test scores) from a file, constructs an array of class 
 * Student, calculates a final grade and assigns a letter grade for each student, sorts them by last name,
 * and prints the result.
 */

public class Project3 {
 
  /** Reads student data (name and homework/test scores) from a file, constructs an array of class 
   * Student, calculates a final grade and assigns a letter grade for each student, sorts them by last name,
   * and prints the result.
   */
  
  public static void main(String args[]) {

    /* Declare variables
     * studentArray: array of class Student
     * i: counting variable
     * firstName: first name of the student
     * lastName: last name of the student
     * letterGrade: letter grade for the semester
     * test1Score: score on 1st test
     * test2Score: score on 2nd test
     * test3Score: score on 3rd test
     * hw1Score: score on 1st homework
     * hw2Score: score on 2nd homework
     * hw3Score: score on 3rd test
     * finalScore: score on final exam
     * semesterScore: total score for the class
     */
    
    Student[] studentArray;
    int i;
    String lastName, firstName;
    double test1Score, test2Score, test3Score, homework1Score, homework2Score, homework3Score, finalScore;    
    studentArray = new Student[10];
    i = 0;
   
    // Read data from project3input.txt and insert it into the studentArray array
    
    try {
      FileReader reader = new FileReader("project3input.txt");
      Scanner in = new Scanner(reader);
      while (in.hasNext()) {
        firstName = in.next();
        lastName = in.next();
        test1Score = in.nextDouble();
        test2Score = in.nextDouble();
        test3Score = in.nextDouble();
        homework1Score = in.nextDouble();
        homework2Score = in.nextDouble();
        homework3Score = in.nextDouble();
        finalScore = in.nextDouble();
        studentArray[i] = new Student(firstName, lastName, test1Score, test2Score, test3Score, homework1Score,
          homework2Score, homework3Score, finalScore);
        studentArray[i].computeFinalGrade();
        i = i + 1;
      } // end of while loop
    
    } // end of try
    // catch I/O exceptions
    
    catch (IOException exception) {  
      System.out.println("Error processing file: " + exception);
    } // end of catch      

    // Sort students by last name, then first name
    
    bubbleSort(studentArray);

    // Print header for student printout
    
    System.out.println("CS110, Spring 2006\n");
    System.out.printf("%-12s%-12s%7s%7s%7s%7s%7s%7s%7s%5s%7s%n", "Last Name", "First Name", "Test1", "Test2",
                      "Test3", "HW1", "HW2", "HW3", "Final", "Avg", "Grade");
    
    // Print student info
    
    for (i = 0; i < 10; i++) {
      studentArray[i].printStudent();
    } // end of for statement

  } // end of main method
  
  /** Sort the students by last name, then by first name.
   */
  
  public static void bubbleSort(Student [] studentArray) {
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 9; j++) {
        if (Student.compareStudents(studentArray[j], studentArray[j+1]) == 1) {
          Student temp;
          temp = studentArray[j];
          studentArray[j] = studentArray[j+1];
          studentArray[j+1] = temp;
        } // end of if statement
      } // end of j for loop
    } // end of i for loop
  } // end of bubbleSort method
  
} // end of class Project3
