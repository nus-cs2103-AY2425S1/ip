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
        if (rest.isEmpty() || rest.trim().isEmpty()) {
            throw new XBotException("Mmm...can you select a task number for me to delete? >_< \n"
                    + "(e.g. delete 1)");
        }
        int taskNumber = Integer.parseInt(rest.trim());
        String output = deleteTask(list, taskNumber);
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
                throw new XBotException("Ah mm... Im sorry but I dont think this task number exist :0");
            }
            Task deleteTask = list.get(taskNumber - 1);
            String output = showDeletedTask(deleteTask, list.size() - 1);
            list.remove(taskNumber - 1);
            return output;
        } catch (NumberFormatException e) {
            throw new XBotException("Mmm... I only understand numbers... >_< \n"
                    + "please check the task number you inputted is a number! "
                    + "(e.g. delete 1)");
        }
    }

    /**
     * Generates a success message indicating that a task has been deleted.
     * @param task the task that was deleted
     * @param endSize the new size of the task list after deletion
     * @return a string message indicating the deletion of the task and the updated list size
     */
    public String showDeletedTask(Task task, int endSize) {
        String output;
        output = ("Phew sounds like one less things to do!! \n"
                + "I have helped you remove this task: \n");
        output = output + (task.toString() + "\n");
        output = output + ("And now you have " + endSize + " tasks in the list!! Jiayouu :D");
        return output;
    }
}
