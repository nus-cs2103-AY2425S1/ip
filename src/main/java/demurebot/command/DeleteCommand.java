package demurebot.command;

import demurebot.task.Task;
import demurebot.task.TaskList;
import demurebot.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final String index;

    /**
     * Constructor for DeleteCommand.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(String index) {
        this.index = index;
    }

    /**
     * Deletes a task from the task list.
     *
     * @param list The task list.
     * @param ui The user interface to display the result of the command.
     * @return The result of the command.
     */
    @Override
    public String execute(TaskList list, Ui ui) {
        try {
            int i = Integer.parseInt(index) - 1;
            Task task = list.getTask(i);
            list.removeTask(i);
            return ui.displayDeleteTask(task, list.getSize());
        } catch (NumberFormatException e) {
            return "Please enter a positive integer after delete.\n";
        } catch (IndexOutOfBoundsException e) {
            return ("Invalid index: "
                    + index
                    + "\n"
                    + "Please enter a number within 1 to number of current tasks.\n"
            );
        }
    }
}
