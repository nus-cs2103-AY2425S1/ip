package chacha.command;

import chacha.ChaCha;
import chacha.Storage;
import chacha.Ui;
import chacha.exception.ChaChaException;
import chacha.task.Task;
import chacha.task.TaskList;

/**
 * Represents the command to mark a Task.
 */
public class MarkCommand extends Command {
    private static final String MISSING_FIELD = "You are missing the index of task you want to mark. \n"
            + "Please type again!";

    public MarkCommand(ChaCha chacha) {
        super(chacha);
    }

    /**
     * Returns the string representation of response to marking a Task done.
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
            Task taskMarked = tasks.markDone(userInput, storage);

            return ui.printMark(taskMarked);
        } catch (ChaChaException e) {
            return MISSING_FIELD;
        } catch (IndexOutOfBoundsException e) {
            return "I can't find such a task number... You don't enough tasks!";
        } catch (NumberFormatException e) {
            return "The index does not seem to be a number... Please type again. ";
        }
    }
}
