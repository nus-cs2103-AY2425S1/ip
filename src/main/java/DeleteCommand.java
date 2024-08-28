public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws JudeException {
        list.deleteTask(index);
        ui.showMessage("Task " + list.getTask(index) + " has been deleted.");
        storage.save(list);
    }

}
