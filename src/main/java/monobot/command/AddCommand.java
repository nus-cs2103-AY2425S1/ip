package monobot.command;

import monobot.util.Storage;
import monobot.task.Task;
import monobot.util.TaskList;
import monobot.util.Ui;
import monobot.exception.MonoBotException;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(CommandType type, Task task) {
        super(type);
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonoBotException {
        tasks.addTask(task);
        ui.printAddedTask(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
