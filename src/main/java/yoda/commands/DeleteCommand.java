package yoda.commands;

import yoda.TaskList;
import yoda.exceptions.YodaException;
import yoda.tasks.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private TaskList taskList;
    private String input;

    /**
     * Constructs a DeleteCommand with the specified task list and input.
     *
     * @param taskList Tasklist where the task will be deleted from.
     * @param input User input containing the index of the task to be deleted.
     */
    public DeleteCommand(TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
    }

    /**
     * Executes a delete command and deletes the task at the specified
     * index from the task list.
     *
     * @return
     * @throws YodaException if input formatting is invalid.
     */
    public String run() throws YodaException {
        if (!hasValidFormat(input)) {
            throw new YodaException("Delete... which one?");
        }
        String[] splitInput = input.split(" ", 2);
        int index = Integer.parseInt(splitInput[1]);
        Task currentTask = taskList.get(index - 1);
        taskList.delete(index - 1);
        String message = "Deleted this, I have." + "\n" + currentTask + "\n"
                + String.format("Now you have %d tasks in the list\n", taskList.getLength());
        return message;
    }

    /**
     * Checks if input format is valid.
     *
     * @return true if input formatting is valid.
     */

    public boolean hasValidFormat(String input) {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length == 2) {
            if (splitInput[1].matches("\\d+")) {
                return Integer.parseInt(splitInput[1]) <= taskList.getLength();
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
