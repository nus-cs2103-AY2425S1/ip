package command;

import exception.ExecuteCommandException;
import exception.FileException;
import helper.Storage;
import helper.TaskList;
import helper.Ui;
import task.Event;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public EventCommand(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException, FileException {
        tasks.addTask(new Event(description, startDateTime, endDateTime));
        storage.saveTaskToFile(tasks);
        // TODO: Add smt??
    }

    @Override
    public String toString() {
        return "event command " + description + " " + startDateTime + " " + endDateTime;
    }

}
