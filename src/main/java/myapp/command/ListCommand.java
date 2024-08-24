package myapp.command;

import myapp.core.BingBongException;
import myapp.core.BingBongUi;
import myapp.core.Storage;
import myapp.task.TaskList;

public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, BingBongUi ui, Storage storage)
            throws BingBongException {
        if (tasks.isEmpty()) {
            throw new BingBongException("No tasks have been saved.");
        } else {
            StringBuilder list = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                list.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
            ui.showResponse(list.toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
