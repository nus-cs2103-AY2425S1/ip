package shenhe.command;

import java.time.LocalDateTime;
import shenhe.TaskList;
import shenhe.Ui;
import shenhe.Storage;
import shenhe.exception.EmptyTaskDescriptionException;
import shenhe.parser.DateParser;
import shenhe.task.Deadline;

/**
 * Represents a command to add a deadline task to the task list.
 * <p>
 * The {@code DeadlineCommand} class is a concrete implementation of the {@code Command}
 * class that handles the creation and addition of deadline tasks. It parses user input to
 * extract task descriptions and deadline information, and then updates the task list and storage.
 * </p>
 */
public class DeadlineCommand extends Command {
    private String userInput;

    /**
     * Constructs a {@code DeadlineCommand} with the specified user input.
     *
     * @param userInput The input string provided by the user, which includes the task description
     *                  and deadline information.
     */
    public DeadlineCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the command to add a deadline task to the task list.
     * <p>
     * This method parses the user input to extract the task description and deadline. It then creates
     * a {@code Deadline} object and adds it to the task list. The updated task list is then saved to
     * storage.
     * </p>
     *
     * @param tasks The current list of tasks to which the new deadline task will be added.
     * @param ui The user interface used to display messages to the user.
     * @param storage The storage system to save the updated task list.
     * @throws EmptyTaskDescriptionException If the user input does not contain a valid task description.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskDescriptionException {
        if (userInput.trim().length() == 8) {
            throw new EmptyTaskDescriptionException();
        }

        // Split the input string into two parts using the first "/" character
        String[] parts = userInput.split("/", 2);

        // Extract the task description part (before the first "/")
        String task = parts[0].substring(8).trim();

        // Extract the deadline part (after the first "/")
        String by = parts[1].substring(2).trim();
        LocalDateTime dateTime = DateParser.parse(by);
        tasks.addTask(new Deadline(task, false, dateTime));
        int totalNumberOfTasks = tasks.getSize();
        ui.showAddTaskMessage();
        System.out.println(tasks.getTask(totalNumberOfTasks - 1).toString());
        System.out.println("Now you have " + totalNumberOfTasks + " tasks in the list.");
        storage.saveTasks(tasks);
    }

    /**
     * Determines if the command should terminate the application.
     * <p>
     * This method returns {@code false} as the {@code DeadlineCommand} does not cause the application
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
