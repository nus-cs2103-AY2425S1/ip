
public class AddDeadlineCommand extends Command {
    private String description;
    private String deadline;

    public AddDeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MajimaException {
        Task newTask = new Deadline(description, deadline);
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask);
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}