package puke.commands;

import puke.TaskList;
import puke.message.MessageBuilder;

/**
 * Command to list all tasks.
 */
public class ListTasksCommand extends Command {

    /**
     * Executes the command to list all tasks and sends the list as a message.
     *
     * @param taskList the TaskList that manages the tasks
     * @param messageBuilder the MessageBuilder used to construct and send the list of tasks
     */
    @Override
    public void execute(TaskList taskList, MessageBuilder messageBuilder) {
        messageBuilder.sendMessage(taskList.listTasks());
    }
}
