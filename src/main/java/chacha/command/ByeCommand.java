package chacha.command;

import chacha.ChaCha;
import chacha.Storage;
import chacha.Ui;
import chacha.task.TaskList;

/**
 * Represents the command to exit the application.
 */
public class ByeCommand extends Command {
    public ByeCommand(ChaCha chacha) {
        super(chacha);
    }

    /**
     * Returns the string representation of bye response.
     *
     * @param userInput User input.
     * @param storage Storage of ChaCha.
     * @param ui UI of ChaCha.
     * @param tasks List of tasks.
     * @return String representation.
     */
    @Override
    public String execute(String userInput, Storage storage, Ui ui, TaskList tasks) {
        this.chacha.updateIsEnd();
        return ui.printExit();
    }
}
