package shrimp.command;

import java.util.stream.StreamSupport;

import shrimp.task.TaskList;
import shrimp.utility.Ui;

/**
 * Handles the finding of tasks in the list by a keyword.
 */
public class FindCommand implements Command {

    private final String keyword;

    /**
     * Constructs a {@code FindCommand} with the specified keyword.
     *
     * @param keyword The keyword to search for in the task descriptions.
     */
    public FindCommand(String keyword) {
        assert keyword != null : "keyword is empty";
        this.keyword = keyword;
    }

    /**
     * Executes the command to find and display tasks that contain the keyword.
     *
     * @param taskList The list of tasks to search through.
     * @param ui       The user interface to interact with the user.
     */
    @Override
    public String run(TaskList taskList, Ui ui) {
        TaskList matchingTasks = new TaskList();

        StreamSupport.stream(taskList.spliterator(), false)
                .filter(task -> task.getDescription().contains(keyword))
                .forEach(matchingTasks::addTask);

        if (matchingTasks.isEmpty()) {
            return ui.printError("Can't find any matching task...");
        } else {
            return ui.printFind(matchingTasks);
        }
    }
}
