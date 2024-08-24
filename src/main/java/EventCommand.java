import java.util.ArrayList;

public class EventCommand extends Command {
    private final Event event;

    public EventCommand(Event event) {
        this.event = event;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        ArrayList<Task> added = tasks.addTask(event);
        storage.saveTasks(added);
    }
}
