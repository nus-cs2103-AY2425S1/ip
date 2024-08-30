package puke.commands;

import puke.tasklist.TaskManager;
import puke.ui.MessageBuilder;

/**
 * Command to list all tasks.
 */
public class ListTasksCommand extends Command {

    /**
     * Executes the command to list all tasks and sends the list as a message.
     *
     * @param taskManager the TaskManager that manages the tasks
     * @param messageBuilder the MessageBuilder used to construct and send the list of tasks
     */
    @Override
    public void execute(TaskManager taskManager, MessageBuilder messageBuilder) {
        messageBuilder.sendMessage(taskManager.listTasks());
    }
}
