package shenhe.command;

import shenhe.TaskList;
import shenhe.Ui;
import shenhe.Storage;
import shenhe.exception.EmptyTaskDescriptionException;
import shenhe.task.Event;

/**
 * Represents a command to add an event to the task list.
 * <p>
 * The {@code EventCommand} class is a concrete implementation of the {@code Command}
 * class that handles the addition of an event to the task list based on user input.
 * It parses the input, creates an {@code Event} object, and updates the task list and storage.
 * </p>
 */
public class EventCommand extends Command {
    private String userInput;

    /**
     * Constructs an {@code EventCommand} with the specified user input.
     *
     * @param userInput The input string provided by the user, which specifies the event to add.
     */
    public EventCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the command to add an event to the task list.
     * <p>
     * This method parses the user input to extract the event description and its start and end times.
     * It creates an {@code Event} object and adds it to the task list. The updated task list is then saved to storage.
     * </p>
     *
     * @param tasks The current list of tasks to which the event will be added.
     * @param ui The user interface used to display messages to the user.
     * @param storage The storage system to save the updated task list.
     * @throws EmptyTaskDescriptionException If the user input does not contain a valid event description.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskDescriptionException {
        if (userInput.trim().length() == 5) {
            throw new EmptyTaskDescriptionException();
        }

        // Split the input string into three parts using "/" character
        String[] parts = userInput.split("/", 3);

        // Extract the event description part (before the first "/")
        String task = parts[0].substring(5).trim();

        // Extract the start and end time parts (after the first "/")
        String from = parts[1].substring(4).trim();
        String to = parts[2].substring(2).trim();
        tasks.addTask(new Event(task, false, from, to));
        int totalNumberOfTasks = tasks.getSize();
        ui.showAddTaskMessage();
        System.out.println(tasks.getTask(totalNumberOfTasks - 1).toString());
        System.out.println("Now you have " + totalNumberOfTasks + " tasks in the list.");
        storage.saveTasks(tasks);
    }

    /**
     * Determines if the command should terminate the application.
     * <p>
     * This method returns {@code false} as the {@code EventCommand} does not cause the application
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
