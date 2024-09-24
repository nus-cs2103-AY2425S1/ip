package beechat.command;

import beechat.util.Storage;
import beechat.task.TaskList;
import beechat.util.Ui;

import java.io.IOException;

/**
 * Represents an abstract command.
 * This class provides a template for all other command classes.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param tasks   The TaskList object that contains all tasks.
     * @param ui      The Ui object that handles all user interactions.
     * @param storage The Storage object that saves and loads tasks.
     * @throws IOException If an error occurs while executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    /**
     * Checks whether the command is a leave command.
     */
    public boolean isLeave() {
        return false;
    }
}