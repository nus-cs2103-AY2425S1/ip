package Gutti;

import java.time.format.DateTimeParseException;

/**
 * Represents a command to add an Event task.
 */
public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs an EventCommand object.
     *
     * @param input The input string containing the task description, start date/time, and end date/time.
     * @throws GuttiException If the input format is invalid or the date format is incorrect.
     */
    public EventCommand(String input) throws GuttiException {
        String[] parts = input.split("/from");
        if (parts.length != 2) {
            throw new GuttiException("Invalid format. Use: event <task description> /from <start date/time> /to <end date/time>");
        }
        String[] fromToParts = parts[1].split("/to");
        if (fromToParts.length != 2) {
            throw new GuttiException("Invalid format. Use: event <task description> /from <start date/time> /to <end date/time>");
        }
        this.description = parts[0].trim();
        this.from = fromToParts[0].trim();
        this.to = fromToParts[1].trim();
        try {
            DateTimeUtil.parseDateTime(from);
            DateTimeUtil.parseDateTime(to);
        } catch (DateTimeParseException e) {
            throw new GuttiException("Invalid date/time format. Accepted formats: yyyy-MM-dd HHmm, d/MM/yyyy HHmm, MMM dd yyyy h:mma , dd/mm/yyyy HHmm");
        }
    }

    /**
     * Executes the EventCommand by adding an Event task to the task list.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The UI object to handle user interactions.
     * @param storage The Storage object to handle saving tasks to a file.
     * @throws GuttiException If there is an error during command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GuttiException {
        Task eventTask = new Event(description, from, to, false);
        tasks.addTask(eventTask);
        storage.saveTasksToFile(tasks.getTasks());
        ui.showTaskList(tasks);
    }

    /**
     * Returns boolean on whether this command should terminate the application.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}