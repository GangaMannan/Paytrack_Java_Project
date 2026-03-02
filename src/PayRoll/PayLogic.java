package PayRoll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PayLogic {

    private ArrayList<Employee> employees;
    private FundMng fund;

    public PayLogic(FundMng fund) {
        this.employees = new ArrayList<>();
        this.fund = fund;
    }

    // ✅ Used by Excel saving
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    // ✅ Used by Excel loading
    public void setEmployees(List<Employee> list) {
        this.employees = new ArrayList<>();
        if (list != null) {
            this.employees.addAll(list);
        }
    }

    public void addEmployee(Employee emp) {
        if (emp != null) {
            employees.add(emp);
        }
    }

    public void removeEmployee(int empId) {
        employees.removeIf(emp -> emp.empId == empId);
    }

    public void listEmployees() {
        if (employees.isEmpty()) {
            System.out.println(" No employees found.");
            return;
        }

        System.out.println("\n--- Employee List ---");
        for (Employee emp : employees) {
            String type = (emp instanceof FT_Emp) ? "FULL-TIME" : (emp instanceof PT_Emp) ? "PART-TIME" : "EMP";
            System.out.println(emp.empId + " - " + emp.getName() + " [" + type + "]  Salary: ₹" + emp.calculateSalary());
        }
    }

    public void processSalary() {
        LocalDate today = LocalDate.now();

        if (today.getDayOfMonth() != 5) {
            System.out.println(" Salary can only be processed on 5th.");
            return;
        }

        if (employees.isEmpty()) {
            System.out.println(" No employees to pay salary.");
            return;
        }

        double totalSalary = 0;
        for (Employee emp : employees) {
            totalSalary += emp.calculateSalary();
        }

        if (fund.getBalance() >= totalSalary) {
            fund.addSalaryExpense(totalSalary);
            System.out.println(" Salary Paid: ₹" + totalSalary);
        } else {
            System.out.println(" Insufficient funds!");
        }
    }
}