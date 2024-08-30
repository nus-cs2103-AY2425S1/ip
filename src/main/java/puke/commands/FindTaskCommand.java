package puke.commands;

import puke.exceptions.PukeException;
import puke.tasklist.TaskManager;
import puke.tasks.Task;
import puke.ui.MessageBuilder;

import java.util.ArrayList;

/**
 * Command to find tasks by searching for a keyword in the task description.
 */
public class FindTaskCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindTaskCommand with the specified keyword for searching tasks.
     * @param keyword the keyword to use for searching tasks
     */
    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find tasks by keyword and sends the results using MessageBuilder.
     * @param taskManager the TaskManager to search tasks in
     * @param messageBuilder the MessageBuilder to use for sending the output message
     * @throws PukeException if an error occurs during the execution of the command
     */
    @Override
    public void execute(TaskManager taskManager, MessageBuilder messageBuilder) throws PukeException {
        ArrayList<Task> foundTasks = taskManager.findTasks(keyword);
        messageBuilder.sendMessage(buildFindResult(foundTasks));
    }

    /**
     * Builds a message displaying the tasks found.
     * @param tasks the ArrayList of tasks that matched the find query
     * @return a formatted string listing the tasks
     */
    private String buildFindResult(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder("These are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
        }
        return result.toString();
    }
}
