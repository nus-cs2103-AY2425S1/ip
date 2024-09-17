package chacha.command;

import chacha.ChaCha;
import chacha.Storage;
import chacha.Ui;
import chacha.task.TaskList;

/**
 * Represents the command to list all tasks.
 */
public class ListCommand extends Command {
    public ListCommand(ChaCha chacha) {
        super(chacha);
    }

    /**
     * Returns the string representation of the list of tasks.
     *
     * @param userInput User input
     * @param storage Storage of ChaCha
     * @param ui UI of ChaCha
     * @param tasks List of tasks
     * @return String representation.
     */
    @Override
    public String execute(String userInput, Storage storage, Ui ui, TaskList tasks) {
        return tasks.printList(ui);
    }
}
