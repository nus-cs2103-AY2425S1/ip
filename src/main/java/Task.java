import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    protected String description;
    protected boolean isDone;
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public TaskType getType() {
        return type;
    }

    public String toSaveString() {
        return getTypeIcon() + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + getTypeIcon() + "][" + getStatusIcon() + "] " + description;
    }

    public String getTypeIcon() {
        switch (type) {
            case TODO:
                return "T";
            case DEADLINE:
                return "D";
            case EVENT:
                return "E";
            default:
                return " ";
        }
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toSaveString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}

class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        try {
            this.by = LocalDate.parse(by);  // Parse date in yyyy-mm-dd format
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Please use yyyy-mm-dd.");
        }
    }

    @Override
    public String toSaveString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return super.toString() + " (by: " + by.format(formatter) + ")";
    }
}

class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        try {
            this.from = LocalDate.parse(from);  // Parse start date in yyyy-mm-dd format
            this.to = LocalDate.parse(to);      // Parse end date in yyyy-mm-dd format
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Please use yyyy-mm-dd.");
        }
    }

    @Override
    public String toSaveString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
}