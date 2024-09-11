package arts.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import arts.ArtsException;
import arts.task.Event;
import arts.task.TaskList;
import arts.util.Storage;
import arts.util.Ui;

/**
 * Represents a command to add an event task to the task list.
 */
public class AddEventCommand implements Command {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    private final String details;
    private final DateTimeFormatter[] inputFormatters;

    /**
     * Constructs an AddEventCommand with the specified task list, storage, UI, task details,
     * and date formatters.
     *
     * @param tasks The list of tasks.
     * @param storage The storage used to save tasks.
     * @param ui The user interface for displaying messages.
     * @param details The details of the task to be added.
     * @param inputFormatters An array of date formatters for parsing the event dates.
     */
    public AddEventCommand(TaskList tasks, Storage storage, Ui ui, String details,
                           DateTimeFormatter... inputFormatters) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
        this.details = details;
        this.inputFormatters = inputFormatters;
    }

    /**
     * Executes the command to add an event task. Parses the task details and adds the task
     * to the task list. Saves the updated task list to storage and displays a confirmation message.
     *
     * @throws ArtsException If the task details are invalid or if the date format is incorrect.
     */
    @Override
    public String execute() throws ArtsException {
        String[] eventParts = details.split(" /from | /to ");
        if (eventParts.length < 3) {
            throw new ArtsException("The event must have /from and /to times.");
        }
        LocalDateTime eventFromDate = parseDate(eventParts[1]);
        LocalDateTime eventToDate = parseDate(eventParts[2]);
        tasks.addTask(new Event(eventParts[0], eventFromDate, eventToDate));
        storage.save(tasks.getTasks());
        return "Got it. I've added this task:\n " + tasks.getTask(tasks.size() - 1)
                + "\nNow you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks")
                + " in the list.";
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
        return java.util.Arrays.stream(inputFormatters)
                .map(formatter -> {
                    try {
                        return LocalDateTime.parse(dateString, formatter);
                    } catch (DateTimeParseException e) {
                        return null;
                    }
                })
                .filter(date -> date != null)
                .findFirst()
                .orElseThrow(() -> new ArtsException(
                        "Invalid date format. Please use yyyy-MM-dd HHmm or d/M/yyyy HHmm."));
    }
}
