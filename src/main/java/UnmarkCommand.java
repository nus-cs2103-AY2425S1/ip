import java.io.IOException;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EdithException {
        if (index < 0 || index >= tasks.getNumOfTasks()) {
            throw new EdithException("Task " + index + " does not exist. Please enter a valid task number.");
        }

        Task taskToUnmark = tasks.getTask(index);
        taskToUnmark.markTaskUndone();

        ui.showIndentedMessage("Sure, I've marked task " + (index + 1) + " as not done yet:");
        ui.showIndentedMessage(taskToUnmark.toString());
        ui.showLineBreak();

        try {
            storage.save(tasks.getListOfTasks());
        } catch (IOException e) {
            ui.showErrorMessage("An error occurred while saving updated task list.");
        }
    }
}
