package chacha.command;

import chacha.ChaCha;
import chacha.Storage;
import chacha.Ui;
import chacha.exception.ChaChaException;
import chacha.task.Task;
import chacha.task.TaskList;

/**
 * Represents the command to unmark a Task.
 */
public class UnmarkCommand extends Command {
    private static final String MISSING_FIELD = "You are missing the index of task you want to unmark. \n"
            + "Please type again!";

    public UnmarkCommand(ChaCha chacha) {
        super(chacha);
    }

    /**
     * Returns the string representation of response to mark a Task undone.
     *
     * @param userInput User input
     * @param storage Storage of ChaCha
     * @param ui UI of ChaCha
     * @param tasks List of tasks
     * @return String representation.
     */
    @Override
    public String execute(String userInput, Storage storage, Ui ui, TaskList tasks) {
        try {
            Task taskUnmarked = tasks.markUndone(userInput, storage);
            return ui.printUnmark(taskUnmarked);
        } catch (ChaChaException e) {
            return MISSING_FIELD;
        } catch (IndexOutOfBoundsException e) {
            return "I can't find such a task number... You don't enough tasks!";
        } catch (NumberFormatException e) {
            return "The index does not seem to be a number... Please type again. ";
        }
    }
}
