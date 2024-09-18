package delta.command;

import delta.exception.DeltaException;
import delta.util.Storage;
import delta.util.TaskList;
import delta.util.Ui;

/**
 * Concrete subclass of Command abstract class.
 * Prints all tasks from stored list.
 */
public class PrintCommand extends Command {
    /**
     * Creates a PrintCommand instance.
     */
    public PrintCommand() {
        super(CommandType.Print);
    }

    /**
     * Returns that MarkCommand is not the exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Prints all tasks from list.
     *
     * @param tasks List containing all current tasks.
     * @param ui User Interfacing object to print all tasks.
     * @param storage Storage object to save list.
     * @throws DeltaException If list is empty.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DeltaException {
        if (tasks.getSize() == 0) {
            throw new DeltaException("There are no tasks in your list.");
        }
        String message = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            message += String.format("%d. %s\n", i + 1, tasks.getTask(i));
        }
        ui.showCommand(message);
        return message;
    }
}
