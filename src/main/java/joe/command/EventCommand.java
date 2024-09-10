package joe.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructs an {@code EventCommand} with the specified description, start date, and end date.
     *
     * @param description The description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public EventCommand(String description, String from, String to) {
        if (description.isEmpty() || from == null) {
            throw new JoeException("Oops! Try: event {desc} /from {start} /to {end}");
        }
        this.description = description;
        try {
            this.from = LocalDate.parse(from);
            this.to = to == null ? LocalDate.MAX : LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new JoeException("Please enter a date with the format yyyy-mm-dd");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JoeException {
        Task newTask = new Event(this.description, this.from, this.to);
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
                && Objects.equals(from, that.from)
                && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, from, to);
    }

    public static String getHelp() {
        return "To add a new event, try: event {desc} /from {start} /to {end}";
    }
}
