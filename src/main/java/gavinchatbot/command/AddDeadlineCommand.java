package gavinchatbot.command;

import java.io.IOException;
import java.time.LocalDate;
import gavinchatbot.task.Deadline;
import gavinchatbot.task.Task;
import gavinchatbot.task.TaskList;
import gavinchatbot.util.Ui;
import gavinchatbot.util.Storage;
import gavinchatbot.util.GavinException;

public class AddDeadlineCommand implements Command {
    private final String description;
    private final LocalDate by;

    public AddDeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GavinException, IOException {
        Task task = new Deadline(description, by.toString());
        tasks.addTask(task);
        ui.showAddedTask(task, tasks.size());
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
