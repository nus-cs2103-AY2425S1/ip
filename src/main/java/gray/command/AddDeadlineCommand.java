package gray.command;

import java.time.LocalDateTime;

import gray.Tasks;
import gray.task.DeadlineTask;

/**
 * A command that adds a deadline task.
 */
public class AddDeadlineCommand implements Command {
    private final String description;
    private final LocalDateTime deadline;

    /**
     * Constructs a command to add deadline task.
     *
     * @param description
     * @param deadline
     */
    public AddDeadlineCommand(String description, LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Executes the add deadline task.
     *
     * @param tasks
     * @return
     */
    @Override
    public String execute(Tasks tasks) {
        DeadlineTask task = new DeadlineTask(description, deadline);
        tasks.add(task);
        return String.format("""
                Got it. I've added this task:
                    %s
                Now you have %d tasks in the list.""",
                task, tasks.size());
    }
}
