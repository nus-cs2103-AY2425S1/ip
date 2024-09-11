package lexi.command;

import java.util.ArrayList;

import lexi.exception.LexiException;
import lexi.storage.Storage;
import lexi.task.Task;
import lexi.task.TaskList;
import lexi.ui.Ui;

/**
 * Represents a command to find a task from the task list.
 * When executed, this command finds a task based on user input in the list.
 */
public class FindCommand extends Command {
    private final String query;
    private final ArrayList<Task> results = new ArrayList<>();
    private String response;

    /**
     * Constructs a FindCommand with the specified search query.
     *
     * @param query The search term used to find matching tasks in the task list.
     *              It must not be null or empty.
     */
    public FindCommand(String query) {
        // Precondition assertion to ensure query is not null or empty
        assert query != null : "Query cannot be null";
        assert !query.trim().isEmpty() : "Query cannot be empty";
        this.query = query;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LexiException {
        // Assertions to ensure that tasks and ui are not null
        assert tasks != null : "Task list cannot be null";
        assert ui != null : "UI object cannot be null";

        ArrayList<Task> taskList = tasks.getTasks();

        // Assertion to ensure taskList is not null (even if empty)
        assert taskList != null : "Task list array should not be null";

        for (Task task : taskList) {
            // Assertion to ensure task is not null before using it
            assert task != null : "Task in the list cannot be null";

            if (task.containsExactWord(query)) {
                results.add(task);
            }
        }

        if (results.isEmpty()) {
            throw new LexiException("Sorry that task does not exist!");
        }

        // Assertion to ensure results is not empty before showing the response
        assert !results.isEmpty() : "Results should not be empty at this point";

        response = ui.showListOfMatchingTasks(results);

        // Assertion to ensure the response is properly set
        assert response != null && !response.isEmpty() : "Response should be a non-null, non-empty string";
    }

    @Override
    public String getCommandName() {
        return "FIND";
    }

    @Override
    public String getString() {
        return response;
    }
}
