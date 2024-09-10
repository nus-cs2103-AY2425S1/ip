package hoshi.command;

import hoshi.task.Task;
import hoshi.task.TaskList;
import hoshi.ui.Ui;
import hoshi.utils.Storage;

/**
 * FindCommand where the logic for finding a task is handled
 */
public class FindCommand implements Command {

    /**
     * keyword of the task to be found
     */
    private final String keyword;

    /**
     * FindCommand constructor
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
            Task currentTask = tasks.get(i);
            String currentDesc = currentTask.getDesc().toLowerCase();
            // Check if the task contains the keyword
            if (currentDesc.contains(keyword)) {
                // add currentTask if currentDesc contains the keyword
                matchingTasks.add(currentTask);
            }
        }
        // call ui class to display matching tasks list
        return ui.displayFoundTasks(matchingTasks);
    }
}
