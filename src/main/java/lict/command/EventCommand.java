
package lict.command;

import lict.LictException;
import lict.Storage;
import lict.TaskList;
import lict.Ui;
import lict.task.Event;
import lict.task.Task;

/**
 * The {@code EventCommand} class handles the addition of an event task to the task list.
 * It processes the user input, creates a new {@code Event} object, and adds it to the task list.
 * The command also updates the UI and storage with the new task.
 */
public class EventCommand extends Command {
    private String info;

    public EventCommand(String info) {
        this.info = info;
    }

    /**
     * Executes the command to add a new event task.
     * Extracts the event details from the input string, creates the event, and updates the task list, UI, and storage.
     *
     * @param tasks    The task list to which the new event task will be added.
     * @param ui       The UI component responsible for displaying the addition of the new task.
     * @param storage  The storage to save the updated task list to.
     * @throws LictException If the input format is invalid or
     *     if any required information (description, start, end) is missing.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LictException {
        String[] eventInfo = extractInfo();
        Task newTask = new Event(eventInfo[0], eventInfo[1], eventInfo[2]);
        tasks.addTask(newTask);
        ui.hasAddedTask(newTask, tasks.size());
        storage.saveTasks(tasks);
    }

    /**
     * Extracts the event details (description, start, and end times) from the input string.
     *
     * @return A string array containing the description, start time, and end time of the event.
     * @throws LictException If the input format is invalid or
     *     if any required information (description, start, end) is missing.
     */
    public String[] extractInfo() throws LictException {
        String[] messageParts = info.split("/from", 2);
        String description = messageParts[0].trim();
        if (description.isEmpty()) {
            throw new LictException(
                    """
                    OOPS!!! The description of an event cannot be empty.
                     Please ensure that your input is in the format: event {description} /from {start} /to {end}"""
            );
        }
        if (messageParts.length != 2) {
            throw new LictException(
                    """
                    OOPS!!! The start of the event needs to be indicated.
                     Please ensure that your input is in the format: event {description} /from {start} /to {end}"""
            );
        }
        String start = messageParts[1].trim();
        String[] eventInfo = start.split("/to", 2);
        if (eventInfo.length != 2) {
            throw new LictException(
                    """
                    OOPS!!! The end of the event needs to be indicated.
                     Please ensure that your input is in the format: event {description} /from {start} /to {end}"""
            );
        }
        return new String[] {description, eventInfo[0].trim(), eventInfo[1].trim()};
    }
}
