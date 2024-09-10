package mira.command;

import mira.Expense;

/**
 * Represents a command to add an expense to the expense list.
 */
public class AddExpenseCommand extends Command {
    private final Expense expense;

    /**
     * Constructs an {@code AddExpenseCommand} with the specified category, amount, and description.
     *
     * @param category    The category of the expense.
     * @param amount      The amount of the expense.
     * @param description A description of the expense.
     */
    public AddExpenseCommand(String category, double amount, String description) {
        this.expense = new Expense(category, amount, description);
    }

    /**
     * Executes the command by adding the expense to the expense list.
     *
     * @return A message indicating that the expense has been added.
     */
    @Override
    public String execute() {
        expenseList.addExpense(expense);
        return "Got it. I've added this expense:\n  " + expense
                + "\nNow you have " + expenseList.size() + " expenses in the list.";
    }
}

