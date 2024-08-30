import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {

    LocalDateTime deadline;

    public DeadlineTask(String input) {
        int byIndex = input.indexOf("/by");
        this.name = input.substring(9, byIndex).trim();
        String deadlineString = input.substring(byIndex + 4).trim();

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            this.deadline = LocalDateTime.parse(deadlineString, inputFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Please use 'd/M/yyyy HHmm'.");
            e.printStackTrace();
        }

        this.taskTypeSymbol = "D";
    }

    // Constructor for deserialization
    public DeadlineTask(String name, String deadlineString) {
        this.name = name;
        this.taskTypeSymbol = "D";
        this.deadline = LocalDateTime.parse(deadlineString); // Assuming the deadlineString is in ISO format
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        String formattedDeadline = this.deadline.format(outputFormatter);
        return String.format("%1$s (by: %2$s)", super.toString(), formattedDeadline);
    }

    public String getDeadlineAsString() {
        return this.deadline.toString(); // This returns the ISO-8601 format by default
    }
}