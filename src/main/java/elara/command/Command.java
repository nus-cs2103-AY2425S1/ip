package elara.command;

import elara.task.InvalidInputException;
import elara.utils.Storage;
import elara.utils.TaskList;
import elara.utils.Ui;

/**
 * Represents a command that can be executed in the Elara chatbot.
 * Implementing classes must define the behavior for executing the command.
 */
public interface Command {
    /**
     * Executes the command.
     *
     * @param taskList The task list that the command interacts with.
     * @param ui The user interface for displaying output to the user.
     * @param storage The storage object used for saving and loading tasks.
     * @throws InvalidInputException if the command input is invalid or cannot be processed.
     */
    void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException;
}
