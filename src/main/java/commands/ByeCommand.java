package commands;

import cook.Storage;
import cook.TaskList;
import cook.Ui;

/**
 * ByeCommand class to process bye commands.
 */
public class ByeCommand extends Command {

    /**
     * Constructor for ByeCommand class.
     */
    public ByeCommand() {
        super("bye");
    }

    /**
     * Returns bye to exit program.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Bye.";
    }
}
