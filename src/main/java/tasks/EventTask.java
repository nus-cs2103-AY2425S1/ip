package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    LocalDateTime start;
    LocalDateTime end;
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");

    public EventTask(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = parseTime(start);
        this.end = parseTime(end);
    }
    /**
     * Changes the time format and then converts into a string to initialise the EventTask variables.
     * @param time a string whose format is to be changed.
     * @return A converted date and time to initialise the EventTask variables.
     * @throws DateTimeParseException for when there is a parsing error.
     */
    @Override
    protected LocalDateTime parseTime(String time) throws DateTimeParseException {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(time, inputFormatter);
    }
    /**
     * A symbol denoting the task type.
     * @return a string denoting the task type.
     */
    @Override
    public String getTaskType() {
        return "E";
    }
    /**
     * Gets the description of the task.
     * @return a string containing all the relevant information of the Task.
     */
    @Override
    public String getDescription() {
        return this.getTaskType() + " | " +
                super.getDescription().replace("\n", "") +
                "| " + String.format("from %s to %s",
                this.start.format(outputFormatter), this.end.format(outputFormatter));
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %s",
                this.getTaskType(), getStatusIcon(), super.description,
                this.start.format(outputFormatter), this.end.format(outputFormatter));
    }
    @Override
    public void updateTask(String field, String newValue) throws IllegalArgumentException {
        switch (field.toLowerCase()) {
        case "description":
            this.setDescription(newValue);
            break;
        case "from":
            this.start = parseTime(newValue);
            break;
        case "to":
            this.end = parseTime(newValue);
            break;
        default:
            throw new IllegalArgumentException("Invalid fields to update for Event Tasks!" +
                    "Correct way to update task: update <task number> <field> <new value>");
        }
    }
}
