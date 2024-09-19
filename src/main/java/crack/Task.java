package crack;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Task class represents a generic task in the application.
 * It stores basic information about the task, such as its description, status
 * (done or not), and type (Todo, Deadline, or Event).
 */
public class Task {

    /**
     * Enum representing the different types of tasks.
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /**
     * Constructs a new Task with a description and type.
     * The task is initially marked as not done.
     *
     * @param description the description of the task.
     * @param type        the type of the task (Todo, Deadline, or Event).
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a blank space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the type of the task.
     *
     * @return the type of the task (Todo, Deadline, or Event).
     */
    public TaskType getType() {
        return type;
    }

    /**
     * Returns a formatted string representation of the task for saving to a file.
     *
     * @return the task details in a save-friendly format.
     */
    public String toSaveString() {
        return getTypeIcon() + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the task, including its type and status.
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getTypeIcon() + "][" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the icon representing the type of the task (T for Todo, D for
     * Deadline, E for Event).
     *
     * @return the icon of the task type.
     */
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

    /**
     * Updates the date associated with the task.
     * This method should be overridden by subclasses that support dates.
     *
     * @param newDate the new date as a String.
     * @throws UnsupportedOperationException if the task type does not support updating the date.
     */
    public void updateDate(String newDate) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This task type cannot be snoozed.");
    }
}

/**
 * Represents a Todo task. A Todo task only has a description and does not have
 * any dates.
 */
class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param description the description of the todo task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns a formatted string representation of the Todo task for saving to a
     * file.
     *
     * @return the Todo task details in a save-friendly format.
     */
    @Override
    public String toSaveString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}

/**
 * Represents a Deadline task. A Deadline task has a description and a due date.
 */
class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a new Deadline task with the specified description and due date.
     * The due date is expected to be in the yyyy-mm-dd format.
     *
     * @param description the description of the deadline task.
     * @param by          the due date of the deadline task (in yyyy-mm-dd format).
     * @throws DateTimeParseException if the date format is invalid.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description, TaskType.DEADLINE);
        this.by = LocalDate.parse(by); // Parse date in yyyy-mm-dd format
    }

    /**
     * Returns a formatted string representation of the Deadline task for saving to
     * a file.
     *
     * @return the Deadline task details in a save-friendly format.
     */
    @Override
    public String toSaveString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    /**
     * Returns a string representation of the Deadline task, including its
     * description and due date.
     *
     * @return a string representation of the Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Updates the deadline date of the task.
     *
     * @param newDate the new deadline date as a String in yyyy-mm-dd format.
     * @throws DateTimeParseException if the date format is invalid.
     */
    @Override
    public void updateDate(String newDate) throws DateTimeParseException {
        this.by = LocalDate.parse(newDate); // Parse new date
    }
}

/**
 * Represents an Event task. An Event task has a description, start date, and
 * end date.
 */
class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs a new Event task with the specified description, start date, and
     * end date.
     * The start and end dates are expected to be in the yyyy-mm-dd format.
     *
     * @param description the description of the event task.
     * @param from        the start date of the event (in yyyy-mm-dd format).
     * @param to          the end date of the event (in yyyy-mm-dd format).
     * @throws DateTimeParseException if the date format is invalid.
     * @throws IllegalArgumentException if the end date is before the start date.
     */
    public Event(String description, String from, String to) throws DateTimeParseException, IllegalArgumentException {
        super(description, TaskType.EVENT);
        this.from = LocalDate.parse(from); // Parse start date in yyyy-mm-dd format
        this.to = LocalDate.parse(to); // Parse end date in yyyy-mm-dd format
        if (this.to.isBefore(this.from)) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }
    }

    /**
     * Returns a formatted string representation of the Event task for saving to a
     * file.
     *
     * @return the Event task details in a save-friendly format.
     */
    @Override
    public String toSaveString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    /**
     * Returns a string representation of the Event task, including its description,
     * start date, and end date.
     *
     * @return a string representation of the Event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    /**
     * Updates the start date of the event.
     *
     * @param newDate the new start date as a String in yyyy-mm-dd format.
     * @throws DateTimeParseException if the date format is invalid.
     * @throws IllegalArgumentException if the new start date is after the end date.
     */
    @Override
    public void updateDate(String newDate) throws DateTimeParseException, IllegalArgumentException {
        LocalDate newFromDate = LocalDate.parse(newDate); // Parse new start date
        if (newFromDate.isAfter(this.to)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        } else {
            this.from = newFromDate;
        }
    }
}
