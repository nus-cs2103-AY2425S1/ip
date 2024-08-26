package Dook.Commands;

import Dook.DookException;
import Dook.Storage.Storage;
import Dook.Tasks.Task;
import Dook.Tasks.TaskList;
import Dook.Ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class FindCommand extends Command {

    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DookException, IOException {
        if (this.keyword.isEmpty()) {
            throw new DookException("Provide a keyword");
        }

        TaskList matches = new TaskList(new ArrayList<Task>());

        for (int i = 0; i < taskList.numOfTasks(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().contains(this.keyword)) {
                matches.add(task);
            }
        }
        ui.separate();
        if (matches.isEmpty()) {
            throw new DookException("No tasks have the following keyword: \"" + this.keyword + "\"");
        } else {
            ui.showMessage("Here are the matching tasks in your list: ");
            for (int i = 0; i < matches.numOfTasks(); i++) {
                ui.showMessage((i + 1) + ". " + matches.getTask(i));
            }
        }
        ui.separate();
    }
}
