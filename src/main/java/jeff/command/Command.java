package jeff.command;

import jeff.TaskList;
import jeff.Ui;
import jeff.Storage;
import jeff.exceptions.JEFFException;

/**
 * Abstract base class for all commands.
 *
 * The Command class provides a common interface for all commands that can be executed
 * by the JEFF application. Each specific command (e.g., MarkCommand, DeleteCommand)
 * should extend this class and implement the execute method.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks The task list on which the command operates.
     * @param ui The UI object used for user interaction.
     * @param storage The storage object used for saving/loading tasks.
     * @throws JEFFException If there is an error during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JEFFException;

    /**
     * Indicates whether this command ends the application.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal
    }
}