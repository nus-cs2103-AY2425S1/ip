public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws JudeException {
        list.markTask(index);
        ui.showMessage("Task " + list.getTask(index) + " has been marked.");
        storage.save(list);
    }
}
