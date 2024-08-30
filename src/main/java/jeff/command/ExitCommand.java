package jeff.command;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.TaskList;
import jeff.ui.Ui;

/**
 * Represents an "Exit the program" command.
 */
public class ExitCommand extends Command {
    /**
     * Constructor for ExitCommand Class.
     * Stores the user's input.
     *
     * @param input User's input.
     */
    public ExitCommand(String input) {
        super(input);
    }

    /**
     * Prints out a statement to tell the user the program is exiting.
     *
     * @param tasks Task list.
     * @param ui UI to print statements.
     * @param storage Place to get and write the task list to the tasks text file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printFarewell();
    }

    /**
     * Returns true.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
