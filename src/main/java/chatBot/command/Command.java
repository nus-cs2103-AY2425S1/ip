package chatBot.command;

import chatBot.bot.Storage;
import chatBot.bot.TaskList;
import chatBot.bot.Ui;

public abstract class Command {
    Command() {}

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
