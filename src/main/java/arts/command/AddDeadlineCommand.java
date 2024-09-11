package arts.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import arts.ArtsException;
import arts.task.Deadline;
import arts.task.TaskList;
import arts.util.Storage;
import arts.util.Ui;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class AddDeadlineCommand implements Command {
    private static final String DATE_FORMAT_ERROR_MESSAGE = "Invalid date format. "
            + "Please use yyyy-MM-dd HHmm or d/M/yyyy HHmm.";
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    private final String details;
    private final DateTimeFormatter[] inputFormatters;


    /**
     * Constructs an AddDeadlineCommand with the specified task list, storage, UI, task details,
     * and date formatters.
     *
     * @param tasks The list of tasks.
     * @param storage The storage used to save tasks.
     * @param ui The user interface for displaying messages.
     * @param details The details of the task to be added.
     * @param inputFormatters An array of date formatters for parsing the deadline date.
     */
    public AddDeadlineCommand(TaskList tasks, Storage storage, Ui ui, String details,
                              DateTimeFormatter... inputFormatters) {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";
        assert ui != null : "Ui cannot be null";
        assert details != null && !details.trim().isEmpty() : "Details cannot be null or empty";
        assert inputFormatters != null && inputFormatters.length > 0 : "At least one DateTimeFormatter "
                + "must be provided";

        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
        this.details = details.trim();
        this.inputFormatters = inputFormatters;
    }

    /**
     * Executes the command to add a deadline task. Parses the task details and adds the task
     * to the task list. Saves the updated task list to storage and displays a confirmation message.
     *
     * @throws ArtsException If the task details are invalid or if the date format is incorrect.
     */
    @Override
    public String execute() throws ArtsException {
        String[] deadlineParts = details.split(" /by ");
        if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
            throw new ArtsException("Details must contain a '/by' to separate task description and deadline");
        }

        String taskDescription = deadlineParts[0].trim();
        if (taskDescription.isEmpty()) {
            throw new ArtsException("Task description cannot be empty.");
        }

        LocalDateTime deadlineDate = parseDate(deadlineParts[1].trim());

        if (tasks.contains(new Deadline(taskDescription, deadlineDate))) {
            throw new ArtsException("A task with the same description and deadline already exists.");
        }

        tasks.addTask(new Deadline(taskDescription, deadlineDate));

        assert tasks.size() > 0 : "Task was not added to the task list";

        storage.save(tasks.getTasks());

        return "Yatta! 🎉 I've successfully added this task to your list:\n✨ " + tasks.getTask(tasks.size() - 1)
                + " ✨\nNow your quest has " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks")
                + " to conquer! Ganbatte! 💪";
    }

    /**
     * Parses a date string using the provided date formatters. Attempts to parse the date string
     * with each formatter until successful. Throws an exception if all formatters fail.
     *
     * @param dateString The date string to parse.
     * @return The parsed LocalDateTime object.
     * @throws ArtsException If the date string cannot be parsed with any of the provided formatters.
     */
    private LocalDateTime parseDate(String dateString) throws ArtsException {
        assert dateString != null && !dateString.isEmpty() : "Date string cannot be null or empty";

        for (DateTimeFormatter formatter : inputFormatters) {
            try {
                return LocalDateTime.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                // Continue trying with the next formatter
            }
        }
        throw new ArtsException(DATE_FORMAT_ERROR_MESSAGE);
    }
}
