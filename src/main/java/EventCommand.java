import java.time.LocalDateTime;

public class EventCommand extends AddCommand {
    LocalDateTime from;
    LocalDateTime to;

    public EventCommand(String s, LocalDateTime from, LocalDateTime to) {
        super(s);
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, BingBongUI ui, Storage storage) {
        Task task = new Event(description, from, to);
        tasks.add(task);
        saveTasks(tasks, ui, storage);
        printAddMessage(ui, tasks, task);
    }
}
