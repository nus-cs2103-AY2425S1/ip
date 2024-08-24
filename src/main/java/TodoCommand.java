public class TodoCommand extends Command {
    private final Todo todo;

    public TodoCommand(Todo todo) {
        this.todo = todo;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.addTask(todo);
        storage.saveTasks(tasks.getTasks());
    }
}
