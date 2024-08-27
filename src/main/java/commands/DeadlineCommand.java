package commands;

import exceptions.DownyException;
import storage.Storage;
import tasks.Deadline;
import tasks.TaskList;
import ui.Ui;

import java.time.LocalDateTime;

public class DeadlineCommand implements Command {

    private final String taskDescription;

    private final LocalDateTime time;

    public DeadlineCommand(String taskDescription, LocalDateTime time) {
        this.taskDescription = taskDescription;
        this.time = time;

    }

    public void execute(Storage storage, TaskList tasks, Ui ui) throws DownyException {
        Deadline d = tasks.addDeadline(this.taskDescription, this.time);
        storage.writeDeadlineToFile(d);
        ui.displayTaskAdded(d, tasks.getSize());
    }
    public boolean isExit() {
        return false;
    }
}
