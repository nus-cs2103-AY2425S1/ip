package demurebot.command;

import demurebot.task.Task;
import demurebot.task.TaskList;
import demurebot.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final String remainder;

    /**
     * Constructor for DeleteCommand.
     *
     * @param remainder The index of the task to be deleted.
     */
    public DeleteCommand(String remainder) {
        super(false);
        this.remainder = remainder;
    }

    /**
     * Deletes a task from the task list.
     *
     * @param list The task list.
     * @param ui The user interface to display the result of the command.
     */
    @Override
    public void execute(TaskList list, Ui ui) {
        try {
            int index = Integer.parseInt(remainder) - 1;
            Task task = list.getTask(index);
            list.removeTask(index);
            ui.displayDeleteTask(task, list.getSize());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a positive integer after delete.\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index: " +
                (Integer.parseInt(remainder)) +
                "\n" +
                "Please enter a number within 1 to number of current tasks.\n"
            );
        }
    }
}
