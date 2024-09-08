package qwerty.command;

import java.util.HashMap;

import qwerty.Storage;
import qwerty.TaskList;
import qwerty.ui.Ui;

/**
 * This class encapsulates an unknown command.
 */
public class UnknownCommand extends Command {

    public UnknownCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    /**
     * Informs the user that the command they entered is unknown.
     * Displays a message asking them to try again.
     *
     * @param tasks The TaskList component handling storage of Task objects.
     * @param ui The Ui component handling user interaction.
     * @param storage The Storage component handling read/write to hard disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showQwertyMessage("\nNo idea what that means. Try again.");
    }
}
