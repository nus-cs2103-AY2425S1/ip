package potong.command;

import potong.Storage;
import potong.TaskList;
import potong.Ui;

import java.io.IOException;

/**
 * Represent the command for exiting the program.
 */
public class ExitCommand extends Command {

    /**
     * Initialise the exit command.
     * @param command Empty String.
     */
    public ExitCommand(String command) {
        super(command);
    }

    /**
     * Saves the list of tasks in storage.
     *
     * @param tasks List of tasks at hand.
     * @param storage Storage class for loading and saving.
     * @param ui Ui class for printing output.
     * @return Empty String.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.writeToStorage(storage);
        ui.sayGoodbye();
        return "";
    }

}
