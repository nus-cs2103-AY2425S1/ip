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
     * @param userInput
     * @param storage
     * @param ui
     * @param tasks
     * @return String representation.
     */
    @Override
    public String execute(String userInput, Storage storage, Ui ui, TaskList tasks) {
        this.chacha.updateIsEnd();
        return ui.printExit();
    }
}
