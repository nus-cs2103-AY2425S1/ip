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

    /**
     * Gets the chatbot response to list command.
     *
     * @param tasks List of current {@code Task} objects.
     * @return Chatbot response.
     */
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
                + responseBody
                + this.getQuirkyResponse();
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
        return this.getResponse(tasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommandType() {
        return "list";
    }
}
