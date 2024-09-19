package luna.command;

import luna.LunaException;
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
     * @param inputs Inputs from user to find matching tasks.
     */
    public FindCommand(String[] inputs, Command previousCommand) throws LunaException {
        if (inputs.length == 1 || inputs[1].trim().isEmpty()) {
            throw new LunaException("Enter task description to search\n"
                    + "e.g. find book");
        }

        this.query = inputs[1].trim();
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
