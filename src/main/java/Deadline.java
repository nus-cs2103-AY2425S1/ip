public class Deadline extends Task {
    private final String dueDate;

    Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }
}
