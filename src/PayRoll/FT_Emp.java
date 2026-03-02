package PayRoll;

public class FT_Emp extends Employee {
    private double monthlySalary;

    public FT_Emp(int empId, String name, double monthlySalary) {
        super(empId, name);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }
}
