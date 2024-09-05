import java.time.LocalDateTime;

public class EventCommand extends Command {
    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;
    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }
    @Override
    public void execute(TaskList tasks, Storage storage) {
        Event e = tasks.addEvent(this.description, this.from, this.to);
        storage.appendToFile(e.toFile());
        Ui.printEvent(tasks);
    }
}
