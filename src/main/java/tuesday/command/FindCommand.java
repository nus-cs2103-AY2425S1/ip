package tuesday.command;

import tuesday.task.Task;
import tuesday.util.Storage;
import tuesday.util.Ui;

/**
 * Represents a command to find a task in the list of tasks
 */
public class FindCommand extends Command {
    private final String keyword;
    private String responseMessage;

    /**
     * Constructor for FindCommand
     *
     * @param command Description of the command
     * @param keyword The postfix of the command
     */
    public FindCommand(String command, String keyword) {
        super(command);
        this.keyword = keyword;
    }

    /**
     * Update the response message to list the tasks with the keyword
     *
     * @param task The Task object
     * @param ui The UI object
     * @param storage The Storage object
     */
    public void execute(Task task, Ui ui, Storage storage) {
        assert this.keyword != null : "The FindCommand constructor must be created";
        this.responseMessage = ui.showFindMessage(this.keyword);
    }

    /**
     * Gets the response message of the command
     *
     * @return Response message of the command
     */
    public String getString() {
        assert this.responseMessage != null : "The execute() method must be called first";
        return this.responseMessage;
    }

    public boolean isExit() {
        return false;
    }
}
