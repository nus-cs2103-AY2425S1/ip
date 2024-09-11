package Commands;

import Storage.Storage;
import TaskList.TaskList;
import UI.Ui;

/**
 * Command to list all tasks.
 * This command retrieves and displays all tasks from the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand with the given input string.
     *
     * @param s The input string associated with the command (typically unused in this command).
     */
    public ListCommand(String s) {
        super(s);
    }

    /**
     * Executes the command to list all tasks.
     * This method retrieves all tasks from the task list and sends them to the user interface for display.
     *
     * @param t  The task list containing all tasks to be listed.
     * @param s  The storage object (not used in this command).
     * @param ui The user interface to display the list of tasks.
     */
    @Override
    public String execute(TaskList t, Storage s, Ui ui) {
        return t.printTasks();
    }
}

