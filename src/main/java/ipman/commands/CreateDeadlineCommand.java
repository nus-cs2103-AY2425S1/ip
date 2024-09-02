package ipman.commands;

import ipman.models.Deadline;
import ipman.models.Task;
import ipman.models.TaskList;
import ipman.ui.Ui;

import java.time.LocalDate;

public class CreateDeadlineCommand implements Command {
    private final String name;
    private final LocalDate by;

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
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(task.toString());
        ui.showMessageF("Now you have %d tasks in the list.", tasks.size());
    }
}
