package bimo.command;

import bimo.Storage;
import bimo.TaskList;
import bimo.Ui;

/**
 * Creates an object that can execute user input.
 */
public abstract class Command {
    /**
     * Decides what action to take and what response to return.
     * @param tasks List of user tasks.
     * @param ui User interface that interacts with users.
     * @param storage Storage that writes and load files
     * @return Response of chatbot.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
