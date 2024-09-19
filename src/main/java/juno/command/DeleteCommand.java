package juno.command;

import java.util.ArrayList;

import juno.manager.FileManager;
import juno.manager.TaskManager;
import juno.manager.exception.TaskManagerException;
import juno.task.Task;

/**
 * A class to execute the delete functionality for the tasks in Juno chat bot.
 * Subclass of Command class.
 */
public class DeleteCommand extends Command {
    private static final String SPLIT_TASK_DELIMITER = "\\s+";
    private static final String DELETE_TASK_STRING_PART_A = "Got it! 🗑️ I've waved goodbye to this task:\n";
    private static final String DELETE_TASK_STRING_PART_B = "\nYour list just got lighter! 🌟 Now you're down to "
            + "Now you're down to ";
    private static final String DELETE_TASK_STRING_PART_C = " tasks. Keep up the momentum!";
    private static final String TASK_OUT_OF_RANGE_ERROR = "\uD83D\uDEAB Oops! That task number is out of range. "
            + "(\uD83D\uDCA1 Tip: You can type \"list\" to see task numbers)";
    private static final String INVALID_TASK_NUMBER_ERROR = "\uD83D\uDE15 Hmm, something went wrong. "
            + "Please enter a task number after mark/unmark/delete command. "
            + "(\uD83D\uDCA1 Tip: You can type \"list\" to see task numbers)";
    private FileManager fileManager;
    private String userInput;
    private ArrayList<Task> tasks;

    /**
     * Constructs a DeleteCommand instance with the specified user input, TaskManager, and FileManager.
     * Initialises the task list from the TaskManager.
     *
     * @param userInput The input provided by the user which specify the task to delete.
     * @param taskManager  TaskManager to handle task specific operations.
     * @param fileManager  FileManager to handle file operations related to tasks.
     */
    public DeleteCommand(String userInput, TaskManager taskManager, FileManager fileManager) {
        this.userInput = userInput;
        this.fileManager = fileManager;
        this.tasks = taskManager.getTasksArray();
    }

    /**
     * Executes the command to delete a task based on the user input.
     * The user input should specify the task number to delete. (e.g. delete 2, which deletes the second task in
     * the task list.
     *
     * <p>Handles errors such as out-of-range task numbers and incorrect input format.
     * Writes the updated task list to the file task.json after deletion.</p>
     *
     * @throws TaskManagerException If an error occurs during task deletion, such as an invalid task number.
     */
    @Override
    public String runCommand() throws TaskManagerException {
        try {
            assert this.userInput != null : "User input in DeleteCommand() cannot be null";
            int taskNumber = Integer.parseInt(this.userInput.split(SPLIT_TASK_DELIMITER, 2)[1]) - 1;
            if (taskNumber >= 0 && taskNumber < this.tasks.size()) {
                Task taskToDelete = this.tasks.remove(taskNumber);
                StringBuilder outString = new StringBuilder(DELETE_TASK_STRING_PART_A);
                outString.append(taskToDelete.toString())
                         .append(DELETE_TASK_STRING_PART_B)
                         .append(this.tasks.size())
                         .append(DELETE_TASK_STRING_PART_C);
                return outString.toString();
            } else {
                throw new TaskManagerException(TASK_OUT_OF_RANGE_ERROR,
                        TaskManagerException.ErrorType.TASK_OUT_OF_RANGE);
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            throw new TaskManagerException(INVALID_TASK_NUMBER_ERROR,
                    TaskManagerException.ErrorType.INVALID_DELETE_TASK_NUMBER);
        } finally {
            this.fileManager.writeTasksToFile(this.tasks);
        }
    }
}
