package lexi.command;

import lexi.exception.LexiException;
import lexi.storage.Storage;
import lexi.task.Task;
import lexi.task.TaskList;
import lexi.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String query;
    private final ArrayList<Task> results = new ArrayList<>();

    public FindCommand(String query) {
        this.query = query;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LexiException {
        ArrayList<Task> taskList = tasks.getTasks();
        for (Task task : taskList) {
            if (task.containsQuery(query)) {
                results.add(task);
            }
        }
        if (results.isEmpty()) {
            throw new LexiException("Sorry that task does not exist!");
        }
        ui.showListOfMatchingTasks(results);
    }

    @Override
    public String getCommandName() {
        return "FIND";
    }
}
