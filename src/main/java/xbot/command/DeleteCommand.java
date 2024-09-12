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
        String output = deleteTask(list, rest);
        if (rest.isEmpty()) {
            throw new XBotException("The task number to be deleted cannot be empty!");
        }
        storage.saveTask(list);
        return output;
    }

    /**
     * Deletes the task at the specified position in the list.
     * The task number should be a 1-based index as given by the user.
     *
     * @param rest The 1-based index of the task to be deleted.
     * @throws XBotException If the task number is invalid.
     */
    public String deleteTask(TaskList list, String rest) throws XBotException {
        try {
            int taskNumber = Integer.parseInt(rest.trim());
            if (taskNumber < 0 && taskNumber > list.size()) {
                throw new XBotException("This task number do not exist.");
            }
            String output;
            output = ("Noted. I've removed this task:\n");
            output = output + (list.get(taskNumber - 1).toString() + "\n");
            list.remove(taskNumber - 1);
            output = output + ("Now you have " + list.size() + " tasks in the list.");
            return output;
        } catch (NumberFormatException e) {
            throw new XBotException("Invalid task number!");
        }
    }
}
