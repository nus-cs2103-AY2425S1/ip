package bimo.command;

import bimo.Storage;
import bimo.TaskList;
import bimo.Ui;

/**
 * Creates a command that list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Lists all task created.
     *
     * @param tasks List of user tasks.
     * @param ui User interface that interacts with users.
     * @param storage Storage that writes and load files.
     * @return Response of chatbot.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String response = "Here are the tasks in your tasks:";
        assert tasks != null : "Task list must not be null";
        for (int i = 0; i < tasks.getLength(); i++) {
            String message = String.format("\n    %d. %s", i + 1, tasks.getTask(i).toString());
            response += message;
        }
        return response;
    }
}
