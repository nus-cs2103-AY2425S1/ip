import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
public class Deadline extends Task {
    protected String end;

    public Deadline(String command, String end) {
        super(command);  // Pass task description to Task class

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        // Parse the input string into a LocalDateTime object
        LocalDateTime endDate = LocalDateTime.parse(end.trim(), inputFormatter);

        // Define a formatter for the output string
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");


        // Format the LocalDateTime into the desired output format
        this.end = endDate.format(outputFormatter);

    }

    @Override
    public String toFileString() {
        return "[D][" + (isDone ? "X" : " ") + "]/" + description + "/" + end;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + end + ")";  // Ensure the end time is displayed
    }
}