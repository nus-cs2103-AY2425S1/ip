package tudee.command;

import tudee.task.TaskList;
import tudee.task.Task;
import tudee.ui.Ui;
import tudee.storage.Storage;
import tudee.TudeeException;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TudeeException {
        boolean haveKeyword = false;
        for (Task task: tasks.get()) {
            if (task.getDescription().contains(keyword)) {
                ui.showTask(task);
                haveKeyword = true;
            }
        }
        if (!haveKeyword) {
            throw new TudeeException("There is no such task with the keyword specified");
        }
    }
}
