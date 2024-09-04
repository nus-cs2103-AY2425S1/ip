package evan.command;

import evan.service.Storage;
import evan.service.TaskList;
import evan.service.Ui;

/**
 * Represents a command that the chatbot can find a task with a matching description.
 */
public class FindCommand extends Command {
    private final String description;

    /**
     * Instantiates a FindCommand object.
     *
     * @param description Description that is being searched for.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Get Tasks with matching descriptions
        TaskList matchingTasks = taskList.getTasksWithMatchingDescription(description);

        ui.showSuccess("Here are the matching tasks in your list:\n" + matchingTasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
