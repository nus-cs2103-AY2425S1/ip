package yapbot.commands;

import yapbot.exceptions.YapBotException;
import yapbot.util.Storage;
import yapbot.util.TaskList;
import yapbot.util.Ui;

public abstract class Command {

    public abstract boolean execute(TaskList tasks, Ui ui, Storage storage) throws YapBotException;

    public abstract boolean isExit();
}
