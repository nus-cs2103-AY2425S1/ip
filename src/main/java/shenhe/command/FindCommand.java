package shenhe.command;

import java.util.List;

import shenhe.Storage;
import shenhe.TaskList;
import shenhe.Ui;
import shenhe.task.Task;

/**
 * Represents a command to find tasks by searching for a keyword in the task description.
 * <p>
 * The {@code FindCommand} class handles the command to search for tasks that contain a specified keyword
 * in their descriptions. It retrieves the matching tasks from the task list and displays them to the user.
 * </p>
 */
public final class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} object with the specified user input.
     * <p>
     * Extracts the keyword from the user input, which is the substring following the command prefix.
     * </p>
     *
     * @param userInput The full user input containing the keyword to search for.
     */
    public FindCommand(String userInput) {
        this.keyword = userInput.substring(4).trim();
    }

    /**
     * Executes the find command to search for tasks matching the keyword.
     * <p>
     * This method searches for tasks that contain the specified keyword in their description,
     * and then displays the matching tasks to the user through the {@link Ui} instance.
     * </p>
     *
     * @param tasks The current task list containing all tasks.
     * @param ui The user interface instance used for displaying messages to the user.
     * @param storage The storage instance used for saving tasks. This parameter is not used in this method.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.findTasks(keyword);
        String message = ui.showMatchingMessage();
        for (int i = 0; i < matchingTasks.size(); i++) {
            message += "\n" + (i + 1) + ". " + matchingTasks.get(i).toString();
        }
        return message;
    }

    /**
     * Indicates whether this command should exit the application.
     * <p>
     * This method returns {@code false} to signal that the application should continue running.
     * </p>
     *
     * @return {@code false}, indicating that the application should not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
