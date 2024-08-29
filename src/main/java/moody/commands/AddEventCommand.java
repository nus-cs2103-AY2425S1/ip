package moody.commands;

import moody.storage.Storage;
import moody.tasks.Event;
import moody.tasks.Task;
import moody.tasks.TaskList;
import moody.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected String description;
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    public AddEventCommand(String description, String startTime, String endTime) throws DateTimeParseException {
        this.description = description;
        this.startTime = LocalDateTime.parse(startTime, INPUT_FORMATTER);
        this.endTime = LocalDateTime.parse(endTime, INPUT_FORMATTER);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task newTask = new Event(description, startTime, endTime);
        tasks.add(newTask);
        storage.save(tasks.toArrayList());
        ui.showTaskAdded(newTask, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
