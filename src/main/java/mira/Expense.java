package mira;

/**
 * Represents an expense with a category, amount, and description.
 */
public class Expense {
    private final String category;
    private final double amount;
    private final String description;

    /**
     * Constructs a new Expense with the specified category, amount, and description.
     *
     * @param category    The category of the expense (e.g., Food, Transport).
     * @param amount      The amount of the expense.
     * @param description A short description of the expense.
     */
    public Expense(String category, double amount, String description) {
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    /**
     * Returns a string representation of the expense.
     *
     * @return The formatted string showing the category, amount, and description.
     */
    @Override
    public String toString() {
        return "[" + category + "] $" + amount + " - " + description;
    }
}
