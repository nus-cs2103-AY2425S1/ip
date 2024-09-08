package cloud.command;

import cloud.task.Deadline;
import cloud.util.DateTime;
import cloud.util.Storage;
import cloud.util.TaskList;
import cloud.util.Ui;

public class DeadlineCommand extends Command {
    private final String description;
    private final DateTime dueDate;

    public DeadlineCommand(String description, DateTime dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(this.description, this.dueDate);
        tasks.add(deadline);
        storage.saveData(tasks);
        return ui.showAddedTask(tasks);
    }
}
