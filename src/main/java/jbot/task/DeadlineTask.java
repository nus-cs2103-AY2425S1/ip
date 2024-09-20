package jbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * A <code>DeadlineTask</code> includes a name and a specific deadline date and time.
 */
public class DeadlineTask extends Task {

    private LocalDateTime deadline;

    /**
     * Constructs a <code>DeadlineTask</code> with the specified input string.
     * The input string must include the task name and deadline in the format 'd/M/yyyy HHmm'.
     *
     * @param input The input string containing the task details and deadline.
     */
    public DeadlineTask(String input) {
        int byIndex = input.indexOf("/by");
        this.setName(input.substring(9, byIndex).trim());
        String deadlineString = input.substring(byIndex + 4).trim();

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            this.deadline = LocalDateTime.parse(deadlineString, inputFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Please use 'd/M/yyyy HHmm'.");
            e.printStackTrace();
        }

        this.setTaskTypeSymbol("D");
    }

    /**
     * Constructs a <code>DeadlineTask</code> with the specified name and deadline.
     * This constructor is intended for deserialization.
     *
     * @param name The name of the task.
     * @param deadlineString The deadline in ISO-8601 format.
     */
    public DeadlineTask(String name, String deadlineString) {
        this.setName(name);
        this.setTaskTypeSymbol("D");
        this.deadline = LocalDateTime.parse(deadlineString); // Assuming the deadlineString is in ISO format
    }

    /**
     * Returns a string representation of the deadline task.
     * The format is the task's super string representation followed by the formatted deadline.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        String formattedDeadline = this.getDeadline().format(outputFormatter);
        return String.format("%1$s (by: %2$s)", super.toString(), formattedDeadline);
    }

    /**
     * Returns the deadline as a string in ISO-8601 format.
     *
     * @return The deadline in ISO-8601 format.
     */
    public String getDeadlineAsString() {
        return this.getDeadline().toString(); // This returns the ISO-8601 format by default
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }
}
