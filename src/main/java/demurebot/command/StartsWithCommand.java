package demurebot.command;

import demurebot.task.TaskList;
import demurebot.ui.Ui;

/**
 * Represents a command to find tasks starting with a keyword.
 */
public class StartsWithCommand extends Command {
    private final String keyword;

    /**
     * Constructor for StartsWithCommand.
     *
     * @param keyword Keyword to search for.
     */
    public StartsWithCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find tasks starting with the keyword.
     *
     * @param tasks List of tasks.
     * @param ui Ui to interact with user.
     * @return String representation of tasks with the keyword.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).getDescription().startsWith(keyword)) {
                result.append(tasks.getTask(i)).append("\n");
            }
        }
        return result.toString().isEmpty() ? ui.displayNoTasksFound() : result.toString();
    }
}
