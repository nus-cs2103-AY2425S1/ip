package xbot.command;

import xbot.TaskList;
import xbot.exception.XBotException;
import xbot.storage.Storage;
import xbot.task.Task;
import xbot.ui.Ui;

/**
 * Handles the "delete" command.
 */
public class DeleteCommand implements Command {
    @Override
    public String execute(TaskList list, Ui ui, Storage storage, String rest) throws XBotException {
        int taskNumber = Integer.parseInt(rest.trim());
        String output = deleteTask(list, taskNumber);
        if (rest.trim().isEmpty()) {
            throw new XBotException("The task number to be deleted cannot be empty!");
        }
        storage.saveTask(list);
        return output;
    }

    /**
     * Deletes the task at the specified position in the list.
     * The task number should be a 1-based index as given by the user.
     *
     * @param list The list of tasks
     * @param taskNumber The 1-based index of the task to be deleted.
     * @throws XBotException If the task number is invalid.
     */
    public String deleteTask(TaskList list, int taskNumber) throws XBotException {
        try {
            if (taskNumber < 0 || taskNumber > list.size()) {
                throw new XBotException("This task number do not exist.");
            }
            Task deleteTask = list.get(taskNumber - 1);
            String output = showDeletedTask(deleteTask, list.size() - 1);
            list.remove(taskNumber - 1);
            return output;
        } catch (NumberFormatException e) {
            throw new XBotException("Invalid task number!");
        }
    }

    public String showDeletedTask(Task task, int endSize) {
        String output;
        output = ("Noted. I've removed this task:\n");
        output = output + (task.toString() + "\n");
        output = output + ("Now you have " + endSize + " tasks in the list.");
        return output;
    }
}
