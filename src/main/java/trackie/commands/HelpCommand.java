package trackie.commands;

import trackie.storage.Storage;
import trackie.storage.TaskList;

public class HelpCommand extends Command {
    private String helpText = "List of available commands:\n" +
            "t - add todo task\n" +
            "d - add delete task\n" +
            "e - add event task\n" +
            "mark - mark task as completed\n" +
            "unmark - mark task as NOT completed\n" +
            "rm - delete task" +
            "ls - list tasks" +
            "help - display available commands";

    @Override
    public String execute(TaskList taskList, Storage storage) {
        return helpText;
    }
}
