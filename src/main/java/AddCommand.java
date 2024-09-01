public class AddCommand extends Command {

    // AddCommand will have a task to add
    private Task toAdd;

    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ConverSageException {
        tasks.add(toAdd);
        ui.showLine();
        ui.showMessage("Understood, I've added this task: ");
        ui.showMessage("  " + toAdd.toString());
        ui.showMessage("You have " + tasks.size() + " tasks in your list.");
        ui.showLine();
        storage.save(tasks.getTasks());
    }

}
