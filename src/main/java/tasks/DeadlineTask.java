package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline Task where it has its own description, completion status
 * and deadline.
 */
public class DeadlineTask extends Task {
    private LocalDateTime deadline;

    public DeadlineTask(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = parseTime(deadline);
    }
    /**
     * Changes the time format and then converts into a string to initialise the DeadlineTask variables.
     * @param deadline a string whose format is to be changed.
     * @return A converted date and time to initialise the DeadlineTask variables.
     * @throws DateTimeParseException for when there is a parsing error.
     */
    @Override
    protected LocalDateTime parseTime(String deadline) throws DateTimeParseException {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(deadline, inputFormatter);
    }
    /**
     * Returns the symbol denoting the task type.
     * @return a string denoting the task type.
     */
    @Override
    public String getTaskType() {
        return "D";
    }
    /**
     * Returns the description of the task.
     * @return a string containing all the relevant information of the Task.
     */
    @Override
    public String getDescription() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
        return this.getTaskType() + " | " +
                super.getDescription().replace("\n", "") + "| " +
                String.format("%s", this.deadline.format(outputFormatter));
    }

    /**
     * Returns the description of the task to be parsed.
     * @return a string to be parsed.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
        return String.format("%s | %s | %s | %s",
                this.getTaskType(), getStatusIcon(), super.description, this.deadline.format(outputFormatter));
    }

    /**
     * Replaces the value of the field with the new value.
     *
     * @param field Field that is being replaced with the new value.
     * @param newValue Value replacing the old value.
     * @throws IllegalArgumentException If there are invalid fields or incorrect input
     * of command.
     */
    @Override
    public void updateTask(String field, String newValue) throws IllegalArgumentException {
        switch(field.toLowerCase()) {
        case "description":
            this.setDescription(newValue + " ");
            break;
        case "by":
            this.deadline = parseTime(newValue);
            break;
        default:
            throw new IllegalArgumentException("Invalid fields to update for Deadline Tasks!\n" +
                    "Correct way to update task: update <task number> <field> <new value>\n");
        }

    }
}
