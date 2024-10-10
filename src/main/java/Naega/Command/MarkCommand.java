package Naega.Command;

import Naega.NaegaException;
import Naega.Storage.Storage;
import Naega.Task.TaskList;
import Naega.Ui.Ui;

/**
 * Represents a command to mark a specific task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Creates a MarkCommand with the specified user input to determine the task index.
     *
     * @param userInput the input containing the task number to mark as done
     * @throws NaegaException if the task number format is invalid
     */
    public MarkCommand(String userInput) throws NaegaException {
        this.index = parseTaskIndex(userInput);
    }

    /**
     * Executes the command by marking the specified task as done, displaying the updated task using the UI,
     * and saving the updated task list to storage.
     *
     * @param tasks   the task list to update
     * @param ui      the UI component to display the updated task
     * @param storage the storage component to save the updated task list
     * @return a string message that confirms the task has been marked as done and displays the task details
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.getTask(index).markAsDone();
        storage.save(tasks.getTasks());

        return ui.showLine() + "\nNice! I've marked this task as done:\n"
                + "   " + tasks.getTask(index) + "\n"
                + ui.showLine();
    }

    /**
     * Parses the task index from the user input.
     *
     * @param userInput the input containing the task number
     * @return the zero-based index of the task
     * @throws NaegaException if the task number format is invalid
     */
    private int parseTaskIndex(String userInput) throws NaegaException {
        try {
            return Integer.parseInt(userInput.substring(5).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new NaegaException("Invalid task number format.");
        }
    }
}