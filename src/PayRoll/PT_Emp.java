package PayRoll;

public class PT_Emp extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PT_Emp(int empId, String name, int hoursWorked, double hourlyRate) {
        super(empId, name);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }
}
