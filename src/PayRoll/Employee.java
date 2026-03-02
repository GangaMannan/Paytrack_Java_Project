package PayRoll;

public abstract class Employee {
    protected int empId;
    protected String name;

    public Employee(int empId, String name) {
        this.empId = empId;
        this.name = name;
    }

    public abstract double calculateSalary();

    public String getName() {
        return name;
    }

    public int getEmpId() {
        return empId;
    }
}
