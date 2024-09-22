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
     * @param taskList the TaskList that contains all the tasks to be listed.
     * @param messageBuilder the MessageBuilder used to construct and send the list of tasks.
     * @return the message containing the list of all tasks.
     */
    @Override
    public String execute(TaskList taskList, MessageBuilder messageBuilder) {
        return messageBuilder.sendMessage(taskList.listTasks());
    }
}
