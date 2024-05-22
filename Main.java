import java.util.*;

public class Main {
    public static void main(String[] args)
    {
      
      //Object creation
        Scanner in = new Scanner(System.in);
        Employee emp = new Employee();
        Hourly hour = new Hourly();
        Salary sal = new Salary();

      //Asks the user for input if they wat to find an employee or add a new employee
        System.out.println("What would you like to do today?\n1: Add new employee\n2: Find employee");
        int choice = in.nextInt();

      //If the user chooses to add a new employee
        if (choice == 1)
        {
            emp.addEmployee();

      //If/Else if statements that checks if the user is an hourly or salaried employee
            if (emp.getEmpStatus().equals("Hourly"))
            {
                hour.addHourly(emp.getFirst(), emp.getLast());
            }
            else if (emp.getEmpStatus().equals("Salaried"))
            {
                sal.addSalary(emp.getFirst(), emp.getLast());
            }

            System.out.println("Employee addition complete");
        }
        else if (choice == 2)
        {
            System.out.println("What would you like to do?\n1: See all employees\n2. Find specific employee");
            choice = in.nextInt();

            if (choice == 1)
            {
                emp.displayAll();
                System.out.println("To access specific employee information, return to beginning.");
            }
            else if (choice == 2)
            {
                System.out.println("Enter first name of employee: ");
                String first = in.next();
    
                System.out.println("Enter last name of employee: ");
                String last = in.next();
    
                emp.findEmployee(first, last);
    
          //Asks the user if they want to see the logged hours and payroll
                System.out.println("Would you like to access the time clock and payroll?\n1: Yes\n2: No");
                choice = in.nextInt();
    
                if (choice == 1)
                {
                    String status = emp.filingStatus(first, last);
                  
                //If statement that checks if the employee is hourly or salaried
                    if (status == "Hourly")
                    {
    
                      //If statement that asks the user if they want to input hours manually for employee
                        System.out.println("Would you like to manually input hours for this pay period?\n1: Yes\n2: No");
                        choice = in.nextInt();
    
                        if (choice == 1)
                        {   
                            String filingStatus = hour.filingStatus(first, last);
                            hour.inputHours(first, last, filingStatus);
                        }
    
                      //If statement that asks the user if they want to see currently logged hours
                        System.out.println("Would you like to see currently logged hours?\n1: Yes\n2: No");
                            choice = in.nextInt();
    
                            if (choice == 1)
                            {
                                hour.displayHours(first, last);
                            }
                            else if (choice == 2)
                            {
                                System.out.println("Exiting program...");
                            }
    
                    }
                    else if (status == "Salaried")
                    {
                        sal.findSalary(first, last);
                        System.out.println("Exiting program...");
                    }
                }
                else if (choice == 2)
                {
                    System.out.println("Exiting program...");
                }

            }

        }

        in.close();
    }
}
