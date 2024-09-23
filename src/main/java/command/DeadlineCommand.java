package command;

import main.Ui;
import task.Deadline;
import main.Storage;
import main.TaskList;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime deadline;
    public DeadlineCommand(String description, LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Deadline d = tasks.addDeadline(this.description, this.deadline);
        new Deadline(this.description, this.deadline);
        storage.appendToFile(d.toFile());
        return Ui.showAddDeadline(tasks);
    }
}
