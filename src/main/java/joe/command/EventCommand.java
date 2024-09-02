package joe.command;

import java.time.LocalDate;
import java.util.Objects;

import joe.JoeException;
import joe.Storage;
import joe.Ui;
import joe.task.Event;
import joe.task.Task;
import joe.task.TaskList;

/**
 * This class represents the 'event' command.
 */
public class EventCommand extends Command {
    private final String description;
    private final LocalDate by;
    private final LocalDate to;

    /**
     * Constructs an {@code EventCommand} with the specified description, start date, and end date.
     *
     * @param description The description of the event.
     * @param by The start date of the event.
     * @param to The end date of the event.
     */
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        EventCommand that = (EventCommand) obj;
        return Objects.equals(description, that.description)
                && Objects.equals(by, that.by)
                && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, by, to);
    }
}
