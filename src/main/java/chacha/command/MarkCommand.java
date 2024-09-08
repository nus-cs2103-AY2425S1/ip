package chacha.command;

import chacha.ChaCha;
import chacha.ChaChaException;
import chacha.Storage;
import chacha.Ui;
import chacha.task.Task;
import chacha.task.TaskList;

/**
 * Represents the command to mark a Task.
 */
public class MarkCommand extends Command {

    public MarkCommand(ChaCha chacha) {
        super(chacha);
    }

    /**
     * Returns the string representation of response to marking a Task done.
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
            Task taskMarked = tasks.markDone(userInput, ui, storage);

            return ui.printMark(taskMarked);
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
