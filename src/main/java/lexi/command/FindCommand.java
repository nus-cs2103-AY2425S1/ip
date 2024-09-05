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

    public FindCommand(String query) {
        this.query = query;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LexiException {
        ArrayList<Task> taskList = tasks.getTasks();
        for (Task task : taskList) {
            if (task.containsExactWord(query)) {
                results.add(task);
            }
        }
        if (results.isEmpty()) {
            throw new LexiException("Sorry that task does not exist!");
        }
        response = ui.showListOfMatchingTasks(results);
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
