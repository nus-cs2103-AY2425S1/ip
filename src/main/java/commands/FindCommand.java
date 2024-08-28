package commands;

import main.TaskList;
import storage.Storage;
import ui.Ui;
import tasks.Task;

public class FindCommand extends Command {
    private final String searchable;
    private final TaskList results;

    public FindCommand(String searchable) {
        this.searchable = searchable;
        this.results = new TaskList();
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        for (Task t : taskList.getTasks()) {
            if (t.getDescription().contains(searchable)) {
                results.addTask(t);
            }
        }
        ui.showTasks(this.results);
    }

}
