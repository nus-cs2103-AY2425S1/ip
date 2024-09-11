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
    private boolean markAsDone;

    /**
     * Constructs a MarkCommand instance which takes in a specified user input, TaskManager instance , FileManager
     * instance, and whether it is a mark/unmark command (through the markAsDone boolean parameter).
     *
     * @param userInput The input provided by the user to specify which task to mark or unmark.
     * @param taskManager The TaskManager instance to handle all task specific operations.
     * @param fileManager The FileManager instance to handle file operations related to tasks.
     * @param markAsDone Boolean indicating whether to mark the task (true) or unmark the task (false).
     */
    public MarkCommand(String userInput, TaskManager taskManager, FileManager fileManager, boolean markAsDone) {
        this.userInput = userInput;
        this.taskManager = taskManager;
        this.fileManager = fileManager;
        this.tasks = taskManager.getTasksArray();
        this.markAsDone = markAsDone;
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
            assert this.userInput != null : "User input in MarkCommand() cannot be null!";
            int taskNumber = Integer.parseInt(this.userInput.split("\\s+", 2)[1]) - 1;
            if (taskNumber >= 0 && taskNumber < this.tasks.size()) {
                Task taskToMark = this.tasks.get(taskNumber);
                StringBuilder outString = new StringBuilder();
                if (this.markAsDone) {
                    if (taskToMark.getIsDone()) {
                        outString.append("You have completed the task \"")
                                 .append(taskToMark.getDescription()).append("\" already!");
                    } else {
                        taskToMark.markAsDone();
                        outString.append("Great work! Knew you would have completed it.");
                    }
                } else {
                    if (!taskToMark.getIsDone()) {
                        outString.append("Task \"").append(taskToMark.getDescription())
                                 .append("\" is still not done! You can't unmark an undone task!");
                    } else {
                        this.tasks.get(taskNumber).markAsNotDone();
                        outString.append("Hey, I have unmarked this task for you. ")
                                 .append("Maybe you should start working on it soon?");
                    }
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
}
