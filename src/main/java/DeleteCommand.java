public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws JudeException {
        list.deleteTask(index);
        ui.showMessage("Task has been deleted.");
        storage.save(list);
    }

}
