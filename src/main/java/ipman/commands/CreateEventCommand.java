package ipman.commands;

import java.time.LocalDate;

import ipman.models.Event;
import ipman.models.Task;
import ipman.models.TaskList;
import ipman.ui.Ui;

public class CreateEventCommand implements Command {
    private final String name;
    private final LocalDate from;
    private final LocalDate to;

    public CreateEventCommand(String name, LocalDate from, LocalDate to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(Context context) {
        TaskList tasks = context.tasks();
        Task task = new Event(this.name, this.from, this.to);
        tasks.add(task);

        Ui ui = context.ui();
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(task.toString());
        ui.showMessageF("Now you have %d tasks in the list.", tasks.size());
    }
}
