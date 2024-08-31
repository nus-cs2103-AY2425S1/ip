package atreides.command;

import atreides.task.Task;
import atreides.task.TaskList;
import atreides.ui.AtreidesException;
import atreides.ui.Storage;
import atreides.ui.Ui;

/**
 * Represents an Add command where user adds a task to the list of tasks
 */

public class AddCommand implements Command {
    private final String msg;
    private final String[] words;

    public AddCommand(String msg) {
        this.msg = msg;
        words = msg.split(" ");
    }

    /**
     * adds the task to the list of tasks and gets the Ui to acknowledge
     * that a task has been added.
     * @param tasks represents the list of tasks
     * @param ui represents the logic for the Ui
     * @param storage represents the storage for the tasks
     * @throws AtreidesException If file not found
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AtreidesException {
        Task newTask = TaskList.getTask(msg, words);
        tasks.addTask(newTask);
        String plural = tasks.size() == 1 ? " task" : " tasks";
        String response = "atreides.task.Task added\n"
                + newTask.toString().indent(2)
                + tasks.size() + plural + " in list\n";
        ui.showMessage(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
