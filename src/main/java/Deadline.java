public class Deadline extends Task {
    protected String end;

    public Deadline(String command, String end) {
        super(command);  // Pass task description to Task class
        this.end = end.trim();  // Assign the end time
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + end + ")";  // Ensure the end time is displayed
    }
}
