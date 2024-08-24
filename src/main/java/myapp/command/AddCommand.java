package myapp.command;

import myapp.core.BingBongUi;
import myapp.task.TaskList;
import myapp.task.Task;

public abstract class AddCommand extends Command {
    String description;
    public AddCommand(String s) {
        super();
        this.description = s;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    public void printAddMessage(BingBongUi ui, TaskList tasks, Task t) {
        ui.showResponse("Got it. I've added this task:\n" + t
                + "\n" + "Now you have " + tasks.size()
                + " tasks in the list");
    }
}
