public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws JudeException {
        list.addTask(task);
        ui.showMessage("Task " + task + " has been added.");
        storage.save(list);
    }
}
