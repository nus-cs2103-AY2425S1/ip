import java.io.IOException;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FridayException {
        try {
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new InvalidUnmarkArgument();
            }
            Task taskToUnmark = tasks.getTask(taskIndex);  // Get the task to be unmarked
            tasks.unmarkTask(taskIndex);  // Unmark the task as not completed
            ui.showTaskUnmarked(taskToUnmark);  // Show the user that the task was unmarked

            storage.save(tasks.getTasks());  // Save the updated task list to the file
        } catch (IOException e) {
            throw new FridayException("Error saving tasks to file.");
        }
    }
}