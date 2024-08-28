import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private Deadline deadlineTask;
    public DeadlineCommand(String description, LocalDateTime by) {
        this.deadlineTask = new Deadline(description, by);
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
            taskList.add(this.deadlineTask);
            ui.addTaskMessage(taskList, deadlineTask);
            storage.saveTasks(taskList);
    }
}
