package morgana.commands;

import morgana.exceptions.MorganaException;
import morgana.parser.Parser;
import morgana.storage.Storage;
import morgana.task.Task;
import morgana.task.TaskList;
import morgana.ui.Ui;

public class MarkCommand extends Command {
    private final String args;

    public MarkCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MorganaException {
        int index = Parser.parseTaskIndex(args, tasks);
        Task task = tasks.get(index);
        task.markAsDone(true);
        ui.showToUser(
                "Nice! I've marked this task as done:",
                "%d. %s".formatted(index + 1, task));
        storage.save(tasks);
    }
}
