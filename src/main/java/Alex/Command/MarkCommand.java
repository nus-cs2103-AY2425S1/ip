package Alex.Command;
import Alex.Exceptions.AlexException;
import Alex.Storage.Storage;
import Alex.Task.Task;
import Alex.Task.TaskList;
import Alex.Ui.Ui;

public class MarkCommand extends CommandBase {
    private int index;
    private boolean markAsDone;

    public MarkCommand(int index, boolean markAsDone) {
        this.index = index;
        this.markAsDone = markAsDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlexException {
        try {
            Task task = tasks.getTask(index);
            if (markAsDone) {
                task.markAsDone();
                ui.printTaskStatusChange("Nice! I've marked this task as done:", index);
            } else {
                task.markAsNotDone();
                ui.printTaskStatusChange("OK, I've marked this task as not done yet:", index);
            }
            storage.save(tasks.getAllTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new AlexException("Task not found.");
        }
    }
}