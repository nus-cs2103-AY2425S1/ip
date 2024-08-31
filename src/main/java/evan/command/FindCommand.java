package evan.command;

import evan.main.Storage;
import evan.main.TaskList;
import evan.main.Ui;
import evan.task.Task;
import evan.task.Todo;

import java.util.ArrayList;

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
