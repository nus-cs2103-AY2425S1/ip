package sigma.command;

import sigma.Storage;
import sigma.TaskList;
import sigma.Ui;

/**
 * Represents the command to exit the program.
 */
public class ByeCommand extends Commands {

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
        System.exit(0);
    }
}
