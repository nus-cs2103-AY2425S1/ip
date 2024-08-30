package joe.command;

import joe.JoeException;
import joe.Storage;
import joe.Ui;
import joe.task.Event;
import joe.task.Task;
import joe.task.TaskList;

import java.time.LocalDate;

/**
 * This class represents the 'event' command.
 */
public class EventCommand extends Command {
    private final String description;
    private final LocalDate by;
    private final LocalDate to;

    public EventCommand(String description, LocalDate by, LocalDate to) {
        this.description = description;
        this.by = by;
        this.to = to;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JoeException {
        Task newTask = new Event(this.description, this.by, this.to);
        taskList.addTask(newTask);
        ui.printAddedTask(newTask, taskList.getSize());
    }
}
