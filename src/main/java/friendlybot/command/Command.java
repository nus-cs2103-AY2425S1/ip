package friendlybot.command;

import friendlybot.Storage;
import friendlybot.Ui;
import friendlybot.task.TaskList;

/**
 * Command is an abstract class for all types of commands.
 */
public abstract class Command {
    /**
     * An abstract method that does something when the Command is executed.
     *
     * @param tasks An instance of TaskList where the new task is added to.
     * @param ui An instance of Ui (User Interface) that handles the interactions between FriendlyBot and user.
     * @param storage An instance of Storage that loads tasks and saves tasks in a file.
     *
     * @return Returns a response String from FriendlyBot to the User.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Tells FriendlyBot whether the command is an Exit Command to exit out of the loop.
     *
     * @return Returns true if the command is an ExitCommand, else false.
     */
    public boolean isExit() {
        return false;
    }
}
