// This is a subclass of the Employee class. It is for salary employees. It has the same information as the Employee class, but it has a salary. It also has a method to calculate the salary.


import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class Salary extends Employee{
  
// Instance variables
    private double salary;
    private double paycheck;
    private double taxRate;
    private double tax;
    private double yearlyNet;
    private String filingStatus;

// Constructor, calls the super constructor
    public Salary()
    {
        super();
        this.salary = 0;
        this.paycheck = 0;
        this.taxRate = 0;
        this.tax = 0;
        this.yearlyNet = 0;
        this.filingStatus = "";
    }

    //getter method for salary
    public double getSalary()
    {
        return salary;
    }

    //setter method for salary
    //@param salary
    public void setSalary(double salary)
    {
        this.salary = salary;
    }

    // getter method for tax rate
    //@return taxRate
    public double getTaxRate()
    {
        return taxRate;
    }

    // setter method for tax rate
    //@param taxRate
    public void setTaxRate(double taxRate)
    {
        this.taxRate = taxRate;
    }

    // getter method for one paycheck
    //@return paycheck
    public double getPaycheck()
    {
        return paycheck;
    }

    // setter method for one paycheck
    //@param paycheck
    public void setPaycheck(double paycheck)
    {
        this.paycheck = paycheck;
    }

    // method that returns monthly salary
    //@param first
    //@param last
    //@return monthlySalary
    public double monthlySalary(String first, String last)
    {
        DecimalFormat df = new DecimalFormat("#.##");  
        filingStatus = filingStatus(first, last);
        yearlyNet = calcNetPay(filingStatus, salary);
        paycheck = yearlyNet / 24;

        paycheck = Double.valueOf(df.format(paycheck));
        setPaycheck(paycheck);
        return paycheck * 2;
    }

    // method that returns yearly salary
    //@param first
    //@param last
    //@return monthlySalary
    public double yearlySalary(String first, String last){
      return monthlySalary(first, last) * 12;
    }

    // method for adding a new employee's salary 
    //@param first
    //@param last
    public void addSalary(String first, String last)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("What is " + first + "'s salary?");
        salary = in.nextDouble();
        setSalary(salary);

        try
        {
            FileWriter writer = new FileWriter("payroll.txt", true);
            writer.write("\n");
            writer.write("\nName: " + last + ", " + first);
            writer.write("\nSalary: " + salary);
            writer.write("\nYearly net pay: " + yearlySalary(first, last));
            writer.close();
        }

        catch(IOException e)
        {
            e.printStackTrace();
        }
        in.close();
    }

    // method for finding a new employee's salary
    //@param first
    //@param last
    public void findSalary(String first, String last)
    {
        first = first.toUpperCase().charAt(0) + first.toLowerCase().substring(1, first.length());
        last = last.toUpperCase().charAt(0) + last.toLowerCase().substring(1, last.length());

        try
        {
        FileReader reader = new FileReader ("payroll.txt");
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

    //method for calculating net pay based on salary, filing status, and federal taxes
    //@param filingStatus
    //@param salary
    //@return yearlyNet
    public double calcNetPay(String filingStatus, double salary)
    {
      if (filingStatus.equals("Single"))
      {
        double SB1TAX = (11000 * 0.10);
        double SB2TAX = ((44725 - 11000) * 0.12);
        double SB3TAX = ((95375 - 44725) * 0.22);
        double SB4TAX = ((182100 - 95375) * 0.24);
        double SB5TAX = ((231250 - 182100) * 0.32);
        double SB6TAX = ((578125 - 231250) * 0.35);

        if (salary > 0 && salary <= 11000)
        {
          taxRate = 0.1;
          tax = salary * taxRate;
          yearlyNet = (salary - tax);
        }

        else if (salary > 11000 && salary <= 44725)
        {
          taxRate = 0.12;
          tax = SB1TAX + (salary - 11000) * taxRate;
          yearlyNet = (salary - tax);
        }

        else if (salary > 44725 && salary <= 95375)
        {
          taxRate = 0.22;
          tax = SB1TAX + SB2TAX + (salary - 44725) * taxRate;
          yearlyNet = (salary - tax);
        }

        else if (salary > 95375 && salary <= 182100)
        {
          taxRate = 0.24;
          tax = SB1TAX + SB2TAX + SB3TAX + (salary - 95375) * taxRate;
          yearlyNet = (salary - tax);
        }

        else if (salary > 182100 && salary <= 231250)
        {
          taxRate = 0.32;
          tax = SB1TAX + SB2TAX + SB3TAX + SB4TAX + (salary - 182100) * taxRate;
          yearlyNet = (salary - tax);
        }

        else if (salary > 231250 && salary <= 578125)
        {
          taxRate = 0.35;
          tax = SB1TAX + SB2TAX + SB3TAX + SB4TAX + SB5TAX + (salary - 231250) * taxRate;
          yearlyNet = (salary - tax);
        }

        else if (salary > 578125)
        {
          taxRate = 0.37;
          tax = SB1TAX + SB2TAX + SB3TAX + SB4TAX + SB5TAX + SB6TAX + (salary - 578125) * taxRate;
          yearlyNet = (salary - tax);
        }
      }

      else if (filingStatus.equals("Joint"))
      {
        double JB1TAX = (22000 * 0.1);
        double JB2TAX = ((89450 - 22000) * 0.12);
        double JB3TAX = ((190750 - 89450) * 0.22);
        double JB4TAX = ((364200 - 190750) * 0.24);
        double JB5TAX = ((462500 - 364200) * 0.32);
        double JB6TAX = ((693750 - 462500) * 0.35);

        if (salary > 0 && salary <= 22000)
        {
          taxRate = 0.1;
          tax = salary * taxRate;
          yearlyNet = (salary - tax);
        }

        else if (salary > 22000 && salary <= 89450)
        {
          taxRate = 0.12;
          tax = JB1TAX + (salary - 22000) * taxRate;
          yearlyNet = (salary - tax);
        }

        else if (salary > 89450 && salary <= 190750)
        {
          taxRate = 0.22;
          tax = JB1TAX + JB2TAX + (salary - 89450) * taxRate;
          yearlyNet = (salary - tax);
        }

        else if (salary > 190750 && salary <= 364200)
        {
          taxRate = 0.24;
          tax = JB1TAX + JB2TAX + JB3TAX + (salary - 190750) * taxRate;
          yearlyNet = (salary - tax);
        }

        else if (salary > 364200 && salary <= 462500)
        {
          taxRate = 0.32;
          tax = JB1TAX + JB2TAX + JB3TAX + JB4TAX + (salary - 364200) * taxRate;
          yearlyNet = (salary - tax);
        }

        else if (salary > 462500 && salary <= 693750)
        {
          taxRate = 0.35;
          tax = JB1TAX + JB2TAX + JB3TAX + JB4TAX + JB5TAX + (salary - 462500) * taxRate;
          yearlyNet = (salary - tax);
        }

        else if (salary > 693750)
        {
          taxRate = 0.37;
          tax = JB1TAX + JB2TAX + JB3TAX + JB4TAX + JB5TAX + JB6TAX + (salary - 578125) * taxRate;
          yearlyNet = (salary - tax);
        }
      }

    return yearlyNet;
    }

    // method to read filing status from employee profile
    @Override
    public String filingStatus(String first, String last)
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
            line = b.readLine();

            while (line != null && (line.contains("Name") == false))
            {
                if (line.contains("Single"))
                {
                setFilingStatus("Single");
                }
                else if (line.contains("Joint"))
                {
                setFilingStatus("Joint");
                }
                line = b.readLine();
          }
        }

        line = b.readLine();

        }

        reader.close();
        b.close();
        }

        catch(IOException e)
        {
        e.printStackTrace();
        }

        return getFilingStatus();
    }

    //toString Method that displays the salary

  @Override
  public String toString() {
      return super.toString() + 
          ", weekly Salary is: " + String.format("%.2f", getPaycheck()) + 
          ", Monthly Salary is: " + String.format("%.2f", monthlySalary("","")) + 
          ", Yearly Salary is: " + String.format("%.2f", getSalary());
  }
}











