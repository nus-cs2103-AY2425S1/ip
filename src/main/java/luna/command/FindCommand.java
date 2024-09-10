package luna.command;

import luna.Storage;
import luna.TaskList;

/**
 * Represents a command to find tasks from current list of tasks.
 */
public class FindCommand implements Command {
    private final String query;
    private final Command previousCommand;

    /**
     * Creates command to search list of tasks.
     *
     * @param query Description of task to search for.
     */
    public FindCommand(String query, Command previousCommand) {
        this.query = query;
        this.previousCommand = previousCommand;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        String matched = tasks.find(query);

        if (matched.isEmpty()) {
            return "No task with matching description";
        }
        return "Here are the tasks with the matching description:\n" + matched;
    }

    @Override
    public String undo(TaskList tasks, Storage storage) {
        return "Nothing to undo for 'find' command";
    }

    @Override
    public Command getPreviousCommand() {
        return previousCommand;
    }
}
