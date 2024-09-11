package rizz.commands;
import rizz.source.TaskList;
import rizz.source.Ui;
import rizz.source.Storage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import rizz.tasks.Event;



public class AddEventCommand extends Command {
    private final String description;
    private final LocalDateTime from;
    private final LocalTime to;

    public AddEventCommand(String description, LocalDateTime from, LocalTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addTask(new Event(description, from, to, false));
            storage.saveTasks(tasks); 
        } catch (IOException e) {
            ui.showError("Unexpected error: " + e.getMessage());
        }
    }
}
