/* Jonathan Mack
 * This class constructs an object of type Student, then computes a total (semester score given a series of 
 * initial scores, compares one element in an array to another using first and last names, and prints the 
 * results.
 */

import java.util.Scanner;

/** Constructs a student with last name, first name, grades, and a letter grade. 
 */ 

public class Student {
  
  /* Declare variables
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
  
  private String firstName, lastName, letterGrade;
  private double test1Score, test2Score, test3Score, homework1Score, homework2Score, 
    homework3Score, finalScore, semesterScore;
  private int order;
  
  /** Constructs an object of type Student, with first and last name, three test scores, 3 homework scores, 
   * and one final exam score.
   * @param nameFirst first name
   * @param nameLast first name of the student
   * @param scoreTest1 score on 1st test
   * @param scoreTest2 score on 2nd test
   * @param scoreTest3 score on 3rd test
   * @param scoreHomework1 score on 1st homework
   * @param scoreHomework2 score on 2nd homework
   * @param scoreHomework3 score on 3rd test
   * @param scoreFinal score on final exam
   */
  
  public Student(String nameFirst, String nameLast, double scoreTest1, double scoreTest2,
                 double scoreTest3, double scoreHomework1, double scoreHomework2, double
                 scoreHomework3, double scoreFinal) {
    firstName = nameFirst;
    lastName = nameLast;
    test1Score = scoreTest1;
    test2Score = scoreTest2;
    test3Score = scoreTest3;
    homework1Score = scoreHomework1;
    homework2Score = scoreHomework2;
    homework3Score = scoreHomework3;
    finalScore = scoreFinal;
  } // end of constructor
  
  /** Computes the final grade from test/homework grades.
   */
    
    public void computeFinalGrade() {
     
      // homeworkAverage: average score for all three homeworks
      
      double homeworkAverage;

      homeworkAverage = (homework1Score + homework2Score + homework3Score)/3;
      semesterScore = .2 * test1Score + .2 * test2Score +.2 * test3Score + .15 * homeworkAverage + .25 *
        finalScore;
      
      if (semesterScore < 60) letterGrade = "F";
      else if (semesterScore >= 60 & semesterScore < 70) letterGrade = "D";
      else if (semesterScore >= 70 & semesterScore < 80) letterGrade = "C";
      else if (semesterScore >= 80 & semesterScore < 90) letterGrade = "B";
      else if (semesterScore >= 90) letterGrade = "A";

    } // end of method computeFinalGrade
    
    /** Prints out student data.
     */
    
    public void printStudent() {
      System.out.printf("%-12s%-12s%7.1f%7.1f%7.1f%7.1f%7.1f%7.1f%7.1f%5.0f%7s%n", lastName, firstName, 
                        test1Score, test2Score, test3Score, homework1Score, homework2Score, homework3Score, 
                        finalScore, semesterScore, letterGrade);
    } // end of method printStudent
    
    /** Compares the last names of two students (first if the last names are the same).
     * If the first name comes before the second, a -1 is returned. If the second comes first, a 1 is
     * returned. If both first and last names are equal, a 0 is returned.
     * @return sorting status: -1 if first before second, 1 if reverse, 0 if same
     */
    
    public static int compareStudents(Student students1, Student students2) {
      int order;
      if (students1.lastName.compareTo(students2.lastName) > 0) order = 1;
      else if (students1.lastName.compareTo(students2.lastName) < 0) order = -1;
      else {
        if (students1.firstName.compareTo(students2.firstName) > 0) order = 1;
        else if (students1.firstName.compareTo(students2.firstName) < 0) order = -1;
        else order = 0;
      } // end of else if
      return order;
    } // end of method compareStudents
    
} // end of Class Student
    
 
