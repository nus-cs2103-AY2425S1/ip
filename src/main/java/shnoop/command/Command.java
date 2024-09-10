package shnoop.command;

import java.io.IOException;

import shnoop.exceptions.IncompleteEventOrDeadlineException;
import shnoop.exceptions.ShnoopException;
import shnoop.storage.Storage;
import shnoop.tasks.TaskList;
import shnoop.ui.Ui;

/**
 * Encapsulates all the relevant actions to be taken when a Command is issued.
 */
public abstract class Command {
    /**
     * Executes all the relevant actions involved in the specific Command.
     *
     * @param tasks TaskList to be used.
     * @param ui Display mechanism to be used.
     * @param storage Storage class to be utilized.
     * @throws IOException If any issues pertaining to a missing or corrupt file arise during execution.
     * @throws ShnoopException If any ChatBot specific issue arise during execution.
     * @throws IncompleteEventOrDeadlineException If there was an incomplete input when creating an Event or Deadline
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IOException,
            ShnoopException, IncompleteEventOrDeadlineException;

    public boolean isExit() {
        return false;
    }
}
