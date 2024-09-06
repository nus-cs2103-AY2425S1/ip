package stan.commands;

import stan.Storage;
import stan.TaskList;
import stan.exceptions.StanInvalidArgumentException;
import stan.exceptions.StanInvalidDateTimeFormatException;
import stan.exceptions.StanMissingArgumentException;
import stan.tasks.Event;
import stan.tasks.Task;
import stan.ui.Ui;

/**
 * Represents a command to add an event task.
 */
public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs an EventCommand.
     *
     * @param words The user input split into words.
     * @throws StanMissingArgumentException If the description, '/from', or '/to' clause is missing.
     * @throws StanInvalidArgumentException If the description, start time, or end time is empty.
     */
    public EventCommand(String[] words) throws StanMissingArgumentException, StanInvalidArgumentException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new StanMissingArgumentException("The description of an event cannot be empty.");
        }

        if (!words[1].contains("/from") || !words[1].contains("/to")) {
            throw new StanInvalidArgumentException("The event description is present but the '/from' or '/to' "
                    + "clause is missing. Please add the '/from' clause followed by the start time and "
                    + "the '/to' clause followed by the end time.");
        }

        String[] parts = words[1].split(" /from ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            throw new StanInvalidArgumentException("The event description cannot be empty.");
        }

        String[] times = parts[1].split(" /to ", 2);
        if (times.length < 2 || times[0].trim().isEmpty() || times[1].trim().isEmpty()) {
            throw new StanInvalidArgumentException("The event start time and end time cannot be empty.");
        }

        this.description = parts[0];
        this.from = times[0];
        this.to = times[1];
    }

    /**
     * Executes the command to add an event task to the task list.
     *
     * @param tasks The task list where the task will be added.
     * @param ui The UI object to display feedback to the user.
     * @param storage The storage object to save the updated task list.
     * @throws StanInvalidDateTimeFormatException If the date and time format is incorrect.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws StanInvalidDateTimeFormatException {
        Task task = new Event(description, from, to);
        tasks.add(task);
        storage.saveTasks(tasks.getTasks());
        return ui.showTaskAdded(task, tasks.size());
    }
}
