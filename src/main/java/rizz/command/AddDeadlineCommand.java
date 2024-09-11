package rizz.command;
import rizz.task.Deadline;
import rizz.source.TaskList;
import rizz.source.Ui;
import rizz.source.Storage;
import java.io.IOException;
import java.time.LocalDateTime;


public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;

    public AddDeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (description == null || description.trim().isEmpty() || by == null) {
            ui.invalidDeadlineCommand();
        }
        try {
            Deadline newDeadline = new Deadline(description, by, false);
            tasks.addTask(newDeadline);
            ui.addDeadline(newDeadline);
            storage.saveTasks(tasks);
        } catch (IOException e) {
            ui.showError("Unexpected error: " + e.getMessage());
        }
    }
}

