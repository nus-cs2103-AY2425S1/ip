package chatBot.command;

import chatBot.bot.TaskList;
import chatBot.bot.Ui;
import chatBot.bot.Storage;

public class ListCommand extends Command {
    public ListCommand() {}

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.listTasks();
    }
}
