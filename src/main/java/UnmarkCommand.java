public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws JudeException {
        list.unmarkTask(index);
        ui.showMessage("Task " + list.getTask(index) + " been unmarked.");
        storage.save(list);
    }
}
