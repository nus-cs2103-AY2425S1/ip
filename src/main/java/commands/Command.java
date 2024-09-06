package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Encapsulates relevant information behind a user command and abstracts the required state changes from the user.
 */
public abstract class Command {

    /**
     * Carry out the required actions of the command of task list updating, user feedback, and storage.
     * Unifying under a single method allows for abstraction of the effects of the command from the caller.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Command;
    }
}
