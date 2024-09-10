package mira;

import java.util.ArrayList;

/**
 * Represents a list of expenses.
 */
public class ExpenseList {
    private final ArrayList<Expense> expenses;

    /**
     * Constructs an empty ExpenseList.
     */
    public ExpenseList() {
        this.expenses = new ArrayList<>();
    }

    /**
     * Adds an expense to the list.
     *
     * @param expense The expense to be added.
     */
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    /**
     * Returns the total number of expenses in the list.
     *
     * @return The number of expenses.
     */
    public int size() {
        return expenses.size();
    }

    /**
     * Returns a string representation of all expenses in the list.
     *
     * @return A formatted string of all expenses.
     */
    public String listExpenses() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < expenses.size(); i++) {
            result.append(i + 1).append(". ").append(expenses.get(i));
            if (i < expenses.size() - 1) {
                result.append("\n"); // Add newline only if it is not the last task
            }
        }
        return result.toString();
    }
}

