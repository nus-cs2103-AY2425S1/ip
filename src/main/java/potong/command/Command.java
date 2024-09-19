package potong.command;

import potong.Storage;
import potong.TaskList;
import potong.Ui;

/**
 * Abstract class to write methods that commands need to have.
 */
public abstract class Command {

    private String command;

    /**
     * Initialise the parent command class.
     *
     * @param command The general description of any command.
     */
    public Command(String command) {
        this.command = command;
    }

    /**
     * Carry out the command.
     *
     * @param tasks List of tasks at hand.
     * @param storage Storage class for loading and saving.
     * @param ui Ui class for printing output.
     * @return String representation of the command.
     */
    public abstract String execute(TaskList tasks, Storage storage, Ui ui);

}
