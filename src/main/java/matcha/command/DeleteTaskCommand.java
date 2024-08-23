package matcha.command;

import matcha.exception.MatchaException;

import matcha.task.Task;

import matcha.storage.Storage;

import matcha.tasklist.TaskList;

import matcha.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteTaskCommand extends Command{
    private String[] inputWords;

    /**
     * Constructor for DeleteTaskCommand.
     *
     * @param inputWords Array of words from user input.
     */
    public DeleteTaskCommand(String[] inputWords) {
        this.inputWords = inputWords;
    }

    /**
     * Deletes the given task. Prints out the task details and saves
     * the updated task list to file.
     *
     * @param tasks Task list to delete task from.
     * @param ui Ui object to interact with user.
     * @param storage Storage object to save tasks to file.
     * @throws MatchaException If the task number is not provided or is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MatchaException {
        if (inputWords.length != 2) {
            throw new MatchaException("Please enter the task number of the task you want to delete.");
        }

        int taskNum = 0;
        try {
            taskNum = Integer.parseInt(inputWords[1]) - 1;
        } catch (NumberFormatException e) {
            throw new MatchaException("Please enter the task number of the task you want to delete.");
        }

        if (taskNum < 0 || taskNum >= tasks.getSize()) {
            throw new MatchaException("This task does not exist!");
        }

        System.out.println("Alright, I have removed this task for you:");
        Task taskToRemove = tasks.getTask(taskNum);
        tasks.deleteTask(taskNum);
        tasks.printTask(taskToRemove);
        storage.saveTasks(tasks.getTasks());
    }
}
