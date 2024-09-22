package rizz.command;
import rizz.source.TaskList;
import rizz.task.Deadline;
import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime by;

    public AddDeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks) {
        if (description == null || description.trim().isEmpty() || by == null) {
            return "Description and by cannot be empty";
        }
        Deadline newDeadline = new Deadline(description, by, false);
        tasks.addTask(newDeadline);
        return "Deadline added: " + newDeadline.toString();
    }
}

