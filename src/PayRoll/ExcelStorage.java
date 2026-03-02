package PayRoll;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple CSV-based storage to avoid external dependencies.
 * Format per line:
 * FT,<id>,<name>,<monthlySalary>
 * PT,<id>,<name>,<hoursWorked>,<hourlyRate>
 */
public class ExcelStorage {
    private final Path filePath;
    private final Path transactionPath;

    public ExcelStorage(String filename) {
        this.filePath = Paths.get(filename);
        this.transactionPath = Paths.get("transactions.csv");
    }

    public List<Employee> loadEmployees() {
        List<Employee> list = new ArrayList<>();
        if (!Files.exists(filePath)) return list;

        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] parts = line.split(",", -1);
                String t = parts[0];
                try {
                    if ("FT".equals(t) && parts.length >= 4) {
                        int id = Integer.parseInt(parts[1]);
                        String name = parts[2];
                        double salary = Double.parseDouble(parts[3]);
                        list.add(new FT_Emp(id, name, salary));
                    } else if ("PT".equals(t) && parts.length >= 5) {
                        int id = Integer.parseInt(parts[1]);
                        String name = parts[2];
                        int hours = Integer.parseInt(parts[3]);
                        double rate = Double.parseDouble(parts[4]);
                        list.add(new PT_Emp(id, name, hours, rate));
                    }
                } catch (Exception ex) {
                    // skip malformed lines
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void saveEmployees(List<Employee> employees) {
        try {
            if (!Files.exists(filePath)) Files.createFile(filePath);
            try (BufferedWriter bw = Files.newBufferedWriter(filePath)) {
                for (Employee e : employees) {
                    if (e instanceof FT_Emp) {
                        FT_Emp f = (FT_Emp) e;
                        bw.write(String.format("FT,%d,%s,%.2f", f.getEmpId(), f.getName().replace(',', ' '), f.getMonthlySalary()));
                        bw.newLine();
                    } else if (e instanceof PT_Emp) {
                        PT_Emp p = (PT_Emp) e;
                        bw.write(String.format("PT,%d,%s,%d,%.2f", p.getEmpId(), p.getName().replace(',', ' '), p.getHoursWorked(), p.getHourlyRate()));
                        bw.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ✅ Load transactions from CSV
    public ArrayList<Transac> loadTransactions() {
        ArrayList<Transac> list = new ArrayList<>();
        if (!Files.exists(transactionPath)) return list;

        try (BufferedReader br = Files.newBufferedReader(transactionPath)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    try {
                        String type = parts[0];
                        double amount = Double.parseDouble(parts[1]);
                        list.add(new Transac(type, amount));
                    } catch (Exception ex) {
                        // skip malformed lines
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ✅ Save transactions to CSV
    public void saveTransactions(List<Transac> transactions) {
        try {
            if (!Files.exists(transactionPath)) Files.createFile(transactionPath);
            try (BufferedWriter bw = Files.newBufferedWriter(transactionPath)) {
                for (Transac t : transactions) {
                    bw.write(String.format("%s,%.2f", t.getType(), t.getAmount()));
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}