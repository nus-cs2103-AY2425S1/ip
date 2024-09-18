package cookie.command;

import cookie.exception.CookieException;
import cookie.storage.Storage;
import cookie.task.Event;
import cookie.task.TaskList;
import cookie.ui.Ui;

/**
 * Represents a command to create and add a new {@code Event} task to the task list.
 */
public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructs a new {@code EventCommand} with the specified event details.
     *
     * @param description the description of the event
     * @param from the start time or date of the event
     * @param to the end time or date of the event
     */
    public EventCommand(String description, String from, String to) {
        assert !description.isEmpty();
        assert !from.isEmpty();
        assert !to.isEmpty();

        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the {@code EventCommand} by creating a new {@code Event} task with the provided details,
     * adding it to the {@code TaskList}, and updating the user interface with the details of the newly added task
     * and the current task list.
     *
     * @param taskList the list of tasks to which the new event task will be added
     * @param ui the user interface object used to print the details of the added task and the updated task count
     * @param storage the storage object (not used in this method, but included for command consistency)
     * @return a string containing the confirmation of the added event task and the updated task list
     * @throws CookieException if the event details are invalid or incomplete
     */
    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) throws CookieException {

        Event newEventTask = new Event(this.description, this.from, this.to);
        taskList.addTask(newEventTask);
        String response = ui.printLatestTask(newEventTask);
        response = response + ui.printNoTasksInList(taskList.getTaskArrayList());
        return response;
    }
}
