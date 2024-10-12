package evan.command;

import evan.service.TaskList;

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
    public String execute(TaskList taskList) {
        // Get Tasks with matching descriptions
        TaskList matchingTasks = taskList.getTasksWithMatchingDescription(description);

        return "Here are the matching tasks in your list:\n" + matchingTasks;
    }
}
