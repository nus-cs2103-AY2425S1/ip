import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public EventCommand(String input) throws GuttiException {
        String[] parts = input.split("/from");
        if (parts.length != 2) {
            throw new GuttiException("Invalid format. Use: event <task description> /from <start date/time> /to <end date/time>");
        }
        String[] fromToParts = parts[1].split("/to");
        if (fromToParts.length != 2) {
            throw new GuttiException("Invalid format. Use: event <task description> /from <start date/time> /to <end date/time>");
        }
        this.description = parts[0].trim();
        this.from = fromToParts[0].trim();
        this.to = fromToParts[1].trim();
        try {
            DateTimeUtil.parseDateTime(from);
            DateTimeUtil.parseDateTime(to);
        } catch (DateTimeParseException e) {
            throw new GuttiException("Invalid date/time format. Accepted formats: yyyy-MM-dd HHmm, d/MM/yyyy HHmm, MMM dd yyyy h:mma");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GuttiException {
        Task eventTask = new Event(description, from, to, false);
        tasks.addTask(eventTask);
        storage.saveTasksToFile(tasks.getTasks());
        ui.showTaskList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}