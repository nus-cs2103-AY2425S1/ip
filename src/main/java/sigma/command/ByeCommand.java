package sigma.command;

import sigma.exception.SigmaException;
import sigma.utils.Storage;
import sigma.utils.TaskList;
import sigma.utils.Ui;

/**
 * Represents the command to exit the program.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a ByeCommand object.
     *
     * @param commandArray
     */
    public ByeCommand(String[] commandArray) {
        super(commandArray);
    }

    /**
     * Exits the program.
     * Writes the tasks to the file.
     * Assertions to ensure that the TaskList and Storage objects are not null.
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SigmaException {
        storage.write(tasks.getTasks());
        System.out.println("Tasks logged.");
        return Ui.exit();
    }

    /**
     * Returns true to indicate that the program should exit.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
