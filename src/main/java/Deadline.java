/**
 * Deadline is a class that encapsulates the characteristics of a Deadline Task.
 * It extends from the class Task,
 * and contains an additional characteristic of
 * date.
 */
public class Deadline extends Task {
    private String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public Deadline(String description, boolean isDone, String date) {
        super(description, isDone);
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    /**
     * The method converts the task to its text representation in the file.
     */
    @Override
    public String toText() {
        String completed = isDone ? "1" : "0";
        return "D | " + completed + " | " + super.description + " | " + date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDate() + ")";
    }
}
