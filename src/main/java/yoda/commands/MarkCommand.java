package yoda.commands;

import yoda.TaskList;
import yoda.exceptions.YodaException;
import yoda.tasks.Task;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private TaskList taskList;
    private String input;

    /**
     * Constructs a MarkCommand with the specified task list and input
     *
     * @param taskList Task list from which the specified task will be
     *                 marked done.
     * @param input User input containing the index of the task to be marked.
     */
    public MarkCommand(TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
    }

    /**
     * Executes the command to mark a task as done.
     *
     * @return
     * @throws YodaException if input format is valid or task is out of bounds
     */
    public String run() throws YodaException {
        if (!hasValidFormat()) {
            throw new YodaException("Mark... which one?");
        }
        String[] splitInput = input.split(" ", 2);
        int index = Integer.parseInt(splitInput[1]);
        Task currentTask = taskList.get(index - 1);
        currentTask.markDone();
        String message = "Good job! Marked this as done, I have" + String.format("%s\n", taskList.get(index - 1));
        return message;
    }

    /**
     * Checks if input format is valid and index is in bounds of task list.
     *
     * @return true if input format is valid and index is in bounds of task list.
     */
    public boolean hasValidFormat() {
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
