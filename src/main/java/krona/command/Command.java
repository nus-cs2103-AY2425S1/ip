package krona.command;

import krona.task.TaskList;
import krona.ui.Ui;
import krona.storage.Storage;
import krona.exception.KronaException;

/**
 * Represents a executable command in the Krona chatbot.
 */
public abstract class Command {

    /**
     * Executes the command, affecting the provided task list, UI, and storage.
     *
     * @param tasks The task list that the command operates on.
     * @param ui The UI component that handles interactions with the user.
     * @param storage The storage component that handles saving and loading tasks.
     * @throws KronaException If an error occurs during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws KronaException;

    /**
     * Indicates whether this command causes the application to exit.
     *
     * @return {@code true} if the command should cause the application to exit, {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
