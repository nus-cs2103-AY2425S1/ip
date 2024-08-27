import java.io.IOException;

public class DeadlineCommand extends Command implements Savable {
    private final Deadline deadline;

    public DeadlineCommand(String description, String by) {
        this.deadline = new Deadline(description, by);
    }

    @Override
    public String execute() {
        taskList.addTask(deadline);
        return "Got it. I've added this task:\n  " + deadline +
                "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    @Override
    public void save(Storage storage) throws IOException {
        storage.saveTask(deadline);
    }
}
