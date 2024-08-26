package juno.command;

import juno.manager.FileManager;
import juno.manager.TaskManager;
import juno.manager.exception.TaskManagerException;
import juno.task.Task;

import java.util.ArrayList;

public class MarkCommand extends Command {
    TaskManager taskManager;
    FileManager fileManager;
    String userInput;
    ArrayList<Task> tasks;
    boolean markAsDone;
    public MarkCommand(String userInput, TaskManager taskManager, FileManager fileManager, boolean markAsDone) {
        this.userInput = userInput;
        this.taskManager = taskManager;
        this.fileManager = fileManager;
        this.tasks = taskManager.getTasksArray();
        this.markAsDone = markAsDone;
    }

    @Override
    public void runCommand() throws TaskManagerException {
        try {
            int taskNumber = Integer.parseInt(userInput.split("\\s+", 2)[1]) - 1;
            if (taskNumber >= 0 && taskNumber < this.tasks.size()) {

                Task taskToMark = this.tasks.get(taskNumber);
                if (this.markAsDone) {
                    if (taskToMark.getIsDone()) {
                        System.out.println("You have completed the task \"" +
                                taskToMark.getDescription() +
                                "\" already!");
                    } else {
                        taskToMark.markAsDone();
                        System.out.println("Great work! Knew you would have completed it.");
                    }
                } else {
                    if (!taskToMark.getIsDone()) {
                        System.out.println("Task \"" +
                                taskToMark.getDescription() +
                                "\" is still not done! You can't unmark an undone task!");
                    } else {
                        this.tasks.get(taskNumber).markAsNotDone();
                        System.out.println("Hey, I have unmarked this task for you. " +
                                "Maybe you should start working on it soon?");
                    }
                }
                System.out.println("  " + this.tasks.get(taskNumber).toString());
            } else {
                throw new TaskManagerException("\uD83D\uDEAB Oops! That task number is out of range. " +
                        "(\uD83D\uDCA1 Tip: You can type \"list\" to see task numbers)",
                        TaskManagerException.ErrorType.TASK_OUT_OF_RANGE);
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            throw new TaskManagerException("\uD83D\uDE15 Hmm, something went wrong. " +
                    "Please enter a task number after mark/unmark/delete command. " +
                    "(\uD83D\uDCA1 Tip: You can type \"list\" to see task numbers)",
                    TaskManagerException.ErrorType.INVALID_MARK_TASK_NUMBER);
        } finally {
            this.fileManager.writeTasksToFile(this.tasks);
        }
    }
}
