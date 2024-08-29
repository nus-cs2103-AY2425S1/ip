package pixel.command;

import pixel.Storage;
import pixel.PixelException;
import pixel.Ui;
import pixel.task.Deadline;
import pixel.task.Event;
import pixel.task.Task;
import pixel.task.TaskList;
import pixel.task.Todo;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Deadline deadline) {
        super(false);
        this.task = deadline;
    }

    public AddCommand(Todo todo) {
        super(false);
        this.task = todo;
    }

    public AddCommand(Event event) {
        super(false);
        this.task = event;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PixelException {
        taskList.addTask(this.task);
        ui.PixelSays("Got it. I've added this task:", "  " + this.task,
                "Now you have " + taskList.size() + " tasks in the list.");
    }
}
