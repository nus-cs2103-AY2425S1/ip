public class Deadline extends Task {
    private final String dueDate;

    Deadline(String description, String symbol, String dueDate) {
        super(description, symbol);
        this.dueDate = dueDate;
    }
}
