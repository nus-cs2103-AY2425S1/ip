package juno.command;

import juno.manager.FileManager;
import juno.manager.TaskManager;
import juno.manager.exception.TaskManagerException;
import juno.task.Task;

import java.util.ArrayList;

/**
 * A class to execute the delete functionality for the tasks in Juno chat bot.
 * Subclass of Command class.
 */
public class DeleteCommand extends Command {
    TaskManager taskManager;
    FileManager fileManager;
    String userInput;
    ArrayList<Task> tasks;

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
        this.taskManager = taskManager;
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
     * @throws TaskManagerException If an error occurs during task deletion, such as an invalid task number or
     * formatting issue.
     */
    @Override
    public void runCommand() throws TaskManagerException {

        try {
            int taskNumber = Integer.parseInt(userInput.split("\\s+", 2)[1]) - 1;
            if (taskNumber >= 0 && taskNumber < this.tasks.size()) {
                Task taskToDelete = this.tasks.remove(taskNumber);
                System.out.println("Got it! 🗑️ I've waved goodbye to this task:");
                System.out.println(taskToDelete.toString());
                System.out.println("Your list just got lighter! 🌟 " +
                        "Now you're down to " + this.tasks.size() + " tasks. Keep up the momentum!");
            } else {
                throw new TaskManagerException("\uD83D\uDEAB Oops! That task number is out of range. " +
                        "(\uD83D\uDCA1 Tip: You can type \"list\" to see task numbers)",
                        TaskManagerException.ErrorType.TASK_OUT_OF_RANGE);
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            throw new TaskManagerException("\uD83D\uDE15 Hmm, something went wrong. " +
                    "Please enter a task number after mark/unmark/delete command. " +
                    "(\uD83D\uDCA1 Tip: You can type \"list\" to see task numbers)",
                    TaskManagerException.ErrorType.INVALID_DELETE_TASK_NUMBER);
        } finally {
            this.fileManager.writeTasksToFile(this.tasks);
        }
    }

}
