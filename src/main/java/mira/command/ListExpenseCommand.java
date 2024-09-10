package mira.command;

/**
 * Represents a command to list all expenses.
 */
public class ListExpenseCommand extends Command {

    /**
     * Executes the command by listing all expenses in the expense list.
     *
     * @return A formatted string of all expenses.
     */
    @Override
    public String execute() {
        if (expenseList.size() == 0) {
            return "No expenses recorded yet.";
        }
        return "Here are your recorded expenses:\n" + expenseList.listExpenses();
    }
}
