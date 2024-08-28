import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    public DeadlineCommand(String input) throws GuttiException {
        String[] parts = input.split("/by");
        if (parts.length != 2) {
            throw new GuttiException("Invalid format. Use: deadline <task description> /by <date/time>");
        }
        this.description = parts[0].trim();
        this.by = parts[1].trim();
        try {
            DateTimeUtil.parseDateTime(by);
        } catch (DateTimeParseException e) {
            throw new GuttiException("Invalid date/time format. Accepted formats: yyyy-MM-dd HHmm, d/MM/yyyy HHmm, MMM dd yyyy h:mma");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GuttiException {
        Task deadlineTask = new Deadline(description, by, false);
        tasks.addTask(deadlineTask);
        storage.saveTasksToFile(tasks.getTasks());
        ui.showTaskList(tasks);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}