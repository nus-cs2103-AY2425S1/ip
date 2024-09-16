package chatbot.command;

import chatbot.logic.TaskList;

/**
 * Represents the List command
 */
public class ListCommand extends Command {
    /** TaskList object that represents the Task List of the current chatbot instance */
    private TaskList taskList;

    /**
     * Constructor for the ListCommand class
     *
     * @param taskList the TaskList instance of the chatbot
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Execution of the List command
     *
     * @return The string representation of the list of tasks
     */
    @Override
    public String run() {
        return taskList.listTasks();
    }
}
