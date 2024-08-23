package citadel.commands;

import citadel.Task.TaskList;
import citadel.ui.TextUI;

public class FindTasks extends Command {

    /**
     * Constructs a new {@code FindTask} command with the specified input and task list.
     *
     * @param input The user input associated with the find command.
     * @param tasks The task list to search within.
     */
    public FindTasks(String input, TaskList tasks) {
        super(input, tasks);
    }

    /**
     * Executes the find task command.
     * <p>
     * This method searches the task list for tasks that contain the keyword provided
     * by the user and displays the matching tasks.
     * </p>
     */
    @Override
    public void run() {
        String keyword = input.substring(5).trim();
        TaskList matchingTasks = tasks.findTasks(keyword);
        TextUI.printFind(matchingTasks);
    }
}
