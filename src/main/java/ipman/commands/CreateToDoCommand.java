package ipman.commands;

import ipman.models.Task;
import ipman.models.TaskList;
import ipman.models.ToDo;
import ipman.ui.Ui;

public class CreateToDoCommand implements Command {
    private final String name;

    public CreateToDoCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(Context context) {
        TaskList tasks = context.tasks();
        Task task = new ToDo(name);
        tasks.add(task);

        Ui ui = context.ui();
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(task.toString());
        ui.showMessageF("Now you have %d tasks in the list.", tasks.size());
    }
}
