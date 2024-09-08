package patrick.tasklist;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import patrick.DateFormatChecker;
import patrick.parser.Parser;
import patrick.storage.Storage;
import patrick.ui.Ui;

/**
 * The {@code Deadline} class represents a task with a specific deadline.
 * It extends the {@code Task} class and includes a {@code LocalDateTime} field to store the deadline.
 */
public class Deadline extends Task {
    public static final String PREFIX = "D | ";
    public static final String COLUMN = " | ";
    public static final String THERE_IS_AN_ERROR = "There is an error: ";
    protected LocalDateTime by;

    /**
     * Constructs a {@code Deadline} task with the specified description and deadline.
     *
     * @param description the description of the deadline task.
     * @param by the deadline date and time as a string.
     */
    public Deadline(String description, String by) {
        super(description);
        String format = DateFormatChecker.getDateFormat(by);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(format));
    }

    /**
     * Returns a string representation of the {@code Deadline} task, including its type,
     *  status, description, and deadline.
     *
     * @return a formatted string representing the {@code Deadline} task.
     */
    @Override
    public String toString() {
        return PREFIX + super.toString() + COLUMN + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }

    /**
     * Parses the user input to create a new {@code Deadline} task and adds it to the task list.
     * The method also validates the input for the task details and deadline format.
     *
     * @param input the user input containing the task description and deadline.
     * @return a response message indicating the result of the operation.
     * @throws Parser.PatrickException if the input is invalid or missing required components.
     */
    public static String deadlineTask(String input) throws Parser.PatrickException {
        String response;
        String newInput = input.replace("deadline ", "");
        if (newInput.isEmpty()) {
            throw new Parser.PatrickException("Deadline Task Details cannot be empty!!");
        }
        if (!newInput.contains("/by")) {
            throw new Parser.PatrickException("You are missing a '/by' in your details!!");
        }

        String taskDescription = newInput.substring(0, newInput.indexOf("/") - 1);
        if (taskDescription.isEmpty()) {
            throw new Parser.PatrickException("Deadline Task Description cannot be empty!!");
        }

        String deadline = newInput.substring(newInput.indexOf("/by")).replace("/by ", "");
        if (deadline.isEmpty()) {
            throw new Parser.PatrickException("Deadline Task deadline cannot be empty!!");
        }

        if (DateFormatChecker.getDateFormat(deadline).equals("Unknown Format")) {
            throw new Parser.PatrickException("Your deadline format is incorrect.\n"
                    + "Type 'formats' for the formats.");
        }

        Task task = new Deadline(taskDescription, deadline);
        Storage.addList(task);
        response = Ui.showUserMsg(task.toString());
        try {
            Storage.appendToFile("\n" + task);
        } catch (IOException e) {
            response = THERE_IS_AN_ERROR + e.getMessage();
        }
        return response;
    }
}
