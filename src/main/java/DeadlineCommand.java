import java.time.LocalDateTime;

public class DeadlineCommand implements Command {
    String name;
    String deadLineString;
    LocalDateTime deadline;
    public DeadlineCommand(String name, LocalDateTime deadline) {
        this.name = name;
        this.deadline = deadline;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline task = new Deadline(name, taskList.size(), deadline);
        taskList.add(task);
        ui.showTaskAdded(task, taskList.size());
        storage.saveToFile(taskList.toArr());

    }
}
