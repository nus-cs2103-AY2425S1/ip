package dave.command;

import java.io.IOException;
import dave.task.TaskList;
import dave.storage.Storage;
import dave.ui.Ui;

/**
 * Represents an abstract command in the Dave application.
 * Each command will inherit from this class and implement its own behavior
 * by overriding the {@code execute} method.
 */
public abstract class Command {

    /**
     * Executes the command.
     * This method must be implemented by any class that inherits from {@code Command}.
     * It defines the actions to be performed when the command is run.
     *
     * @param tasks   The {@code TaskList} containing all the tasks.
     * @param storage The {@code Storage} object to handle saving and loading tasks.
     * @param ui      The {@code Ui} object to handle user interaction.
     * @throws IOException If an I/O error occurs during the execution of the command.
     */
    public abstract void execute(TaskList tasks, Storage storage, Ui ui) throws IOException;

    /**
     * Determines if the command is an exit command.
     * By default, this method returns {@code false}. Classes that represent
     * an exit command should override this method to return {@code true}.
     *
     * @return {@code true} if this command is an exit command; {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
