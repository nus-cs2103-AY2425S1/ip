import java.io.IOException;

public class EventCommand extends Command implements Savable {
    private final Event event;

    public EventCommand(String description, String from, String to) {
        this.event = new Event(description, from, to);
    }

    @Override
    public String execute() {
        taskList.addTask(event);
        return "Got it. I've added this task:\n  " + event +
                "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    @Override
    public void save(Storage storage) throws IOException {
        storage.saveTask(event);
    }
}
