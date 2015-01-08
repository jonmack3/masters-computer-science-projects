// Jonathan Mack
// This class determines the decimal equivalent of a binary number entered by the user.
// Methods used: main, binaryToDecimal (performs the conversion)

import java.util.Scanner;

public class Project5
{
  public static void main(String[] args)
  {
    // binaryString: binary number entered by user
    // decimalNumber: conversion of binaryString to decimal equivalent
    // binaryLength: number of digits of binary number
    
    String binaryString;
    int decimalNumber, binaryLength;
    Scanner in = new Scanner(System.in);
        
    System.out.println("Please enter a number in binary notation: ");
    binaryString = in.next();
    binaryLength = binaryString.length();
    
    decimalNumber = binaryToDecimal(binaryString, binaryLength - 1);
    
    System.out.println("The binary number was: " + binaryString);
    System.out.println("The number in decimal notation is: " + decimalNumber);
    
  } // end main method

  //This method converts a binary number string into its corresponding decimal number
  
  public static int binaryToDecimal(String binary, int weight)
  {
    // decimal: decimal equivalent
    // weight: digit of binary number, 0 at rightmost
    // digit: value of the binary string at a certain (length - 1 - weight)
    
    int decimal, digit;
    decimal = 0;
    digit = Integer.parseInt(binary.substring(binary.length() - 1 - weight, binary.length() - weight));
    if (weight == 0) {
      decimal = digit;
    } // end if block
    else
    {
      decimal = digit * (int) Math.pow(2, weight) + binaryToDecimal(binary, weight - 1);
    } // end else block
    
    return (decimal);

  } // end binaryToDecimal method

} // end Project5 class