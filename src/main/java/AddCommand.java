public class AddCommand extends Command {
    private Task task;
    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskCreation(this.task);
        tasks.add(this.task);
        ui.showCount(tasks);
    }

    public boolean isExit() {
        return false;
    }

}
