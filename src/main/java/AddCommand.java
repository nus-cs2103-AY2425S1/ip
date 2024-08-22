public class AddCommand implements Command<TaskList>{
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(task);
    }
}
