package commands;

import task.TaskList;

/**
 * Represents a command to unmark a task as completed (i.e., mark it as not done) in the task list.
 * This class extends the {@link Command} class and handles the operation of marking a task as not done.
 */
public class UnmarkCommand extends Command {

    private int indexToUnmark;

    /**
     * Constructs an {@code UnmarkCommand} with the specified index of the task to unmark.
     *
     * @param indexToUnmark The index of the task to be marked as not done (0-based index).
     */
    public UnmarkCommand(int indexToUnmark) {
        this.indexToUnmark = indexToUnmark;
    }

    /**
     * Executes the unmark command, changing the status of the task at the specified index to "not done".
     * If the index is out of bounds, an error message is displayed.
     *
     * @param taskList The task list containing the task to be unmarked as not done.
     */
    @Override
    public String execute(TaskList taskList) {
        try {
            StringBuilder resultString = new StringBuilder();
            taskList.changeTaskStatus("unmark", indexToUnmark);
            resultString.append("Alright, this task is not done yet faster finish leh:\n")
                    .append(taskList.get(indexToUnmark));
            return resultString.toString();
        } catch (IndexOutOfBoundsException e) {
            return "No valid index was given!!";
        }
    }
}
