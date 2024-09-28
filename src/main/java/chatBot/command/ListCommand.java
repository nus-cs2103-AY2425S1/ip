package chatbot.command;

import chatbot.bot.Storage;
import chatbot.bot.TaskList;
import chatbot.bot.Ui;

/** ListCommand is a subclass of Command to list tasks */
public class ListCommand extends Command {
    public ListCommand() {}

    /** Calls listTasks method in TaskList class, which prints the tasks */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return "You have these tasks: \n" + taskList.listTasks();
    }
}
