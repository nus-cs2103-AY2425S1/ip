package ipman.commands;

import java.time.LocalDate;

import ipman.models.Deadline;
import ipman.models.Task;
import ipman.models.TaskList;
import ipman.ui.Ui;

/**
 * Creates a <code>Deadline</code> inside <code>Context</code>'s
 * <code>TaskList</code>
 *
 * @see Deadline
 * @see Context
 * @see TaskList
 */
public class CreateDeadlineCommand implements Command {
    private final String name;
    private final LocalDate by;

    /**
     * Creates command that creates a particular deadline
     *
     * @param name deadline name
     * @param by deadline completion date
     * @see Deadline
     */
    public CreateDeadlineCommand(String name, LocalDate by) {
        this.name = name;
        this.by = by;
    }

    @Override
    public void execute(Context context) {
        TaskList tasks = context.tasks();
        Task task = new Deadline(this.name, this.by);
        tasks.add(task);

        Ui ui = context.ui();
        ui.showMessage(String.format("""
            Got it. I've added this task:
            %s
            Now you have %d tasks in the list.
        """, task, tasks.size()));
    }
}
