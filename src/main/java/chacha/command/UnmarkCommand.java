package chacha.command;

import chacha.ChaCha;
import chacha.ChaChaException;
import chacha.Storage;
import chacha.Ui;
import chacha.task.Task;
import chacha.task.TaskList;

/**
 * Represents the command to unmark a Task.
 */
public class UnmarkCommand extends Command {

    public UnmarkCommand(ChaCha chacha) {
        super(chacha);
    }

    /**
     * Returns the string representation of response to mark a Task undone.
     *
     * @param userInput
     * @param storage
     * @param ui
     * @param tasks
     * @return String representation.
     */
    @Override
    public String execute(String userInput, Storage storage, Ui ui, TaskList tasks) {
        try {
            Task taskUnmarked = tasks.markUndone(userInput, ui, storage);

            return ui.printUnmark(taskUnmarked);
        } catch (ChaChaException e) {
            return e.toString();
        } catch (IndexOutOfBoundsException e) {
            String[] arr = {"I can't find such a task number... You don't enough tasks!"};
            return ui.printStrings(arr);
        } catch (NumberFormatException e) {
            String[] arr = {"The index does not seem to be a number... Please type again. "};
            return ui.printStrings(arr);
        }
    }
}
