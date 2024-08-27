package delta.command;

import delta.util.TaskList;
import delta.util.Ui;
import delta.util.Storage;

/**
 * Concrete subclass of Command abstract class.
 * Exits Delta ChatBot.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super(CommandType.Exit);
    }
    /**
     * Returns that ExitCommand is the exit command.
     */
    public boolean isExit() {
        return true;
    }

    /**
     * Exits Delta ChatBot.
     *
     * @param tasks List containing all current tasks.
     * @param ui User Interfacing object to print exit message.
     * @param storage Storage object to save list.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showCommand("Bye. Hope to see you again soon!");
    }
}
