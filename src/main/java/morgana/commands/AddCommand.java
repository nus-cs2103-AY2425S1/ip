package morgana.commands;

import morgana.exceptions.MorganaException;
import morgana.storage.Storage;
import morgana.task.Task;
import morgana.task.TaskList;
import morgana.ui.Ui;

public abstract class AddCommand extends Command {
    private final String args;

    public AddCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MorganaException {
        Task task = createTask(args);
        tasks.add(task);
        ui.showToUser(
                "Got it. I've added this task:",
                task.toString(),
                "Now you have %d task%s in the list.".formatted(tasks.size(),
                        tasks.size() > 1 ? "s" : ""));
        storage.save(tasks);
    }

    abstract Task createTask(String args) throws MorganaException;
}
