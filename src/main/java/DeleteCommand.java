import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ScheduloException, IOException {
        tasks.delete(index - 1);
        storage.save(tasks);
    }
}