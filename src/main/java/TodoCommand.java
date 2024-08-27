public class TodoCommand extends Command {
    private Todo todoTask;
    public TodoCommand(Todo todoTask) {
        this.todoTask = todoTask;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Commands.addTask(taskList, todoTask);
        ui.addTaskMessage(taskList, todoTask);
        storage.saveTasks(taskList);
    }
}
