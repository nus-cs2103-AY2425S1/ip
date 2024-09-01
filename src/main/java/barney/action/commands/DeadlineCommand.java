package barney.action.commands;

import java.util.HashMap;

import barney.data.TaskList;
import barney.data.exception.InvalidArgumentException;
import barney.data.task.DeadlineTask;
import barney.ui.Ui;

/**
 * Represents a command for creating a deadline task. Extends the
 * {@link Command} class.
 */
public class DeadlineCommand extends Command {
    /**
     * Constructs a new DeadlineCommand with the specified argument map.
     *
     * @param argumentMap map of the arguments
     */
    public DeadlineCommand(HashMap<String, String> argumentMap) {
        super("deadline", argumentMap);
    }

    /**
     * Executes the DeadlineCommand.
     * <p>
     * This method is responsible for executing the DeadlineCommand. It adds a new
     * DeadlineTask to the TaskList with the provided description and deadline. It
     * also prints a message to the user indicating that the task has been added
     * successfully.
     *
     * @param tasks The TaskList object that stores the list of tasks.
     * @param ui    The Ui object that handles user interface interactions.
     * @return true if the command is executed successfully, false otherwise.
     * @throws InvalidArgumentException if the argumentMap is invalid.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags();

        String description = getParameter("description");
        String by = getParameter("by");

        DeadlineTask newDeadline = new DeadlineTask(description, by);
        tasks.add(newDeadline);
        ui.printAddedTask(newDeadline, tasks.size());

        return true;
    }
}
