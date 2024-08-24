public class EventCommand extends Command {
    private final Event event;

    public EventCommand(Event event) {
        this.event = event;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.addTask(event);
        storage.saveTasks(tasks.getTasks());
    }
}
