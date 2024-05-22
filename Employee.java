//This is the Employee class. Its purpose is to store employee information.

import java.util.*;
import java.io.*;

public class Employee {

  //Scanner object creation
    Scanner in = new Scanner(System.in);

  //Instance variables
    private String first;
    private String last;
    private String ID;
    private int[] DOB;
    private int[] empStart;
    private String empStatus;
    private String filingStatus;

    /**
     * Constructor
     */
    public Employee()
    {
        this.first = "";
        this.last = "";
        this.ID = "";
        this.DOB = new int[3];
        this.empStart = new int[3];
        this.empStatus = "";
    }

    // getter method for first name
  //@return first name
    public String getFirst()
    {
        return first;
    }

    // setter method for first name
   //@param first
    public void setFirst(String first)
    {
        this.first = first;
    }

    // getter method for last name
  //@return last name
    public String getLast()
    {
        return last;
    }

    // setter method for last name
    //@param last
    public void setLast(String last)
    {
        this.last = last;
    }

    // getter method for ID
    //@return ID
    public String getID()
    {
        return ID;
    }

    // setter method for ID
    //@param ID
    public void setID(String ID)
    {
        this.ID = ID;
    }

    // getter method for date of birth
    //@return DOB
    public int[] getDOB()
    {
        return DOB;
    }

    // setter method for date of birth
    //@param DOB
    public void setDOB(int[] DOB)
    {
        this.DOB = DOB;
    }

    // getter method for employment start date
    //@return empStart
    public int[] getEmpStart()
    {
        return empStart;
    }

    // setter method for employment start date
    //@param empStart
    public void setEmpStart(int[] empStart)
    {
        this.empStart = empStart;
    }

    // getter method for employment status (hourly or salary)
    //@return empStatus
    public String getEmpStatus()
    {
        return empStatus;
    }

    // setter method for employment status (hourly or salary)
    //@param empStatus
    public void setEmpStatus(String empStatus)
    {
        this.empStatus = empStatus;
    }

    // getter method for filing status for taxes
    //@return filingStatus
    public String getFilingStatus()
    {
        return filingStatus;
    }

    // setter method for filing status for taxes
    //@param filingStatus
    public void setFilingStatus(String filingStatus)
    {
        this.filingStatus = filingStatus;
    }

    /**
     * adds and saves a new employee profile by writing it to a file (employees.txt)
     */
    public void addEmployee()
    {
        System.out.println("Enter first name: ");
        String first = in.next();
        first = first.toUpperCase().charAt(0) + first.toLowerCase().substring(1, first.length());
        setFirst(first);

        System.out.println("Enter last name: ");
        String last = in.next();
        last = last.toUpperCase().charAt(0) + last.toLowerCase().substring(1, last.length());
        setLast(last);

        System.out.println("Enter date of birth: ");
        int[] DOB = dateArray(in);
        setDOB(DOB);

        System.out.println("Enter start date of employment: ");
        int[] empStart = dateArray(in);
        setEmpStart(empStart);

        ID = generateID();

        System.out.println("Here is " + getFirst() + "'s employee ID: " + ID);


        System.out.println("What is " + getFirst() + "'s filing status?\n1. Single\n2. Married (joint)");
        int choice = in.nextInt();

        if (choice == 1)
        {
            filingStatus = "Single";
            setFilingStatus(filingStatus);
        }
        else if (choice == 2)
        {
            filingStatus = "Joint";
            setFilingStatus(filingStatus);
        }

        System.out.println("Is " + getFirst() + " an hourly or salaried employee?\n1. Hourly\n2. Salaried");
        choice = in.nextInt();

        if (choice == 1)
        {
            empStatus = "Hourly";
            setEmpStatus(empStatus);
        }
        else if (choice == 2)
        {
            empStatus = "Salaried";
            setEmpStatus(empStatus);
        }

        try
        {
            FileWriter writer = new FileWriter("employees.txt", true);
            writer.write("\n");
            writer.write("\nName: " + last + ", " + first);
            writer.write("\nEmployee ID: " + ID);
            writer.write("\nDate of birth: " + Arrays.toString(DOB));
            writer.write("\nEmployment start date: " + Arrays.toString(empStart));
            writer.write("\nEmployee status: " + empStatus);
            writer.write("\nFiling status: " + filingStatus);
            writer.close();
        }

        catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * finds and prints saved employee profile from the file (employees.txt)
     * @param first first name of employee
     * @param last last name of employee
     */
    public void findEmployee(String first, String last)
    {
        first = first.toUpperCase().charAt(0) + first.toLowerCase().substring(1, first.length());
        last = last.toUpperCase().charAt(0) + last.toLowerCase().substring(1, last.length());

        try
        {
            FileReader reader = new FileReader ("employees.txt");
            BufferedReader b = new BufferedReader(reader);
            String line = b.readLine();

            while ( line != null)
            {
                if (line.contains(first) && line.contains(last))
                {
                    System.out.println(line);
                    line = b.readLine();

                    while (line != null && line.contains("Name") == false)
                    {
                        System.out.println(line);
                        line = b.readLine();
                    }
                }
                line = b.readLine();
            }

            reader.close();
            b.close();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // method to read all employee profiles
    // @return void
    public void displayAll()
    {
        try
        {
        FileReader reader = new FileReader ("employees.txt");
        BufferedReader b = new BufferedReader(reader);
        String line = b.readLine();

        while ( line != null)
        {
            System.out.println(line);
            line = b.readLine();
        }

        reader.close();
        b.close();
        }

        catch(IOException e)
        {
        e.printStackTrace();
        };
    }

    // method to read all employee profiles
    // @param first, last
    // @return filingStatus
    public String filingStatus(String first, String last)
    {
        first = first.toUpperCase().charAt(0) + first.toLowerCase().substring(1, first.length());
        last = last.toUpperCase().charAt(0) + last.toLowerCase().substring(1, last.length());

        String status = "";

        // reads employee profile to confirm status
        try
        {
            FileReader reader = new FileReader ("employees.txt");
            BufferedReader b = new BufferedReader(reader);
            String line = b.readLine();

        //While loop that reads the file line by line
            while (line != null)
            {

                //If statement that checks if the first and last name match the inputted name
                if (line.contains(first) && line.contains(last))
                {
                line = b.readLine();
                    
                //While loop that reads the file line by line
                while (line != null && (line.contains("Name") == false))
                {

                    //If statement that checks if the line contains the word "Hourly"
                    if (line.contains("Hourly"))
                    {
                        status = "Hourly";
                        break;
                    }

                    //Else if statement that checks if the line contains the word "Salaried"
                    else if (line.contains("Salaried"))
                    {
                        status = "Salaried";
                        break;
                    }
                    line = b.readLine();
                }
                }

                line = b.readLine();
            }
    //Closes the file reader
            reader.close();
            b.close();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
        return status;
    }
    
    /**
     * takes three numbers and stores them in a one-dimensional array as a date
     * @param dateIn scanner to accept input
     * @return date
     */
    public int[] dateArray(Scanner dateIn)
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);

        int[] date = new int[3];
        int num;

        System.out.println("Enter the month: ");

        while (true)
        {
            String input = dateIn.next();
            try
            {
                num = Integer.parseInt(input);
                if (num >= 1 && num <= 12)
                {
                    date[0] = num;
                } else {
                    System.out.println("Invalid month. Please enter month within range: ");
                    continue;
                }
                break;
            }
            catch (Exception e)
            {
                System.out.println("Invalid month. Please reenter month: ");
            }
        }

        System.out.println("Enter the date: ");

        while (true)
        {
            String input = dateIn.next();
            try
            {
                num = Integer.parseInt(input);
                if (num >= 1 && num <= 31)
                {
                    if (date[0] <= 7 && date[0] % 2 == 1)
                    {
                        date[1] = num;
                    }
                    else if (date[0] < 7 && date[0] % 2 == 0 && (num >= 1 && num <= 30))
                    {
                        date[1] = num;
                    }
                    else if (date[0] > 7 && date[0] % 2 == 1 && (num >= 1 && num <= 30))
                    {
                        date[1] = num;
                    }
                    else if ((date[0] > 7 && date[0] % 2 == 0) || date[0] == 8)
                    {
                        date[1] = num;
                    }
                    else if (date[0] == 2 && (num >= 1 && num <= 29))
                    {
                        date[1] = num;
                    }
                } else {
                    System.out.println("Invalid date. Please enter date within range:");
                    continue;
                }    
                break;
            } catch (Exception e)
            {
                System.out.println("Invalid date. Please reenter date: ");
            }
        }

        System.out.println("Enter the year: ");

        while (true)
        {
            String input = dateIn.next();
            try
            {
                num = Integer.parseInt(input);
                if (num > 999 && num <= year)
                {
                    if (date[0] == 2 && date[1] == 29 && num % 4 != 0)
                    {
                        System.out.println("Leap day in invalid year. Please reenter year: ");
                        num = in.nextInt();
                    } else {
                        date[2] = num;
                    }
                } else {
                    System.out.println("Invalid year. Please enter year within range: ");
                    continue;
                }
                break;
            } catch (Exception e)
            {
                System.out.println("Invalid year. Please reenter year: ");
            }
        }
        return date;
    }

    /**
     * generates an employee ID using the employee's initials and randomly generated numbers
     * @return
     */
    public String generateID()
    {
        if (first.length() >= 10) 
        {
            last = last.substring(0, 10);
        }

        ID = first.toUpperCase().charAt(0) + last.toLowerCase() + String.valueOf((int) (Math.random() * (10000 - 1000) + 1000));
        return ID;
    }

}
