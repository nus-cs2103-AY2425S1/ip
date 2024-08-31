import java.io.IOException;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EdithException {
        if (index < 0 || index >= tasks.getNumOfTasks()) {
            throw new EdithException("Task " + index + " does not exist. Please enter a valid task number.");
        }

        Task taskToMark = tasks.getTask(index);
        taskToMark.markTaskDone();

        ui.showIndentedMessage("Alright, great job! I've marked task " + (index + 1) + " as done:");
        ui.showIndentedMessage(taskToMark.toString());
        ui.showLineBreak();

        try {
            storage.save(tasks.getListOfTasks());
        } catch (IOException e) {
            ui.showErrorMessage("An error occurred while saving updated task list.");
        }
    }
}
