package patrick.tasklist;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import patrick.DateFormatChecker;
import patrick.parser.Parser;
import patrick.storage.Storage;
import patrick.ui.Ui;

import static patrick.ui.Ui.THERE_IS_AN_ERROR;

/**
 * The {@code Event} class represents a task that occurs at a specific time range.
 * It extends the {@code Task} class and includes {@code LocalDateTime} and
 * {@code LocalTime} fields to store the event's start and end times.
 */
public class Event extends Task {
    public static final String PREFIX = "E | ";
    public static final String COLUMN = " | ";
    private final LocalDateTime from;
    private final LocalTime to;

    /**
     * Constructs an {@code Event} task with the specified description, start time, and end time.
     *
     * @param description the description of the event task.
     * @param from the start time of the event as a string.
     * @param to the end time of the event as a string.
     */
    public Event(String description, String from, String to) {
        super(description);
        String format = DateFormatChecker.getDateFormat(from);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern(format));
        this.to = LocalTime.parse(to, DateTimeFormatter.ofPattern("HHmm"));
    }

    /**
     * Returns a string representation of the {@code Event} task, including its type,
     * status, description, start time, and end time.
     *
     * @return a formatted string representing the {@code Event} task.
     */
    @Override
    public String toString() {
        return PREFIX + super.toString() + COLUMN + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + "-" + this.to.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    /**
     * Parses the user input to create a new {@code Event} task and adds it to the task list.
     * The method also validates the input for the task details and the time format.
     *
     * @param input the user input containing the task description, start time, and end time.
     * @return a response message indicating the result of the operation.
     * @throws Parser.PatrickException if the input is invalid or missing required components.
     */
    public static String eventTask(String input) throws Parser.PatrickException {
        String response;
        String newInput = input.replace("event", "").trim();
        Task task;
        if (newInput.isEmpty()) {
            throw new Parser.PatrickException("Event Task Details cannot be empty!!");
        }

        if (!newInput.contains("/from")) {
            throw new Parser.PatrickException("You are missing a '/from' in your details!!");
        }

        if (!newInput.contains("/to")) {
            throw new Parser.PatrickException("You are missing a '/to' in your details!!");
        }

        String taskDescription = newInput.substring(0, newInput.indexOf("/from") - 1);
        if (taskDescription.isEmpty()) {
            throw new Parser.PatrickException("Event Task Description cannot be empty!!");
        }

        String from = newInput.substring(newInput.indexOf("/from"),
                newInput.indexOf("/to") - 1).replace("/from ", "");
        String to = newInput.substring(newInput.indexOf("/to")).replace("/to ", "");
        if (from.isEmpty()) {
            throw new Parser.PatrickException("You are missing 'from' information from your details!!");
        }

        if (to.isEmpty()) {
            throw new Parser.PatrickException("You are missing 'to' information from your details!!");
        }

        if (DateFormatChecker.getDateFormat(from).equals("Unknown Format")) {
            throw new Parser.PatrickException("Your 'from' format is incorrect.\n"
                    + "Type 'formats' for the formats.\n");
        }
        if (DateFormatChecker.getTimeFormat(to).equals("Unknown Format")) {
            throw new Parser.PatrickException("Your 'to' format is incorrect.\nFormat of 'to' is HHmm.");
        }

        task = new Event(taskDescription, from, to);
        if (Parser.isDuplicate(task)) {
            return Ui.NO_DUPLICATES;
        }
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
