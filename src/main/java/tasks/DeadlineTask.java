package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    private final LocalDateTime deadline;

    public DeadlineTask(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = parseDeadline(deadline);
    }
    /**
     * Changes the time format and then converts into a string to initialise the DeadlineTask variables.
     * @param deadline a string whose format is to be changed.
     * @return A converted date and time to initialise the DeadlineTask variables.
     * @throws DateTimeParseException for when there is a parsing error.
     */
    private LocalDateTime parseDeadline(String deadline) throws DateTimeParseException {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(deadline, inputFormatter);
    }

    /**
     * A symbol denoting the task type.
     * @return a string denoting the task type.
     */
    @Override
    public String getTaskType() {
        return "D";
    }
    /**
     * Gets the description of the task.
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
     * Description of the task to be parsed.
     * @return a string to be parsed.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
        return String.format("%s | %s | %s | %s",
                this.getTaskType(), getStatusIcon(), super.description, this.deadline.format(outputFormatter));
    }

}
