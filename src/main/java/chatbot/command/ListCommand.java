package chatbot.command;

import chatbot.logic.TaskList;

/**
 * Represents the List command
 */
public class ListCommand extends Command {
    /** TaskList object that represents the Task List of the current chatbot instance */
    private TaskList taskList;

    /**
     * Constructs the ListCommand object
     *
     * @param taskList the TaskList instance of the chatbot
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the List command
     *
     * @return The string representation of the list of tasks
     */
    @Override
    public String run() {
        return taskList.listTasks();
    }
}
