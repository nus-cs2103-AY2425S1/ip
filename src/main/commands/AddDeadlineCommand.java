package main.commands;
import java.io.IOException;
import java.time.LocalDateTime;
import main.source.*;
import main.tasks.*;

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

