package friendlybot.command;

import friendlybot.Storage;
import friendlybot.Ui;
import friendlybot.task.TaskList;

/**
 * BadCommand is a Command that does nothing when executed. BadCommand is used when the user's input does not
 * correspond with any methods.
 */
public class BadCommand extends Command {
    private String errorString;

    public BadCommand(String errorString) {
        this.errorString = errorString;
    }

    public BadCommand() {
    }

    /**
     * Does nothing upon execution.
     *
     * @param tasks An instance of TaskList where the new task is added to.
     * @param ui An instance of Ui (User Interface) that handles the interactions between FriendlyBot and user.
     * @param storage An instance of Storage that loads tasks and saves tasks in a file.
     *
     * @return Returns an empty response String from FriendlyBot to the User.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return errorString == null ? "OOPS!! I'm sorry, that's not a command :-(" : errorString;
    }
}
