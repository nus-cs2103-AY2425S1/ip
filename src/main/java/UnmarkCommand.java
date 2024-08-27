import java.io.IOException;

public class UnmarkCommand extends Command implements Savable {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute() {
        taskList.unmarkTask(index);
        return "OK, I've marked this task as not done yet:\n  " + taskList.getTask(index);
    }

    @Override
    public void save(Storage storage) throws IOException {
        storage.saveTasks(taskList.getTasks());
    }
}
