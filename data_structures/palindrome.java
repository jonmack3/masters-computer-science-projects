// Jonathan Mack
// This class takes in a string, and finds if it is a palindrome.

import java.util.Scanner;

public class Assignment10 
{
  public static void main(String args[])
  {
    String word;
    int start, end;
    
    Scanner in = new Scanner(System.in);
    
    System.out.println("Please enter a string of alphabetic characters");
    word = in.nextLine();
    word.toLowerCase();
    start = 0;
    end = word.length() - 1;
    if (isPalindrome(start, end, word))
    {
      System.out.println("The string is a palindrome.");
    }
    else
    {
      System.out.println("The string is not a palindrome.");
    }
  } // end of main method
    
    public static boolean isPalindrome(int start, int end, String word)
    {
      if (start >= end)
      {
        return true;
      } // end of if block
      else 
      {
        if (word.charAt(start) != word.charAt(end))
        {
          return false;
        }
        else
        {
          return isPalindrome(start + 1, end - 1, word);
        }
      } // end of else block
  } // end of method isPalindrome
} // end of Assigment10 class