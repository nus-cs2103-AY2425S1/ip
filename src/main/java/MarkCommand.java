import java.io.IOException;

public class MarkCommand extends Command implements Savable {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute() {
        taskList.markTask(index);
        return "Nice! I've marked this task as done:\n  " + taskList.getTask(index);
    }

    @Override
    public void save(Storage storage) throws IOException {
        storage.saveTasks(taskList.getTasks());
    }
}
