package chatbot.command;

import chatbot.logic.TaskList;

/**
 * Represents the "deadline" command that searches for a task based on a query
 */
public class FindCommand extends Command {
    /** TaskList object that represents the Task List of the current chatbot instance */
    private TaskList taskList;
    /** query task name to be searched */
    private String query;

    /**
     * Constructs the FindCommand object
     *
     * @param taskList The TaskList instance of the chatbot
     * @param query The query task name to be searched
     */
    public FindCommand(TaskList taskList, String query) {
        this.taskList = taskList;
        this.query = query;
    }

    /**
     * Executes the Find command, searches for a task with
     * the query as a substring of its title
     *
     * @return The String representation of the result of the query
     */
    @Override
    public String run() {
        return taskList.find(query);
    }
}
