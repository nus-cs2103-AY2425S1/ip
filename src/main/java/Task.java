import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }
    public abstract String toFileFormat();

    public static Task parse(String line) {
        // Check the first character to determine the task type
        if (line.startsWith("T")) {
            // Assume the format is "T | isDone | description"
            String[] parts = line.split(" \\| ");
            boolean isDone = Objects.equals(parts[1], "1");
            return new Todo(parts[2], isDone); // Adjust constructor as per your Todo class definition
        } else if (line.startsWith("D")) {
            // Assume the format is "D | isDone | description | by"
            String[] parts = line.split(" \\| ");
            boolean isDone = parts[1].equals("1");
            try {

                LocalDateTime by = DateParser.parse(parts[3]); // Parse date string
                return new Deadline(parts[2], isDone, by); // Adjust constructor as per your Deadline class definition
            } catch (Exception e) {
                System.out.println("Invalid date/time format.");
                return null; // Return null if parsing fails
            }
        } else if (line.startsWith("E")) {
            // Assume the format is "E | isDone | description | from | to"
            String[] parts = line.split(" \\| ");
            boolean isDone = parts[1].equals("1");
            try {
                String from = parts[3]; // Parse "from" date string
                String to = parts[4];   // Parse "to" date string
                return new Event(parts[2], isDone, from, to); // Adjust constructor as per your Event class definition
            } catch (Exception e) {
                System.out.println("Invalid date/time format.");
                return null; // Return null if parsing fails
            }
        }

        // Handle unknown types by returning null
        return null;
    }


    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
