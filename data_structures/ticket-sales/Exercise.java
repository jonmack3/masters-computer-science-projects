import java.util.Scanner;
import java.io.*;

public class Exercise
{
  public static void main(String args[]) throws IOException
  {
    File inFile = new File("c:\\Documents and Settings\\asdf\\My Documents\\Java Programs\\tickets.dat");
    File outFile = new File("c:\\Documents and Settings\\asdf\\My Documents\\Java Programs\\sales.txt");
    
    Scanner input = new Scanner(inFile);
    PrintWriter output = new PrintWriter(outFile);
    
    double boxPrice, sidelinePrice, premiumPrice, generalPrice;
    double boxSales, sidelineSales, premiumSales, generalSales;
    double boxNumber, sidelineNumber, premiumNumber, generalNumber;
    double totalNumber, totalSales;
    
    boxPrice = input.nextDouble();
    boxNumber = input.nextDouble();
    sidelinePrice = input.nextDouble();
    sidelineNumber = input.nextDouble();
    premiumPrice = input.nextDouble();
    premiumNumber = input.nextDouble();
    generalPrice = input.nextDouble();
    generalNumber = input.nextDouble();
    
    boxSales = boxPrice * boxNumber;
    sidelineSales = sidelinePrice * sidelineNumber;
    premiumSales = premiumPrice * premiumNumber;
    generalSales = generalPrice * generalNumber;
    
    totalNumber = boxNumber + sidelineNumber + premiumNumber + generalNumber;
    totalSales = boxSales + sidelineSales + premiumSales + generalSales;
    
    output.println("Weekly Ticket Sales Report");
    output.println();
    output.println("Type        Price       #Sold       Amount Sold");
    output.printf("%-12s%-12.2f%-12.0f%-12.2f%n", "Box", boxPrice, boxNumber, boxSales);
    output.printf("%-12s%-12.2f%-12.0f%-12.2f%n", "Sideline", sidelinePrice, sidelineNumber, sidelineSales);
    output.printf("%-12s%-12.2f%-12.0f%-12.2f%n", "Premium", premiumPrice, premiumNumber, premiumSales);
    output.printf("%-12s%-12.2f%-12.0f%-12.2f%n", "General", generalPrice, generalNumber, generalSales);
    output.println();
    output.printf("%-30s%-30.0f", "Total number of Tickets Sold:", totalNumber);
    output.println();
    output.printf("%-14s%-30.2f", "Total Sales: $", totalSales);
    
    output.flush();
    output.close();
  }
}