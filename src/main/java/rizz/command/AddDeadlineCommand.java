package rizz.command;
import rizz.source.TaskList;
import rizz.task.Deadline;
import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime deadlineTimeBy;

    public AddDeadlineCommand(String description, LocalDateTime deadlineTimeBy) {
        this.description = description;
        this.deadlineTimeBy = deadlineTimeBy;
    }

    @Override
    public String execute(TaskList tasks) {
        if (description == null || description.trim().isEmpty() || deadlineTimeBy == null) {
            return "Description and by cannot be empty";
        }
        Deadline newDeadline = new Deadline(description, deadlineTimeBy, false);
        tasks.addTask(newDeadline);
        return "Deadline added: " + newDeadline.toString();
    }
}

