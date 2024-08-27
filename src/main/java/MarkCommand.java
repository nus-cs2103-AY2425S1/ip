import java.io.IOException;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.get(index - 1);
            task.markAsDone();
            ui.showTaskMarked(task);
            storage.save(tasks);
        } catch (IndexOutOfBoundsException | IOException e) {
            ui.showSavingError();
        }
    }
}