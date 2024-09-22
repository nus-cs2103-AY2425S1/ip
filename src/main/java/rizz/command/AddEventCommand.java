package rizz.command;
import rizz.source.TaskList;
import rizz.task.Event;
import java.time.LocalDateTime;
import java.time.LocalTime;


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
    public String execute(TaskList tasks) {
        if (this.description == null || this.description.trim().isEmpty() || this.from == null || this.to == null) {
            return "Event, from and by cannot be empty";
        }
        Event newEvent = new Event(description, from, to, false);
        tasks.addTask(newEvent);
        return "Event added: " + newEvent.toString();
    }
}
