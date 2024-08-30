package talkie.command;

import talkie.components.Storage;
import talkie.components.Ui;
import talkie.task.TaskList;

/**
 * Represents a command to list all tasks in the task list in the Talkie application.
 * The command triggers the display of the current list of tasks to the user.
 */
public class ListCommand extends Command {

    private String fullCommand;

    /**
     * Constructs a new {@code ListCommand} with the full user input.
     *
     * @param fullCommand The full user input containing the command type.
     */
    public ListCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the {@code ListCommand} by displaying the current list of tasks to the user.
     *
     * @param tasks   The task list containing all current tasks.
     * @param ui      The UI component used to display messages to the user.
     * @param storage The storage component used to save task data (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(tasks);
    }

    /**
     * Indicates that this command does not terminate the application.
     *
     * @return {@code false}, as this command does not end the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
