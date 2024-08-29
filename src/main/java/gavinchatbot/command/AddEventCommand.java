package gavinchatbot.command;

import java.io.IOException;

import gavinchatbot.task.Event;
import gavinchatbot.task.Task;
import gavinchatbot.task.TaskList;
import gavinchatbot.util.Ui;
import gavinchatbot.util.Storage;
import gavinchatbot.util.GavinException;

/**
 * Represents a command to add an event task to the task list.
 */
public class AddEventCommand implements Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs an AddEventCommand with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command to add an event task.
     *
     * @param tasks The task list to add the event to.
     * @param ui The UI that will show the output to the user.
     * @param storage The storage where the task list is saved.
     * @throws GavinException If there is an error during the execution.
     * @throws IOException If there is an error saving the task list to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GavinException, IOException {
        Task task = new Event(description, from, to);
        tasks.addTask(task);
        ui.showAddedTask(task, tasks.size());
        storage.save(tasks.getTasks());
    }

    /**
     * Indicates whether this command will cause the application to exit.
     *
     * @return false as adding an event does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
