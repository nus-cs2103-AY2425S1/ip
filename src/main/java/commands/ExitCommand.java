package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * The caller must ensure to exit the program when they encounter this command using isExit().
 */
public class ExitCommand extends Command {

    public ExitCommand() {}

    /**
     * Execution actions:
     * - Says goodbye :)
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        System.exit(0);
        return "";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
