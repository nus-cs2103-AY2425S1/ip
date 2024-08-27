package gray.command;

import gray.Ui;
import gray.task.DeadlineTask;
import gray.TaskList;

import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {

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
     * @param ui
     * @param tasks
     */
    @Override
    public void execute(Ui ui, TaskList tasks) {
        DeadlineTask task = new DeadlineTask(description, deadline);
        tasks.add(task);
        ui.say(String.format("""
                Got it. I've added this task:
                    %s
                Now you have %d tasks in the list.""",
                task, tasks.size()));
    }
}
