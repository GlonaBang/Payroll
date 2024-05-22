//Class for writing time clock information to a file

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TimeClockFileWriter {
    public static void writeTimeClockData(ArrayList<Employee> employeeList) {
        try {
            FileWriter writer = new FileWriter("timeclock.txt");

            // Loop through the employee list and write each employee's time clock details
            for (Employee emp : employeeList) {
                writer.write(emp.toString()); // This will use the correct toString() method
                writer.write("\n"); // Add a newline after each employee's details
            }

            writer.close(); 
            System.out.println("Time clock data written to file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
