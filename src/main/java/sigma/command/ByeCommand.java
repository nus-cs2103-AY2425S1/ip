package sigma.command;

import sigma.exception.SigmaException;
import sigma.utils.Storage;
import sigma.utils.TaskList;
import sigma.utils.Ui;

import static java.lang.Thread.sleep;

/**
 * Represents the command to exit the program.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a ByeCommand object.
     * @param split
     */
    public ByeCommand(String[] split) {
        super(split);
    }

    /**
     * Exits the program.
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SigmaException {
        storage.write(tasks.getTasks());
        System.out.println("Tasks logged.");
        return ui.exit();
    }

    /**
     * Returns true to indicate that the program should exit.
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
