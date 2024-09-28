package chatbot.command;

import chatbot.bot.Storage;
import chatbot.bot.TaskList;
import chatbot.bot.Ui;

/** ExitCommand is a subclass of Command to close the application */
public class ExitCommand extends Command {
    public ExitCommand() {}

    /** Writes to file location in storage object and prints exit statement */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        storage.writeToFile(taskList.getTaskCommands());
        // ui.showGoodbye();
        return "Bye B****";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
