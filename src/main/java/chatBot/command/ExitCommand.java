package chatBot.command;

import chatBot.bot.Storage;
import chatBot.bot.TaskList;
import chatBot.bot.Ui;

/** ExitCommand is a subclass of Command to close the application */
public class ExitCommand extends Command {
    public ExitCommand() {}

    /** Writes to file location in storage object and prints exit statement */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.writeToFile(taskList.getTaskCommands());
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
