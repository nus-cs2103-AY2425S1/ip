package commands;

import models.Deadline;
import models.Task;
import ui.Ui;

import java.time.LocalDate;
import java.util.List;

public class CreateDeadlineCommand implements Command {
    private final String name;
    private final LocalDate by;

    public CreateDeadlineCommand(String name, LocalDate by) {
        this.name = name;
        this.by = by;
    }

    @Override
    public void execute(Context context) {
        List<Task> tasks = context.tasks();
        Task task = new Deadline(this.name, this.by);
        tasks.add(task);

        Ui ui = context.ui();
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(task.toString());
        ui.showMessageF("Now you have %d tasks in the list.", tasks.size());
    }
}
