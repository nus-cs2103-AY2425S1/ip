package lexi.command;

import java.util.ArrayList;

import lexi.exception.LexiException;
import lexi.storage.Storage;
import lexi.task.Task;
import lexi.task.TaskList;
import lexi.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 * When executed, this command displays all tasks to the user.
 */
public class ListCommand extends Command {

    private String response;

    /**
     * Executes the list command, displaying all tasks in the task list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The UI object to interact with the user.
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LexiException {
        // Assertions to ensure preconditions
        assert tasks != null : "Task list cannot be null";
        assert ui != null : "UI object cannot be null";

        ArrayList<Task> taskList = tasks.getTasks();

        // Assertion to ensure taskList is not null (even if empty)
        assert taskList != null : "Task list array should not be null";

        if (taskList.isEmpty()) {
            throw new LexiException("You have no tasks in your list!");
        }

        // Perform the task listing and store the response
        response = ui.showListOfTasks(taskList);

        // Assertion to ensure the response is properly set
        assert response != null && !response.isEmpty() : "Response should be a non-null, non-empty string";
    }

    /**
     * Returns the name of the command.
     *
     * @return The string "LIST".
     */
    @Override
    public String getCommandName() {
        return "LIST";
    }

    @Override
    public String getString() {
        return response;
    }
}
