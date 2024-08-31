package bestie.command;

import bestie.Storage;
import bestie.TaskList;
import bestie.Ui;

/**
 * Creates a command that tells the user their input was invalid.
 */
public class InvalidCommand extends Command {
    /**
     * Prints message on the console to show that user has entered an invalid command.
     *
     * @param tasks User's list of tasks.
     * @param ui User Interface object that executes printing of message to console.
     * @param storage Loads list of tasks from file and writes tasks to the bestie.txt file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.invalidCommand();
    }
}
