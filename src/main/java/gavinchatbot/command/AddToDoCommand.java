package gavinchatbot.command;

import java.io.IOException;

import gavinchatbot.task.Task;
import gavinchatbot.task.TaskList;
import gavinchatbot.task.ToDos;
import gavinchatbot.util.GavinException;
import gavinchatbot.util.Storage;
import gavinchatbot.util.Ui;

/**
 * Represents a command to add a ToDo task to the task list.
 */
public class AddToDoCommand implements Command {
    private final String description;

    /**
     * Constructs an AddToDoCommand with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public AddToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add a ToDo task.
     *
     * @param tasks The task list to add the ToDo task to.
     * @param ui The UI that will show the output to the user.
     * @param storage The storage where the task list is saved.
     * @return A message indicating the ToDo has been successfully added.
     * @throws GavinException If there is an error during the execution.
     * @throws IOException If there is an error saving the task list to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GavinException, IOException {
        Task task = new ToDos(description);
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        return ui.showAddedTask(task, tasks.size());
    }

    /**
     * Indicates whether this command will cause the application to exit.
     *
     * @return false as adding a ToDo task does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
