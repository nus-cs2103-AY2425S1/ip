package thanos.commands;

import java.util.ArrayList;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.Task;
import thanos.tasks.TaskList;
import thanos.ui.Ui;

/**
 * Represents a command to list all tasks in the {@code TaskList}.
 */
public class ListCommand extends Command {
    /**
     * Constructs a {@code ListCommand} with the given argument.
     *
     * @param argument the argument associated with this command. This argument is not used for this command.
     */
    public ListCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the command to retrieve and display the list of all tasks.
     * <p>
     * This method retrieves the current list of tasks from the {@code TaskList} and passes it to the {@code Ui}
     * component for display. A message is shown to indicate that the tasks are being listed.
     * </p>
     *
     * @param taskList the list of tasks to be retrieved and displayed.
     * @param ui the user interface component used to display the tasks to the user.
     *
     * @throws InvalidCommandException if there is an issue retrieving or displaying the tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidCommandException {
        ArrayList<Task> result = taskList.getTaskList();
        ui.displayTasks(result, "Here are the tasks in your list:");
    }
}
