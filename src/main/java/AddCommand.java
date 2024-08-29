public class AddCommand extends Command {

    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        ui.add(String.valueOf(task), taskList.getSize());
    }

}
