package delta.command;

import delta.util.Storage;
import delta.util.TaskList;
import delta.util.Ui;

/**
 * Concrete subclass of Command abstract class.
 * Exits Delta ChatBot.
 */
public class ExitCommand extends Command {
    /**
     * Creates a ExitCommand instance.
     */
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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String message = "Bye. Hope to see you again soon!";
        ui.showCommand(message);
        return message;
    }
}
