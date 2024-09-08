package qwerty.command;

import java.util.HashMap;

import qwerty.Storage;
import qwerty.TaskList;
import qwerty.ui.Ui;

/**
 * This class encapsulates an empty command, i.e. when the user does not enter anything.
 */
public class EmptyCommand extends Command {

    public EmptyCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    /**
     * Informs the user that they have entered an empty command, and do nothing.
     *
     * @param tasks The TaskList component handling storage of Task objects.
     * @param ui The Ui component handling user interaction.
     * @param storage The Storage component handling read/write to hard disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showQwertyMessage("""
                That's a blank. I can't help you if you don't say anything.""");
    }

}
