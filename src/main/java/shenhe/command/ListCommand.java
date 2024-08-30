package shenhe.command;

import shenhe.exception.InvalidListEnquiry;
import shenhe.TaskList;
import shenhe.Ui;
import shenhe.Storage;

/**
 * Represents a command to list all tasks in the task list.
 * <p>
 * The {@code ListCommand} class handles the command to display all tasks stored in the task list.
 * It checks if the user input is valid and, if so, retrieves and displays each task in the list.
 * </p>
 */
public class ListCommand extends Command {
    private String userInput;

    /**
     * Constructs a {@code ListCommand} object with the specified user input.
     * <p>
     * Initializes the command with the user input, which is expected to be "list" to retrieve all tasks.
     * </p>
     *
     * @param userInput The full user input to be processed, which should be "list" for listing tasks.
     */
    public ListCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the list command to display all tasks in the task list.
     * <p>
     * This method checks if the user input matches "list". If not, it throws an {@link InvalidListEnquiry}
     * exception. If the input is valid, it retrieves the tasks from the {@link TaskList} and displays them
     * through the {@link Ui} instance.
     * </p>
     *
     * @param tasks The current task list containing all tasks.
     * @param ui The user interface instance used for displaying messages to the user.
     * @param storage The storage instance used for saving tasks. This parameter is not used in this method.
     * @throws InvalidListEnquiry If the user input does not match "list".
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidListEnquiry {
        if (!userInput.trim().equals("list")) {
            throw new InvalidListEnquiry();
        } else {
            ui.showTasksMessage();
            int totalNumberOfTasks = tasks.getSize();
            for (int i = 0; i < totalNumberOfTasks; i++) {
                System.out.println((i + 1) + "." + tasks.getTask(i).toString());
            }
        }
    }

    /**
     * Indicates whether this command should exit the application.
     * <p>
     * This method returns {@code false} to signal that the application should continue running.
     * </p>
     *
     * @return {@code false}, indicating that the application should not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
