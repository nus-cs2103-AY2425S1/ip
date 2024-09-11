package juno.command;

import java.util.ArrayList;

import juno.manager.FileManager;
import juno.manager.TaskManager;
import juno.manager.exception.TaskManagerException;
import juno.task.Task;


/**
 * A class to mark or unmark a task as done or not done (with a X).
 * Updates the status of a specified task based on the user input.
 * Updates the file tasks.json() after the <code>runCommand()</code> is executed.
 */

public class MarkCommand extends Command {
    private TaskManager taskManager;
    private FileManager fileManager;
    private String userInput;
    private ArrayList<Task> tasks;
    private boolean isMarked;

    /**
     * Constructs a MarkCommand instance which takes in a specified user input, TaskManager instance , FileManager
     * instance, and whether it is a mark/unmark command (through the isMarked boolean parameter).
     *
     * @param userInput The input provided by the user to specify which task to mark or unmark.
     * @param taskManager The TaskManager instance to handle all task specific operations.
     * @param fileManager The FileManager instance to handle file operations related to tasks.
     * @param isMarked Boolean indicating whether to mark the task (true) or unmark the task (false).
     */
    public MarkCommand(String userInput, TaskManager taskManager, FileManager fileManager, boolean isMarked) {
        this.userInput = userInput;
        this.taskManager = taskManager;
        this.fileManager = fileManager;
        this.tasks = taskManager.getTasksArray();
        this.isMarked = isMarked;
    }

    /**
     * Executes the command to mark or unmark a task based on the user input.
     * The user input should specify the task number to be updated. (e.g. mark 1 marks the first task on list as done).
     * If the task is already marked and user wants to mark the task, it will tell user that it cannot be done.
     * Similarly, if the task is already unmarked and user wants to unmark the task.
     * Handles errors such as out-of-range task numbers and incorrect input format. (e.g. mark abc)
     * Writes the updated task list to the file using the fileManager instance after the operation.
     *
     * @throws TaskManagerException If an error occurs during mark or unmark, such as an invalid task number.
     */
    @Override
    public String runCommand() throws TaskManagerException {
        try {
            int taskNumber = Integer.parseInt(userInput.split("\\s+", 2)[1]) - 1;
            if (taskNumber >= 0 && taskNumber < this.tasks.size()) {
                Task taskToMark = this.tasks.get(taskNumber);
                StringBuilder outString;
                if (this.isMarked) {
                    outString = this.handleMarkTask(taskToMark);
                } else {
                    outString = this.handleUnmarkTask(taskToMark);
                }
                outString.append("\n").append("  ").append(this.tasks.get(taskNumber).toString());
                return outString.toString();
            } else {
                throw new TaskManagerException("\uD83D\uDEAB Oops! That task number is out of range. "
                        + "(\uD83D\uDCA1 Tip: You can type \"list\" to see task numbers)",
                        TaskManagerException.ErrorType.TASK_OUT_OF_RANGE);
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            throw new TaskManagerException("\uD83D\uDE15 Hmm, something went wrong. "
                    + "Please enter a task number after mark/unmark/delete command. "
                    + "(\uD83D\uDCA1 Tip: You can type \"list\" to see task numbers)",
                    TaskManagerException.ErrorType.INVALID_MARK_TASK_NUMBER);
        } finally {
            this.fileManager.writeTasksToFile(this.tasks);
        }
    }

    /**
     * Handles the marking of a task as done.
     * Checks if the task is already marked as done; if so, informs the user that the task has already been completed.
     * Otherwise, marks the task as done and provides a confirmation message.
     *
     * @param taskToMark The Task object to be marked as done.
     * @return A StringBuilder containing the response message indicating the result of the mark operation.
     */
    private StringBuilder handleMarkTask(Task taskToMark) {
        StringBuilder outString = new StringBuilder();
        if (taskToMark.getIsDone()) {
            outString.append("You have completed the task \"")
                    .append(taskToMark.getDescription()).append("\" already!");
        } else {
            taskToMark.markAsDone();
            outString.append("Great work! Knew you would have completed it.");
        }
        return outString;
    }

    /**
     * Handles the unmarking of a task as not done.
     * Checks if the task is currently marked as done; if so, unmarks the task and provides a confirmation message.
     * If the task is already not done, informs the user that the task cannot be unmarked.
     *
     * @param taskToMark The Task object to be unmarked as not done.
     * @return A StringBuilder containing the response message indicating the result of the unmark operation.
     */
    private StringBuilder handleUnmarkTask(Task taskToMark) {
        StringBuilder outString = new StringBuilder();
        if (taskToMark.getIsDone()) {
            taskToMark.markAsNotDone();
            outString.append("Hey, I have unmarked this task for you. ")
                    .append("Maybe you should start working on it soon?");
        } else {
            outString.append("Task \"").append(taskToMark.getDescription())
                    .append("\" is still not done! You can't unmark an undone task!");
        }
        return outString;
    }
}
