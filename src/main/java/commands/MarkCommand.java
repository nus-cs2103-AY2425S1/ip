package commands;

import task.TaskList;

/**
 * Represents a command to mark a task as completed in the task list.
 * This class extends the {@link Command} class and handles the operation of marking a task as done.
 */
public class MarkCommand extends Command {
    private final int indexToMark;

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
    public String execute(TaskList taskList) {
        try {
            StringBuilder resultString = new StringBuilder();
            taskList.changeTaskStatus("mark", indexToMark);
            resultString.append("\"GOOD RIDDANCE! Finally, this task is done: \n")
                    .append(taskList.get(indexToMark));
            taskList.writeToStorage();
            return resultString.toString();
        } catch (IndexOutOfBoundsException e) {
            return "No valid index was given!!";
        }
    }
}
