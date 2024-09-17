package yapbot.commands;

import yapbot.exceptions.YapBotException;
import yapbot.main.YapBot;
import yapbot.util.Storage;
import yapbot.util.TaskList;

public class LoadCommand extends Command {
    private String filepath;
    private YapBot yapBot;

    public LoadCommand(String filepath, YapBot yapBot) throws YapBotException {
        if (filepath.isEmpty()) {
            throw new YapBotException("Error, filepath must be specified.");
        }

        this.filepath = filepath;
        this.yapBot = yapBot;
    }

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
