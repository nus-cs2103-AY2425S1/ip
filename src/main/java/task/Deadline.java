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
        return this.type + "[" + this.status + "] - " + this.description + "(due on " + this.dueDate + ")";
    }
}
