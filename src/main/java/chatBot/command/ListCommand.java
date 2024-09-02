package chatBot.command;

import chatBot.bot.TaskList;
import chatBot.bot.Ui;
import chatBot.bot.Storage;

/** ListCommand is a subclass of Command to list tasks */
public class ListCommand extends Command {
    public ListCommand() {}

    /** Calls listTasks method in TaskList class, which prints the tasks */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.listTasks();
    }
}
