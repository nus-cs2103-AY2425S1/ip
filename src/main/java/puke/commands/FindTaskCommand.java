package puke.commands;

import java.util.ArrayList;

import puke.TaskList;
import puke.exceptions.PukeException;
import puke.message.MessageBuilder;
import puke.task.Task;

/**
 * Command to find tasks by searching for a keyword in the task description.
 */
public class FindTaskCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindTaskCommand with the specified keyword for searching tasks.
     *
     * @param keyword the keyword to use for searching tasks.
     */
    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find tasks that match the keyword and sends the results using MessageBuilder.
     *
     * @param taskList the TaskList to search for tasks.
     * @param messageBuilder the MessageBuilder used to construct and send the output message.
     * @return the formatted message listing the tasks that match the search query.
     * @throws PukeException if an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList taskList, MessageBuilder messageBuilder) throws PukeException {
        ArrayList<Task> foundTasks = taskList.findTasks(keyword);
        return messageBuilder.sendMessage(buildFindResult(foundTasks));
    }

    /**
     * Builds a message displaying the tasks that matched the search query.
     *
     * @param tasks the ArrayList of tasks that matched the search query.
     * @return a formatted string listing the matching tasks.
     */
    private String buildFindResult(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder("These are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return result.toString();
    }
}
