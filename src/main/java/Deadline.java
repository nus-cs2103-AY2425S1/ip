import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private LocalDateTime deadline;

    public Deadline(String name, String deadline) {
        super(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.deadline = LocalDateTime.parse(deadline, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd HH:mm.");
        }
    }

    @Override
    public String toFileText() {
        return "D | " + (isDone ? "1" : "0") + " | " + name + " | " + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }
}
