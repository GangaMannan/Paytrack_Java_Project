package PayRoll;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class PayrollUI extends JFrame {

    private final FundMng fund;
    private final PayLogic payroll;
    private final ExcelStorage storage;

    private final DefaultTableModel empModel;
    private final DefaultTableModel txnModel;

    private final JLabel balanceLabel;
    private final JLabel dateLabel;

    public PayrollUI() {
        this.fund = new FundMng(3000000);
        this.payroll = new PayLogic(fund);
        this.storage = new ExcelStorage("payroll.csv");
        
        // Load previously saved employees and transactions
        payroll.setEmployees(storage.loadEmployees());
        fund.setTransactions(storage.loadTransactions());

        setTitle("Company Payroll System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 600);
        setLocationRelativeTo(null);

        // ---------- Top bar ----------
        JPanel top = new JPanel(new BorderLayout());
        top.setBorder(new EmptyBorder(10, 12, 10, 12));

        JLabel title = new JLabel("COMPANY PAYROLL SYSTEM");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));

        dateLabel = new JLabel("Today: " + LocalDate.now());
        balanceLabel = new JLabel("Balance: ₹" + String.format("%.2f", fund.getBalance()));
        JPanel rightTop = new JPanel(new GridLayout(2, 1, 0, 2));
        rightTop.add(dateLabel);
        rightTop.add(balanceLabel);

        top.add(title, BorderLayout.WEST);
        top.add(rightTop, BorderLayout.EAST);

        // ---------- Center Tabs ----------
        JTabbedPane tabs = new JTabbedPane();

        // Employees tab
        empModel = new DefaultTableModel(new Object[]{"ID", "Name", "Type", "Salary/Details"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable empTable = new JTable(empModel);
        JScrollPane empScroll = new JScrollPane(empTable);

        JPanel empPanel = new JPanel(new BorderLayout());
        empPanel.add(empScroll, BorderLayout.CENTER);

        JPanel empButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addEmpBtn = new JButton("Add Employee");
        JButton removeEmpBtn = new JButton("Remove Employee");
        JButton refreshEmpBtn = new JButton("Refresh Employees");
        empButtons.add(addEmpBtn);
        empButtons.add(removeEmpBtn);
        empButtons.add(refreshEmpBtn);
        empPanel.add(empButtons, BorderLayout.NORTH);

        // Transactions tab
        txnModel = new DefaultTableModel(new Object[]{"Type", "Amount"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable txnTable = new JTable(txnModel);
        JScrollPane txnScroll = new JScrollPane(txnTable);

        JPanel txnPanel = new JPanel(new BorderLayout());
        txnPanel.add(txnScroll, BorderLayout.CENTER);

        JPanel txnButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addSaleBtn = new JButton("Add Sale");
        JButton addExpenseBtn = new JButton("Add Expense");
        JButton refreshTxnBtn = new JButton("Refresh Transactions");
        txnButtons.add(addSaleBtn);
        txnButtons.add(addExpenseBtn);
        txnButtons.add(refreshTxnBtn);
        txnPanel.add(txnButtons, BorderLayout.NORTH);

        tabs.addTab("Employees", empPanel);
        tabs.addTab("Transactions", txnPanel);

        // ---------- Bottom actions ----------
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setBorder(new EmptyBorder(8, 12, 10, 12));

        JButton processSalaryBtn = new JButton("Process Salary (5th Only)");
        JButton exitBtn = new JButton("Exit");
        bottom.add(processSalaryBtn);
        bottom.add(exitBtn);

        // ---------- Layout ----------
        setLayout(new BorderLayout());
        add(top, BorderLayout.NORTH);
        add(tabs, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        // ---------- Actions ----------
        addEmpBtn.addActionListener(e -> showAddEmployeeDialog());
        removeEmpBtn.addActionListener(e -> showRemoveEmployeeDialog());
        refreshEmpBtn.addActionListener(e -> refreshEmployees());

        addSaleBtn.addActionListener(e -> addSale());
        addExpenseBtn.addActionListener(e -> addExpense());
        refreshTxnBtn.addActionListener(e -> refreshTransactions());

        processSalaryBtn.addActionListener(e -> processSalaryUI());
        exitBtn.addActionListener(e -> {
            storage.saveEmployees(payroll.getEmployees());
            System.exit(0);
        });

        // save on window close as well
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                storage.saveEmployees(payroll.getEmployees());
            }
        });

        // Initial load
        refreshEmployees();
        refreshTransactions();
        updateBalanceLabel();
    }

    private void showAddEmployeeDialog() {
        String[] options = {"Full-Time", "Part-Time"};
        int type = JOptionPane.showOptionDialog(
                this,
                "Select Employee Type",
                "Add Employee",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (type == JOptionPane.CLOSED_OPTION) return;

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();

        JPanel base = new JPanel(new GridLayout(0, 1, 6, 6));
        base.add(new JLabel("Employee ID:"));
        base.add(idField);
        base.add(new JLabel("Name:"));
        base.add(nameField);

        if (type == 0) {
            JTextField salaryField = new JTextField();

            base.add(new JLabel("Monthly Salary:"));
            base.add(salaryField);

            int ok = JOptionPane.showConfirmDialog(this, base, "Add Full-Time Employee", JOptionPane.OK_CANCEL_OPTION);
            if (ok != JOptionPane.OK_OPTION) return;

            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                double salary = Double.parseDouble(salaryField.getText().trim());

                payroll.addEmployee(new FT_Emp(id, name, salary));
                // persist
                storage.saveEmployees(payroll.getEmployees());
                JOptionPane.showMessageDialog(this, "Employee Added ✅");
                refreshEmployees();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input ❌", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JTextField hoursField = new JTextField();
            JTextField rateField = new JTextField();

            base.add(new JLabel("Hours Worked:"));
            base.add(hoursField);
            base.add(new JLabel("Hourly Rate:"));
            base.add(rateField);

            int ok = JOptionPane.showConfirmDialog(this, base, "Add Part-Time Employee", JOptionPane.OK_CANCEL_OPTION);
            if (ok != JOptionPane.OK_OPTION) return;

            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                int hours = Integer.parseInt(hoursField.getText().trim());
                double rate = Double.parseDouble(rateField.getText().trim());

                payroll.addEmployee(new PT_Emp(id, name, hours, rate));
                // persist
                storage.saveEmployees(payroll.getEmployees());
                JOptionPane.showMessageDialog(this, "Employee Added ✅");
                refreshEmployees();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input ❌", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showRemoveEmployeeDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Employee ID to remove:");
        if (idStr == null) return;
        try {
            int id = Integer.parseInt(idStr.trim());
            payroll.removeEmployee(id);
            // persist
            storage.saveEmployees(payroll.getEmployees());
            JOptionPane.showMessageDialog(this, "Employee Removed ✅");
            refreshEmployees();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID ❌", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addSale() {
        String amtStr = JOptionPane.showInputDialog(this, "Enter Sale Amount:");
        if (amtStr == null) return;
        try {
            double amt = Double.parseDouble(amtStr.trim());
            if (amt <= 0) {
                JOptionPane.showMessageDialog(this, "Amount must be positive ❌", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            } else {
                fund.addIncome(amt);
                storage.saveTransactions(fund.getTransactions()); // ✅ SAVE
                refreshTransactions();
                updateBalanceLabel();
                JOptionPane.showMessageDialog(this, "Sale Added ✅");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Amount ❌", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addExpense() {
        String amtStr = JOptionPane.showInputDialog(this, "Enter Expense Amount:");
        if (amtStr == null) return;
        try {
            double amt = Double.parseDouble(amtStr.trim());
            if (fund.addExpense(amt)) {
                storage.saveTransactions(fund.getTransactions()); // ✅ SAVE
                refreshTransactions();
                updateBalanceLabel();
                JOptionPane.showMessageDialog(this, "Expense Added ✅");
            } else {
                JOptionPane.showMessageDialog(this, "❌ Insufficient Balance!\nAvailable: ₹" + String.format("%.2f", fund.getBalance()), "Transaction Failed", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Amount ❌", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void processSalaryUI() {
        LocalDate today = LocalDate.now();
        if (today.getDayOfMonth() != 5) {
            JOptionPane.showMessageDialog(this, "Salary can only be processed on 5th.", "Not Allowed", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // call your existing logic
        payroll.processSalary();
        storage.saveTransactions(fund.getTransactions()); // ✅ SAVE

        // update UI
        refreshTransactions();
        updateBalanceLabel();
        JOptionPane.showMessageDialog(this, "Salary processed (check transactions). ✅");
    }

    private void refreshEmployees() {
        empModel.setRowCount(0);

        // We don't have a getter for employees list in your PayLogic,
        // so we add a small helper method below OR we keep employees public (not recommended).
        // Best: add a getter in PayLogic: public ArrayList<Employee> getEmployees()

        for (Employee emp : payroll.getEmployees()) {
            String type = (emp instanceof FT_Emp) ? "Full-Time" : "Part-Time";
            String details;
            if (emp instanceof FT_Emp) {
                details = "Monthly: ₹" + String.format("%.2f", emp.calculateSalary());
            } else {
                details = "Computed: ₹" + String.format("%.2f", emp.calculateSalary());
            }
            empModel.addRow(new Object[]{emp.empId, emp.getName(), type, details});
        }
    }

    private void refreshTransactions() {
        txnModel.setRowCount(0);
        for (Transac t : fund.getTransactions()) {
            // Transac has private fields; so easiest is toString parsing OR improve Transac with getters.
            // We'll do simple display using toString:
            String s = t.toString(); // "TYPE : amount"
            String[] parts = s.split(":");
            String type = parts[0].trim();
            String amt = (parts.length > 1) ? parts[1].trim() : "";
            txnModel.addRow(new Object[]{type, amt});
        }
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: ₹" + String.format("%.2f", fund.getBalance()));
        dateLabel.setText("Today: " + LocalDate.now());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PayrollUI().setVisible(true));
    }
}