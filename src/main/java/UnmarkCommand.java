import java.io.IOException;

// UnmarkCommand.java
public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.get(index - 1);
            task.markAsNotDone();
            ui.showTaskUnmarked(task);
            storage.save(tasks);
        } catch (IndexOutOfBoundsException | IOException e) {
            ui.showSavingError();
        }
    }
}