package krona.command;

import krona.exception.KronaException;
import krona.storage.Storage;
import krona.task.TaskList;
import krona.ui.Ui;

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

}
