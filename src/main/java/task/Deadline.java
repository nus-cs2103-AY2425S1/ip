package task;


public class Deadline extends Task {
    private final String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
        this.type = "[D]";
        System.out.println("Successfully added task: " + this.toString());
        super.outputTaskCount();
    }

    @Override
    public String toString() {
        if (isCompleted) {
            return this.type + "[x] - " + this.description + "(due on " + this.dueDate + ")";
        }
        return this.type + "[ ] - " + this.description + " (due on " + this.dueDate + ")";
    }
}
