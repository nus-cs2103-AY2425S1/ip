package yapbot.commands;

import yapbot.exceptions.YapBotException;
import yapbot.main.YapBot;
import yapbot.util.Storage;
import yapbot.util.TaskList;

/**
 * Command for loading tasks from another file.
 */
public class LoadCommand extends Command {
    private String filepath;
    private YapBot yapBot;

    /**
     * Creates a LoadCommand.
     *
     * @param filepath The path of the file to load tasks from
     * @param yapBot Reference to YapBot instance to load tasks into
     * @throws YapBotException If filepath is empty.
     */
    public LoadCommand(String filepath, YapBot yapBot) throws YapBotException {
        if (filepath.isEmpty()) {
            throw new YapBotException("Error, filepath must be specified.");
        }

        this.filepath = filepath;
        this.yapBot = yapBot;
    }

    /**
     * {@inheritDoc}
     * Loads tasks from another file.
     * If the process fails, the original file and tasks remain
     * (ie reverts to the state before the execution of this command).
     *
     * @throws YapBotException If tasks cannot be loaded from the file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws YapBotException {
        String successMessage = "Accessing data at <" + filepath + ">...";

        if (yapBot.changeLoadFile(filepath)) {
            return successMessage + "Success\nUse Command \"list\" to view your tasks.";
        } else {
            throw new YapBotException(successMessage + "Failure\nUnable to load tasks.");
        }

    }
}
