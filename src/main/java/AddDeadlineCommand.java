import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected String description;
    protected LocalDateTime deadline;

    public AddDeadlineCommand(String description, String deadline) throws DateTimeParseException {
        this.description = description;
        this.deadline = LocalDateTime.parse(deadline, INPUT_FORMATTER);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task newTask = new Deadline(description, deadline);
        tasks.add(newTask);
        storage.save(tasks.toArrayList());
        ui.showTaskAdded(newTask, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
