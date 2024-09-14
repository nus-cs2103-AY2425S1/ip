package joe.commands;

import joe.tasks.TaskList;

/**
 * Represents a command to find tasks in the task list.
 */
public class FindCommand extends Command {

    private final TaskList taskList;
    private final String keyword;

    /**
     * Constructs a FindCommand object.
     *
     * @param taskList Task list to find tasks from.
     * @param keyword  Keyword to search for in the task list.
     */
    public FindCommand(TaskList taskList, String keyword) {
        this.taskList = taskList;
        this.keyword = keyword;
    }

    @Override
    public String execute() {
        return taskList.findTasks(keyword);
    }
}
