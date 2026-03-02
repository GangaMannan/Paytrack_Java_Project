package PayRoll;

import java.util.ArrayList;
import java.util.List;

public class FundMng {
    private double balance;
    private ArrayList<Transac> transactions;

    public FundMng(double initialBalance) {
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public void addIncome(double amount) {
        balance += amount;
        transactions.add(new Transac("SALE", amount));
    }

    // ✅ Returns false if insufficient balance
    public boolean addExpense(double amount) {
        if (balance < amount) {
            return false; // Insufficient funds
        }
        balance -= amount;
        transactions.add(new Transac("EXPENSE", amount));
        return true;
    }

    // ✅ Returns false if insufficient balance
    public boolean addSalaryExpense(double amount) {
        if (balance < amount) {
            return false; // Insufficient funds
        }
        balance -= amount;
        transactions.add(new Transac("SALARY", amount));
        return true;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Transac> getTransactions() {
        return transactions;
    }

    // ✅ Load transactions from storage
    public void setTransactions(List<Transac> transactions) {
        this.transactions = new ArrayList<>();
        if (transactions != null) {
            this.transactions.addAll(transactions);
        }
    }
}
