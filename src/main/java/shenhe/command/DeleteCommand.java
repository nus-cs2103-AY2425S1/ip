package shenhe.command;

import shenhe.TaskList;
import shenhe.Ui;
import shenhe.Storage;
import shenhe.exception.EmptyTaskDescriptionException;

/**
 * Represents a command to delete a task from the task list.
 * <p>
 * The {@code DeleteCommand} class is a concrete implementation of the {@code Command}
 * class that handles the deletion of a specified task from the task list based on user input.
 * It updates the task list and saves the changes to storage.
 * </p>
 */
public class DeleteCommand extends Command {
    private String userInput;

    /**
     * Constructs a {@code DeleteCommand} with the specified user input.
     *
     * @param userInput The input string provided by the user, which specifies the task to delete.
     */
    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the command to delete a task from the task list.
     * <p>
     * This method parses the user input to extract the task number and deletes the corresponding
     * task from the task list. The updated task list is then saved to storage.
     * </p>
     *
     * @param tasks The current list of tasks from which the task will be deleted.
     * @param ui The user interface used to display messages to the user.
     * @param storage The storage system to save the updated task list.
     * @throws EmptyTaskDescriptionException If the user input does not contain a valid task number.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskDescriptionException {
        if (userInput.trim().length() == 6) {
            throw new EmptyTaskDescriptionException();
        } else {
            int taskNumber = Integer.parseInt(userInput.substring(6).trim());
            int totalNumberOfTasks = tasks.getSize();
            System.out.println("taskNumber: " + taskNumber);
            System.out.println("total number of tasks: " + tasks.getSize());
            if (taskNumber >= 1 && taskNumber <= tasks.getSize()) {
                ui.showDeleteMessage();
                System.out.println(tasks.getTask(taskNumber - 1).toString());
                tasks.deleteTask(taskNumber - 1);
                totalNumberOfTasks--;
                System.out.println("Now you have " + totalNumberOfTasks + " tasks in the list.");
                storage.saveTasks(tasks);
            }
        }
    }

    /**
     * Determines if the command should terminate the application.
     * <p>
     * This method returns {@code false} as the {@code DeleteCommand} does not cause the application
     * to exit.
     * </p>
     *
     * @return {@code false} as the command does not terminate the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
