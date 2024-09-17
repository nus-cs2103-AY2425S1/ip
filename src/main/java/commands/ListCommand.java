package commands;

import storage.task.TaskStorage;
import storage.temp.TempStorage;
import task.TaskList;

/**
 * Represents a list command entered by the user.
 */
public class ListCommand extends Command {

    /**
     * Stores the command string associated with list command.
     *
     * @param command Command string.
     */
    public ListCommand(String command) {
        super(command);
    }

    private String getResponse(TaskList tasks) {
        String tasksString = tasks.listTasks();
        int totalTasks = tasks.numTasks();

        String responseBody;
        if (totalTasks == 0) {
            responseBody = "No current tasks!";
        } else {
            responseBody = tasksString;
        }

        return (totalTasks == 1
                ? "Here is the task in your list:\n"
                : "Here are the tasks in your list:\n")
                + responseBody;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Chatbot reads from task list, returns all the existing tasks.
     * </p>
     */
    @Override
    public String execute(TaskStorage taskStorage, TempStorage tempStorage, TaskList tasks) {
        tempStorage.setPreviousCommand("list");

        return this.getResponse(tasks);
    }
}
