import java.util.Scanner;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class Assignment3 {
  public static void main(String args[]) {
    
    // Enter month, date, and year
    
    Scanner console = new Scanner(System.in);
    
    System.out.println("Enter the month, day, and year in the form mm/dd/yyyy:");
    String dateString = console.next();
    
    // Convert month, day, and year to integers
    
    String monthString = dateString.substring(0,2);
    String dayString = dateString.substring(3,5);
    String yearString = dateString.substring(6,10);
    
    int month = Integer.parseInt(monthString) - 1;
    int day = Integer.parseInt(dayString);
    int year = Integer.parseInt(yearString);
    
    GregorianCalendar calendar = new GregorianCalendar(year, month, day);
    
    // Enter number of seconds in the future
    
    System.out.println("Enter a large number of seconds (as an integer), then press <Enter>:");
    int seconds = console.nextInt();
    System.out.flush();
    
    // Calculate days from seconds
    
    final int SECONDS_PER_HOUR = 3600;
    final int HOURS_PER_DAY = 24;
    int extraDays = seconds / (SECONDS_PER_HOUR * HOURS_PER_DAY);
   
    calendar.add(GregorianCalendar.DATE, extraDays);
    
    // Print results
    
    month = calendar.get(Calendar.MONTH) + 1;
    day = calendar.get(Calendar.DATE);
    year = calendar.get(Calendar.YEAR);
    System.out.println(seconds + " seconds into the future is " + month + "/" + day + "/" + year);

  }                       
}
  