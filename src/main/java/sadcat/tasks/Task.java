package sadcat.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import sadcat.exceptions.SadCatException;

/**
 * Represents a generic task.
 */
public class Task {
    protected static final DateTimeFormatter inputFormatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HHmm");
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;
    protected final DateTimeFormatter printFormatter = DateTimeFormatter
            .ofPattern("MMM d yyyy HH:mm");
    protected final DateTimeFormatter saveFormatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructs a new Task.
     *
     * @param description The description of the task
     * @param taskType The type of the task
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return "X" if the task is done, " " otherwise
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Marks the task as done and prints a message.
     */
    public void markAsDone() {
        if (this.isDone) {
            System.out.println("Task already marked as done:\n" + this);
        } else {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:\n" + this);
        }
    }

    /**
     * Marks the task as done without printing a message.
     */
    public void markAsDoneNonVerbose() {
        if (!this.isDone) {
            this.isDone = true;
        }
    }

    /**
     * Marks the task as not done and prints a message.
     */
    public void markAsNotDone() {
        if (!this.isDone) {
            System.out.println("Task already marked as not done:\n" + this);
        } else {
            this.isDone = false;
            System.out.println("Ok, I've marked this task as not done yet:\n" + this);
        }
    }

    /**
     * Returns a string representation of the task for saving.
     *
     * @return A string representation of the task
     */
    public String saveFormat() {
        return (this.isDone ? 1 : 0) + " | " + this.description;
    }

    /**
     * Creates a new Todo task.
     *
     * @param input The description of the todo task
     * @return A new Todo task
     * @throws SadCatException if the input is empty
     */
    public static Task createTodo(String input) throws SadCatException {
        if (input.isEmpty()) {
            throw new SadCatException("Empty Task description provided.");
        }
        return new Todo(input);
    }

    /**
     * Creates a new Deadline task.
     *
     * @param input The description and deadline of the task, separated by "/by"
     * @return A new Deadline task
     * @throws SadCatException if the input format is invalid
     * @throws DateTimeParseException if the deadline cannot be parsed
     */
    public static Task createDeadline(String input) throws SadCatException, DateTimeParseException {
        if (!input.contains("/by") || input.indexOf("/by") == input.length() - 3) {
            throw new SadCatException("Invalid deadline description provided.");
        }
        String[] deadlineInput = input.split("/by", 2);
        return new Deadline(deadlineInput[0].trim(),
                LocalDateTime.parse(deadlineInput[1].trim(), inputFormatter));
    }

    /**
     * Creates a new Event task.
     *
     * @param input The description and time range of the event, separated by "/from" and "/to"
     * @return A new Event task
     * @throws SadCatException if the input format is invalid
     * @throws DateTimeParseException if the event times cannot be parsed
     */
    public static Task createEvent(String input) throws SadCatException, DateTimeParseException {
        if (!input.contains("/from") || !input.contains("/to")
                || input.indexOf("/from") == input.length() - 5
                || input.indexOf("/to") == input.length() - 2) {
            throw new SadCatException("Invalid event description provided.");
        }
        String[] eventInput = input.split("/from", 2);
        String[] eventTimeInput = eventInput[1].trim().split("/to", 2);
        return new Event(eventInput[0].trim(),
                LocalDateTime.parse(eventTimeInput[0].trim(), inputFormatter),
                LocalDateTime.parse(eventTimeInput[1].trim(), inputFormatter));
    }
}
