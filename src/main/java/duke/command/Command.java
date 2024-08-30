package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that can be executed in the Duke application.
 */
public interface Command {

    /**
     * Executes the command, performing the specific action defined by the implementation.
     *
     * @param tasks The task list on which the command operates.
     * @param ui The user interface to interact with the user.
     * @param storage The storage system used to save and load tasks.
     * @throws IOException If an I/O error occurs while executing the command, particularly when interacting with storage.
     */
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    /**
     * Determines whether the command will terminate the application.
     *
     * @return true if the command is meant to exit the application, false otherwise.
     */
    boolean isExit();
}