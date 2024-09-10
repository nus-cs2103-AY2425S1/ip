package commands;

import cook.Storage;
import cook.TaskList;
import cook.Ui;

/**
 * FindCommand class to process list commands.
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand class.
     */
    public ListCommand() {
        super("list");
    }

    /**
     * Prints tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.say(tasks.toString());
    }
}
