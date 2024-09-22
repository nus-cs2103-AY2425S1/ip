package rizz.command;
import rizz.source.TaskList;
import rizz.task.Event;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class AddEventCommand extends SaveableCommand {
    private final String description;
    private final LocalDateTime eventStartTime;
    private final LocalTime eventEndTime;

    public AddEventCommand(String description, LocalDateTime eventStartTime, LocalTime eventEndTime) {
        this.description = description;
        this.eventStartTime= eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    @Override
    public String execute(TaskList tasks) {
        if (this.description == null || this.description.trim().isEmpty() || this.eventStartTime == null
                || this.eventEndTime == null) {
            return "Event, from and by cannot be empty";
        }
        Event newEvent = new Event(description, eventStartTime, eventEndTime, false);
        tasks.addTask(newEvent);
        return "Event added: " + newEvent.toString();
    }
}
