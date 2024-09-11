package commands;

import skibidi.Command;
import skibidi.Ui;
import storage.Task;
import storage.TaskStorage;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Executes the command to list all tasks.
     *
     * @param ui      The user interface to interact with the user.
     * @param storage The task storage to store the task.
     * @return Output message to be displayed to the user.
     */
    @Override
    public String execute(Ui ui, TaskStorage storage) {
        assert ui != null : "Ui should not be null";
        assert storage != null : "TaskStorage should not be null";
        StringBuilder list = new StringBuilder();
        list.append("Here are the tasks in your list:\n");
        int index = 1;

        for (Task task : storage.getTasks()) {
            list.append(index).append(". ").append(task).append("\n");
            index++;
        }

        return ui.outputMessage(list.toString());
    }
}
