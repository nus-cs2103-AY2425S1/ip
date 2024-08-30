package shenhe.command;

import shenhe.exception.EmptyTaskDescriptionException;
import shenhe.task.Todo;
import shenhe.TaskList;
import shenhe.Ui;
import shenhe.Storage;

/**
 * Represents a command to add a new {@link Todo} task to the task list.
 * <p>
 * The {@code TodoCommand} class handles the command to create a new to-do task with a description provided by
 * the user. It validates the input, adds the new task to the list, and provides feedback through the user interface.
 * </p>
 */
public class TodoCommand extends Command {
    private String userInput;

    /**
     * Constructs a {@code TodoCommand} object with the specified user input.
     * <p>
     * Initializes the command with the user input, which specifies the description of the to-do task to be added.
     * </p>
     *
     * @param userInput The full user input to be processed, which should include the task description.
     */
    public TodoCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the command to add a new to-do task to the task list.
     * <p>
     * This method extracts the task description from the user input, creates a new {@link Todo} task with it, and
     * adds it to the task list. It throws an {@link EmptyTaskDescriptionException} if the input does not contain a
     * valid task description. After adding the task, it updates the task list and provides feedback through the
     * {@link Ui} instance. The updated tasks are saved using the {@link Storage} instance.
     * </p>
     *
     * @param tasks The current task list to which the new to-do task will be added.
     * @param ui The user interface instance used for displaying messages to the user.
     * @param storage The storage instance used for saving tasks after updating.
     * @throws EmptyTaskDescriptionException If the user input does not contain a task description.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskDescriptionException {
        if (userInput.trim().length() == 4) {
            throw new EmptyTaskDescriptionException();
        }

        String task = userInput.substring(5);
        tasks.addTask(new Todo(task, false));
        int totalNumberOfTasks = tasks.getSize();
        ui.showAddTaskMessage();
        System.out.println(tasks.getTask(totalNumberOfTasks - 1).toString());
        System.out.println("Now you have " + totalNumberOfTasks + " tasks in the list.");
        storage.saveTasks(tasks);
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
