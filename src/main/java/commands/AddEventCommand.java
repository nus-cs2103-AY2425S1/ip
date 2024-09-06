package commands;

import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import utils.Storage;
import utils.Ui;


/**
 * Command to add an Event task to the task list.
 */
public class AddEventCommand implements Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs a new AddEventCommand.
     *
     * @param description Description of the event task.
     * @param from        Start date/time of the event.
     * @param to          End date/time of the event.
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command to add an event task to the task list.
     *
     * @param tasks   TaskList to which the task is added.
     * @param ui      UI to handle user interaction.
     * @param storage Storage to save the task list.
     * @return Result message of task addition.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task eventTask = new Event(description, from, to);
        tasks.add(eventTask);
        storage.save(tasks);
        return ui.showTaskAdded(eventTask, tasks.size());
    }
}
