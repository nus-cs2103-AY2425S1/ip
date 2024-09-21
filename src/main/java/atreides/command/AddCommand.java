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

    /**
     * Constructs an AddCommand with the specified message.
     * The message is split into individual words and stored.
     *
     * @param msg the message containing the task to be added
     */
    public AddCommand(String msg) {
        this.msg = msg;
        words = msg.split(" ");
    }

    /**
     * Executes the add command by creating a new task, adding it to the task list,
     * and generating a response string indicating the task addition and list size.
     *
     * @param tasks the list of tasks to which the new task will be added
     * @param ui the UI logic used for providing feedback to the user
     * @param storage the storage mechanism for tasks, although it is unused in this method
     * @return a string message confirming the addition of a new task and the total number of tasks
     * @throws AtreidesException if the task cannot be created from the given message
     */
    @Override
    public String executeString(TaskList tasks, Ui ui, Storage storage) throws AtreidesException {
        Task newTask = TaskList.getTask(msg, words);
        tasks.addTask(newTask);
        String plural = tasks.size() == 1 ? " task" : " tasks";
        String response = "Task added\n"
                + newTask.toString().indent(2)
                + tasks.size() + plural + " in list\n";
        return response;
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
        String response = executeString(tasks, ui, storage);
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
