package hien.command;

import hien.exception.HienException;
import hien.main.Storage;
import hien.main.TaskList;
import hien.ui.UI;

/**
 * Represents a command to list all tasks in the task list.
 * When executed, it displays all tasks or a message indicating that there are no tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand object.
     *
     * @param isExit Specifies whether this command will terminate the application.
     */
    public ListCommand(boolean isExit) {
        super(isExit);
    }

    /**
     * Executes the command to list all tasks.
     * It checks if the task list is empty, and either displays a message that there are no tasks
     * or lists all tasks in the task list.
     *
     * @param tasks   The task list to be displayed.
     * @param ui      The UI object that interacts with the user.
     * @param storage The storage object (unused in this command).
     * @return        A string containing the list of tasks or a message if the list is empty.
     * @throws HienException This command does not throw any exceptions.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws HienException {
        return this.listTasks(tasks, ui);
    }

    /**
     * Lists all tasks in the task list.
     *
     * @param tasks The task list to be displayed.
     * @param ui    The UI object used to interact with the user.
     * @return      A string containing the list of tasks or a message if the list is empty.
     * @throws HienException This command does not throw any exceptions.
     */
    private String listTasks(TaskList tasks, UI ui) throws HienException {
        if (tasks.isEmpty()) {
            return ui.showMessage(" There are no tasks in your list.");
        } else {
            String msg = ui.showMessage(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                String taskMessage = " " + (i + 1) + "." + tasks.getTask(i);
                msg += ui.showMessage(taskMessage);
            }
            return msg;
        }
    }
}
