package LBot.command;

import LBot.exception.ExecuteCommandException;
import LBot.exception.FileException;
import LBot.helper.Storage;
import LBot.helper.TaskList;
import LBot.helper.Ui;
import LBot.task.Event;
import LBot.task.Task;

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
        Task event = new Event(description, startDateTime, endDateTime);
        tasks.addTask(event);
        storage.saveTaskToFile(tasks);
        ui.printTaskAddedMessage(event);
    }

    @Override
    public String toString() {
        return "event command " + description + " " + startDateTime + " " + endDateTime;
    }

}
