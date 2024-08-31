package commands;

import task.TaskList;

/**
 * Represents a command to mark a task as completed in the task list.
 * This class extends the {@link Command} class and handles the operation of marking a task as done.
 */
public class MarkCommand extends Command {
    private int indexToMark;

    /**
     * Constructs a {@code MarkCommand} with the specified index of the task to mark as completed.
     *
     * @param indexToMark The index of the task to be marked as done (0-based index).
     */
    public MarkCommand(int indexToMark) {
        this.indexToMark = indexToMark;
    }

    /**
     * Executes the mark command, changing the status of the task at the specified index to "done".
     * If the index is out of bounds, an error message is displayed.
     *
     * @param taskList The task list containing the task to be marked as done.
     */
    @Override
    public void execute(TaskList taskList) {
        try {
            taskList.changeTaskStatus("mark", indexToMark);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No valid index was given!!");
        }
    }
}
