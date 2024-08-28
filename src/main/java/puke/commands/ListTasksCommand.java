package puke.commands;

import puke.tasklist.TaskManager;
import puke.ui.MessageBuilder;

public class ListTasksCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, MessageBuilder messageBuilder) {
        messageBuilder.sendMessage(taskManager.listTasks());
    }
}
