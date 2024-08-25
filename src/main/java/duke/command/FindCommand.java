package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class FindCommand implements Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showMatchingTasks(tasks.findTasksByKeyword(keyword));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}