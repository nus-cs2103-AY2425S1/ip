package luna.command;

import luna.Storage;
import luna.TaskList;

/**
 * Represents a command to list all tasks in list.
 */
public class ListCommand implements Command {
    private final Command previousCommand;

    public ListCommand(Command previousCommand) {
        this.previousCommand = previousCommand;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.list();
    }

    @Override
    public String undo(TaskList tasks, Storage storage) {
        return "Nothing to undo for 'list' command";
    }

    @Override
    public Command getPreviousCommand() {
        return previousCommand;
    }
}
