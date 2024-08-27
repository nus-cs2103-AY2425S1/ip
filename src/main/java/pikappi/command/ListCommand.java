package pikappi.command;

import pikappi.Storage;
import pikappi.TaskList;
import pikappi.Ui;

public class ListCommand extends Command {
    public ListCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks();
    }
}
