package nuffle.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Loan extends Task {

    private String borrower;
    private String lender;
    private double amount;
    private LocalDateTime dueDate;
    private boolean isPaid;

    public Loan(String borrower, String lender, double amount, LocalDateTime dueDate) {
        super("Loan: " + borrower + " owes " + lender + " $" + amount);
        this.borrower = borrower;
        this.lender = lender;
        this.amount = amount;
        this.dueDate = dueDate;
        this.isPaid = false;
    }

    public String getBorrower() {
        return borrower;
    }

    public String getLender() {
        return lender;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public boolean getPaidStatus() {
        return isPaid;
    }

    @Override
    public void markAsDone() {
        this.isPaid = true;

    }
    @Override
    public void markNotDone() {
        this.isPaid = false;

    }

    public String printSaveFormat() {
        String temp;
        if (isPaid) {
            temp = "1";
        } else {
            temp = "0";
        }
        return "L | " + temp + " | " + getBorrower() + " | "  + getLender() + " | " + getAmount() + " | " + dueDate.format(DateTimeFormatter.ofPattern("yyyy-MMM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[L] " + getBorrower() + " owes " + getLender() + " $" + getAmount() + ", due by " + getDueDate() + (isPaid ? " (repaid)" : " (pending)");
    }
}
