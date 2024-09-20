package hoshi.command;

import hoshi.task.Task;
import hoshi.task.TaskList;
import hoshi.ui.Ui;
import hoshi.utils.Storage;

/**
 * FindCommand where the logic for finding a task is handled.
 */
public class FindCommand implements Command {

    /**
     * Stores the keyword of the task to be found.
     */
    private final String keyword;

    /**
     * Constructs a new instance of FindCommand.
     *
     * @param keyword the string to be used in search for a specified task.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Executes the logic for finding a task
     *
     * @param tasks  the TaskList that stores 3 types of tasks
     * @param ui     Ui that handles all user interaction
     * @param storage Storage that handles all input output of Hoshi
     * @return response corresponding to the function that was called depending on user input
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // create new matchingTasks list for matched tasks
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {

            // get current description of task
            Task currentTask = tasks.get(i);
            String currentDesc = currentTask.getDesc().toLowerCase();

            if (currentDesc.contains(keyword)) {
                // add currentTask if currentDesc contains the keyword
                matchingTasks.add(currentTask);
            }
        }
        // call ui class to display matching tasks list
        return ui.displayFoundTasks(matchingTasks);
    }
}
