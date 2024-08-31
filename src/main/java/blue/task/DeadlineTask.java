package blue.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class DeadlineTask extends Task {

    protected LocalDateTime deadline;

    // Constructor accepting a LocalDateTime object directly
    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    // Constructor that accepts a String and parses it
    public DeadlineTask(String description, String deadline) {
        super(description);
        try {
            // Ensure the format used for parsing matches the format used in the file
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.deadline = LocalDateTime.parse(deadline.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for deadline: " + deadline);
        }
    }

    @Override
    public String toString() {
        // Display the deadline in a human-readable format (you can keep this as it is)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        String formattedDeadline = deadline.format(formatter);
        return "[D] " + super.toString() + " (by: " + formattedDeadline + ")";
    }

    @Override
    public String toFileString() {
        // Write the deadline to the file in the format "d/M/yyyy HHmm"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        String formattedDeadline = deadline.format(formatter);
        return "D | " + getStatusIcon() + " | " + getDescription() + " | " + formattedDeadline;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeadlineTask that = (DeadlineTask) o;
        return Objects.equals(description, that.description) &&
                Objects.equals(deadline, that.deadline) && this.isDone == that.isDone;
    }
}
