package bruno.command;

import bruno.task.TaskList;

/**
 * Represents a command to find tasks containing a specific keyword in their description.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand object with the specified TaskList and keyword.
     *
     * @param tasks   The TaskList that contains the tasks to be searched.
     * @param keyword The keyword to search for in the task descriptions.
     */
    public FindCommand(TaskList tasks, String keyword) {
        super(tasks);
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks that contain the specified keyword.
     * Prints the tasks that match the keyword to the user.
     */
    @Override
    public void execute() {
        getTasks().findTask(keyword);
    }
}
