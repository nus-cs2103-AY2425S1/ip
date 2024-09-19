package chacha.command;

import java.util.ArrayList;

import chacha.ChaCha;
import chacha.Storage;
import chacha.Ui;
import chacha.exception.ChaChaException;
import chacha.task.Task;
import chacha.task.TaskList;

/**
 * Represents the command to find a Task.
 */
public class FindCommand extends Command {
    private static final String MISSING_TEXT = "You are missing the text you want to find.\n"
            + "Please type again!";

    public FindCommand(ChaCha chacha) {
        super(chacha);
    }
    /**
     * Returns the string representation of response to finding a Task.
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
            ArrayList<Task> results = tasks.find(userInput);

            if (results.isEmpty()) {
                return "There are no matching tasks in your list.";
            }

            return ui.printList(results, "Here are the matching tasks in your list: \n");
        } catch (ChaChaException e) {
            return MISSING_TEXT;
        }
    }
}
