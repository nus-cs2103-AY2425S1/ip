package chacha.command;

import java.util.ArrayList;

import chacha.ChaCha;
import chacha.ChaChaException;
import chacha.Storage;
import chacha.Ui;
import chacha.task.Task;
import chacha.task.TaskList;

/**
 * Represents the command to find a Task.
 */
public class FindCommand extends Command {

    public FindCommand(ChaCha chacha) {
        super(chacha);
    }

    /**
     * Returns the string representation of response to finding a Task.
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
            ArrayList<Task> results = tasks.find(userInput, ui);
            return ui.printList(results, "Here are the matching tasks in your list: \n");
        } catch (ChaChaException e) {
            return e.toString();
        }
    }
}
