// This class is a subclass of the Employee class. It is for hourly employees. It has the same information as the Employee class, but it has methods to calculate the amount of time for work.

import java.io.FileWriter;
import java.io.*;
import java.text.DecimalFormat;

public class Hourly extends Employee{

  // Instance variables
    private double hourly;
    private double time;
    private double totalTime;
    private double OTRate;
    private double OT;
    private double OTPay;  
    private double paycheck;
    private double taxRate;
    private double tax;
    private double yearlyGross;
    private double yearlyNet;
    

  // Constructor; calls the super constructor
    public Hourly()
    {
      super();
      this.hourly = 0;
      this.time = 0;
      this.totalTime = 0;
      this.OTRate = 0;
      this.OT = 0;
      this.OTPay = 0;
      this.paycheck = 0;
      this.taxRate = 0;
      this.tax = 0;
      this.yearlyGross = 0;
      this.yearlyNet = 0;
    }

  // getter method for hourly pay
  // @return hourly
  public double getHourly()
  {
    return hourly;
  }

  // setter method for hourly pay
  // @param hourly
  public void setHourly(double hourly)
  {
    this.hourly = hourly;
  }

  // getter method for hours worked
  // @return time  
  public double getTime()
  {
    return time;
  }

  // setter method for hours worked
  // @param time
  public void setTime(double time)
  {
    this.time = time;
  }

  // getter method for total annual hours worked
  // @return totalTime
  public double getTotalTime()
  {
    return time;
  }

  // setter method for total annual hours worked
  // @param totalTime
  public void setTotalTime(double totalTime)
  {
    this.totalTime = totalTime;
  }

  // getter method for overtime rate
  // @return OTRate
  public double getOTRate()
  {
    return OTRate;
  }

  // setter method for overtime rate
  // @param OTRate
  public void setOTRate(double OTRate)
  {                    
    this.OTRate = OTRate;
  }

  // getter method for overtime hours
  // @return OT
  public double getOT()
  {
    return OTRate;
  }

  // setter method for overtime hours
  // @param OT
  public void setOT(double OT)
  {                    
    this.OT = OT;
  }

  // getter method for overtime pay
  // @return OTPay
  public double getOTPay()
  {
    return OTPay;
  }

  // setter method for overtime pay
  // @param OTPay
  public void setOTPay(double OTPay)
  {
    this.OTPay = OTPay;
  }

  // getter method for paycheck pay
  // @return paycheck
  public double getPaycheck()
  {
    return paycheck;
  }

  // setter method for paycheck pay
  // @param paycheck
  public void setPaycheck(double paycheck)
  {
    this.paycheck = paycheck;
  }

  // getter method for tax rate
  // @return taxRate
  public double getTaxRate()
  {
    return taxRate;
  }

  // setter method for tax rate
  // @param taxRate
  public void setTaxRate(double taxRate)
  {
    this.taxRate = taxRate;
  }

  // getter method for total tax
  // @return tax
  public double getTax()
  {
    return tax;
  }

  // setter method for total tax
  // @param tax
  public void setTax(double tax)
  {
    this.tax = tax;
  }

  // getter method for yearlyGross pay
  // @return yearlyGross
  public double getYearlyGross()
  {
    return yearlyGross;
  }

  //setter method for yearlyGross pay
  // @param yearlyGross
  public void setYearlyGross(double yearlyGross)
  {
    this.yearlyGross = yearlyGross;
  }

  // getter method for yearly gross pay
  // @return yearlyNet
  public double getYearlyNet()
  {
    return yearlyGross;
  }

  //setter method for yearly gross pay
  // @param yearlyNet
  public void setYearlyNet(double yearlyGross)
  {
    this.yearlyGross = yearlyGross;
  }

  // method for calculating monthly salary
  // @return monthly
  public double monthlySalary()
  {
    double monthlyPay = getPaycheck()*2;
    return monthlyPay;
  }

  // method for calculating yearlyGross salary
  // @return yearlyGross
  public double yearlyGrossSalary()
  {
    double yearlyGross = monthlySalary()*12;
    return yearlyGross;
  }

  // method for adding a new employee's hourly pay
  // @param first, last
  public void addHourly(String first, String last)
  {
    System.out.println("What is " + first + "'s hourly wage?");
    hourly = in.nextDouble();
    setHourly(hourly);

    System.out.println("What is " + first + "'s overtime rate?\n1: (x 1.5)\n2: (x 2.0)");
    int choice = in.nextInt();

    if (choice == 1 || choice == 2)
    {

      if (choice == 1)
      {
        OTRate = 1.5;
        setOTRate(OTRate);
      }
      else if (choice == 2)
      {
        OTRate = 2.0;
        setOTRate(OTRate);
      }

    } else {
      throw new IllegalArgumentException("Invalid overtime rate. Please select 1 or 2.");
    }

    System.out.println("How many hours will " + first + " work in one pay period (2 weeks)?");
    time = in.nextDouble();
    setTime(time);

    try
    {
      FileWriter writer = new FileWriter("payroll.txt", true);
      writer.write("\n");
      writer.write("\nName: " + last + ", " + first);
      writer.write("\nOvertime rate: " + OTRate);
      writer.write("\nHourly rate: " + hourly);
      writer.write("\nHours per pay period: " + time);
      writer.close();
    }

    catch(IOException e)
    {
      e.printStackTrace();
    }

  }

  // method for finding an existing employee's hourly pay
  // @param first, last
  public void findHourly(String first, String last)
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

  // method for inputting hours worked so far
  // @param first, last
    public void inputHours(String first, String last, String filingStatus)
    {
    first = first.toUpperCase().charAt(0) + first.toLowerCase().substring(1, first.length());
    last = last.toUpperCase().charAt(0) + last.toLowerCase().substring(1, last.length());

    DecimalFormat df = new DecimalFormat("#.##");  

    System.out.println("How many hours did " + first + " work this pay period (2 weeks)?");
    time = in.nextDouble();
    setTime(time);

    hourly = hourly(first, last);
    OTRate = OTRate(first, last);

    OT = calcOT(time);
    OTPay = (OTRate * OT)*hourly;
    OTPay = Double.valueOf(df.format(OTPay));
    setOTPay(OTPay);

    totalTime = time * 26;
    setTotalTime(totalTime);

    yearlyGross = totalTime * hourly + OTPay;
    setYearlyGross(yearlyGross);

    yearlyNet = calcNetPay(filingStatus, yearlyGross);
    setYearlyNet(yearlyNet);

    paycheck = (yearlyNet / 26);    
    paycheck = Double.valueOf(df.format(paycheck));
    setPaycheck(paycheck);

  try {
          FileWriter writer = new FileWriter("timeclock.txt", true);
          writer.write("\n");
          writer.write("\nName: " + last + ", " + first);
          writer.write("\nCurrent logged hours: " + String.valueOf(time));
          writer.write("\nOvertime hours: " + OT);
          writer.write("\nOvertime pay: " + OTPay);
          writer.write("\nCurrent net paycheck: " + paycheck);
          writer.write("\nCurrent monthly salary: " + (paycheck * 2));
          writer.write("\nCurrent yearly salary: " + yearlyNet);
          writer.close();
      } catch(IOException e) {
          e.printStackTrace();
      }
  }

  // method for displaying logged hours for the pay period
  // @param first, last
  public void displayHours(String first, String last)
  {
    first = first.toUpperCase().charAt(0) + first.toLowerCase().substring(1, first.length());
    last = last.toUpperCase().charAt(0) + last.toLowerCase().substring(1, last.length());

    try
    {
      FileReader reader = new FileReader ("timeclock.txt");
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

    catch(IOException e)
    {
      e.printStackTrace();
    }
  }

  // method to read filing status from employee profile
  // @param first, last
  // @return filingStatus
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

  // method to read hourly pay rate from employee profile
  // @param first, last
  // @return hourly
  public double hourly(String first, String last)
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
          line = b.readLine();

          while (line != null && (line.contains("Name") == false))
          {
            if (line.contains("Hourly rate"))
            {
              String[] lineArray = line.split(" ");
              hourly = Double.valueOf(lineArray[2]);
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

    return hourly;
  }

  // method to read overtime pay rate from employee profile
  // @param first, last
  // @return OTRate
  public double OTRate(String first, String last)
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
          line = b.readLine();

          while (line != null && (line.contains("Name") == false))
          {
            if (line.contains("1.5"))
            {
              OTRate = 1.5;
            }
            else if (line.contains("2.0"));
            {
              OTRate = 2.0;
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

    return OTRate;

  }

  // method to calculate net pay for the year using federal taxes
  // @param filingStatus, yearlyGross
  // @return yearlyNet
  public double calcNetPay(String filingStatus, double yearlyGross)
  {
      if (filingStatus.equals("Single"))
      {
        double SB1TAX = (11000 * 0.10);
        double SB2TAX = ((44725 - 11000) * 0.12);
        double SB3TAX = ((95375 - 44725) * 0.22);
        double SB4TAX = ((182100 - 95375) * 0.24);
        double SB5TAX = ((231250 - 182100) * 0.32);
        double SB6TAX = ((578125 - 231250) * 0.35);

        if (yearlyGross > 0 && yearlyGross <= 11000)
        {
          taxRate = 0.1;
          tax = yearlyGross * taxRate;
          yearlyNet = (yearlyGross - tax);
        }

        else if (yearlyGross > 11000 && yearlyGross <= 44725)
        {
          taxRate = 0.12;
          tax = SB1TAX + (yearlyGross - 11000) * taxRate;
          yearlyNet = (yearlyGross - tax);
        }

        else if (yearlyGross > 44725 && yearlyGross <= 95375)
        {
          taxRate = 0.22;
          tax = SB1TAX + SB2TAX + (yearlyGross - 44725) * taxRate;
          yearlyNet = (yearlyGross - tax);
        }

        else if (yearlyGross > 95375 && yearlyGross <= 182100)
        {
          taxRate = 0.24;
          tax = SB1TAX + SB2TAX + SB3TAX + (yearlyGross - 95375) * taxRate;
          yearlyNet = (yearlyGross - tax);
        }

        else if (yearlyGross > 182100 && yearlyGross <= 231250)
        {
          taxRate = 0.32;
          tax = SB1TAX + SB2TAX + SB3TAX + SB4TAX + (yearlyGross - 182100) * taxRate;
          yearlyNet = (yearlyGross - tax);
        }

        else if (yearlyGross > 231250 && yearlyGross <= 578125)
        {
          taxRate = 0.35;
          tax = SB1TAX + SB2TAX + SB3TAX + SB4TAX + SB5TAX + (yearlyGross - 231250) * taxRate;
          yearlyNet = (yearlyGross - tax);
        }

        else if (yearlyGross > 578125)
        {
          taxRate = 0.37;
          tax = SB1TAX + SB2TAX + SB3TAX + SB4TAX + SB5TAX + SB6TAX + (yearlyGross - 578125) * taxRate;
          yearlyNet = (yearlyGross - tax);
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

        if (yearlyGross > 0 && yearlyGross <= 22000)
        {
          taxRate = 0.1;
          tax = yearlyGross * taxRate;
          yearlyNet = (yearlyGross - tax);
        }

        else if (yearlyGross > 22000 && yearlyGross <= 89450)
        {
          taxRate = 0.12;
          tax = JB1TAX + (yearlyGross - 22000) * taxRate;
          yearlyNet = (yearlyGross - tax);
        }

        else if (yearlyGross > 89450 && yearlyGross <= 190750)
        {
          taxRate = 0.22;
          tax = JB1TAX + JB2TAX + (yearlyGross - 89450) * taxRate;
          yearlyNet = (yearlyGross - tax);
        }

        else if (yearlyGross > 190750 && yearlyGross <= 364200)
        {
          taxRate = 0.24;
          tax = JB1TAX + JB2TAX + JB3TAX + (yearlyGross - 190750) * taxRate;
          yearlyNet = (yearlyGross - tax);
        }

        else if (yearlyGross > 364200 && yearlyGross <= 462500)
        {
          taxRate = 0.32;
          tax = JB1TAX + JB2TAX + JB3TAX + JB4TAX + (yearlyGross - 364200) * taxRate;
          yearlyNet = (yearlyGross - tax);
        }

        else if (yearlyGross > 462500 && yearlyGross <= 693750)
        {
          taxRate = 0.35;
          tax = JB1TAX + JB2TAX + JB3TAX + JB4TAX + JB5TAX + (yearlyGross - 462500) * taxRate;
          yearlyNet = (yearlyGross - tax);
        }

        else if (yearlyGross > 693750)
        {
          taxRate = 0.37;
          tax = JB1TAX + JB2TAX + JB3TAX + JB4TAX + JB5TAX + JB6TAX + (yearlyGross - 578125) * taxRate;
          yearlyNet = (yearlyGross - tax);
        }
      }

    return yearlyNet;
  }

  // method to calculate overtime hours logged
  // @param time, 
  // @return OT
  public double calcOT(double time)
  {
    if (time > 80)
    {
      OT = time - 80;
    } else {
      OT = 0;
    }

    return OT;
  }

  // toString method that displays the hourly pay
  @Override
  public String toString() {
      return super.toString() + 
          ", Hourly Pay: " + String.format("%.2f", getHourly()) + 
          ", Overtime Rate: " + String.format("%.2f", getOTRate()) + 
          ", Tax Rate: " + String.format("%.2f", getTax()) + 
          ", Hours Worked: " + String.format("%.2f", getTime()) + 
          ", Overtime Pay: " + String.format("%.2f", getOTPay()) + 
          ", Weekly Pay: " + String.format("%.2f", getPaycheck()) + 
          ", Monthly Salary: " + String.format("%.2f", getPaycheck() * 2) + 
          ", Yearly Salary: " + String.format("%.2f", yearlyNet);
  }
}
