package shrimp.command;

import shrimp.task.Task;
import shrimp.task.TaskList;
import shrimp.utility.Ui;

/**
 * Represents a command to mark or unmark a task in the task list.
 */
public class MarkCommand implements Command {
    /**
     * The index of the task list to be marked.
     */
    private final int index;
    /**
     * The boolean value to signify to mark or unmark the task.
     */
    private final Boolean hasMark;

    /**
     * Constructs a {@code MarkCommand} with the specified task index and mark status.
     *
     * @param index   The index of the task to be marked or unmarked.
     * @param hasMark {@code true} if the task is to be marked as done, {@code false} otherwise.
     */
    public MarkCommand(int index, Boolean hasMark) {
        assert index >= 0 : "index is less than 0";
        assert hasMark != null : "the program doesn't know to mark or unmark";
        this.index = index;
        this.hasMark = hasMark;
    }

    /**
     * Executes the mark command by marking or unmarking the task in the task list
     * and printing the result to the user interface.
     *
     * @param taskList The list of tasks containing the task to be marked or unmarked.
     * @param ui       The user interface to print the result of the command.
     */
    @Override
    public String run(TaskList taskList, Ui ui) {
        String output;
        Task task;
        if (hasMark) {
            task = taskList.getTask(index).markAsDone();
            output = ui.printMark(task);
        } else {
            task = taskList.getTask(index).markAsNotDone();
            output = ui.printUnmark(task);
        }
        taskList.replaceTask(index, task);
        return output;
    }
}
