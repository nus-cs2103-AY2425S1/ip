package thanos.commands;

import java.util.ArrayList;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.Task;
import thanos.tasks.TaskList;
import thanos.ui.Ui;

/**
 * Represents a command to find tasks in the {@code TaskList} that match a given keyword.
 */
public class FindCommand extends Command {
    /**
     * Constructs a {@code FindCommand} with the specified keyword argument.
     *
     * @param argument The keyword used to search for matching tasks.
     */
    public FindCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the find command by searching for tasks that contain the keyword.
     * The matching tasks are then displayed to the user.
     * If no keyword is provided, an {@code InvalidCommandException} is thrown.
     *
     * @param taskList The {@code TaskList} containing all tasks.
     * @param ui The {@code Ui} used to display the matching tasks.
     * @throws InvalidCommandException If no keyword is provided in the command argument.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidCommandException {
        if (this.getArgument().isEmpty()) {
            throw new InvalidCommandException(
                    "No keyword provided. Please use the correct format: 'find [keyword]'"
            );
        }

        ArrayList<Task> result = taskList.find(this.getArgument());
        ui.displayTasks(result, "Here are the matching tasks in your list:");
    }
}
