package chatBot.command;

import chatBot.bot.Storage;
import chatBot.bot.TaskList;
import chatBot.bot.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {}

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.writeToFile(taskList.getTaskCommands());
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
