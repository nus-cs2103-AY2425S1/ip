package demurebot.command;

import demurebot.task.Task;
import demurebot.task.TaskList;
import demurebot.ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private final String index;

    /**
     * Constructs a MarkCommand object.
     *
     * @param index Index of task to mark.
     */
    public MarkCommand(String index) {
        this.index = index;
    }

    /**
     * Marks a task as done in the task list.
     *
     * @param list Task list to mark task in.
     * @param ui Ui to display the marked task.
     * @return a message indicating the task has been marked.
     */
    @Override
    public String execute(TaskList list, Ui ui) {
        try {
            int i = Integer.parseInt(index) - 1;
            Task task = list.getTask(i);
            task.markAsDone();
            return ui.displayMarkTask(task);
        } catch (NumberFormatException e) {
            return "Please enter a positive integer after mark.\n";
        } catch (IndexOutOfBoundsException e) {
            return ("Invalid index: "
                    + index + "\n"
                    + "Please enter a number within 1 to number of current tasks.\n"
            );
        }
    }
}
